package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> listar() {
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
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (uS.listId(id).isPresent()) {
            uS.delete(id);
            return ResponseEntity.ok("Usuario eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    @GetMapping("/{id}")
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
    public ResponseEntity<?> getTopUsers() {
        return ResponseEntity.ok(uS.listTopUsersByPoints());
    }
    @GetMapping("/por-idioma")
    public ResponseEntity<?> getUsersByPreferredLanguage() {
        return ResponseEntity.ok(uS.listUsersByPreferredLanguage());
    }
}
