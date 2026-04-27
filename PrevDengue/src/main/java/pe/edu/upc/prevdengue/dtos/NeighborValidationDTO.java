package pe.edu.upc.prevdengue.dtos;

import java.time.LocalDateTime;

public class NeighborValidationDTO {
    private int idValidation;
    private ReportDTO report;
    private UserDTO validatorUser;
    private boolean isValid;
    private String comments;
    private LocalDateTime validationDate;

    public int getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(int idValidation) {
        this.idValidation = idValidation;
    }

    public ReportDTO getReport() {
        return report;
    }

    public void setReport(ReportDTO report) {
        this.report = report;
    }

    public UserDTO getValidatorUser() {
        return validatorUser;
    }

    public void setValidatorUser(UserDTO validatorUser) {
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
