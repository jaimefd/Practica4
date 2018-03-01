package Events;

import java.util.ArrayList;

import SimulatedObjects.Vehicle;
import Simulator.TrafficSimulator;

public class VehicleFaultyEvent extends Event{
	int duration;
	String vehicles;

	public VehicleFaultyEvent(int t, String vehicles, int duration) {
		super(t);
		this.duration = duration;
		this.vehicles = vehicles;
	}

	public void execute(TrafficSimulator sim) {
		
	}
}
