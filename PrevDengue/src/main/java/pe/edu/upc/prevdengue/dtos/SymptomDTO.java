package pe.edu.upc.prevdengue.dtos;

public class SymptomDTO {
    private int id_Symptom;
    private String name_Symptom;
    private int gravity_level;

    public int getId_Symptom() {
        return id_Symptom;
    }

    public void setId_Symptom(int id_Symptom) {
        this.id_Symptom = id_Symptom;
    }

    public String getName_Symptom() {
        return name_Symptom;
    }

    public void setName_Symptom(String name_Symptom) {
        this.name_Symptom = name_Symptom;
    }

    public int getGravity_level() {
        return gravity_level;
    }

    public void setGravity_level(int gravity_level) {
        this.gravity_level = gravity_level;
    }
}
