package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public class VehicleFaultyEventBuilder implements EventBuilder {

	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("make_vehicle_faulty")) return null;
		return new VehicleFaultyEvent(Integer.parseInt(sec.getValue("time")),
				sec.getValue("vehicles"), parseInt(sec, "duration"));
	}

	public boolean isValidId(String id){
		return id.charAt(0) == 'v';
	}
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}

	public void parseIdList(IniSection sec, String key) {
		// TODO Auto-generated method stub
		
	}

}
