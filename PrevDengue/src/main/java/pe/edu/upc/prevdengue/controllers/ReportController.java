package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.ReportDTO;
import pe.edu.upc.prevdengue.entities.Report;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/informes")
public class ReportController {
    @Autowired
    private IReportService irS;
    @GetMapping
    public ResponseEntity<List<ReportDTO>> listar(){
        ModelMapper m=new ModelMapper();
        List<ReportDTO> listaInformes=irS.list().stream()
                .map(x->m.map(x,ReportDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaInformes);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody ReportDTO dto){
        ModelMapper m = new ModelMapper();
        Report r = m.map(dto, Report.class);

        Report cur = irS.insert(r);
        ReportDTO responseDTO = m.map(cur, ReportDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Report> informe = irS.listId(id);

        if (informe.isPresent()) {
            ReportDTO dto = m.map(informe.get(), ReportDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Informe no encontrado");
        }
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody ReportDTO dto) {
        Optional<Report> existente = irS.listId(dto.getIdReport());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Informe no encontrado");
        }
        Report rep = existente.get();
        rep.setLatitude(dto.getLatitude());
        rep.setLongitude(dto.getLongitude());
        rep.setDirection(dto.getDirection());
        rep.setPhotoUrl(dto.getPhotoUrl());
        rep.setAnonymous(dto.isAnonymous());
        irS.update(rep);
        return ResponseEntity.ok("Informe actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Report> informe = irS.listId(id);

        if (informe.isPresent()) {
            irS.delete(id);
            return ResponseEntity.ok("Informe eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Informe no encontrado");
        }
    }
}
