package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public class NewJunctionEventBuilder implements EventBuilder {
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_junction")) return null;
		return new NewJunctionEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'j';
	}
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}
	
	public void parseIdList(IniSection sec, String key){
		
	}
}
