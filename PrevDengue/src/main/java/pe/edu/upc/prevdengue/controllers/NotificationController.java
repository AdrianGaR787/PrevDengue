package pe.edu.upc.prevdengue.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.InterventionCampaignDTO;
import pe.edu.upc.prevdengue.dtos.NotificationDTO;
import pe.edu.upc.prevdengue.dtos.ReportStatusDTO;
import pe.edu.upc.prevdengue.dtos.SymptomDTO;
import pe.edu.upc.prevdengue.entities.InterventionCampaign;
import pe.edu.upc.prevdengue.entities.Notification;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.servicesinterfaces.INotificationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificationes")
public class NotificationController {

    @Autowired
    private INotificationService nS;

    @GetMapping
    public ResponseEntity<?> listar() {
        ModelMapper m = new ModelMapper();
        List<NotificationDTO> listaNotificaciones = nS.list().stream()
                .map(x -> m.map(x, NotificationDTO.class))
                .collect(Collectors.toList());

        if (listaNotificaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay notificaciones registrados");
        }

        return ResponseEntity.ok(listaNotificaciones);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody NotificationDTO dto) {
        ModelMapper m = new ModelMapper();
        Notification n = m.map(dto, Notification.class);
        Notification guardada = nS.insert(n);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(guardada, NotificationDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Notification> rs = nS.listId(id);
        if (rs.isPresent()) {
            NotificationDTO dto = m.map(rs.get(), NotificationDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification con ese id no encontrado");
    }
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody NotificationDTO dto) {
        Optional<Notification> existente = nS.listId(dto.getIdNotification());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificacion a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Notification u = m.map(dto, Notification.class);
        Notification actualizado = nS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, NotificationDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (nS.listId(id).isPresent()) {
            nS.delete(id);
            return ResponseEntity.ok("Notificacion eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificacion con ese ID no encontrado");
    }
    @GetMapping("/leidas")
    public ResponseEntity<?> getReadNotifications() {
        return ResponseEntity.ok(nS.listReadNotifications());
    }
}
