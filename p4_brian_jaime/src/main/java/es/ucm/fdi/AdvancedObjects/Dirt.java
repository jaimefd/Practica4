package es.ucm.fdi.AdvancedObjects;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.util.MultiTreeMap;

public class Dirt extends Road{

	public Dirt(String ident, int l, int maxV, Junction junction_i, Junction junction_f) {
		super(ident, l, maxV, junction_i, junction_f);
	}
	
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
