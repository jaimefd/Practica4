package es.ucm.fdi.Simulator;

import java.io.OutputStream;
import java.util.List;

import es.ucm.fdi.Events.Event;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.util.MultiTreeMap;

public class TrafficSimulator {
	private int contadorTiempo;
	private MultiTreeMap<Integer, Event> eventos;
	private RoadMap SimObjects;

	public TrafficSimulator() {
		this.SimObjects = new RoadMap();
		this.eventos = new MultiTreeMap<>();
		this.contadorTiempo = 0;
	}
	
	public void insertaEvento(Event e) {
		if (e.getTime() < contadorTiempo)
			throw new IllegalArgumentException();
		eventos.putValue(e.getTime(), e);
	}
	
	public void execute(int pasosSimulacion, OutputStream o){
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		while (this.contadorTiempo <= limiteTiempo) {
			List<Event> eventActuales = eventos.get(contadorTiempo);
			if (eventActuales != null) {
				for (Event e : eventActuales)
					e.execute(SimObjects);
			}
			for (Road r : SimObjects.getRoads())
				r.avanza();
			for (Junction j : SimObjects.getJunctions())
				j.avanza();
			this.contadorTiempo++;
		// 5. esciribir un informe en OutputStream en caso de que no sea null
		}
	}

}
