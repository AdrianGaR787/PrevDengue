package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.Report;

import java.util.List;
import java.util.Optional;

public interface IReportService {
    public List<Report> list();
    public Report insert(Report r);
    public Optional<Report> listId(int id);
    public void update(Report r);
    public void delete(int id);
}
