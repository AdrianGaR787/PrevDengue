package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "District")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDistrict;
    @Column(name ="nameDistrict" ,length = 100 ,nullable = false)
    private String nameDistrict;

    public District() {
    }

    public District(int idDistrict, String nameDistrict) {
        this.idDistrict = idDistrict;
        this.nameDistrict = nameDistrict;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }
}
