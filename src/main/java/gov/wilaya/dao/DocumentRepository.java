package gov.wilaya.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import gov.wilaya.entities.Document;
@Component
public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("select document from Document document where document.nomDocument like %:x%")
	public Page<Document> findByName(@Param("x") String nomDocument, Pageable p);

	@Query("select document from Document document where document.nomDocument like :x ")
	public List<Document> searchByName(@Param("x") String nomDocument);
	
	@Query("select document from Document document where document.type.libelleType like %:x% ")
	public Page<Document> chercherParType(@Param("x") String typeDocument, Pageable p);

	/*@Query("select document from Document document where document.projet.idProjet = :x")
	public Page<Document> chercherParProjet(@Param("x") Long idProjet, Pageable p);
*/
	@Query("select document from Document document where document.chargeurDocument like %:x%")
	public Page<Document> chercherParChargeur(@Param("x") String chargeurDocument, Pageable p);

}
