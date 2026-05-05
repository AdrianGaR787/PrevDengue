package pe.edu.upc.prevdengue.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.User;
import pe.edu.upc.prevdengue.repositories.IUserRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository uR;

    @Override
    public List<User> list() {
        return uR.findAll();
    }

    @Override
    public User insert(User uS) {
        return uR.save(uS);
    }

    @Override
    public void delete(int idUser) {
        uR.deleteById(idUser);

    }

    @Override
    public Optional<User> listId(int idUser) {
        return uR.findById(idUser);
    }

    @Override
    public void update(User uS) {
        uR.save(uS);
    }

    @Override
    public List<User> listTopUsersByPoints() {
        return uR.getTopUsersByPoints();
    }

    @Override
    public List<User> listUsersByPreferredLanguage() {
        return uR.getUsersByPreferredLanguage();
    }
}
