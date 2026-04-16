package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.District;
import pe.edu.upc.prevdengue.repositories.IDistrictRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IDistrictService;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImplement implements IDistrictService {

    @Autowired
    private IDistrictRepository dR;


    @Override
    public List<District> list() {
        return dR.findAll();
    }

    @Override
    public District insert(District d) {
        return dR.save(d);
    }

    @Override
    public Optional<District> listId(int id) {
        return dR.findById(id);
    }

    @Override
    public void update(District d) {
        dR.save(d);
    }
}
