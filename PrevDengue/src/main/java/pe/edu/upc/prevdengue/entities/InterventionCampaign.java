package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Intervention_Campaign")
public class InterventionCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampana;

    @ManyToOne
    @JoinColumn(name = "id_distrito", nullable = false)
    private District district;

    @Column(name = "tipo_intervencion", length = 50, nullable = false)
    private String tipoIntervencion;

    @Column(name = "fecha_inicio",nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    public InterventionCampaign() {
    }

    public InterventionCampaign(LocalDateTime fechaFin, LocalDateTime fechaInicio, String tipoIntervencion, District district, int idCampana) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.tipoIntervencion = tipoIntervencion;
        this.district = district;
        this.idCampana = idCampana;
    }

    public int getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(int idCampana) {
        this.idCampana = idCampana;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(String tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
