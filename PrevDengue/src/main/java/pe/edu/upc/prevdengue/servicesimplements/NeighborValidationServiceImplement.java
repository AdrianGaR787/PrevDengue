package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.NeighborValidation;
import pe.edu.upc.prevdengue.repositories.INeighborValidationRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.INeighborValidationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NeighborValidationServiceImplement implements INeighborValidationService {

    @Autowired
    private INeighborValidationRepository nvR;

    @Override
    public List<NeighborValidation> list() {
        return nvR.findAll();
    }

    @Override
    public NeighborValidation insert(NeighborValidation validation) {
        if (validation.getValidationDate() == null) {
            validation.setValidationDate(LocalDateTime.now());
        }
        return nvR.save(validation);
    }

    @Override
    public void delete(int idValidation) {
        nvR.deleteById(idValidation);
    }

    @Override
    public Optional<NeighborValidation> listId(int idValidation) {
        return nvR.findById(idValidation);
    }

    @Override
    public void update(NeighborValidation nV) {
        nvR.save(nV);
    }
}
