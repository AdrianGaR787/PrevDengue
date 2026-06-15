package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.PredictiveAlertDTO;
import pe.edu.upc.prevdengue.dtos.ReportDTO;
import pe.edu.upc.prevdengue.entities.*;
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
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<ReportDTO>> listAll() {
        ModelMapper m = new ModelMapper();
        List<ReportDTO> reportList = rS.list().stream()
                .map(x -> m.map(x, ReportDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportList);
    }
    @PostMapping("/nuevo")
    //@PreAuthorize("hasAuthority('CIUDADANO')")
    public ResponseEntity<?> register(@RequestBody ReportDTO dto) {
        ModelMapper m = new ModelMapper();
        Report r = m.map(dto, Report.class);
        r.setAnonymous(dto.isAnonymous());
        Report saved = rS.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(saved, ReportDTO.class));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody ReportDTO dto) {
        Optional<Report> existente = rS.listId(dto.getIdReport());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Report u = m.map(dto, Report.class);
        u.setAnonymous(dto.isAnonymous());
        // 🛡️ REFUERZO MANUAL: Evitamos que los IDs se vuelvan null (¡Crucial!)
        if (dto.getDistrict() != null) {
            District d = new District();
            d.setIdDistrict(dto.getDistrict().getIdDistrict());
            u.setDistrict(d);
        }
        if (dto.getUser() != null) {
            User user = new User();
            user.setIdUser(dto.getUser().getIdUser());
            u.setUser(user);
        }
        if (dto.getHatcheryType() != null) {
            HatcheryType h = new HatcheryType();
            h.setIdHatcheryType(dto.getHatcheryType().getIdHatcheryType());
            u.setHatcheryType(h);
        }
        if (dto.getStatus() != null) {
            ReportStatus s = new ReportStatus();
            s.setIdStatus(dto.getStatus().getIdStatus());
            u.setStatus(s);
        }
        if (dto.getSymptoms() != null && !dto.getSymptoms().isEmpty()) {
            List<Symptom> listSymptoms = dto.getSymptoms().stream().map(sDto -> {
                Symptom s = new Symptom();
                s.setIdSymptom(sDto.getIdSymptom());
                return s;
            }).collect(Collectors.toList());
            u.setSymptoms(listSymptoms);
        }

        // Cambié insert por update para mantener la semántica, aunque usen .save() por debajo
        rS.update(u);
        return ResponseEntity.ok(m.map(u, ReportDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'BRIGADISTA')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (rS.listId(id).isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Reporte eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte con ese ID no encontrado");
    }
    @GetMapping("/reportes-tipo-criadero")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> getReportCountByHatcheryType() {
        List<String[]> report = rS.getReportCountByHatcheryType();
        if (report.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reportes disponibles");
        }
        return ResponseEntity.ok(report);
    }
    @GetMapping("/reporte-por-estado")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> getHighRiskReports() {
        return ResponseEntity.ok(rS.listHighRiskReports());
    }

    @GetMapping("/por-distrito/{id}")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<ReportDTO>> listByDistrict(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        List<ReportDTO> list = rS.findByDistrict(id).stream()
                .map(x -> m.map(x, ReportDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/ranking-distritos")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<String[]>> getDistrictRanking() {
        return ResponseEntity.ok(rS.getReportCountByDistrict());
    }
}
