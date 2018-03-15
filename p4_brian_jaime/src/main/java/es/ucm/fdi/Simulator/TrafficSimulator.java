package es.ucm.fdi.Simulator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.Events.Event;
import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.SimObject;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;
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
	
	public Ini report() {
		Map<String, String> m = new LinkedHashMap<>();
		Ini salida = new Ini();
		for (SimObject sim : SimObjects.getSimObjects()) {
			sim.report(contadorTiempo, m);
			IniSection s = new IniSection(m.get(""));
			for (String key: m.keySet()) {
				if (key != "") s.setValue(key, m.get(key));
			}
			salida.addsection(s);
			m.clear();
		}
		return salida;
	}
	
	public void execute(int pasosSimulacion, OutputStream o) throws IOException{
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
			report().store(o);
		}
	}

}
