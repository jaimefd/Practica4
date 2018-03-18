package es.ucm.fdi.SimulatedObjects;

import java.util.ArrayList;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class RoadTest {
	List<Junction> itinerario = new ArrayList<>();

	@Test
	public void roadTest() {
		Junction j1 = new Junction("j1");
		Junction j2 = new Junction("j2");
		Junction j3 = new Junction("j3");
		itinerario.add(j1);
		itinerario.add(j2);
		itinerario.add(j3);
		Road r1 = new Road("r1", 15, 20, itinerario.get(0), itinerario.get(1));
		Road r2 = new Road("r2", 20, 20, itinerario.get(1), itinerario.get(2));
		Vehicle v1 = new Vehicle("v1", 12, itinerario);
		Vehicle v2 = new Vehicle("v2", 15, itinerario);
		v1.moverASiguienteCarretera(r1);
		v2.moverASiguienteCarretera(r1);
		
		r1.avanza();
		assertTrue("El vehículo va a una velocidad incorrecta (fallo al calcular velBase sin avería))", v1.getPos() == 11 && v2.getPos() == 11);
		
		r1.avanza();
		assertTrue("Los vehículos no han entrado en la cola", r1.getQueue().size() == 2);
		assertTrue("Los vehículos no se insertan de forma correcta en la cola", r1.getQueue().getFirst() == v1);

		v1.moverASiguienteCarretera(r2);
		r1.avanza();
		r2.avanza();
		assertTrue("v2 debe estar al final de r1", v2.getPos() == r1.longitud);
		
		v2.moverASiguienteCarretera(r2);
		v1.setTiempoAveria(2);
		r2.avanza();
		assertTrue("Los vehículos van a velocidades incorrectas (fallo al calcular velBase con avería))", v1.getPos() == 12 && v2.getPos() == 5);
		
		r2.avanza();
		r2.avanza();
		v1.moverASiguienteCarretera(null);
		v2.moverASiguienteCarretera(null);
		assertTrue("Los vehículos no han llegado a su destino (siguen en r2)", r1.vehiculos.sizeOfValues() == 0);
	}
}