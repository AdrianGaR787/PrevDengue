package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name="ReportStatus")
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatus;
    @Column(name = "nameStatus" ,length = 100 ,nullable = false)
    private String nameStatus;

    public ReportStatus() {
    }

    public ReportStatus(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
