package gov.wilaya.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.ContactEntreprise;
@Component
public interface ContactEntrepriseRepository extends JpaRepository<ContactEntreprise, Long> {
	@Query("select contact from ContactEntreprise contact where contact.nomContact = :x")
	public List<ContactEntreprise> findByName(@Param("x") String nom);
	/*@Query("select contact from contactEntreprise contact where contact.entreprise.idEntreprise = :x")
	public List<ContactEntreprise> findContacts(Long id);*/
}
