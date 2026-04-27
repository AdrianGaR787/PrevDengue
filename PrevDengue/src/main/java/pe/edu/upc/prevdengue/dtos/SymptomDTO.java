package pe.edu.upc.prevdengue.dtos;

public class SymptomDTO {
    private int idSymptom;
    private String nameSymptom;
    private int gravitylevel;

    public String getNameSymptom() {
        return nameSymptom;
    }

    public void setNameSymptom(String nameSymptom) {
        this.nameSymptom = nameSymptom;
    }

    public int getIdSymptom() {
        return idSymptom;
    }

    public void setIdSymptom(int idSymptom) {
        this.idSymptom = idSymptom;
    }

    public int getGravitylevel() {
        return gravitylevel;
    }

    public void setGravitylevel(int gravitylevel) {
        this.gravitylevel = gravitylevel;
    }
}
