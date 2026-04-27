package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.DistrictSpecialDTO;
import pe.edu.upc.prevdengue.dtos.HatcheryTypeDTO;
import pe.edu.upc.prevdengue.dtos.HatcheryTypeSpecialDTO;
import pe.edu.upc.prevdengue.entities.District;
import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.servicesinterfaces.IHatcheryTypeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiposcriaderos")
public class HatcheryTypeController {
    @Autowired
    private IHatcheryTypeService cS;
    @GetMapping

    public ResponseEntity<List<HatcheryTypeDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<HatcheryTypeDTO> listaCriaderos=cS.list().stream()
                .map(x->m.map(x, HatcheryTypeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCriaderos);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody HatcheryTypeSpecialDTO dto){
        ModelMapper m = new ModelMapper();
        HatcheryType h = m.map(dto, HatcheryType.class);

        HatcheryType cur = cS.insert(h);
        HatcheryTypeSpecialDTO responseDTO = m.map(cur, HatcheryTypeSpecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<HatcheryType> criadero = cS.listId(id);

        if (criadero.isPresent()) {
            HatcheryTypeDTO dto = m.map(criadero.get(), HatcheryTypeDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Curso no encontrado");
        }
    }
    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody HatcheryTypeSpecialDTO dto) {

        Optional<HatcheryType> existente = cS.listId(dto.getIdHatcheryType());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de criadero no encontrado");
        }

        HatcheryType hT = existente.get();

        hT.setNameHatchery(dto.getNameHatchery());

        cS.update(hT);

        return ResponseEntity.ok("Tipo de criadero actualizado correctamente");
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<HatcheryType> existente = cS.listId(id);

        if (existente.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Tipo de criadero eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de criadero no encontrado");
        }
    }

}
