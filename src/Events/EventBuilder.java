package Events;

import es.ucm.fdi.ini.IniSection;

public interface EventBuilder {
	public Event parse(IniSection sec);
	public boolean isValidId(String id);
	public void parseInt(IniSection sec, String key, int de);
	public void parseIdList(IniSection sec, String key);
}
