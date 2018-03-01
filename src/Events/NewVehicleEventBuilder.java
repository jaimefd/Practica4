package Events;

import es.ucm.fdi.ini.IniSection;

public class NewVehicleEventBuilder implements EventBuilder{
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_vehicle")) return null;
		return new NewVehicleEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"),
				sec.getValue("max_speed"), sec.getValue("itinerary"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'v';
	}
	
	public void parseInt(IniSection sec, String key, int de){
		
	}
	
	public void parseIdList(IniSection sec, String key){
		
	}
}
