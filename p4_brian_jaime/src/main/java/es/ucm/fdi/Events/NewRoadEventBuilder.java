package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public class NewRoadEventBuilder implements EventBuilder {
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_road")) return null;
		return new NewRoadEvent(parseInt(sec, "time"), sec.getValue("id"), 
				sec.getValue("src"), sec.getValue("dest"),
				parseInt(sec, "max_speed"), parseInt(sec, "length"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'r';
	}
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}
	
	public void parseIdList(IniSection sec, String key){
		
	}
}
