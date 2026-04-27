package pe.edu.upc.prevdengue.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NeighborValidation")
public class NeighborValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idValidation;

    @ManyToOne
    @JoinColumn(name = "idReport",nullable = false)
    private Report report;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User validatorUser;

    @Column(name = "isValid",nullable = false)
    private boolean isValid;

    @Column(name = "Comments",length = 200)
    private String comments;

    @Column(name="validationDate",nullable = false)
    private LocalDateTime validationDate;

    public NeighborValidation() {
    }

    public NeighborValidation(int idValidation, Report report, User validatorUser, boolean isValid, String comments, LocalDateTime validationDate) {
        this.idValidation = idValidation;
        this.report = report;
        this.validatorUser = validatorUser;
        this.isValid = isValid;
        this.comments = comments;
        this.validationDate = validationDate;
    }

    public int getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(int idValidation) {
        this.idValidation = idValidation;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public User getValidatorUser() {
        return validatorUser;
    }

    public void setValidatorUser(User validatorUser) {
        this.validatorUser = validatorUser;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDateTime validationDate) {
        this.validationDate = validationDate;
    }
}
