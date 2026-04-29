package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.User;
import pe.edu.upc.prevdengue.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("1. Intentando hacer login con el correo: " + username);

        pe.edu.upc.prevdengue.entities.User miUsuario = repo.findByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("ERROR: No se encontró el correo en la BD");
                    return new UsernameNotFoundException("Usuario no existe: " + username);
                });
        System.out.println("2. ¡Usuario encontrado en la BD!: " + miUsuario.getEmail());
        System.out.println("3. El hash en la BD es: " + miUsuario.getPasswordHash());

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(miUsuario.getRole().getNameRole().toUpperCase().replace(" ", "_")));

        return new org.springframework.security.core.userdetails.User(
                miUsuario.getEmail(),
                miUsuario.getPasswordHash(),
                true, true, true, true,
                roles
        );
    }
}