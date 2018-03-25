package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.TypeDocument;
import gov.wilaya.entities.Utilisateur;
@Component
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.loginUser = :x")
	public List<TypeDocument> findByName(@Param("x") String label);
}
