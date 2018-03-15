package es.ucm.fdi.Control;

import java.io.IOException;
import java.io.OutputStream;

import es.ucm.fdi.Events.EventBuilder;
import es.ucm.fdi.Events.NewJunctionEventBuilder;
import es.ucm.fdi.Events.NewRoadEventBuilder;
import es.ucm.fdi.Events.NewVehicleEventBuilder;
import es.ucm.fdi.Events.VehicleFaultyEventBuilder;
import es.ucm.fdi.Simulator.TrafficSimulator;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;

public class Controller {
	private EventBuilder[] events = {
			new NewVehicleEventBuilder(), new NewRoadEventBuilder(),
			new NewJunctionEventBuilder(), new VehicleFaultyEventBuilder() };
	private Ini ini;
	private OutputStream out;
	private int timeLimit;
	
	
	public Controller(Ini ini, OutputStream out, Integer timeLimit) {
		this.ini = ini;
		this.out = out;
		this.timeLimit = timeLimit;
	}
	
	public void execute(TrafficSimulator sim) throws IOException {

		for (IniSection n : ini.getSections()) {
			boolean b = false;
			for (EventBuilder eBuilder : events) {
				if (n.getTag().equals(eBuilder.type())) {
					sim.insertaEvento(eBuilder.parse(n));
					b = true;
				}
			}
			if (!b)
				throw new IllegalArgumentException();
		}
		sim.execute(timeLimit, out);
	}
}
