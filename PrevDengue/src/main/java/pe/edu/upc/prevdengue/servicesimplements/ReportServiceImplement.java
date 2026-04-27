package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.Report;
import pe.edu.upc.prevdengue.repositories.IReportRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;

import java.time.LocalDateTime;
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
    public Report insert(Report rP) {
        if(rP.getReportDate() == null) {
            rP.setReportDate(LocalDateTime.now());
        }
        return rR.save(rP);
    }

    @Override
    public void delete(int idReport) {
        rR.deleteById(idReport);
    }

    @Override
    public Optional<Report> listId(int idReport) {
        return rR.findById(idReport);
    }

    @Override
    public void update(Report rP) {
        rR.save(rP);
    }

    @Override
    public List<String[]> getReportCountByHatcheryType() {
        return rR.countReportsByHatcheryType();
    }
}
