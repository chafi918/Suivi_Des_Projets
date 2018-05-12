package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Utilisateur;

@Component
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.loginUser = :x")
	public List<Utilisateur> searchByLogin(@Param("x") String label);
	
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.nomUser like %:x% "
			+ "or utilisateur.prenomUser like %:x% "
			+ "or utilisateur.loginUser like %:x% ")
	public Page<Utilisateur> findByMotCle(@Param("x") String label, Pageable p);
	
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.estActive = 1")
	public Page<Utilisateur> findActiveUsers(Pageable p);
	
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.division.idDivision = :x")
	public Page<Utilisateur> findByDivision(@Param("x") Long idDivision, Pageable p);
	
	@Query("select utilisateur from Utilisateur utilisateur where utilisateur.loginUser = :x")
	public Utilisateur findByUserName(@Param("x") String username);
	
}
