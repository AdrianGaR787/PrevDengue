package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Role;
import pe.edu.upc.prevdengue.repositories.IRoleRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplement implements IRoleService {

    @Autowired
    private IRoleRepository cR;


    @Override
    public List<Role> list() {
        return cR.findAll();
    }

    @Override
    public Role insert(Role r) {
        return cR.save(r);
    }

    @Override
    public Optional<Role> listId(int id) {
        return cR.findById(id);
    }
}
