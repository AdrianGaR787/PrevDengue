package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.SymptomDTO;
import pe.edu.upc.prevdengue.dtos.UserDTO;
import pe.edu.upc.prevdengue.entities.User;
import pe.edu.upc.prevdengue.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private IUserService uS;
    @Autowired
    private pe.edu.upc.prevdengue.servicesimplements.FcmService fcmService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> listar()
    {
        ModelMapper m = new ModelMapper();
        List<UserDTO> lista = uS.list().stream()
                .map(x -> m.map(x, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        User guardado = uS.insert(u);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
    }
    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody UserDTO dto) {
        Optional<User> existente = uS.listId(dto.getIdUser());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        User actualizado = uS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, UserDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (uS.listId(id).isPresent()) {
            uS.delete(id);
            return ResponseEntity.ok("Usuario eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<User> curso = uS.listId(id);

        if (curso.isPresent()) {
            UserDTO dto = m.map(curso.get(), UserDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
    @GetMapping("/top-participacion")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> getTopUsers() {
        return ResponseEntity.ok(uS.listTopUsersByPoints());
    }

    @GetMapping("/por-idioma")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getUsersByPreferredLanguage() {
        return ResponseEntity.ok(uS.listUsersByPreferredLanguage());
    }
    // 🚀 NUEVO ENDPOINT: Buscar usuario por su correo
    @GetMapping("/buscar-email/{email}")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        try {
            User usuario = uS.findByEmail(email);
            if (usuario != null) {
                ModelMapper m = new ModelMapper();
                return ResponseEntity.ok(m.map(usuario, UserDTO.class));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    // 🚀 ENDPOINT PARA GUARDAR EL TOKEN DE FIREBASE (CELULAR)
    @PutMapping("/actualizar-token-celular")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizarFcmToken(@RequestBody java.util.Map<String, String> request) {
        String email = request.get("email");
        String fcmToken = request.get("fcmToken");

        try {
            User usuario = uS.findByEmail(email);
            if (usuario != null) {
                usuario.setFcmToken(fcmToken);
                uS.update(usuario); // Guardamos el usuario con su nuevo token

                // 🚀 SOLUCIÓN: Devolvemos un JSON válido para que Angular no falle al leerlo
                java.util.Map<String, String> response = new java.util.HashMap<>();
                response.put("mensaje", "Token de notificaciones guardado exitosamente.");
                return ResponseEntity.ok(response);
            } else {
                java.util.Map<String, String> errorResponse = new java.util.HashMap<>();
                errorResponse.put("error", "Usuario no encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            java.util.Map<String, String> errorResponse = new java.util.HashMap<>();
            errorResponse.put("error", "Error al guardar el token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PostMapping("/disparar-alerta-prueba")
    public ResponseEntity<?> dispararAlerta(@RequestBody java.util.Map<String, String> request) {
        String email = request.get("email");

        // Buscamos al usuario en PostgreSQL
        User usuario = uS.findByEmail(email);

        if (usuario != null && usuario.getFcmToken() != null) {
            // ¡Disparamos la notificación usando su token guardado!
            fcmService.sendPushNotification(
                    usuario.getFcmToken(),
                    "🚨 Alerta PrevDengue",
                    "¡Felicidades! Tu conexión Spring Boot -> Firebase -> Angular está funcionando a la perfección."
            );
            return ResponseEntity.ok("Alerta disparada al celular de: " + email);
        } else {
            return ResponseEntity.badRequest().body("El usuario no existe o aún no tiene un token FCM guardado.");
        }
}
}
