package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.RoleDTO;
import pe.edu.upc.prevdengue.dtos.RoleSpecialDTO;
import pe.edu.upc.prevdengue.entities.Role;
import pe.edu.upc.prevdengue.servicesinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService rS;

    @GetMapping
    public ResponseEntity<List<RoleDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<RoleDTO> listaRoles= rS.list().stream()
                .map(x -> m.map(x, RoleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRoles);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody RoleSpecialDTO dto){
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto,Role.class);

        Role ro = rS.insert(r);
        RoleSpecialDTO responseDTO = m.map(ro, RoleSpecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Role> curso = rS.listId(id);

        if (curso.isPresent()) {
            RoleSpecialDTO dto = m.map(curso.get(), RoleSpecialDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Curso no encontrado");
        }
    }
    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody RoleSpecialDTO dto) {

        Optional<Role> existente = rS.listId(dto.getIdRole());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }

        Role ro = existente.get();

        ro.setNameRole(dto.getNameRole());
        ro.setDescription(dto.getDescription());

        rS.update(ro);

        return ResponseEntity.ok("Rol actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Role> role = rS.listId(id);

        if (role.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }
    }
}
