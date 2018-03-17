package es.ucm.fdi.Events;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.Simulator.RoadMap;

/** 
 * La clase NewJunctionEvent se encarga de crear un cruce
 * @author Jaime Fernández y Brian Leiva
*/

public class NewJunctionEvent extends Event{
	protected String id;
	
	/** 
	 * Constructor de la clase NewJunctionEvent
	 * @param time Entero que representa el momento en el que ocurrirá el evento.
	 * @param id String con el identificador del cruce
	*/
	
	public NewJunctionEvent(int time, String id) {
		super(time);
		this.id = id;
	}
	
	/** 
	 * Método que ejecuta el evento de creación de un nuevo cruce
	 * @param map El mapa de carreteras e intersecciones.
	*/
	
	public void execute(RoadMap map) {
		map.addJunction(new Junction(id));
	}

}
