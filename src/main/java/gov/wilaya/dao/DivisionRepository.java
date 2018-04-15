package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Division;
@Component
public interface DivisionRepository extends JpaRepository<Division, Long> {
	//used to avoid insert of the same division twice
	@Query("select division from Division division where division.libelleDivision like %:x%")
	public List<Division> findByName(@Param("x") String label);
	
	@Query("select division from Division division where division.libelleDivision like %:x%")
	public Page<Division> searchByName(@Param("x") String label, Pageable p);
}
