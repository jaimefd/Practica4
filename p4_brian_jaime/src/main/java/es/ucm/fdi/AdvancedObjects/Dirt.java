package es.ucm.fdi.AdvancedObjects;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.util.MultiTreeMap;

/** 
 * La clase Dirt representa un camino del simulador.
 * @author Jaime Fernández y Brian Leiva
*/

public class Dirt extends Road{
	
	/** 
	 * Constructor de la clase Dirt.
	 * @param ident : Identificador
	 * @param l : Longitud del camino
	 * @param maxV : Velocidad máxima
	 * @param junction_i : Cruce inicial
	 * @param junction_f : Cruce final
	*/

	public Dirt(String ident, int l, int maxV, Junction junction_i, Junction junction_f) {
		super(ident, l, maxV, junction_i, junction_f);
	}
	
	/**
	 * Método que hace avanzar la simulación en el camino
	 */
	
	public void avanza(){
		int velBase = Math.min(maxVel, maxVel / Math.max(vehiculos.size(), 1) + 1);
		int factorRed = 1;
		MultiTreeMap<Integer, Vehicle> map = new MultiTreeMap<>((a, b) -> a - b);
		for (int i = longitud - 1; i >= 0; --i){
			if (vehiculos.containsKey(i)){
				for(Vehicle v: vehiculos.get(i)){
					if (v.getAveria()) factorRed++;
				}
			}
		}
		for (int i = longitud - 1; i >= 0; --i){
			if (vehiculos.containsKey(i)){
				for(Vehicle v: vehiculos.get(i)){
					v.setVelocidadActual(velBase / factorRed);
					v.avanza();
					map.putValue(v.getPos(), v);
				}
			}
		}
		vehiculos = map;
	}

}
