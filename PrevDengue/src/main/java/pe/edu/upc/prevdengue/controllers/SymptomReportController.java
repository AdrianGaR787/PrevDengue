package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.SymptomReportDTO;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomReportService;
import pe.edu.upc.prevdengue.servicesinterfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportesintoma")
public class SymptomReportController {
    @Autowired
    private ISymptomReportService irS;

    @GetMapping
    public List<SymptomReportDTO> listar() {
        return irS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, SymptomReportDTO.class);
        }).collect(Collectors.toList());
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        irS.delete(id);
    }
}
