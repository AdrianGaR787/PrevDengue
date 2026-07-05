package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.CleaningEvent;

import java.util.List;
import java.util.Optional;

public interface ICleaningEventService{
    public List<CleaningEvent> list();
    public CleaningEvent insert(CleaningEvent cE);
    public void delete(int idEvent);
    public Optional<CleaningEvent> listId(int idEvent);
    public void update(CleaningEvent cE);
}
