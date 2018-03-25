package gov.wilaya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.wilaya.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
