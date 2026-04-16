package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table (name="HatcheryType")
public class HatcheryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHatcheryType;
    @Column(name ="nameHatchery" ,length = 40 ,nullable = false)
    private String nameHatchery;

    public HatcheryType() {
    }

    public HatcheryType(int idHatcheryType, String nameHatchery) {
        this.idHatcheryType = idHatcheryType;
        this.nameHatchery = nameHatchery;
    }

    public int getIdHatcheryType() {
        return idHatcheryType;
    }

    public void setIdHatcheryType(int idHatcheryType) {
        this.idHatcheryType = idHatcheryType;
    }

    public String getNameHatchery() {
        return nameHatchery;
    }

    public void setNameHatchery(String nameHatchery) {
        this.nameHatchery = nameHatchery;
    }
}
