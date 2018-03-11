package Events;

import SimulatedObjects.Junction;
import Simulator.RoadMap;

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
