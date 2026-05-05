package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.PredictiveAlertDTO;
import pe.edu.upc.prevdengue.dtos.ReportDTO;
import pe.edu.upc.prevdengue.entities.PredictiveAlert;
import pe.edu.upc.prevdengue.entities.Report;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReportController {
    @Autowired
    private IReportService rS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUIDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<ReportDTO>> listAll() {
        ModelMapper m = new ModelMapper();
        List<ReportDTO> reportList = rS.list().stream()
                .map(x -> m.map(x, ReportDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportList);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('CIUDADANO')")
    public ResponseEntity<?> register(@RequestBody ReportDTO dto) {
        ModelMapper m = new ModelMapper();
        Report r = m.map(dto, Report.class);
        Report saved = rS.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, ReportDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Report> rO = rS.listId(id);
        if (rO.isPresent()) {
            ReportDTO dto = m.map(rO.get(), ReportDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte con ese id no encontrado");
    }
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualizar(@RequestBody ReportDTO dto) {
        Optional<Report> existente = rS.listId(dto.getIdReport());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Report u = m.map(dto, Report.class);
        Report actualizado = rS.insert(u);
        return ResponseEntity.ok(m.map(actualizado, ReportDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (rS.listId(id).isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Reporte eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte con ese ID no encontrado");
    }
    @GetMapping("/reportes-tipo-criadero")
    public ResponseEntity<?> getReportCountByHatcheryType() {
        List<String[]> report = rS.getReportCountByHatcheryType();
        if (report.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reportes disponibles");
        }
        return ResponseEntity.ok(report);
    }
    @GetMapping("/reporte-por-estado")
    public ResponseEntity<?> getReportCountByStatus() {
        List<String[]> report = rS.getReportCountByStatus();
        if (report.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reportes disponibles para mostrar");
        }
        return ResponseEntity.ok(report);
    }
    @GetMapping("/con-mas-sintomas")
    public ResponseEntity<?> getReportsWithMostSymptoms() {
        return ResponseEntity.ok(rS.listReportsWithMostSymptoms());
    }
    @GetMapping("/zonas-alto-riesgo")
    public ResponseEntity<?> getHighRiskReports() {
        return ResponseEntity.ok(rS.listHighRiskReports());
    }
}
