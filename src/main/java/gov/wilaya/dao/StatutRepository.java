package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Statut;

@Component
public interface StatutRepository extends JpaRepository<Statut, Long>{

	@Query("select statut from Statut statut where statut.libelleStatut = :x")
	public Statut searchByName(@Param("x") String label);
	
	@Query("select statut from Statut statut where statut.libelleStatut like %:x%")
	public Page<Statut> findByName(@Param("x") String label, Pageable p);
}
