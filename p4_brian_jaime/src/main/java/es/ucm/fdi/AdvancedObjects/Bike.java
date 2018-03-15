package es.ucm.fdi.AdvancedObjects;

import java.util.List;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;

/** 
 * La clase Bike representa una bicicleta del simulador.
 * @author Jaime Fernández y Brian Leiva
*/

public class Bike extends Vehicle{
	
	/** 
	 * Constructor de la clase Bike.
	 * @param ident : Identificador
	 * @param vmax : Velocidad máxima
	 * @param it : Itinerario
	*/

	public Bike(String ident, int vmax, List<Junction> it) {
		super(ident, vmax, it);
	}
	
	/**
	 * Método que ajusta el tiempo de avería
	 * @param n : Tiempo de avería
	 */
	
	public void setTiempoAveria(int n) {
		if (2 * velActual > velMaxima)
			super.setTiempoAveria(n);
	}

}