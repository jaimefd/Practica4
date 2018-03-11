package Events;

import java.util.ArrayList;
import java.util.List;

import SimulatedObjects.Junction;
import SimulatedObjects.Vehicle;
import Simulator.RoadMap;

public class NewVehicleEvent extends Event{
	private String id, cruces;
	private int max;
	
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
