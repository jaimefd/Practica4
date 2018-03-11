package Events;

import Simulator.RoadMap;

public abstract class Event {
	private int time;
	
	public Event(int t) {
		time = t;
	}
	
	public int getTime() {
		return time;
	}
	
	public abstract void execute(RoadMap map);
	
}
