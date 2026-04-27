package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.PredictiveAlert;
import pe.edu.upc.prevdengue.repositories.IPredictiveAlertRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IPredictiveAlertService;

import java.util.List;
import java.util.Optional;

@Service
public class PredictiveAlertImplement implements IPredictiveAlertService {
    @Autowired
    private IPredictiveAlertRepository paR;

    @Override
    public List<PredictiveAlert> list() {
        return paR.findAll();
    }

    @Override
    public PredictiveAlert insert(PredictiveAlert pA) {
        return paR.save(pA);
    }

    @Override
    public void delete(int idAlert) {
        paR.deleteById(idAlert);
    }

    @Override
    public Optional<PredictiveAlert> listId(int idAlert) {
        return paR.findById(idAlert);
    }

    @Override
    public void update(PredictiveAlert pA) {
        paR.save(pA);
    }
}
