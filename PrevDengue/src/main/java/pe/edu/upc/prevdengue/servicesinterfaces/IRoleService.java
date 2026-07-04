package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.Role;
import pe.edu.upc.prevdengue.entities.Symptom;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public List<Role>list();
    public Role insert(Role r);
    public Optional<Role> listId(int id);
    public void delete(int idRole);
    public void update(Role rO);
}
