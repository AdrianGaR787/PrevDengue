package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.SymptomReport;

import java.util.List;

public interface ISymptomReportService {
    public List<SymptomReport> list();
    public void delete(int id);
}
