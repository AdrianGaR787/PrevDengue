package pe.edu.upc.prevdengue.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PredictiveAlert")
public class PredictiveAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlert;

    @ManyToOne
    @JoinColumn(name = "id_district",nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "id_riskLevel",nullable = false)
    private RiskLevel riskLevel;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "alertDate", nullable = false)
    private LocalDateTime alertDate;

    @Column(name = "isActive")
    private boolean isActive;

    public PredictiveAlert() {
    }

    public PredictiveAlert(int idAlert, District district, RiskLevel riskLevel, String description, LocalDateTime alertDate, boolean isActive) {
        this.idAlert = idAlert;
        this.district = district;
        this.riskLevel = riskLevel;
        this.description = description;
        this.alertDate = alertDate;
        this.isActive = isActive;
    }

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
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
