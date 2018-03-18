package es.ucm.fdi.SimulatedObjects;

import java.util.ArrayList;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class VehicleTest {
	List<Junction> itinerario = new ArrayList<>();
	
	@Test
	public void vehicleTest() {
		Junction j1 = new Junction("j1");
		Junction j2 = new Junction("j2");
		itinerario.add(j1);
		itinerario.add(j2);
		Road r1 = new Road("r1", 15, 20, itinerario.get(0), itinerario.get(1));
		itinerario.get(0).addSale(r1);
		itinerario.get(1).addEntra(r1);
		Vehicle v1 = new Vehicle("v1", 10, itinerario);
		
		v1.moverASiguienteCarretera(r1);
		assertTrue("El vehículo no entra en la carretera", v1.getCarretera() == r1);
		
		v1.setVelocidadActual(20);
		v1.avanza();
		assertFalse("El vehículo va a más velocidad de la que puede", v1.getPos() > 10);
		assertTrue("El vehículo va a una velocidad incorrecta", v1.getPos() == 10);
		
		v1.setTiempoAveria(1);
		v1.avanza();
		assertFalse("El vehículo no está averiado", v1.getPos() > 10);
		
		v1.avanza();
		assertFalse("El vehículo se ha salido de la carretera", v1.getPos() > 15);
		assertTrue("El vehículo no ha llegado a su destino", v1.fin());

	}
}