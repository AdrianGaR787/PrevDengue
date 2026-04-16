package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.repositories.IReportStatusRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportStatusService;

import java.util.List;

@Service
public class ReportStatusServiceImplement implements IReportStatusService {

    @Autowired
    private IReportStatusRepository eR;


    @Override
    public List<ReportStatus> list() {
        return eR.findAll();
    }
}
