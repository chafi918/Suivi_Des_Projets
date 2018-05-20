package gov.wilaya.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.groups.Tuple;
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

import gov.wilaya.beans.DocumentMapOutput;
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

	@RequestMapping(value = "/ajout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void ajouterDocument(@RequestBody InputDocument inputDocument) {
		Document document = inputDocument.getDocument();
		System.out.println("idProjet pour le document: " + inputDocument.getIdProjet());
		System.out.println("nom pour le document: " + inputDocument.getDocument().getNomDocument());
		System.out.println("contenu pour le document: " + inputDocument.getContenu());

		document.setProjet(projetRepository.findOne(inputDocument.getIdProjet()));
		document.setContenu(inputDocument.getContenu().getBytes());
		document.setDateAjout(new Date());
		System.out.println("bytes: " + document.getContenu());
		for (int i = 0; i < document.getContenu().length; i++) {
			System.out.print(document.getContenu()[i]);
		}
		documentRepository.save(document);
	}

	@RequestMapping(value = "/documents", method = RequestMethod.GET)
	public List<Document> getDocuments() {
		return documentRepository.findAll();
	}

	@RequestMapping(value = "/documentsMap", method = RequestMethod.GET)
	public DocumentMapOutput getMapDocuments() {
		List<Document> listdocument = documentRepository.findAll();
		Map<String, Map<String, List<Document>>> documentsMap = listdocument.stream()
				.collect(Collectors.groupingBy(projet -> projet.getProjet().getIntitule(),
								Collectors.groupingBy(type -> type.getType().getLibelleType())));
		
		Set<String> projets = documentsMap.keySet();
		
		Set<String> types = listdocument.stream().map(document -> {
			return document.getType().getLibelleType();
		}).collect(Collectors.toSet());

		Set<String> years = listdocument.stream().map(document -> {
			return getYearOfDate(document.getProjet().getDateCommTravaux());
		}).collect(Collectors.toSet());

		return new DocumentMapOutput(documentsMap, projets, types, years);
	}

	@RequestMapping(value = "/documentsMapByYear", method = RequestMethod.GET)
	public DocumentMapOutput getDocumentsByYear(
			@RequestParam(name = "year", defaultValue = "0") int year,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		System.out.println("year: "+ year + ", page: " + page);
		Page<Document> documentPage;
		if (year==0) {
			documentPage = documentRepository.findAll(new PageRequest(page, size));
		} else {
			documentPage = documentRepository.getDocumentsByYear(year, new PageRequest(page, size));
		}
		int pages = documentPage.getTotalPages();
		System.out.println("pages: " + pages + ", documentPage.totl: "+ documentPage.getTotalElements());
		List<Document> listdocument = new ArrayList<>();
		documentPage.forEach(d -> listdocument.add(d));
		DocumentMapOutput result = getReturnOutput(listdocument);
		int currentPage = documentPage.getNumber();
		System.out.println("currentPage: " + currentPage);
		result.setTotalPages(pages);
		result.setCurrentPage(currentPage);
		return result;
		
	}
	
	private DocumentMapOutput getReturnOutput(List<Document> listdocument) {
		Map<String, Map<String, List<Document>>> documentsMap = listdocument.stream()
				.collect(Collectors.groupingBy(projet -> projet.getProjet().getIntitule(),
								Collectors.groupingBy(type -> type.getType().getLibelleType())));
		Set<String> projets = documentsMap.keySet();
		Set<String> types = listdocument.stream().map(document -> {
			return document.getType().getLibelleType();
		}).collect(Collectors.toSet());
		Set<String> years = projetRepository.findAll().stream().map( p -> {
			return getYearOfDate(p.getDateCommTravaux());
		}).collect(Collectors.toSet());
		return new DocumentMapOutput(documentsMap, projets, types, years);
		
	}

	public String getYearOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.valueOf(cal.get(Calendar.YEAR));
	}

	@RequestMapping(value = "/getDocuments", method = RequestMethod.GET)
	public Page<Document> getDocuments(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return documentRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public Page<Document> getDocuments(@PathVariable String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return documentRepository.findByName(name, new PageRequest(page, size));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Document getDocumentById(@PathVariable Long id) {
		return documentRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateProjet(@PathVariable Long id, @RequestBody Document document) {
		if (documentRepository.findOne(id) != null) {
			document.setIdDocument(id);
			documentRepository.save(document);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerDocument(@PathVariable Long id) {
		if (documentRepository.findOne(id) != null) {
			documentRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "documents", method = RequestMethod.DELETE)
	public void supprimerDocumentss() {
		documentRepository.deleteAll();
	}

	@RequestMapping(value = "/projet", method = RequestMethod.GET)
	public Page<Document> getDocumentsByProject(@RequestParam(name = "idProjet") Long idProjet,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		System.out.println("get documents du projet : " + idProjet);
		return documentRepository.chercherParProjet(idProjet, new PageRequest(page, size));
	}

	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public Page<Document> getDocumentsByType(
			@RequestParam(name = "typeDocument", defaultValue = "") String typeDocument,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return documentRepository.chercherParType(typeDocument, new PageRequest(page, size));
	}

	@RequestMapping(value = "/chargeur/{chargeur}", method = RequestMethod.GET)
	public Page<Document> getDocumentParCharger(@PathVariable String chargeur,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return documentRepository.chercherParChargeur(chargeur, new PageRequest(page, size));
	}
}
