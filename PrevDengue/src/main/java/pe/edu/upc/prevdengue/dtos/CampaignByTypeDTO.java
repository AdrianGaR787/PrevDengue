package pe.edu.upc.prevdengue.dtos;

public class CampaignByTypeDTO {
    private String tipoIntervencion;
    private int totalCampanas;

    // Constructor vacío (necesario para serialización/deserialización)
    public CampaignByTypeDTO() {
    }

    // Constructor con parámetros (necesario para JPA/Hibernate)
    public CampaignByTypeDTO(String tipoIntervencion, int totalCampanas) {
        this.tipoIntervencion = tipoIntervencion;
        this.totalCampanas = totalCampanas;
    }

    public String getTipoIntervencion() {
        return tipoIntervencion;
    }

    public void setTipoIntervencion(String tipoIntervencion) {
        this.tipoIntervencion = tipoIntervencion;
    }

    public int getTotalCampanas() {
        return totalCampanas;
    }

    public void setTotalCampanas(int totalCampanas) {
        this.totalCampanas = totalCampanas;
    }
}
