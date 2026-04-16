package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.prevdengue.dtos.RoleDTO;
import pe.edu.upc.prevdengue.dtos.SymptomDTO;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sintomas")
public class SymptomController {
    @Autowired
    private ISymptomService sS;
    @GetMapping

    public ResponseEntity<List<SymptomDTO>> listar(){
        ModelMapper m=new ModelMapper();
        List<SymptomDTO> listaSintomas= sS.list().stream()
                .map(x->m.map(x,SymptomDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaSintomas);
    }
}
