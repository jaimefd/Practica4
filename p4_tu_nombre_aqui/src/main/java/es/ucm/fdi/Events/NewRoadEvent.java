package es.ucm.fdi.Events;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.Simulator.RoadMap;

public class NewRoadEvent extends Event{
	private String id, sr, dest;
	private int max, length;

	public NewRoadEvent(int time, String id, String sr, String dest, int max, int length) {
		super(time);
		this.id = id;
		this.sr = sr;
		this.dest = dest;
		this.max = max;
		this.length = length;
	}

	public void execute(RoadMap map) {
		Junction ini = map.getJunction(sr);
		Junction fin = map.getJunction(dest);
		Road r = new Road(id, length, max, ini, fin);
		ini.addSale(r);
		fin.addEntra(r);
		map.addRoad(r);
	}
	
}
