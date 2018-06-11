package gov.wilaya.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Province;
@Component
public interface ProvinceRepository extends JpaRepository<Province, Long> {
	@Query("select province from Province province where province.libelleProvince like %:x%")
	public Page<Province> searchByName(@Param("x") String label, Pageable p);
	
	@Query("select province from Province province where province.libelleProvince = :x")
	public Province findByName(@Param("x") String label);


}
