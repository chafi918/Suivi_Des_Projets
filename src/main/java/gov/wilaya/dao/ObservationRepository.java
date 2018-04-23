package gov.wilaya.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Observation;
@Component
public interface ObservationRepository extends JpaRepository<Observation, Long> {
	
	/*@Query("select observation from Observation observation where observation.projet.idProjet = :x")
	public Page<Observation> findByProjet(@Param("x") Long idProjet, Pageable p);*/
	
	@Query("select observation from Observation observation where observation.nomObservant like %:x%")
	public Page<Observation> findByObservant(@Param("x") String observant, Pageable p);

}
