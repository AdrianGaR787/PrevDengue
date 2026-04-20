package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.SymptomReport;
import pe.edu.upc.prevdengue.repositories.ISymptomReportRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ISymptomReportService;

import java.util.List;
@Service
public class SymptomReportServiceImplement implements ISymptomReportService {

    @Autowired
    private ISymptomReportRepository srR;
    @Override
    public List<SymptomReport> list() {
        return srR.findAll();
    }
    @Override
    public void delete(int id) {
        srR.deleteById(id);
    }

}
