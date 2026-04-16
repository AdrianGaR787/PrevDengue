package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.prevdengue.dtos.RiskLevelDTO;
import pe.edu.upc.prevdengue.dtos.RoleDTO;
import pe.edu.upc.prevdengue.servicesinterfaces.IRiskLevelService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nivelesriesgo")
public class RiskLevelController {
    @Autowired
    private IRiskLevelService rL;
    @GetMapping

    public ResponseEntity<List<RiskLevelDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<RiskLevelDTO> listaNiveles= rL.list().stream()
                .map(x->m.map(x,RiskLevelDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaNiveles);
    }
}
