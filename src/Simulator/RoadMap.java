package Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import SimulatedObjects.Junction;
import SimulatedObjects.Road;
import SimulatedObjects.SimObject;
import SimulatedObjects.Vehicle;

public class RoadMap {

	// búsqueda por ids, unicidad
	private Map<String, SimObject> simObjects;
	// listados reales
	private List<Junction> junctions = new ArrayList<>();
	private List<Road> roads = new ArrayList<>();
	private List<Vehicle> vehicles = new ArrayList<>();
	// listados read-only, via Collections.unmodifiableList();
	private List<Junction> junctionsRO;
	private List<Road> roadsRO;
	private List<Vehicle> vehiclesRO; 
	
	
}
