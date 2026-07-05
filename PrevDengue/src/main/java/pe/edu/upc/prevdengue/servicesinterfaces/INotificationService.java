package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.Notification;

import java.util.List;
import java.util.Optional;

public interface INotificationService {
    public List<Notification> list();
    public Notification insert(Notification nT);
    public void delete(int idNotification);
    public Optional<Notification> listId(int idNotification);
    public void update(Notification nT);
    List<Notification> listReadNotifications();
}
    