package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Symptom;
import pe.edu.upc.prevdengue.repositories.ISymptomRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomService;

import java.util.List;
import java.util.Optional;

@Service
public class SymptomServiceImplement implements ISymptomService {
    @Autowired
    private ISymptomRepository sR;

    @Override
    public List<Symptom> list() {
        return sR.findAll();
    }

    @Override
    public Symptom insert(Symptom s) {
        return sR.save(s);
    }

    @Override
    public void delete(int idSymptom) {
        sR.deleteById(idSymptom);
    }

    @Override
    public Optional<Symptom> listId(int idSymptom) {
        return sR.findById(idSymptom);
    }

    @Override
    public void update(Symptom s) {
        sR.save(s);
    }
}
