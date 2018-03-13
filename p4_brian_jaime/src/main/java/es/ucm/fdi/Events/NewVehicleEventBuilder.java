package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public class NewVehicleEventBuilder implements EventBuilder{
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_vehicle")) return null;
		return new NewVehicleEvent(parseInt(sec, "time"), sec.getValue("id"),
				parseInt(sec, "max_speed"), parseIdList(sec, "itinerary"));
	}
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'v';
	}
	
	public String type(){
		return "new_vehicle";
	}
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}
	
	public String[] parseIdList(IniSection sec, String key){
		String[] s = sec.getValue(key).split(",");
		return s;
	}
}
