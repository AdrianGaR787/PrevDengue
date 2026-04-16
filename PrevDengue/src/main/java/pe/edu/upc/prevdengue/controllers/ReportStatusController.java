package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.prevdengue.dtos.ReportStatusDTO;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportStatusService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estadosinforme")
public class ReportStatusController {
    @Autowired
    private IReportStatusService eS;

    @GetMapping

    public ResponseEntity<List<ReportStatusDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<ReportStatusDTO> listaEstados= eS.list().stream()
                .map(x->m.map(x, ReportStatusDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaEstados);
    }

}
