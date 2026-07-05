package pe.edu.upc.prevdengue.servicesinterfaces;

import pe.edu.upc.prevdengue.entities.RiskLevel;

import java.util.List;
import java.util.Optional;

public interface IRiskLevelService {
    public List<RiskLevel> list();
    public RiskLevel insert(RiskLevel rN);
    public void delete(int idRiskLevel);
    public Optional<RiskLevel> listId(int idRiskLevel);
    public RiskLevel update(RiskLevel rN);
}
