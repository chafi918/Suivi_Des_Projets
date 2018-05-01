package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.DocumentRepository;
import gov.wilaya.entities.Document;

@RestController
@RequestMapping(value = "/adminDocument")
@CrossOrigin("*")
public class DocumentRestControlleur {
	@Autowired
	private DocumentRepository documentRepository;
	
	@RequestMapping(value = "/document", method = RequestMethod.POST)
	public void ajouterDocument(@RequestBody Document document) {
		
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
	
/*	@RequestMapping(value = "/projet/{idProjet}", method = RequestMethod.GET)
	public Page<Document> getDocumentsByProject(@PathVariable Long idProjet,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return documentRepository.chercherParProjet(idProjet, new PageRequest(page, size));
	}*/
	
	@RequestMapping(value = "type/{idType}", method = RequestMethod.GET)
	public Page<Document> getDocumentsByType(@PathVariable Long idType,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return documentRepository.chercherParType(idType, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/chargeur/{chargeur}", method = RequestMethod.GET)
	public Page<Document> getDocumentParCharger(@PathVariable String chargeur ,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return documentRepository.chercherParChargeur(chargeur, new PageRequest(page, size));
	}
}
