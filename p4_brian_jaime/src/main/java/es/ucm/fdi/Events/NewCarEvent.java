package es.ucm.fdi.Events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.AdvancedObjects.Car;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.Simulator.RoadMap;

/** 
 * La clase NewCarEvent se encarga de crear un coche
 * @author Jaime Fernández y Brian Leiva
*/

public class NewCarEvent extends NewVehicleEvent{
	
	private int resistance, fault_probability, max_fault_duration;
	private long seed;
	
	/** 
	 * Constructor de la clase NewCarEvent
	 * @param time Entero que representa el momento en el que ocurrirá el evento.
	 * @param id String con el identificador del vehiculo
	 * @param max Velocidad maxima del coche
	 * @param cruces Array de strings con el itinerario de cruces
	 * @param resistance Entero con la resistencia del coche
	 * @param fault_probability Entero con la probabilidad de avería del coche
	 * @param max_fault_duration Entero con la duración máxima de la avería
	 * @param seed Semilla
	*/

	public NewCarEvent(int time, String id, int max, String[] cruces, int resistance, int fault_probability, int max_fault_duration, long seed) {
		super(time, id, max, cruces);
		this.resistance = resistance;
		this.fault_probability = fault_probability;
		this.max_fault_duration = max_fault_duration;
		this.seed = seed;
	}
	
	/** 
	 * Método que ejecuta el evento de creación de un nuevo coche
	 * @param map El mapa de carreteras e intersecciones.
	*/
	
	public void execute(RoadMap map) {
		
		List<Junction> itinerario = new ArrayList<>();
		for (String n : cruces)
			itinerario.add(map.getJunction(n));
		Vehicle v = new Car(id, max, itinerario, resistance, fault_probability, max_fault_duration, seed);
		v.avanza();
		map.addVehicle(v);
		
	}

}
