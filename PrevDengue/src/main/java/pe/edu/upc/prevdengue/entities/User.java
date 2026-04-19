package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name="nameUser",length = 30 ,nullable = false)
    private String nameUser;
    @Column(name="emailUser",length = 40 ,nullable = false)
    private String emailUser;
    @Column(name = "userPhone",length = 15 ,nullable = false)
    private String userPhone;
    @Column(name = "hashPassword",length = 30 ,nullable = false)
    private String hashPassword;
    @Column(name = "preferredLanguage",length = 15, nullable = false)
    private String preferredLanguage;
    @Column(name = "totalPoints",nullable = false)
    private int totalPoints;
    @Column(name = "activeBiometrics",nullable = false)
    private boolean activeBiometrics;

    @ManyToOne
    @JoinColumn(name ="idRole",nullable = false)
    private Role role;

    public User(int idUser, String nameUser, String emailUser, String userPhone, String hashPassword, String preferredLanguage, int totalPoints, boolean activeBiometrics) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.userPhone = userPhone;
        this.hashPassword = hashPassword;
        this.preferredLanguage = preferredLanguage;
        this.totalPoints = totalPoints;
        this.activeBiometrics = activeBiometrics;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public boolean isActiveBiometrics() {
        return activeBiometrics;
    }

    public void setActiveBiometrics(boolean activeBiometrics) {
        this.activeBiometrics = activeBiometrics;

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
