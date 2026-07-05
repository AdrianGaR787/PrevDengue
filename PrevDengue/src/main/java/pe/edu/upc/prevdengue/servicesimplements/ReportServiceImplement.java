package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Importa tus repositorios necesarios
import pe.edu.upc.prevdengue.repositories.*;
import pe.edu.upc.prevdengue.entities.*;
import pe.edu.upc.prevdengue.dtos.ReportSymptomCountDTO;
import pe.edu.upc.prevdengue.servicesinterfaces.IReportService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImplement implements IReportService {

    @Autowired
    private IReportRepository rR;
    @Autowired
    private IDistrictRepository dR;
    @Autowired
    private IHatcheryTypeRepository hR;
    @Autowired
    private IUserRepository uR;
    @Autowired
    private IReportStatusRepository sR;
    @Autowired
    private ISymptomRepository symRepo;


    @Override
    public List<Report> list() {
        return rR.findAll();
    }

    @Override
    public Report insert(Report rP) {
        System.out.println("🚨 --- INICIANDO GUARDADO DE REPORTE --- 🚨");

        if(rP.getReportDate() == null) {
            rP.setReportDate(LocalDateTime.now());
        }

        // 1. TRAMPA DEL DISTRITO
        if (rP.getDistrict() == null) {
            System.out.println("❌ ERROR: El objeto District llegó NULL al servicio. El controlador o el Mapper perdieron el dato.");
            throw new RuntimeException("Error en el mapeo: El distrito no llegó al servicio.");
        }

        System.out.println("🔍 ID de distrito recibido desde Angular: " + rP.getDistrict().getIdDistrict());

        if (rP.getDistrict().getIdDistrict() != 0) {
            District d = dR.findById(rP.getDistrict().getIdDistrict())
                    .orElseThrow(() -> new RuntimeException("❌ ERROR: No se encontró ningún distrito en la Base de Datos con el ID: " + rP.getDistrict().getIdDistrict()));
            rP.setDistrict(d);
            System.out.println("✅ Distrito encontrado y enlazado correctamente.");
        } else {
            throw new RuntimeException("❌ ERROR: El ID del distrito es 0.");
        }

        // 2. Asignar Tipo de Criadero
        if (rP.getHatcheryType() != null && rP.getHatcheryType().getIdHatcheryType() != 0) {
            int idBuscado = rP.getHatcheryType().getIdHatcheryType();
            HatcheryType h = hR.findById(idBuscado)
                    .orElseThrow(() -> new RuntimeException("❌ ERROR: No existe ningún Tipo de Criadero en la BD con el ID: " + idBuscado));
            rP.setHatcheryType(h);
        }

        // 3. Asignar Usuario (También le quitamos el orElse(null) por seguridad)
        if (rP.getUser() != null && rP.getUser().getIdUser() != 0) {
            int idBuscado = rP.getUser().getIdUser();
            User u = uR.findById(idBuscado)
                    .orElseThrow(() -> new RuntimeException("❌ ERROR: No existe ningún Usuario en la BD con el ID: " + idBuscado));
            rP.setUser(u);
        }

        // 4. Asignar Estado
        if (rP.getStatus() != null && rP.getStatus().getIdStatus() != 0) {
            int idBuscado = rP.getStatus().getIdStatus();
            ReportStatus s = sR.findById(idBuscado)
                    .orElseThrow(() -> new RuntimeException("❌ ERROR: No existe ningún Estado en la BD con el ID: " + idBuscado));
            rP.setStatus(s);
        }

        System.out.println("💾 Intentando guardar en PostgreSQL...");
        return rR.save(rP);
    }

    @Override
    public void delete(int idReport) {
        rR.deleteById(idReport);
    }

    @Override
    public Optional<Report> listId(int idReport) {
        return rR.findById(idReport);
    }

    @Override
    public void update(Report rP) {
        rR.save(rP);
    }

    @Override
    public List<String[]> getReportCountByHatcheryType() {
        return rR.countReportsByHatcheryType();
    }

    @Override
    public List<String[]> getReportCountByStatus() {
        return rR.countReportsByStatus();
    }

    @Override
    public List<ReportSymptomCountDTO> listReportsWithMostSymptoms() {
        return rR.getReportsWithMostSymptoms();
    }

    @Override
    public List<Report> listHighRiskReports() {
        return rR.getHighRiskReports();
    }

    @Override
    public List<Report> findByDistrict(int idDistrict) {
        return rR.findByDistrictId(idDistrict);
    }

    @Override
    public List<String[]> getReportCountByDistrict() {
        return rR.getReportCountByDistrict();
    }
}
