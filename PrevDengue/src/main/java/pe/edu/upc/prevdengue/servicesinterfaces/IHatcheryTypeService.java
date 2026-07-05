package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.HatcheryType;

import java.util.List;
import java.util.Optional;

public interface IHatcheryTypeService {
    List<HatcheryType> list();

    public HatcheryType insert(HatcheryType h); // Para Crear y Actualizar
    public void delete(int idHatcheryType);    // Para Eliminar
    public Optional<HatcheryType> listId(int idHatcheryType);
    public void update(HatcheryType h);

}
