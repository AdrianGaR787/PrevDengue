package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Report;
import pe.edu.upc.prevdengue.repositories.IReportRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImplement implements IReportService {

    @Autowired
    private IReportRepository rR;
    @Override
    public List<Report> list() {
        return rR.findAll();
    }

    @Override
    public Report insert(Report r) {
        return rR.save(r);
    }

    @Override
    public Optional<Report> listId(int id) {
        return rR.findById(id);
    }

    @Override
    public void update(Report r) {
        rR.save(r);
    }
    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }
}
