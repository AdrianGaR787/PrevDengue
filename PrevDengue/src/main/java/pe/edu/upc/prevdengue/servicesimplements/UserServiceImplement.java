package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORTANTE: Nueva importación
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

    // 1. INYECTAMOS EL ENCRIPTADOR DE SPRING SECURITY
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return uR.findAll();
    }
    @Override
    public User insert(User uS) {
        // Tomamos la clave en texto plano que viene del Swagger/Frontend
        String clavePlana = uS.getPasswordHash();

        // La encriptamos usando BCrypt
        String claveEncriptada = passwordEncoder.encode(clavePlana);

        // Le asignamos la clave ya encriptada al objeto User
        uS.setPasswordHash(claveEncriptada);

        // Guardamos en la base de datos y retornamos
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
        // Nota: Si en el futuro permites cambiar contraseñas en el update,
        // también tendrías que encriptarla aquí antes del save.
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

    @Override
    public User findByEmail(String email) {
        // Desempaquetamos el Optional. Si el correo existe, devuelve el Usuario.
        // Si no existe, devuelve null de forma segura.
        return uR.findByEmail(email).orElse(null);
    }
}