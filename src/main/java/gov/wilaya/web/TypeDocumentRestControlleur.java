package gov.wilaya.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.TypeDocumentRepository;
import gov.wilaya.entities.TypeDocument;

@RestController
@RequestMapping(value = "/typeDoc")
public class TypeDocumentRestControlleur {
	@Autowired
	private TypeDocumentRepository typeDocumentRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterTypeDocument(@RequestBody TypeDocument typeDocument) {
		if ( typeDocumentRepository.findByName(typeDocument.getLibelleType()) == null || 
				 typeDocumentRepository.findByName(typeDocument.getLibelleType()).isEmpty()){
			    typeDocumentRepository.save(typeDocument);
		}
	}
	
	@RequestMapping(value = "/allTypes", method = RequestMethod.GET)
	public List<TypeDocument> getTypesDocuments() {
		return typeDocumentRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TypeDocument getTypeById(@PathVariable Long id) {
		return typeDocumentRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerTypeDoc(@PathVariable Long id) {
		if (typeDocumentRepository.findOne(id) != null) {
			typeDocumentRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	@RequestMapping(value="typesDoc", method=RequestMethod.DELETE)
	public void supprimerTypes(){
		typeDocumentRepository.deleteAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateTypeDoc(@PathVariable Long id, @RequestBody TypeDocument typeDocument) {
		if (typeDocumentRepository.findOne(id) != null) {
			typeDocument.setIdTypeDoc(id);
			typeDocumentRepository.save(typeDocument);
		}

	}

}
