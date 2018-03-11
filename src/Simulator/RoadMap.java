package Simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SimulatedObjects.Junction;
import SimulatedObjects.Road;
import SimulatedObjects.SimObject;
import SimulatedObjects.Vehicle;

public class RoadMap {

	private Map<String, SimObject> simObjects;
 
	private List<Junction> junctions = new ArrayList<>();
	private List<Road> roads = new ArrayList<>();
	private List<Vehicle> vehicles = new ArrayList<>();

	private List<Junction> junctionsRO;
	private List<Road> roadsRO;
	private List<Vehicle> vehiclesRO; 
	
	public RoadMap() {
		this.vehicles = new ArrayList<>();
		this.roads = new ArrayList<>();
		this.junctions = new ArrayList<>();
		this.simObjects = new HashMap<>();
	}
	
	public SimObject getSimObject(String id) {
		return simObjects.get(id);
	}
	
	public Vehicle getVehicle(String id) {
		return (Vehicle) getSimObject(id);
	}
	
	public Road getRoad(String id) {
		return (Road) getSimObject(id);
	}
	
	public Junction getJunction(String id) {
		return (Junction) getSimObject(id);
	}
	
	public List<Vehicle> getVehicles() {
		return vehiclesRO;
	}
		
	public List<Road> getRoads() {
		return roadsRO;
	}
	
	public List<Junction> getJunctions() {
		return junctionsRO;
	}
	
	public void addVehicle(Vehicle v) {
		simObjects.put(v.getID(), v);
		vehicles.add(v);
	}
	
	public void addRoad(Road r) {
		simObjects.put(r.getID(), r);
		roads.add(r);
	}
	
	public void addJunction(Junction j) {
		simObjects.put(j.getID(), j);
		junctions.add(j);
	}
	
}
