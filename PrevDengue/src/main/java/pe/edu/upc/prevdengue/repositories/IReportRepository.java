package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.prevdengue.entities.Report;

public interface IReportRepository extends JpaRepository<Report,Integer> {
}
