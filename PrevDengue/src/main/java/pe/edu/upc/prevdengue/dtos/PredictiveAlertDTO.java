package pe.edu.upc.prevdengue.dtos;

import java.time.LocalDateTime;

public class PredictiveAlertDTO {
    private int idAlert;
    private DistrictDTO district;   // ModelMapper mapeará el ID del Distrito
    private RiskLevelDTO riskLevel; // ModelMapper mapeará el ID del Riesgo
    private String description;
    private LocalDateTime alertDate;
    private boolean isActive;

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public RiskLevelDTO getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevelDTO riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
