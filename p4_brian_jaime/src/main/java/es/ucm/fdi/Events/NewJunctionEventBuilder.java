package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

/** 
 * La clase NewJunctionEventBuilder se encarga de crear el NewJunctionEvent
 * @author Jaime Fernández y Brian Leiva
*/

public class NewJunctionEventBuilder implements EventBuilder {
	
	/** 
	 * Método para reconocer si la sección representa un evento new junction y crear el evento
	 * @param sec : La sección del evento
	 * @return El evento creado.
	*/
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_junction")) return null;
		if (!sec.getKeys().contains("type")) return new NewJunctionEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"));
		else if (sec.getValue("type").equals("mc")) return new NewMostCrowdedEvent(Integer.parseInt(sec.getValue("time")), sec.getValue("id"));
		return new NewRoundRobinEvent(parseInt(sec, "time"), sec.getValue("id"), 
									  parseInt(sec, "max_time_slice"), parseInt(sec, "min_time_slice"));
	}
	
	/** 
	 * Método para comprobar si la id representa un Junction.
	 * @param id : El identificador del objeto
	 * @return Booleano que indica si id es válida.
	*/	
	public boolean isValidId(String id){
		return id.charAt(0) == 'j';
	}
	
	/** 
	 * Método que devuelve el tipo de evento que construye la clase.
	 * @return String cone el nombre del evento.
	*/
	public String type(){
		return "new_junction";
	}
	
	/** 
	 * Método que convierte una key de una sección en un entero
	 * @param sec : La sección.
	 * @param key : La clave que va a ser convertida en entero.
	 * @return El entero obtenido a partir de la key.
	*/
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}
	
	/** 
	 * Método que convierte una key de una sección en un array de strings.
	 * @param sec : La sección.
	 * @param key : La clave que va a ser convertida en entero.
	 * @return El array de strings.
	*/
	public String[] parseIdList(IniSection sec, String key){
		String[] s = sec.getValue(key).split(",");
		return s;
	}
}
