package pe.edu.upc.prevdengue.servicesinterfaces;

import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.PredictiveAlert;

import java.util.List;
import java.util.Optional;

public interface IPredictiveAlertService{
    public List<PredictiveAlert> list();
    public PredictiveAlert insert(PredictiveAlert pA);
    public void delete(int idAlert);
    public Optional<PredictiveAlert> listId(int idAlert);
    public void update(PredictiveAlert pA);
}
