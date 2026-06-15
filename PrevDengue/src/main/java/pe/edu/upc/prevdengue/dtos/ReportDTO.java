package pe.edu.upc.prevdengue.dtos;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDTO {

    private int idReport;
    private UserDTO user;
    private HatcheryTypeDTO hatcheryType;
    private ReportStatusDTO status;
    private double latitude;
    private double longitude;
    private String description;
    private LocalDateTime reportDate;
    @JsonProperty("anonymous")
    private boolean anonymous;
    private List<SymptomDTO> symptoms;
    private DistrictDTO district;


    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public HatcheryTypeDTO getHatcheryType() {
        return hatcheryType;
    }

    public void setHatcheryType(HatcheryTypeDTO hatcheryType) {
        this.hatcheryType = hatcheryType;
    }

    public ReportStatusDTO getStatus() {
        return status;
    }

    public void setStatus(ReportStatusDTO status) {
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
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<SymptomDTO> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<SymptomDTO> symptoms) {
        this.symptoms = symptoms;
    }
}
