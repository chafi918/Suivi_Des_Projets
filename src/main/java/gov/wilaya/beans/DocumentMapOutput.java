package gov.wilaya.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.wilaya.entities.Document;

public class DocumentMapOutput {
	private Map<String, Map<String, List<Document>>> documentMap;
	private Set<String> projetsName;
	private Set<String> typesName;
	
	public Map<String, Map<String, List<Document>>> getDocumentMap() {
		return documentMap;
	}
	public void setDocumentMap(Map<String, Map<String, List<Document>>> documentMap) {
		this.documentMap = documentMap;
	}
	public Set<String> getProjetsName() {
		return projetsName;
	}
	public void setProjetsName(Set<String> projetsName) {
		this.projetsName = projetsName;
	}
	public Set<String> getTypesName() {
		return typesName;
	}
	public void setTypesName(Set<String> typesName) {
		this.typesName = typesName;
	}
	public DocumentMapOutput(Map<String, Map<String, List<Document>>> documentMap, Set<String> projetsName,
			Set<String> typesName) {
		super();
		this.documentMap = documentMap;
		this.projetsName = projetsName;
		this.typesName = typesName;
	}
	public DocumentMapOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
