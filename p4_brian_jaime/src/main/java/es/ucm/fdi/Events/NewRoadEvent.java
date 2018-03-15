package es.ucm.fdi.Events;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.Simulator.RoadMap;

/** 
 * La clase NewRoadEvent se encarga de crear una carretera
 * @author Jaime Fernández y Brian Leiva
*/

public class NewRoadEvent extends Event{
	protected String id, sr, dest;
	protected int max, length;
	
	/** 
	 * Constructor de la clase NewRoadEvent
	 * @param time Entero que representa el momento en el que ocurrirá el evento.
	 * @param id String con el identificador de la carretera
	 * @param sr String con el identificador del cruce inicial
	 * @param dest String con el identificador del cruce final
	 * @param max Entero con la velocidad máxima permitida en la carretera
	 * @param length Entero con la longitud de la carretera
	*/

	public NewRoadEvent(int time, String id, String sr, String dest, int max, int length) {
		super(time);
		this.id = id;
		this.sr = sr;
		this.dest = dest;
		this.max = max;
		this.length = length;
	}
	
	/** 
	 * Método que ejecuta el evento de creación de una nueva carretera
	 * @param map El mapa de carreteras e intersecciones.
	*/

	public void execute(RoadMap map) {
		Junction ini = map.getJunction(sr);
		Junction fin = map.getJunction(dest);
		Road r = new Road(id, length, max, ini, fin);
		ini.addSale(r);
		fin.addEntra(r);
		map.addRoad(r);
	}
	
}
