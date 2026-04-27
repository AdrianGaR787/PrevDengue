package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.CleaningEvent;
import pe.edu.upc.prevdengue.repositories.ICleaningEventRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ICleaningEventService;

import java.util.List;
import java.util.Optional;

@Service
public class CleaningEventServiceImplement implements ICleaningEventService {

    @Autowired
    private ICleaningEventRepository cleaningEventRepository;
    @Override
    public List<CleaningEvent> list() {
        return cleaningEventRepository.findAll();
    }

    @Override
    public CleaningEvent insert(CleaningEvent cE) {
        // Business logic: When created, status is always 'Programado' (Scheduled)
        if (cE.getIdEvent() == 0 && cE.getStatus() == null) {
            cE.setStatus("Programado");
        }
        return cleaningEventRepository.save(cE);
    }

    @Override
    public void delete(int idEvent) {
        cleaningEventRepository.deleteById(idEvent);

    }

    @Override
    public Optional<CleaningEvent> listId(int idEvent) {
        return cleaningEventRepository.findById(idEvent);
    }

    @Override
    public void update(CleaningEvent cE) {
        cleaningEventRepository.save(cE);

    }
}
