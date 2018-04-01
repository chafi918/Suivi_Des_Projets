package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Marche;

@Component
public interface MarcheRepository extends JpaRepository<Marche, Long> {
	@Query("select marche from Marche marche where marche.numeroMarche like %:x%")
	public Page<Marche> findByNumero(@Param("x") String label, Pageable p);
	
	@Query("select marche from Marche marche where marche.numeroMarche = :x")
	public List<Marche> searchByNumero(@Param("x") String label);

	@Query("select marche from Marche marche where marche.nature.idNature = :x")
	public Page<Marche> findByNature(@Param("x") Long idNature, Pageable p);
	
	@Query("select marche from Marche marche where marche.projet.idProjet = :x")
	public Page<Marche> findByProjet(@Param("x") Long idProjet, Pageable p);
	
	@Query("select marche from Marche marche where marche.entreprise.idEntreprise = :x")
	public Page<Marche> findByEntreprise(@Param("x") Long idEntreprise, Pageable p);
}