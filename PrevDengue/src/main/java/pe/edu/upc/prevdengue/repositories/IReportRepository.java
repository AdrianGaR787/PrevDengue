package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.entities.Report;

import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Report,Integer> {
    @Query("SELECT r.hatcheryType.nameHatchery, COUNT(r) FROM Report r GROUP BY r.hatcheryType.nameHatchery ORDER BY COUNT(r) DESC")
    public List<String[]> countReportsByHatcheryType();
    @Query("SELECT r.status.nameStatus, COUNT(r) FROM Report r GROUP BY r.status.nameStatus ORDER BY COUNT(r) DESC")
    public List<String[]> countReportsByStatus();
}
