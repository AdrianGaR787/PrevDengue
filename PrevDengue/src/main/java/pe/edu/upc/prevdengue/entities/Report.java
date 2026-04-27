package pe.edu.upc.prevdengue.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReport;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "IdHatcheryType",nullable = false)
    private HatcheryType hatcheryType;

    @ManyToOne
    @JoinColumn(name = "idStatus",nullable = false)
    private ReportStatus status;

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @Column(name = "longitude",nullable = false)
    private double longitude;

    @Column(name = "description",length = 100)
    private String description;

    @Column(name = "reportDate",nullable = false)
    private LocalDateTime reportDate;

    @Column(name = "isAnonymous")
    private boolean isAnonymous;

    @ManyToMany
    @JoinTable(
            name = "SymptomReport",
            joinColumns = @JoinColumn(name = "idReport"),
            inverseJoinColumns = @JoinColumn(name = "idSymptom")
    )
    private List<Symptom> symptoms;

    public Report() {
    }

    public Report(int idReport, User user, HatcheryType hatcheryType, ReportStatus status, double latitude, double longitude, String description, LocalDateTime reportDate, boolean isAnonymous, List<Symptom> symptoms) {
        this.idReport = idReport;
        this.user = user;
        this.hatcheryType = hatcheryType;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.reportDate = reportDate;
        this.isAnonymous = isAnonymous;
        this.symptoms = symptoms;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HatcheryType getHatcheryType() {
        return hatcheryType;
    }

    public void setHatcheryType(HatcheryType hatcheryType) {
        this.hatcheryType = hatcheryType;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
