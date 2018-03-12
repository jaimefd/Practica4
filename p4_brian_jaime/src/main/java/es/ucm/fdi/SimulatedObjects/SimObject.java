package es.ucm.fdi.SimulatedObjects;

import java.util.Map;

public abstract class SimObject {
	protected String id;
	
	public SimObject(String id) {
		this.id = id;
	}
	
	public String getID(){
		return id;
	}
	
	public abstract void avanza();
	
	protected abstract void fillReportDetails(Map<String, String> out);
	
	protected abstract String getReportHeader(); 
	
	public static boolean equals(SimObject a, SimObject b){
		return a.getID() == b.getID();
	}
	
	public void report(int time, Map<String, String> m) {
		m.put("", getReportHeader());
		m.put("id", id);
		m.put("time", String.valueOf(time));
		fillReportDetails(m);
		}

}
