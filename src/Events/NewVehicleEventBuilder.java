package Events;

import es.ucm.fdi.ini.IniSection;

public class NewVehicleEventBuilder {
	public static Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_junction")) return null;
		return new NewVehicleEvent());
	}
}
