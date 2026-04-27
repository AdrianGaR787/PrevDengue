package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.entities.InterventionCampaign;

import java.util.List;

@Repository
public interface IinterventionCampaignRepository extends JpaRepository<InterventionCampaign,Integer> {
    public List<InterventionCampaign> findByDistrictNameDistrictContainingIgnoreCase(String nameDistrict);
}
