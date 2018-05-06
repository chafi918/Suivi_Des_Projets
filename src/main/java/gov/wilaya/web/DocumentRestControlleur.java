package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.beans.InputDocument;
import gov.wilaya.dao.DocumentRepository;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Document;

@RestController
@RequestMapping(value = "/document")
@CrossOrigin("*")
public class DocumentRestControlleur {
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ProjetRepository projetRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void ajouterDocument(@RequestBody InputDocument inputDocument) {
		Document document = inputDocument.getDocument();
			System.out.println("idProjet pour le document: " + inputDocument.getIdProjet());
			System.out.println("nom pour le document: " + inputDocument.getDocument().getNomDocument());
			System.out.println("contenu pour le document: " + inputDocument.getContenu());
			System.out.println("bytes: "+inputDocument.getContenu().getBytes().toString());
			
			document.setProjet(projetRepository.findOne(inputDocument.getIdProjet()));
			document.setContenu(inputDocument.getContenu().getBytes());
			documentRepository.save(document);
	}
	
	@RequestMapping(value = "/documents", method = RequestMethod.GET)
	public List<Document> getDocuments(){
		return documentRepository.findAll();
	}
	
	@RequestMapping(value = "/getDocuments", method = RequestMethod.GET)
	public Page<Document> getDocuments(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return documentRepository.findAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public Page<Document> getDocuments(@PathVariable String name,
	@RequestParam(name="page",defaultValue="0")int page,
	@RequestParam(name="size",defaultValue="5")int size){
		return documentRepository.findByName(name, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Document getDocumentById(@PathVariable Long id) {
		return documentRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateProjet(@PathVariable Long id, @RequestBody Document document) {
		if ( documentRepository.findOne(id) != null) {
			document.setIdDocument(id);
			documentRepository.save(document);
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerDocument(@PathVariable Long id) {
		if ( documentRepository.findOne(id) != null) {
			documentRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="documents", method=RequestMethod.DELETE)
	public void supprimerDocumentss(){
		documentRepository.deleteAll();
	}
	
	@RequestMapping(value = "/projet", method = RequestMethod.GET)
	public Page<Document> getDocumentsByProject(
			@RequestParam(name = "idProjet") Long idProjet,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		System.out.println("get documents du projet : " + idProjet);
		return documentRepository.chercherParProjet(idProjet, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public Page<Document> getDocumentsByType(@RequestParam(name = "typeDocument", defaultValue = "")  String typeDocument,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return documentRepository.chercherParType(typeDocument, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/chargeur/{chargeur}", method = RequestMethod.GET)
	public Page<Document> getDocumentParCharger(@PathVariable String chargeur ,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return documentRepository.chercherParChargeur(chargeur, new PageRequest(page, size));
	}
}
