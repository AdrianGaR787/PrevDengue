package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.dtos.CampaignByTypeDTO;
import pe.edu.upc.prevdengue.entities.InterventionCampaign;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IinterventionCampaignRepository extends JpaRepository<InterventionCampaign,Integer> {
    public List<InterventionCampaign> findByDistrictNameDistrictContainingIgnoreCase(String nameDistrict);
    @Query(value = "SELECT tipo_intervencion AS tipoIntervencion, " +
            "CAST(COUNT(id_campana) AS INTEGER) AS totalCampanas " +
            "FROM intervention_campaign " +
            "GROUP BY tipo_intervencion " +
            "ORDER BY totalCampanas DESC", nativeQuery = true)
    List<CampaignByTypeDTO> getCampaignsByType();

    @Query(value = "SELECT * FROM intervention_campaign " +
            "WHERE fecha_inicio <= :endDate AND fecha_fin >= :startDate", nativeQuery = true)
    List<InterventionCampaign> getCampaignsInTimeframe(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
