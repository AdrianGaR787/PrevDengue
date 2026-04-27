package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.ReportStatusDTO;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportStatusService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estadosinforme")
public class ReportStatusController {
    @Autowired
    private IReportStatusService eS;

    @GetMapping
    public ResponseEntity<?> listar() {
        ModelMapper m = new ModelMapper();
        List<ReportStatusDTO> listaEstados = eS.list().stream()
                .map(x -> m.map(x, ReportStatusDTO.class))
                .collect(Collectors.toList());

        if (listaEstados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay estados de reporte registrados");
        }

        return ResponseEntity.ok(listaEstados);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody ReportStatusDTO dto) {
        ModelMapper m = new ModelMapper();
        ReportStatus rs = m.map(dto, ReportStatus.class);
        ReportStatus guardado = eS.insert(rs);
        ReportStatusDTO responseDTO = m.map(guardado, ReportStatusDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<ReportStatus> rs = eS.listId(id);
        if (rs.isPresent()) {
            ReportStatusDTO dto = m.map(rs.get(), ReportStatusDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado de informe no encontrado");
    }

    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody ReportStatusDTO dto) {
        Optional<ReportStatus> existente = eS.listId(dto.getIdReportStatus());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado de informe no encontrado");
        }
        ModelMapper m = new ModelMapper();
        ReportStatus rs = m.map(dto, ReportStatus.class);
        ReportStatus actualizado = eS.insert(rs);
        return ResponseEntity.ok("Estado de Reporte actualizado correctamente");
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (eS.listId(id).isPresent()) {
            eS.delete(id);
            return ResponseEntity.ok("Estado de informe eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado de informe no encontrado");
    }

}
