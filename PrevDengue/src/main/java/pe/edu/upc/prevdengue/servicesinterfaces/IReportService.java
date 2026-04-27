package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.Report;

import java.util.List;
import java.util.Optional;

public interface IReportService {
    public List<Report> list();
    public Report insert(Report rP);
    public void delete(int idReport);
    public Optional<Report> listId(int idReport);
    public void update(Report rP);
    public List<String[]> getReportCountByHatcheryType();
    public List<String[]> getReportCountByStatus();
}
