package pe.edu.upc.prevdengue.dtos;
import pe.edu.upc.prevdengue.entities.HatcheryType;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.entities.User;

import java.time.LocalDate;

public class ReportDTO {
    private int idReport;
    private double latitude;
    private double longitude;
    private String direction;
    private String description;
    private String photoUrl;
    private boolean isAnonymous;
    private LocalDate reportDate;
    private User user;
    private HatcheryType hatcheryType;
    private ReportStatus reportStatus;

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
