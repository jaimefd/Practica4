package es.ucm.fdi.AdvancedObjects;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Vehicle;

public class Car extends Vehicle{
	
	private int dist, resistenciaKm, probabilidadDeAveria, duracionMaximaAveria;
	private Random numAleatorio;

	public Car(String ident, int vmax, List<Junction> it, int resistance, int fault_probability, int max_fault_duration, long semilla) {
		super(ident, vmax, it);
		dist = 0;
		resistenciaKm = resistance;
		probabilidadDeAveria = fault_probability;
		duracionMaximaAveria = max_fault_duration;
		numAleatorio = new Random(semilla);
	}
	
	public void avanza(){
		if (super.getAveria() && dist >= resistenciaKm && numAleatorio.nextDouble() < probabilidadDeAveria)
			super.setTiempoAveria(numAleatorio.nextInt(duracionMaximaAveria) + 1);
		super.avanza();
		dist = distTotal - dist;
	}

}
