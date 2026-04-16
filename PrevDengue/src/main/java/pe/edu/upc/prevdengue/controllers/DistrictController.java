package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.DistrictDTO;
import pe.edu.upc.prevdengue.dtos.DistrictSpecialDTO;
import pe.edu.upc.prevdengue.entities.District;
import pe.edu.upc.prevdengue.servicesinterfaces.IDistrictService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/distritos")
public class DistrictController {
    @Autowired
    private IDistrictService dS;

    @GetMapping()
    public ResponseEntity<List<DistrictDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<DistrictDTO> listaDistritos=dS.list().stream()
                .map(x->m.map(x,DistrictDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDistritos);

    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody DistrictSpecialDTO dto){
        ModelMapper m = new ModelMapper();
        District d = m.map(dto, District.class);

        District cur = dS.insert(d);
        DistrictSpecialDTO responseDTO = m.map(cur, DistrictSpecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<District> distrito = dS.listId(id);

        if (distrito.isPresent()) {
            DistrictSpecialDTO dto = m.map(distrito.get(), DistrictSpecialDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Curso no encontrado");
        }
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody DistrictSpecialDTO dto) {

        Optional<District> existente = dS.listId(dto.getIdDistrict());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Curso no encontrado");
        }

        District dis = existente.get();

        dis.setNameDistrict(dto.getNameDistrict());

        dS.update(dis);

        return ResponseEntity.ok("Curso actualizado correctamente");
    }

}
