package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.repositories.IHatcheryTypeRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IHatcheryTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class HatcheryTypeServiceImplement implements IHatcheryTypeService {
   @Autowired
   private IHatcheryTypeRepository hR;


    @Override
    public List<HatcheryType> list() {
        return hR.findAll();
    }

    @Override
    public HatcheryType insert(HatcheryType h) {
        return hR.save(h);
    }

    @Override
    public Optional<HatcheryType> listId(int id) {
        return hR.findById(id);
    }

    @Override
    public void update(HatcheryType h) {
        hR.save(h);

    }
    @Override
    public void delete(int idHatcheryType) {
        hR.deleteById(idHatcheryType);
    }
}
