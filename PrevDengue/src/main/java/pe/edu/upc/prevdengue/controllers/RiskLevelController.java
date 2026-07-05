package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.ReportStatusDTO;
import pe.edu.upc.prevdengue.dtos.RiskLevelDTO;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.entities.RiskLevel;
import pe.edu.upc.prevdengue.servicesinterfaces.IRiskLevelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nivelesriesgo")
public class RiskLevelController {
    @Autowired
    private IRiskLevelService rL;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> listar(){
        ModelMapper m=new ModelMapper();
        List<RiskLevelDTO> listaNiveles= rL.list().stream()
                .map(x->m.map(x,RiskLevelDTO.class))
                .collect(Collectors.toList());

        if (listaNiveles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay niveles de riesgo registrados");
        }

        return ResponseEntity.ok(listaNiveles);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody RiskLevelDTO dto) {
        ModelMapper m = new ModelMapper();
        RiskLevel rl = m.map(dto, RiskLevel.class);   // DTO → entidad
        RiskLevel guardado = rL.insert(rl);           // guardas entidad
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(m.map(guardado, RiskLevelDTO.class)); // entidad → DTO
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<RiskLevel> existente = rL.listId(id);

        if (existente.isPresent()) {
            RiskLevelDTO dto = m.map(existente.get(), RiskLevelDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Nivel de riesgo no encontrado");
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody RiskLevelDTO dto) {
        Optional<RiskLevel> existente = rL.listId(dto.getIdRiskLevel());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nivel de Riesgo a actualizar no encontrado");
        }
        RiskLevel rl = existente.get();
        rl.setNameRiskLevel(dto.getNameRiskLevel());

        RiskLevel actualizado = rL.update(rl);

        ModelMapper m = new ModelMapper();
        RiskLevelDTO responseDTO = m.map(actualizado, RiskLevelDTO.class);

        return ResponseEntity.ok(responseDTO);
        }

    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (rL.listId(id).isPresent()) {
            rL.delete(id);
            return ResponseEntity.ok("Nivel de riesgo eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nivel de riesgo con ese id no encontrado");
    }
}
