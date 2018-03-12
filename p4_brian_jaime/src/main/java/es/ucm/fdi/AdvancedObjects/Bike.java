package es.ucm.fdi.AdvancedObjects;

import java.util.List;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;

public class Bike extends Vehicle{

	public Bike(String ident, int vmax, List<Junction> it) {
		super(ident, vmax, it);
	}
	
	public void setTiempoAveria(int n) {
		if (2 * velActual > velMaxima)
			super.setTiempoAveria(n);
	}

}