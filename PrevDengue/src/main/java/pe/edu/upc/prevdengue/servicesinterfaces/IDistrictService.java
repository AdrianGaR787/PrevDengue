package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.District;

import java.util.List;
import java.util.Optional;

public interface IDistrictService {
    public List<District>list();
    public District insert(District d);
    public Optional<District> listId(int id);
    public void update(District d);
}
