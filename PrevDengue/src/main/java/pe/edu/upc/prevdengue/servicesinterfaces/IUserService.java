package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> list();
    public User insert (User us);
    public Optional<User> listId(int id);
    public void update(User u);
    public void delete(int id);

}
