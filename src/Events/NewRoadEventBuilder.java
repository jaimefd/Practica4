package Events;

import es.ucm.fdi.ini.IniSection;

public class NewRoadEventBuilder extends EventBuilder {
	public static Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_road")) return null;
		return new NewRoadEvent());
	}
}
