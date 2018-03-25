package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.wilaya.entities.Document;
import gov.wilaya.entities.TypeDocument;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	//public List<Document> chercherParType(TypeDocument type);

}
