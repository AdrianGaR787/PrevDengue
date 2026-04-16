package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.HatcheryType;

import java.util.List;
import java.util.Optional;

public interface IHatcheryTypeService {
    public List<HatcheryType>list();
    public HatcheryType insert(HatcheryType h);
    public Optional<HatcheryType>listId(int id);

}
