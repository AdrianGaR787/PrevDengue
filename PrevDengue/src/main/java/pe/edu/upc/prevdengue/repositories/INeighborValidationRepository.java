package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.entities.NeighborValidation;

@Repository
public interface INeighborValidationRepository extends JpaRepository<NeighborValidation,Integer> {
    NeighborValidation findByReportIdReportAndValidatorUserIdUser(int idReport, int idUser);

    // 🚀 2. UTILIDAD: Contar cuántas personas han confirmado que el reporte es REAL
    @Query("SELECT COUNT(nv) FROM NeighborValidation nv WHERE nv.report.idReport = :idReport AND nv.isValid = true")
    int countValidacionesPositivas(@Param("idReport") int idReport);
}
