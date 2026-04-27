package pe.edu.upc.prevdengue.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.entities.CleaningEvent;

@Repository
public interface ICleaningEventRepository extends JpaRepository<CleaningEvent,Integer> {
}
