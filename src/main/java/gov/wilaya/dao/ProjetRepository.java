package gov.wilaya.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Projet;

@Component
public interface ProjetRepository extends JpaRepository<Projet, Long> {
	@Query("select p from Projet p where p.commune like :x")
	public List<Projet> chercherParCommune(@Param("x")String commune);
	
	@Query("select p from Projet p where p.secteur.idSecteur = :x")
	public List<Projet> chercherParSecteur(@Param("x")Long idSecteur);
	/*@Query("select p from Projet p where p.intitule = :x")
	public Projet chercherParIntitule(@Param("x")String intitule);
	public Page<Projet> chercherParCommune(String commune,Pageable p);
	public Page<Projet> chercherParStatut(Statut statut,Pageable p);*/

}
