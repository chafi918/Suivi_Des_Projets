package gov.wilaya.beans;

import gov.wilaya.entities.Document;

public class InputDocument {
	private Long idProjet;
	private Document document;
	private String contenu;
	
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	public InputDocument(Long idProjet, Document document, String contenu) {
		super();
		this.idProjet = idProjet;
		this.document = document;
		this.contenu = contenu;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public InputDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
