package pe.edu.upc.prevdengue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.prevdengue.dtos.InterventionCampaignDTO;
import pe.edu.upc.prevdengue.dtos.SymptomDTO;
import pe.edu.upc.prevdengue.dtos.UserDTO;
import pe.edu.upc.prevdengue.entities.InterventionCampaign;
import pe.edu.upc.prevdengue.entities.User;
import pe.edu.upc.prevdengue.servicesinterfaces.IInterventionCampaignService;
import pe.edu.upc.prevdengue.servicesinterfaces.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campanas")
public class InterventionCampaignController {

    @Autowired
    private IInterventionCampaignService iC;

    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody InterventionCampaignDTO dto) {
        ModelMapper m = new ModelMapper();
        InterventionCampaign c = m.map(dto, InterventionCampaign.class);
        InterventionCampaign guardada = iC.insert(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.map(guardada, InterventionCampaignDTO.class));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<InterventionCampaignDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<InterventionCampaignDTO> lista = iC.list().stream()
                .map(x -> m.map(x, InterventionCampaignDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> actualizar(@RequestBody InterventionCampaignDTO dto) {
        Optional<InterventionCampaign> existente = iC.listId(dto.getIdCampana());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaña a actualizar no encontrado");
        }
        ModelMapper m = new ModelMapper();
        InterventionCampaign u = m.map(dto, InterventionCampaign.class);
        InterventionCampaign actualizado = iC.insert(u);
        return ResponseEntity.ok(m.map(actualizado, InterventionCampaignDTO.class));
    }

    @DeleteMapping("/elimina/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (iC.listId(id).isPresent()) {
            iC.delete(id);
            return ResponseEntity.ok("Campaña eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaña con ese ID no encontrado");
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<InterventionCampaign> curso = iC.listId(id);

        if (curso.isPresent()) {
            InterventionCampaignDTO dto = m.map(curso.get(), InterventionCampaignDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Campaña de Intervencion no encontrado");
        }
    }
    @GetMapping("/buscar-por-distrito")
    @PreAuthorize("hasAnyAuthority('CIUDADANO', 'BRIGADISTA', 'ADMIN')")
    public ResponseEntity<List<InterventionCampaignDTO>> searchByDistrict(@RequestParam String districtName) {
        ModelMapper m = new ModelMapper();
        List<InterventionCampaignDTO> list = iC.searchByDistrictName(districtName).stream()
                .map(x -> m.map(x, InterventionCampaignDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/por-tipo")
    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    public ResponseEntity<?> getCampaignsByType() {
        return ResponseEntity.ok(iC.listCampaignsByType());
    }

    @PreAuthorize("hasAnyAuthority('BRIGADISTA', 'ADMIN')")
    @GetMapping("/por-lapso-tiempo")
    public ResponseEntity<?> getCampaignsInTimeframe(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        return ResponseEntity.ok(iC.listCampaignsInTimeframe(inicio, fin));
    }
}
