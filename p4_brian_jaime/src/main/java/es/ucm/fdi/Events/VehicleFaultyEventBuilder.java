package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public class VehicleFaultyEventBuilder implements EventBuilder {
	
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("make_vehicle_faulty")) return null;
		return new VehicleFaultyEvent(Integer.parseInt(sec.getValue("time")),
				parseIdList(sec, "vehicles"), parseInt(sec, "duration"));
	}

	public boolean isValidId(String id){
		return id.charAt(0) == 'v';
	}
	
	public String type(){
		return "make_vehicle_faulty";
	}
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}

	public String[] parseIdList(IniSection sec, String key){
		String[] s = sec.getValue(key).split(",");
		return s;
	}

}
