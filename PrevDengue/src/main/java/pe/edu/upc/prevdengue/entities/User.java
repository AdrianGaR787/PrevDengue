package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "preferred_language", length = 50)
    private String preferredLanguage;

    @Column(name = "accumulated_points")
    private int accumulatedPoints;

    @Column(name = "biometrics_active")
    private boolean biometricsActive;

    public User() {}

    public User(int idUser, Role role, String name, String email, String phone, String passwordHash, String preferredLanguage, int accumulatedPoints, boolean biometricsActive) {
        this.idUser = idUser;
        this.role = role;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.preferredLanguage = preferredLanguage;
        this.accumulatedPoints = accumulatedPoints;
        this.biometricsActive = biometricsActive;
    }

    // Getters and Setters
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public int getAccumulatedPoints() { return accumulatedPoints; }
    public void setAccumulatedPoints(int accumulatedPoints) { this.accumulatedPoints = accumulatedPoints; }
    public boolean isBiometricsActive() { return biometricsActive; }
    public void setBiometricsActive(boolean biometricsActive) { this.biometricsActive = biometricsActive; }
}