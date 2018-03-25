package gov.wilaya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.wilaya.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
