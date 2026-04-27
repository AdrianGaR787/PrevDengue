package pe.edu.upc.prevdengue.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.CleaningEventDTO;
import pe.edu.upc.prevdengue.dtos.DistrictSpecialDTO;
import pe.edu.upc.prevdengue.dtos.NotificationDTO;
import pe.edu.upc.prevdengue.entities.CleaningEvent;
import pe.edu.upc.prevdengue.entities.District;
import pe.edu.upc.prevdengue.entities.Notification;
import pe.edu.upc.prevdengue.repositories.ICleaningEventRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ICleaningEventService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cleaning-events")
public class CleaningEventController {

    @Autowired
    private ICleaningEventService ceS;

    @PostMapping("/nuevo")
    public ResponseEntity<?> register(@RequestBody CleaningEventDTO dto) {
        ModelMapper m = new ModelMapper();
        CleaningEvent ce = m.map(dto, CleaningEvent.class);
        CleaningEvent saved = ceS.insert(ce);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, CleaningEventDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<CleaningEventDTO>> listAll() {
        ModelMapper m = new ModelMapper();
        List<CleaningEventDTO> eventList = ceS.list().stream()
                .map(x -> m.map(x, CleaningEventDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<CleaningEvent> limpieza = ceS.listId(id);

        if (limpieza.isPresent()) {
            CleaningEventDTO dto = m.map(limpieza.get(), CleaningEventDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento de Limpieza con ese id no encontrado");
        }
    }
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody CleaningEventDTO dto) {
        Optional<CleaningEvent> existente = ceS.listId(dto.getIdEvent());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        CleaningEvent u = m.map(dto, CleaningEvent.class);
        CleaningEvent actualizado = ceS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, CleaningEventDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (ceS.listId(id).isPresent()) {
            ceS.delete(id);
            return ResponseEntity.ok("Evento eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento con ese ID no encontrado");
    }
}
