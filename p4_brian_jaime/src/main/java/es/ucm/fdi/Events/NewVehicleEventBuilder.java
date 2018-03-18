package es.ucm.fdi.Events;

import es.ucm.fdi.ini.IniSection;

/** 
 * La clase NewVehicleEventBuilder se encarga de crear el NewVehicleEvent
 * @author Jaime Fernández y Brian Leiva
*/

public class NewVehicleEventBuilder implements EventBuilder{
	
	/** 
	 * Método para reconocer si la sección representa un evento new vehicle y crear el evento
	 * @param sec La sección del evento
	*/
	
	public Event parse(IniSection sec) {
		if (!sec.getTag().equals("new_vehicle")) return null;
		if (!sec.getKeys().contains("type")) return new NewVehicleEvent(parseInt(sec, "time"), sec.getValue("id"),
				parseInt(sec, "max_speed"), parseIdList(sec, "itinerary"));
		if (sec.getValue("type").equals("car")) {
			long seed;
			if (sec.getValue("seed").isEmpty()) seed = System.currentTimeMillis();
			else seed = parseLong(sec,"seed");
			return new NewCarEvent(parseInt(sec, "time"), sec.getValue("id"), parseInt(sec, "max_speed"), parseIdList(sec, "itinerary"), parseInt(sec, "resistance"),
					parseDouble(sec, "fault_probability"), parseInt(sec, "max_fault_duration"),
					seed);
		}
		return new NewBikeEvent(parseInt(sec, "time"), sec.getValue("id"),
				parseInt(sec, "max_speed"), parseIdList(sec, "itinerary"));
	}
	
	/** 
	 * Método para comprobar si la id representa un vehicle.
	 * @param id El identificador del objeto
	*/
	
	public boolean isValidId(String id){
		return id.charAt(0) == 'v';
	}
	
	/** 
	 * Método que devuelve el tipo de evento que construye la clase.
	*/
	
	public String type(){
		return "new_vehicle";
	}
	
	/** 
	 * Método que convierte una key de una sección en un entero
	 * @param sec La sección.
	 * @param key La clave que va a ser convertida en entero.
	 * @return El entero obtenido a partir de la key.
	*/
	
	public int parseInt(IniSection sec, String key){
		return Integer.parseInt(sec.getValue(key));
	}
	
	/** 
	 * Método que convierte una key de una sección en un double
	 * @param sec La sección.
	 * @param key La clave que va a ser convertida en double.
	 * @return El double obtenido a partir de la key.
	*/
	
	public double parseDouble(IniSection sec, String key){
		return Double.parseDouble(sec.getValue(key));
	}
	
	/** 
	 * Método que convierte una key de una sección en un long
	 * @param sec La sección.
	 * @param key La clave que va a ser convertida en long.
	 * @return El long obtenido a partir de la key.
	*/
	
	public long parseLong(IniSection sec, String key){
		return Long.parseLong(sec.getValue(key));
	}
	
	/** 
	 * Método que convierte una key de una sección en un array de strings.
	 * @param sec La sección.
	 * @param key La clave que va a ser convertida en entero.
	 * @return El array de strings.
	*/
	
	public String[] parseIdList(IniSection sec, String key){
		String[] s = sec.getValue(key).split(",");
		return s;
	}
}
