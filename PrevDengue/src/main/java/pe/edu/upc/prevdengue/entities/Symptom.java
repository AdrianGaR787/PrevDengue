package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table (name="Symptom")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSymptom;
    @Column(name = "nameSymptom",length = 30,nullable = false)
    private String nameSymptom;
    @Column(name = "gravityLevel",nullable = false)
    private int gravityLevel;

    public Symptom() {
    }

    public Symptom(int idSymptom, String nameSymptom, int gravityLevel) {
        this.idSymptom = idSymptom;
        this.nameSymptom = nameSymptom;
        this.gravityLevel = gravityLevel;
    }

    public int getIdSymptom() {
        return idSymptom;
    }

    public void setIdSymptom(int idSymptom) {
        this.idSymptom = idSymptom;
    }

    public String getNameSymptom() {
        return nameSymptom;
    }

    public void setNameSymptom(String nameSymptom) {
        this.nameSymptom = nameSymptom;
    }

    public int getGravityLevel() {
        return gravityLevel;
    }

    public void setGravityLevel(int gravityLevel) {
        this.gravityLevel = gravityLevel;
    }
}
