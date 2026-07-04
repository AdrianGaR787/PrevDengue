package pe.edu.upc.prevdengue.dtos;

public class ReportSymptomCountDTO {
    private int idReporte;
    private String descripcion;
    private int cantidadSintomas;


    public ReportSymptomCountDTO() {
    }

    public ReportSymptomCountDTO(int idReporte, String descripcion, int cantidadSintomas) {
        this.idReporte = idReporte;
        this.descripcion = descripcion;
        this.cantidadSintomas = cantidadSintomas;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadSintomas() {
        return cantidadSintomas;
    }

    public void setCantidadSintomas(int cantidadSintomas) {
        this.cantidadSintomas = cantidadSintomas;
    }
}