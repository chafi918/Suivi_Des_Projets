package gov.wilaya.beans;

import java.util.HashMap;
import java.util.Map;

public class ProjetStatsOutPut {
	private Map<String, Long> statMap = new HashMap<>();

	public Map<String, Long> getStatMap() {
		return statMap;
	}

	public void setStatMap(Map<String, Long> statMap) {
		this.statMap = statMap;
	}

	public ProjetStatsOutPut(Map<String, Long> statMap) {
		super();
		this.statMap = statMap;
	}

	public ProjetStatsOutPut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
