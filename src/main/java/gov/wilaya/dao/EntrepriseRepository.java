package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Entreprise;
@Component
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	@Query("select e from Entreprise e where e.nomEntreprise like %:x%")
	public Page<Entreprise> findByName(@Param("x") String nom, Pageable p);
	
	@Query("select e from Entreprise e where e.nomEntreprise = :x")
	public List<Entreprise> searchByName(@Param("x") String nom);

}
