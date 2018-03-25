package gov.wilaya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.wilaya.entities.Observation;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

}
