package Events;

import es.ucm.fdi.ini.IniSection;

public class NewJunctionEventBuilder implements EventBuilder {
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_junction")) return null;
		return new NewJunctionEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'j';
	}
	
	public void parseInt(IniSection sec, String key, int de){
		
	}
	
	public void parseIdList(IniSection sec, String key){
		
	}
}
