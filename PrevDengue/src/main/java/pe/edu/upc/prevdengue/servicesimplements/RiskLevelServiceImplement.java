package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.RiskLevel;
import pe.edu.upc.prevdengue.repositories.IRiskLevelRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IRiskLevelService;

import java.util.List;

@Service
public class RiskLevelServiceImplement implements IRiskLevelService {

    @Autowired
    private IRiskLevelRepository rR;


    @Override
    public List<RiskLevel> list() {
        return rR.findAll();
    }
}
