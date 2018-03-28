package gov.wilaya.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.wilaya.entities.ContactEntreprise;

public interface ContactEntrepriseRepository extends JpaRepository<ContactEntreprise, Long> {
	@Query("select contact from ContactEntreprise contact where contact.nomContact = :x")
	public List<ContactEntreprise> findByName(@Param("x") String nom);

}
