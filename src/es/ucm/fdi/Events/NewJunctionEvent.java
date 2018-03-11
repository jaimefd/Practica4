package es.ucm.fdi.Events;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.Simulator.RoadMap;

public class NewJunctionEvent extends Event{
	private String id;
	
	public NewJunctionEvent(int time, String id) {
		super(time);
		this.id = id;
	}
	
	public void execute(RoadMap map) {
		map.addJunction(new Junction(id));
	}

}
