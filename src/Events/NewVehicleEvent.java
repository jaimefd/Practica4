package Events;

import SimulatedObjects.Vehicle;
import Simulator.TrafficSimulator;

public class NewVehicleEvent extends Event{
	int max;
	String id;
	
	public NewVehicleEvent(int time, String id, int max, String junction) {
		super(time);
		this.max = max;
		this.id = id;
	}
	
	public void execute(TrafficSimulator sim) {
		sim.map.add(new Vehicle(id, max));
	}
}
