package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Projet;

@Component
public interface ProjetRepository extends JpaRepository<Projet, Long> {
	@Query("select p from Projet p where p.commune.libelleCommune like %:x%")
	public Page<Projet> chercherParCommune(@Param("x")String commune, Pageable p);

	@Query("select p from Projet p where p.intitule like %:x%")
	public Page<Projet> chercherParIntitule(@Param("x")String intitule, Pageable p);
	
	@Query("select p from Projet p where p.intitule = :x")
	public List<Projet> searchParIntitule(@Param("x")String intitule);
	
	@Query("select p from Projet p where p.chargeDuProjet like %:x%")
	public Page<Projet> chercherParChargeDuProjet(@Param("x")String chargeDuProjet, Pageable p);
	
	@Query("select p from Projet p where p.estProjetRoyal = 1")
	public Page<Projet> chercherProjetRoyal(Pageable p);
	
	@Query("select p from Projet p where p.estMasque = 1")
	public Page<Projet> chercherProjetMasque(Pageable p);
	
	@Query("select p from Projet p where p.secteur.libelleSecteur like %:x%")
	public Page<Projet> chercherParSecteur(@Param("x")String libelleSecteur, Pageable p);
	
	@Query("select p from Projet p where p.statut.libelleStatut like %:x%")
	public Page<Projet> chercherParStatut(@Param("x")String libelleStatut ,Pageable p);
	
	//TODO: get a method to calculate the rates of progress for projects ?s

}
