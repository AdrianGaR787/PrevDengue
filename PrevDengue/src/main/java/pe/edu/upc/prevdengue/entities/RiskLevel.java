package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name="RiskLevel")
public class RiskLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRiskLevel;
    @Column(name = "nameRiskLevel",length = 30 ,nullable = false)
    private String nameRiskLevel;
    @Column(name = "colorRiskLevel",length = 30,nullable = false)
    private String colorRiskLevel;

    public RiskLevel() {
    }

    public RiskLevel(int idRiskLevel, String nameRiskLevel, String colorRiskLevel) {
        this.idRiskLevel = idRiskLevel;
        this.nameRiskLevel = nameRiskLevel;
        this.colorRiskLevel = colorRiskLevel;
    }

    public int getIdRiskLevel() {
        return idRiskLevel;
    }

    public void setIdRiskLevel(int idRiskLevel) {
        this.idRiskLevel = idRiskLevel;
    }

    public String getNameRiskLevel() {
        return nameRiskLevel;
    }

    public void setNameRiskLevel(String nameRiskLevel) {
        this.nameRiskLevel = nameRiskLevel;
    }

    public String getColorRiskLevel() {
        return colorRiskLevel;
    }

    public void setColorRiskLevel(String colorRiskLevel) {
        this.colorRiskLevel = colorRiskLevel;
    }
}
