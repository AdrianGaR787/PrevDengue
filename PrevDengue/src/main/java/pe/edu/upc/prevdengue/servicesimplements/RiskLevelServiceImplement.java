package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.RiskLevel;
import pe.edu.upc.prevdengue.repositories.IRiskLevelRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IRiskLevelService;

import java.util.List;
import java.util.Optional;

@Service
public class RiskLevelServiceImplement implements IRiskLevelService {

    @Autowired
    private IRiskLevelRepository rR;


    @Override
    public List<RiskLevel> list() {
        return rR.findAll();
    }

    @Override
    public RiskLevel insert(RiskLevel rL) {
        return rR.save(rL);
    }

    @Override
    public void delete(int idRiskLevel) {
        rR.deleteById(idRiskLevel);
    }

    @Override
    public Optional<RiskLevel> listId(int idRiskLevel) {
        return rR.findById(idRiskLevel);
    }

    @Override
    public RiskLevel update(RiskLevel rL) {
        rR.save(rL);
        return rL;
    }

}
