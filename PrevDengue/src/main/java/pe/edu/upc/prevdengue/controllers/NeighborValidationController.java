package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.NeighborValidationDTO;
import pe.edu.upc.prevdengue.dtos.NotificationDTO;
import pe.edu.upc.prevdengue.entities.NeighborValidation;
import pe.edu.upc.prevdengue.entities.Notification;
import pe.edu.upc.prevdengue.servicesinterfaces.INeighborValidationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/validacion-vecinal")
public class NeighborValidationController {

    @Autowired
    private INeighborValidationService nvS;

    @GetMapping
    public ResponseEntity<List<NeighborValidationDTO>> listAll() {
        ModelMapper m = new ModelMapper();
        List<NeighborValidationDTO> validationList = nvS.list().stream()
                .map(x -> m.map(x, NeighborValidationDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(validationList);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> register(@RequestBody NeighborValidationDTO dto) {
        ModelMapper m = new ModelMapper();
        NeighborValidation nv = m.map(dto, NeighborValidation.class);
        NeighborValidation saved = nvS.insert(nv);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, NeighborValidationDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<NeighborValidation> rs = nvS.listId(id);
        if (rs.isPresent()) {
            NeighborValidationDTO dto = m.map(rs.get(), NeighborValidationDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Validacion con ese id no encontrado");
    }
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody NeighborValidationDTO dto) {
        Optional<NeighborValidation> existente = nvS.listId(dto.getIdValidation());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Validacion a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        NeighborValidation u = m.map(dto, NeighborValidation.class);
        NeighborValidation actualizado = nvS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, NeighborValidationDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (nvS.listId(id).isPresent()) {
            nvS.delete(id);
            return ResponseEntity.ok("Validacion vecinal eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Validacion vecinal con ese ID no encontrado");
    }
}
