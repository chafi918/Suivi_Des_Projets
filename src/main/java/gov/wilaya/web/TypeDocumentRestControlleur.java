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

import gov.wilaya.dao.TypeDocumentRepository;
import gov.wilaya.entities.TypeDocument;

@RestController
@RequestMapping(value = "/adminType")
@CrossOrigin("*")
public class TypeDocumentRestControlleur {
	@Autowired
	private TypeDocumentRepository typeDocumentRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterTypeDocument(@RequestBody TypeDocument typeDocument) {
		if ( typeDocumentRepository.searchByName(typeDocument.getLibelleType()) == null || 
				 typeDocumentRepository.searchByName(typeDocument.getLibelleType()).isEmpty()){
			    typeDocumentRepository.save(typeDocument);
		}
	}
	@RequestMapping(value = "/typeBN", method = RequestMethod.GET)
	public Page<TypeDocument> getTypeByName(@RequestParam(name = "name", defaultValue = "")  String type,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return typeDocumentRepository.findByName(type, new PageRequest(page, size));
	}
	@RequestMapping(value = "/getAllTypes", method = RequestMethod.GET)
	public Page<TypeDocument> getTypesDocuments(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return typeDocumentRepository.findAll(new PageRequest(page, size));
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
