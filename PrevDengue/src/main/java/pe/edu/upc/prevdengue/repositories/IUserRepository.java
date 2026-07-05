package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.prevdengue.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);;

    //BUSCAR POR NOMBRE
    @Query("select count(u) from User u where u.email = :email")
    int buscarEmail(@Param("email") String email);

    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);

    @Query(value = "SELECT * FROM \"user\" ORDER BY accumulated_points DESC", nativeQuery = true)
    List<User> getTopUsersByPoints();

    @Query(value = "SELECT * FROM \"user\" WHERE preferred_language IN ('ES', 'EN', 'Español', 'Ingles')", nativeQuery = true)
    List<User> getUsersByPreferredLanguage();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.fcmToken = null WHERE u.fcmToken = :token")
    void clearDeadFcmToken(@Param("token") String token);


}
