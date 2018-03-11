package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

public interface EventBuilder {
	public Event parse(IniSection sec);
	public boolean isValidId(String id);
	public int parseInt(IniSection sec, String key);
	public void parseIdList(IniSection sec, String key);
}
