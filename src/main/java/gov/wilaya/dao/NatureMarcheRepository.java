package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Division;
import gov.wilaya.entities.NatureMarche;

@Component
public interface NatureMarcheRepository extends JpaRepository<NatureMarche, Long> {
	@Query("select natureMarche from NatureMarche natureMarche where natureMarche.libelleNature like %:x%")
	public Page<NatureMarche> findByName(@Param("x") String label, Pageable p);
	
	@Query("select natureMarche from NatureMarche natureMarche where natureMarche.libelleNature = :x")
	public List<NatureMarche> searchByName(@Param("x") String label);
	
	@Query("select natureMarche from NatureMarche natureMarche where natureMarche.libelleNature like %:x%")
	public Page<Division> searchByName(@Param("x") String label, Pageable p);

}
