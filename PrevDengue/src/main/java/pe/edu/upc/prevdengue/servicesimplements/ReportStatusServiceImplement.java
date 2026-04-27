package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.repositories.IReportStatusRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportStatusServiceImplement implements IReportStatusService {

    @Autowired
    private IReportStatusRepository eR;

    @Override
    public List<ReportStatus> list() {
        return eR.findAll();
    }

    @Override
    public ReportStatus insert(ReportStatus rS) {
        return eR.save(rS);
    }

    @Override
    public void delete(int idStatus) {
        eR.deleteById(idStatus);
    }

    @Override
    public Optional<ReportStatus> listId(int idStatus) {
        return eR.findById(idStatus);
    }

    @Override
    public void update(ReportStatus rS) {
        eR.save(rS);
    }
}