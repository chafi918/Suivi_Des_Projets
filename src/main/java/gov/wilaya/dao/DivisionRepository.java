package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Division;
@Component
public interface DivisionRepository extends JpaRepository<Division, Long> {

	@Query("select division from Division division where division.libelleDivision = :x")
	public List<Division> findByName(@Param("x") String label);
}
