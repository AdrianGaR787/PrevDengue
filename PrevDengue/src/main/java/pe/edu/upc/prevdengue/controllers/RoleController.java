package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.RoleDTO;
import pe.edu.upc.prevdengue.dtos.RoleSpecialDTO;
import pe.edu.upc.prevdengue.dtos.SymptomDTO;
import pe.edu.upc.prevdengue.entities.Role;
import pe.edu.upc.prevdengue.entities.Symptom;
import pe.edu.upc.prevdengue.servicesinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService rS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<RoleDTO>>listar(){
        ModelMapper m=new ModelMapper();
        List<RoleDTO> listaRoles= rS.list().stream()
                .map(x -> m.map(x, RoleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaRoles);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody RoleSpecialDTO dto){
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto,Role.class);

        Role ro = rS.insert(r);
        RoleSpecialDTO responseDTO = m.map(ro, RoleSpecialDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Role> curso = rS.listId(id);

        if (curso.isPresent()) {
            RoleSpecialDTO dto = m.map(curso.get(), RoleSpecialDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol con ese ID no encontrado");
        }
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody RoleSpecialDTO dto) {
        Optional<Role> existente = rS.listId(dto.getIdRole());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        ModelMapper m = new ModelMapper();
        Role s = m.map(dto, Role.class);
        Role actualizado = rS.insert(s);
        return ResponseEntity.ok(m.map(actualizado, RoleSpecialDTO.class));
    }
    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (rS.listId(id).isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Rol eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol con ese id no encontrado al intentar eliminar");
    }
}
