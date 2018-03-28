package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
 
import gov.wilaya.entities.Observation;
@Component
public interface ObservationRepository extends JpaRepository<Observation, Long> {
	
	@Query("select observation from Observation observation where observation.projet.idProjet = :x")
	public List<Observation> findByProjet(@Param("x") Long idProjet);

}
