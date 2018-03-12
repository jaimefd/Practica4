package es.ucm.fdi.Events;

import es.ucm.fdi.Simulator.RoadMap;

public class VehicleFaultyEvent extends Event{
	private int duration;
	private String vehicles;

	public VehicleFaultyEvent(int t, String vehicles, int duration) {
		super(t);
		this.duration = duration;
		this.vehicles = vehicles;
	}

	public void execute(RoadMap map) {
		String[] vehiculos = vehicles.split(",");
		for (String v : vehiculos)
			map.getVehicle(v).setTiempoAveria(duration);
	}
	
}
