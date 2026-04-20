package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Symptom;
import pe.edu.upc.prevdengue.repositories.ISymptomRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomService;

import java.util.List;

@Service
public class SymptomServiceImplement implements ISymptomService {

    @Autowired
    private ISymptomRepository sR;


    @Override
    public List<Symptom> list() {
        return sR.findAll();
    }
}
