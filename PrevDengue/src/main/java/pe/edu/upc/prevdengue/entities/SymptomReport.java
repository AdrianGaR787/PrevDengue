package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name="SymptomReport")
public class SymptomReport {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idSymptomReport;

    @ManyToOne
    @JoinColumn(name="idReport",nullable = false)
    private Report report;
    @ManyToOne
    @JoinColumn(name= "idSymptom",nullable = false)
    private Symptom symptom;

    public SymptomReport() {
    }

    public SymptomReport(Report report, Symptom symptom) {
        this.report = report;
        this.symptom = symptom;
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
