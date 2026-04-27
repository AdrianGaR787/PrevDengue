package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> list();
    public User insert(User uS);
    public void delete(int idUser);
    public Optional<User> listId(int idUser);
    public void update(User uS);
}
