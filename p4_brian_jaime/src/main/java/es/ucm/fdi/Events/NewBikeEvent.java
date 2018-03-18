package es.ucm.fdi.Events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.AdvancedObjects.Bike;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.Simulator.RoadMap;

/** 
 * La clase NewBikeEvent se encarga de crear una bicicleta
 * @author Jaime Fernández y Brian Leiva
*/

public class NewBikeEvent extends NewVehicleEvent{
	
	/** 
	 * Constructor de la clase NewBikeEvent
	 * @param time Entero que representa el momento en el que ocurrirá el evento.
	 * @param id String con el identificador del vehiculo
	 * @param max Velocidad maxima de la bicicleta
	 * @param cruces Array de strings con el itinerario de cruces
	*/
	public NewBikeEvent(int time, String id, int max, String[] cruces) {
		super(time, id, max, cruces);
	}
	
	/** 
	 * Método que ejecuta el evento de creación de una nueva Bicicleta
	 * @param map El mapa de carreteras e intersecciones.
	*/
	public void execute(RoadMap map) {
		
		List<Junction> itinerario = new ArrayList<>();
		for (String n : cruces)
			itinerario.add(map.getJunction(n));
		Vehicle v = new Bike(id, max, itinerario);
		v.moverASiguienteCarretera(itinerario.get(0).road(v));
		map.addVehicle(v);
	}

}

