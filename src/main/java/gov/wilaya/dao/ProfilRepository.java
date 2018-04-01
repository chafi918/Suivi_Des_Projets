package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Profil;
@Component
public interface ProfilRepository extends JpaRepository<Profil, Long> {
	@Query("select profil from Profil profil where profil.libelleProfil like %:x%")
	public Page<Profil> findByName(@Param("x") String label, Pageable p);
	
	@Query("select profil from Profil profil where profil.libelleProfil = :x")
	public List<Profil> searchByName(@Param("x") String label);
		
}
