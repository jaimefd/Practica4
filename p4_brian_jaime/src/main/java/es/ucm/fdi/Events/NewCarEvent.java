package es.ucm.fdi.Events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.AdvancedObjects.Car;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.Simulator.RoadMap;

public class NewCarEvent extends NewVehicleEvent{
	
	private int resistance, fault_probability, max_fault_duration;
	private long seed;

	public NewCarEvent(int time, String id, int max, String cruces, int resistance, int fault_probability, int max_fault_duration, long seed) {
		super(time, id, max, cruces);
		this.resistance = resistance;
		this.fault_probability = fault_probability;
		this.max_fault_duration = max_fault_duration;
		this.seed = seed;
	}
	
	public void execute(RoadMap map) {
		
		List<Junction> itinerario = new ArrayList<>();
		String[] s = cruces.split(",");
		for (String n : s)
			itinerario.add(map.getJunction(n));
		Vehicle v = new Car(id, max, itinerario, resistance, fault_probability, max_fault_duration, seed);
		v.avanza();
		map.addVehicle(v);
		
	}

}
