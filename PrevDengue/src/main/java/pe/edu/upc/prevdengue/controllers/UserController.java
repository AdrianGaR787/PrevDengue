package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.UserDTO;
import pe.edu.upc.prevdengue.entities.User;
import pe.edu.upc.prevdengue.servicesinterfaces.IRoleService;
import pe.edu.upc.prevdengue.servicesinterfaces.IUserService;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private IUserService iuS;
    @Autowired
    private IRoleService irS;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listar(){
        ModelMapper m=new ModelMapper();
        List<UserDTO> listaUsuarios=iuS.list().stream()
                .map(x->m.map(x,UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsuarios);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody UserDTO dto){
        ModelMapper m = new ModelMapper();
        User c = m.map(dto, User.class);

        User use = iuS.insert(c);
        UserDTO responseDTO = m.map(use, UserDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<User> usuario = iuS.listId(id);

        if (usuario.isPresent()) {
            UserDTO dto = m.map(usuario.get(), UserDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody UserDTO dto) {
        Optional<User> existente = iuS.listId(dto.getIdUser());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
        User use = existente.get();
        use.setNameUser(dto.getNameUser());
        use.setEmailUser(dto.getEmailUser());
        use.setUserPhone(dto.getUserPhone());
        use.setHashPassword(dto.getHashPassword());
        use.setPreferredLanguage(dto.getPreferredLanguage());
        use.setTotalPoints(dto.getTotalPoints());
        use.setActiveBiometrics(dto.isActiveBiometrics());
        iuS.update(use);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<User> usuario = iuS.listId(id);

        if (usuario.isPresent()) {
            iuS.delete(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }

}
