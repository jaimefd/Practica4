package es.ucm.fdi.Events;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.Simulator.RoadMap;

public class NewVehicleEvent extends Event{
	protected String id, cruces;
	protected int max;
	
	public NewVehicleEvent(int time, String id, int max, String cruces) {
		super(time);
		this.id = id;
		this.max = max;
		this.cruces = cruces;
	}
	
	public void execute(RoadMap map) {
		
		List<Junction> itinerario = new ArrayList<>();
		String[] s = cruces.split(",");
		for (String n : s)
			itinerario.add(map.getJunction(n));
		Vehicle v = new Vehicle(id, max, itinerario);
		v.avanza();
		map.addVehicle(v);
		
	}
}
