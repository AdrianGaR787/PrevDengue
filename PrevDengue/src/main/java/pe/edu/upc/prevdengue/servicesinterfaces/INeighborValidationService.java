package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.NeighborValidation;

import java.util.List;
import java.util.Optional;

public interface INeighborValidationService {
    public List<NeighborValidation> list();
    public NeighborValidation insert(NeighborValidation nV);
    public void delete(int idValidation);
    public Optional<NeighborValidation> listId(int idValidation);
    public void update(NeighborValidation nV);
}
