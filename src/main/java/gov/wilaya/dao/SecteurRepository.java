package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Secteur;

@Component
public interface SecteurRepository extends JpaRepository<Secteur, Long> {

	@Query("select secteur from Secteur secteur where secteur.libelleSecteur like %:x%")
	public Page<Secteur> searchByName(@Param("x") String label, Pageable p);
	
	@Query("select secteur from Secteur secteur where secteur.libelleSecteur = :x")
	public Secteur findByName(@Param("x") String label);
}
