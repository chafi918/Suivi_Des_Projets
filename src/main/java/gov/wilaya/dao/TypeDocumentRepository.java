package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.TypeDocument;
@Component
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long>{
	@Query("select typeDocument from TypeDocument typeDocument where typeDocument.libelleType = :x")
	public List<TypeDocument> findByName(@Param("x") String label);
}
