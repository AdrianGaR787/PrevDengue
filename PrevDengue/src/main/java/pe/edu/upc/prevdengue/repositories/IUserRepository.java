package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.prevdengue.entities.User;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
