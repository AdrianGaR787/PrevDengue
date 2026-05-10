package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.*;
import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.entities.Role;
import pe.edu.upc.prevdengue.entities.Symptom;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sintomas")
public class SymptomController {
    @Autowired
    private ISymptomService sS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> listar() {
        ModelMapper m = new ModelMapper();
        List<SymptomDTO> listaSintomas = sS.list().stream()
                .map(x -> m.map(x, SymptomDTO.class))
                .collect(Collectors.toList());

        if (listaSintomas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay síntomas registrados");
        }

        return ResponseEntity.ok(listaSintomas);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody SymptomDTO dto) {
        ModelMapper m = new ModelMapper();
        Symptom s = m.map(dto, Symptom.class);

        Symptom sy = sS.insert(s);
        SymptomDTO responseDTO = m.map(sy, SymptomDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody SymptomDTO dto) {
        Optional<Symptom> existente = sS.listId(dto.getIdSymptom());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Síntoma no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Symptom s = m.map(dto, Symptom.class);
        Symptom actualizado = sS.insert(s);
        return ResponseEntity.ok(m.map(actualizado, SymptomDTO.class));
    }

    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (sS.listId(id).isPresent()) {
            sS.delete(id);
            return ResponseEntity.ok("Síntoma eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Síntoma no encontrado");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Symptom> curso = sS.listId(id);

        if (curso.isPresent()) {
            SymptomDTO dto = m.map(curso.get(), SymptomDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Sintoma no encontrado con ese id");
        }
    }
    @GetMapping("/mas-frecuentes")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> getMostFrequentSymptoms() {
        return ResponseEntity.ok(sS.getMostFrequentSymptoms());
    }

}
