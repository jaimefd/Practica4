package Events;

import es.ucm.fdi.ini.IniSection;

public abstract class EventBuilder {
	public void parse();
	public boolean isValidId(String id);
	public void parseInt(IniSection sec, String key, int default);
	public void parseIdList(IniSection sec, String key);
}
