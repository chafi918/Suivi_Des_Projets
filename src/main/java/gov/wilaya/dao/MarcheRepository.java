package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Marche;

@Component
public interface MarcheRepository extends JpaRepository<Marche, Long> {
	@Query("select marche from Marche marche where marche.numeroMarche = :x")
	public List<Marche> findByName(@Param("x") String label);
	

	@Query("select marche from Marche marche where marche.nature.idNature = :x")
	public List<Marche> findByNature(@Param("x") Long idNature);
}