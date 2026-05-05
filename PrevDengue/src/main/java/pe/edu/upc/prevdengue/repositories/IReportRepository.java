package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.dtos.ReportSymptomCountDTO;
import pe.edu.upc.prevdengue.entities.Report;

import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Report,Integer> {
    @Query("SELECT r.hatcheryType.nameHatchery, COUNT(r) FROM Report r GROUP BY r.hatcheryType.nameHatchery ORDER BY COUNT(r) DESC")
    public List<String[]> countReportsByHatcheryType();
    @Query("SELECT r.status.nameStatus, COUNT(r) FROM Report r GROUP BY r.status.nameStatus ORDER BY COUNT(r) DESC")
    public List<String[]> countReportsByStatus();
    @Query(value = "SELECT r.id_report AS idReporte, " +
            "r.description AS descripcion, " +
            "CAST(COUNT(sr.id_symptom) AS INTEGER) AS cantidadSintomas " +
            "FROM report r " +
            "JOIN symptom_report sr ON r.id_report = sr.id_report " +
            "GROUP BY r.id_report, r.description " +
            "ORDER BY cantidadSintomas DESC", nativeQuery = true)
    List<ReportSymptomCountDTO> getReportsWithMostSymptoms();
    @Query(value = "SELECT DISTINCT r.* FROM report r " +
            "JOIN symptom_report sr ON r.id_report = sr.id_report " +
            "JOIN symptom s ON sr.id_symptom = s.id_symptom " +
            "WHERE s.gravity_level >= 4", nativeQuery = true)
    List<Report> getHighRiskReports();
}
