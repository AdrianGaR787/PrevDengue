package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.InterventionCampaign;
import pe.edu.upc.prevdengue.entities.ReportStatus;
import pe.edu.upc.prevdengue.repositories.IinterventionCampaignRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.IInterventionCampaignService;

import java.util.List;
import java.util.Optional;

@Service
public class InterventionCampaignServiceImplement implements IInterventionCampaignService {
    @Autowired
    private IinterventionCampaignRepository iC;

    @Override
    public List<InterventionCampaign> list() {
        return iC.findAll();
    }

    @Override
    public InterventionCampaign insert(InterventionCampaign c) {
        return iC.save(c);
    }

    @Override
    public void delete(int idCampana) {
        iC.deleteById(idCampana);
    }

    @Override
    public Optional<InterventionCampaign> listId(int idCampana) {
        return iC.findById(idCampana);
    }

    @Override
    public void update(InterventionCampaign c) {
        iC.save(c);
    }

    @Override
    public List<InterventionCampaign> searchByDistrictName(String districtName) {
        return iC.findByDistrictNameDistrictContainingIgnoreCase(districtName);
    }
}
