package pe.edu.upc.prevdengue.dtos;

public class SymptomDTO {
    private int idSymptom;
    private String nameSymptom;
    private int gravityLevel;

    public int getId_Symptom() {
        return idSymptom;
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
