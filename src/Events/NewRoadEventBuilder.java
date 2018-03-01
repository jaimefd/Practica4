package Events;

import es.ucm.fdi.ini.IniSection;

public class NewRoadEventBuilder implements EventBuilder {
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_road")) return null;
		return new NewRoadEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"), 
				sec.getValue("src"), sec.getValue("dest"),
				sec.getValue("max_speed"), sec.getValue("length"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'r';
	}
	
	public void parseInt(IniSection sec, String key, int de){
		
	}
	
	public void parseIdList(IniSection sec, String key){
		
	}
}
