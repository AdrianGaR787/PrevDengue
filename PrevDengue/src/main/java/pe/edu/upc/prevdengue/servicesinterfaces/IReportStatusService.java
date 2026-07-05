package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.entities.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface IReportStatusService {
    public List<ReportStatus> list();
    public ReportStatus insert(ReportStatus rS); // Para registrar y actualizar
    public void delete(int idStatus);
    public Optional<ReportStatus> listId(int idStatus);
    public void update(ReportStatus rS);

}
