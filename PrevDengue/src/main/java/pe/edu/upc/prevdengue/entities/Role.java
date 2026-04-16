package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    @Column(name="nameRole",length = 30,nullable = false)
    private String nameRole;
    @Column(name ="description" ,length =150 ,nullable = false)
    private String description;

    public Role() {
    }

    public Role(int idRole, String nameRole, String description) {
        this.idRole = idRole;
        this.nameRole = nameRole;
        this.description = description;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

