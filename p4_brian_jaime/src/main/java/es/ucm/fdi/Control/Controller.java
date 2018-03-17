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

/** 
 * La clase Controller controla la simulación.
 * @author Jaime Fernández y Brian Leiva
*/

public class Controller {
	private EventBuilder[] events = {
			new NewVehicleEventBuilder(), new NewRoadEventBuilder(),
			new NewJunctionEventBuilder(), new VehicleFaultyEventBuilder() };
	private Ini ini;
	private OutputStream out;
	private int timeLimit;
	
	/** 
	 * Constructor de la clase Controller.
	 * @param ini
	 * @param out
	 * @param timeLimit
	*/
	
	public Controller(Ini ini, OutputStream out, Integer timeLimit) {
		this.ini = ini;
		this.out = out;
		this.timeLimit = timeLimit;
	}
	
	/** 
	 * Método que lee las secciones de eventos, les asigna el builder correspondiente a cada una, y ejecuta la simulación.
	 * @param sim : La simulación de tráfico
	*/
	
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
