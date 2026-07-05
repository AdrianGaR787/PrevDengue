package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.dtos.FrequentSymptomDTO;
import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.entities.Symptom;

import java.util.List;
import java.util.Optional;

public interface ISymptomService {
    public List<Symptom> list();
    public Symptom insert(Symptom s);
    public void delete(int idSymptom);
    public Optional<Symptom> listId(int idSymptom);
    public void update(Symptom s);
    List<FrequentSymptomDTO> getMostFrequentSymptoms();
}
