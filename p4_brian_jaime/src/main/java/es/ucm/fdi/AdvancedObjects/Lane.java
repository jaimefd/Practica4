package es.ucm.fdi.AdvancedObjects;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.Vehicle;
import es.ucm.fdi.util.MultiTreeMap;

public class Lane extends Road{
	
	private int numCarriles;

	public Lane(String ident, int l, int maxV, Junction junction_i, Junction junction_f, int lanes) {
		super(ident, l, maxV, junction_i, junction_f);
		numCarriles = lanes;
	}
	
	public void avanza(){
		int velBase = Math.min(maxVel, maxVel * numCarriles / Math.max(vehiculos.size(), 1) + 1);
		int factorRed = 1, numObstaculos = 0;
		MultiTreeMap<Integer, Vehicle> map = new MultiTreeMap<>((a, b) -> a - b);
		for (int i = longitud - 1; i >= 0; --i){
			if (vehiculos.containsKey(i)){
				for(Vehicle v: vehiculos.get(i)){
					if (v.getAveria()) {
						numObstaculos++;
						if (numObstaculos >= numCarriles) {
							factorRed = 2;
							break;
						}
					}
				}
				if (numObstaculos >= numCarriles) break;
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