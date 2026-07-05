package pe.edu.upc.prevdengue.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.entities.CleaningEvent;
import pe.edu.upc.prevdengue.repositories.ICleaningEventRepository;
import pe.edu.upc.prevdengue.servicesinterfaces.ICleaningEventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CleaningEventServiceImplement implements ICleaningEventService {

    @Autowired
    private ICleaningEventRepository cleaningEventRepository;

    @Override
    public List<CleaningEvent> list() {
        return cleaningEventRepository.findAll();
    }

    @Override
    public CleaningEvent insert(CleaningEvent cE) {
        // Business logic: When created, status is always 'Programado' (Scheduled)
        if (cE.getIdEvent() == 0 && cE.getStatus() == null) {
            cE.setStatus("Programado");
        }
        return cleaningEventRepository.save(cE);
    }

    @Override
    public void delete(int idEvent) {
        cleaningEventRepository.deleteById(idEvent);

    }

    @Override
    public Optional<CleaningEvent> listId(int idEvent) {
        return cleaningEventRepository.findById(idEvent);
    }

    @Override
    public void update(CleaningEvent cE) {
        cleaningEventRepository.save(cE);
    }

    @Scheduled(cron = "0 * * * * *") // Se ejecuta cada 1 minuto
    public void actualizarEstadosDeEventos() {
        List<CleaningEvent> todos = cleaningEventRepository.findAll();
        LocalDateTime ahora = LocalDateTime.now();

        for (CleaningEvent evento : todos) {
            LocalDateTime fechaInicio = evento.getEventDate();
            LocalDateTime fechaFin = fechaInicio.plusDays(1); // El evento dura 1 día

            // Si ya pasó 1 día desde el inicio -> FINALIZADO
            if (ahora.isAfter(fechaFin) && !"FINALIZADO".equals(evento.getStatus())) {
                evento.setStatus("FINALIZADO");
                cleaningEventRepository.save(evento);
            }
            // Si ya empezó pero no ha pasado 1 día -> EN PROCESO
            else if (ahora.isAfter(fechaInicio) && ahora.isBefore(fechaFin) && !"EN PROCESO".equals(evento.getStatus()) && !"LLENO".equals(evento.getStatus())) {
                evento.setStatus("EN PROCESO");
                cleaningEventRepository.save(evento);
            }
        }
    }
}