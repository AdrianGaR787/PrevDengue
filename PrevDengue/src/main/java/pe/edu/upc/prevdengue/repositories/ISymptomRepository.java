package pe.edu.upc.prevdengue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.prevdengue.dtos.FrequentSymptomDTO;
import pe.edu.upc.prevdengue.entities.Symptom;

import java.util.List;

@Repository
public interface ISymptomRepository extends JpaRepository<Symptom,Integer> {
    @Query(value = "SELECT s.name_symptom AS sintoma, " +
            "CAST(COUNT(sr.id_symptom) AS INTEGER) AS cantidad " +
            "FROM symptom s " +
            "JOIN symptom_report sr ON s.id_symptom = sr.id_symptom " +
            "GROUP BY s.name_symptom " +
            "ORDER BY cantidad DESC", nativeQuery = true)
    List<FrequentSymptomDTO> getMostFrequentSymptoms();
}
