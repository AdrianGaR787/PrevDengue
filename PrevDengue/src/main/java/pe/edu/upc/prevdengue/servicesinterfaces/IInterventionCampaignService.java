package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.InterventionCampaign;
import pe.edu.upc.prevdengue.entities.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface IInterventionCampaignService {
    public List<InterventionCampaign> list();
    public InterventionCampaign insert(InterventionCampaign c);
    public void delete(int idCampana);
    public Optional<InterventionCampaign> listId(int idCampana);
    public void update(InterventionCampaign c);
    public List<InterventionCampaign> searchByDistrictName(String districtName);
}
