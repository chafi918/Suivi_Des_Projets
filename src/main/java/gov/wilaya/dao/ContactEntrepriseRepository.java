package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.ContactEntreprise;
import gov.wilaya.entities.Marche;

@Component
public interface ContactEntrepriseRepository extends JpaRepository<ContactEntreprise, Long> {
	@Query("select contact from ContactEntreprise contact where contact.nomContact like %:x%")
	public Page<ContactEntreprise> findByName(@Param("x") String nom, Pageable p);
	
	
	@Query("select contact from ContactEntreprise contact where contact.entreprise.idEntreprise = :x")
	public Page<ContactEntreprise> findByEntreprise(@Param("x") Long id, Pageable p);

	@Query("select contact from ContactEntreprise contact where contact.nomContact = :name")
	public List<ContactEntreprise> sameContact(@Param("name") String name);

	@Query("select contact from ContactEntreprise contact where contact.nomContact like %:name% and contact.entreprise.idEntreprise = :idEntreprise")
	public Page<ContactEntreprise> findByNameAndEntreprise(@Param("name")String name,@Param("idEntreprise") Long idEntreprise, Pageable pageRequest);

}
