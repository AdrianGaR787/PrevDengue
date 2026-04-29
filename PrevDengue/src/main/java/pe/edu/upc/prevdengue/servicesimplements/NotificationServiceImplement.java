package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Notification;
import pe.edu.upc.prevdengue.repositories.INotificationRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.INotificationService;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImplement implements INotificationService {

    @Autowired
    private INotificationRepository nR;

    @Override
    public List<Notification> list() {
        return nR.findAll();
    }

    @Override
    public Notification insert(Notification nT) {
        if (nT.getIdNotification() == 0) {
            nT.setRead(false);
        }
        return nR.save(nT);
    }
    @Override
    public void delete(int idNotification) {
        nR.deleteById(idNotification);

    }

    @Override
    public Optional<Notification> listId(int idNotificacion) {
        return nR.findById(idNotificacion);
    }

    @Override
    public void update(Notification nT) {
        nR.save(nT);

    }
}
