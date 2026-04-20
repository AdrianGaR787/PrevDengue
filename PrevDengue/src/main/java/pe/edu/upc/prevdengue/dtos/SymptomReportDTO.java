package pe.edu.upc.prevdengue.dtos;

import pe.edu.upc.prevdengue.entities.Report;
import pe.edu.upc.prevdengue.entities.Symptom;

public class SymptomReportDTO {
    private int idSymptomReport;
    private Report report;
    private Symptom symptom;

    public int getIdSymptomReport() {
        return idSymptomReport;
    }

    public void setIdSymptomReport(int idSymptomReport) {
        this.idSymptomReport = idSymptomReport;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
