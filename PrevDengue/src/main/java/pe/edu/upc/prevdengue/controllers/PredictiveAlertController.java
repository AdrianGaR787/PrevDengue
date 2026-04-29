package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.NotificationDTO;
import pe.edu.upc.prevdengue.dtos.PredictiveAlertDTO;
import pe.edu.upc.prevdengue.entities.Notification;
import pe.edu.upc.prevdengue.entities.PredictiveAlert;
import pe.edu.upc.prevdengue.servicesinterfaces.IPredictiveAlertService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas-predictivas")
public class PredictiveAlertController {

    @Autowired
    private IPredictiveAlertService paS;

    @GetMapping
    public ResponseEntity<List<PredictiveAlertDTO>> listAll() {
        ModelMapper m = new ModelMapper();
        List<PredictiveAlertDTO> alertList = paS.list().stream()
                .map(x -> m.map(x, PredictiveAlertDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(alertList);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> register(@RequestBody PredictiveAlertDTO dto) {
        ModelMapper m = new ModelMapper();
        PredictiveAlert pa = m.map(dto, PredictiveAlert.class);
        PredictiveAlert saved = paS.insert(pa);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, PredictiveAlertDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<PredictiveAlert> pA = paS.listId(id);
        if (pA.isPresent()) {
            PredictiveAlertDTO dto = m.map(pA.get(), PredictiveAlertDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta predictiva con ese id no encontrado");
    }

    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody PredictiveAlertDTO dto) {
        Optional<PredictiveAlert> existente = paS.listId(dto.getIdAlert());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        PredictiveAlert u = m.map(dto, PredictiveAlert.class);
        PredictiveAlert actualizado = paS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, PredictiveAlertDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (paS.listId(id).isPresent()) {
            paS.delete(id);
            return ResponseEntity.ok("Alerta predictiva eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta predictiva con ese ID no encontrado");
    }
}
