package SimulatedObjects;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Road {
	private String id;
	private int longitud, maxVel, velBase;
	private ArrayList<Vehicle> vehiculos;
	private boolean semaforo;
	private ArrayDeque<Vehicle> cola;
	private Junction ini, fin;
	
	public Road(int l, int maxV){
		longitud = l;
		maxVel = maxV;
		vehiculos = new ArrayList<>();
	}

	public ArrayDeque<Vehicle> getQueue(){
		return cola;
	}
	
	public String getID(){
		return id;
	}
	
	public int getLong(){
		return longitud;
	}
	
	public Junction getFin(){
		return fin;
	}
	
	public boolean getSemaforo(){
		return semaforo;
	}
	
	public void setSemaforo (boolean b){
		semaforo = b;
	}
	
	public void entraVehiculo (Vehicle v){
		vehiculos.add(v);
	}
	
	public void saleVehiculo (Vehicle v){
		vehiculos.remove(v);
	}
	
	public void avanza (){ // vehiculos = new MTM ((a, b) -> a < b
		velBase = Math.min(maxVel, maxVel / Math.max(vehiculos.size(), 1) + 1);
		int factorRed = 1;
		for (int i = vehiculos.size() - 1; i >= 0; --i){
			if (factorRed == 1){
				if (vehiculos.get(i).getAveria()) factorRed = 2;
			}
			vehiculos.get(i).setVelocidadActual(velBase / factorRed);
			vehiculos.get(i).avanza();
		}
	}

	public String generaInforme (){
		String s = "[road_report]\nid = " + id + "\ntime = " + time + "\nstate = ";
		for (int i = 0; i < vehiculos.size(); ++i)
			s += "(" + vehiculos.get(i).getID() + ", " + vehiculos.get(i).getPos() + "), ";
		s = s.substring(0, s.length() - 2);
		s += "\n";
		return s;
	}
}
