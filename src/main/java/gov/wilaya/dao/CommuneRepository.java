package gov.wilaya.dao;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Commune;

@Component
public interface CommuneRepository extends JpaRepository<Commune, Long> {
	@Query("select commune from Commune commune where commune.libelleCommune like %:x%")
	public Page<Commune> searchByName(@Param("x") String label, Pageable p);
	
	@Query("select commune from Commune commune where commune.libelleCommune = :x")
	public Commune findByName(@Param("x") String label);

}
