package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.entities.Notification;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification,Integer> {
    @Query(value = "SELECT * FROM notifications WHERE is_read = true", nativeQuery = true)
    List<Notification> getReadNotifications();
}
