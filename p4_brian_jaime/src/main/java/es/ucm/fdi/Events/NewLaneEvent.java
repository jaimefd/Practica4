package es.ucm.fdi.Events;

import es.ucm.fdi.Exceptions.SimulatorException;
import es.ucm.fdi.AdvancedObjects.Lane;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.Simulator.RoadMap;

/** 
 * La clase NewLaneEvent se encarga de crear una autopista
 * @author Jaime Fernández y Brian Leiva
*/

public class NewLaneEvent extends NewRoadEvent {
	
	private int lanes;
	
	/** 
	 * Constructor de la clase NewLanedEvent
	 * @param time Entero que representa el momento en el que ocurrirá el evento.
	 * @param id String con el identificador de la autopista
	 * @param sr String con el identificador del cruce inicial
	 * @param dest String con el identificador del cruce final
	 * @param max Entero con la velocidad máxima permitida en la autopista
	 * @param length Entero con la longitud de la autopista
	*/

	public NewLaneEvent(int time, String id, String sr, String dest, int max, int length, int lanes) {
		super(time, id, sr, dest, max, length);
		this.lanes = lanes;
	}
	
	/** 
	 * Método que ejecuta el evento de creación de una nueva autopista
	 * @param map El mapa de carreteras e intersecciones.
	 * @throws SimulatorException 
	*/
	
	public void execute(RoadMap map) throws SimulatorException {
		try {
			Junction ini = map.getJunction(sr);
			Junction fin = map.getJunction(dest);
			Road r = new Lane(id, length, max, ini, fin, lanes);
			ini.addSale(r);
			fin.addEntra(r);
			map.addRoad(r);
		}
		catch(NullPointerException e) {
			throw new SimulatorException("Lane " + id + ": invalid source/destiny junctions");
		}
		catch(IllegalArgumentException e) {
			throw new SimulatorException("Lane " + id + ": id already exists");
		}
	}

}
