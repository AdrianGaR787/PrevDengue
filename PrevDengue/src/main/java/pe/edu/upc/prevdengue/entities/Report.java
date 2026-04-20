package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="Report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReport;
    @Column(name="latitude", nullable = false)
    private double latitude;
    @Column(name="longitude", nullable = false)
    private double longitude;
    @Column(name="direction",length = 50 ,nullable = false)
    private String direction;
    @Column(name="",length = 70,nullable = false)
    private String description;
    @Column(name = "photoUrl",length = 30,nullable = false)
    private String photoUrl;
    @Column(name="isAnonymous",nullable = false)
    private boolean isAnonymous;
    @Column(name = "reportDate",nullable = false)
    private LocalDate reportDate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idHatcheryType")
    private HatcheryType hatcheryType;
    @ManyToOne
    @JoinColumn(name= "idReportStatus")
    private ReportStatus reportStatus;

    public Report() {
    }

    public Report(int idReport, double latitude, double longitude, String direction, String description, String photoUrl, boolean isAnonymous, LocalDate reportDate, User user, HatcheryType hatcheryType, ReportStatus reportStatus) {
        this.idReport = idReport;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
        this.description = description;
        this.photoUrl = photoUrl;
        this.isAnonymous = isAnonymous;
        this.reportDate = reportDate;
        this.user = user;
        this.hatcheryType = hatcheryType;
        this.reportStatus = reportStatus;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
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

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
}
