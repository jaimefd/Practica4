package es.ucm.fdi.Control;

import java.io.OutputStream;

import es.ucm.fdi.Events.Event;
import es.ucm.fdi.Events.EventBuilder;
import es.ucm.fdi.Simulator.TrafficSimulator;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;

public class Controller {

	private Ini ini;
	private OutputStream out;
	private int timeLimit;
	
	
	public Controller(Ini ini, OutputStream out, Integer timeLimit) {
		this.ini = ini;
		this.out = out;
		this.timeLimit = timeLimit;
	}
	
	public void execute(TrafficSimulator sim) {
		for (IniSection s: ini.getSections()) {
			List<Event> events = ;
			event.parse(s);
		}
		sim.execute(timeLimit, out);
	}
}
