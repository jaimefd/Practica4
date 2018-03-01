package SimulatedObjects;

import java.util.ArrayList;

public class Vehicle {
	private String id;
	private int velMaxima, velActual, localizacion, distTotal, k, tiempoAveria;
	private ArrayList<Junction> itinerario;
	private boolean cruce, haLlegado;
	
	public Vehicle(int vmax, String it){
		velMaxima = vmax;
		velActual = 0;
		distTotal = 0;
		localizacion = 0;
		itinerario = new ArrayList<>();
		k = 0;
		tiempoAveria = 0;
		haLlegado = false;
	}
	
	public String getID(){
		return id;
	}
	
	public boolean getAveria(){
		return tiempoAveria > 0;
	}
	
	public int getPos(){
		return localizacion;
	}
	
	public void avanza(){
		if (tiempoAveria > 0) --tiempoAveria;
		else {
			localizacion += velActual;
			if (localizacion >= itinerario.get(k).getLong()){
				localizacion = itinerario.get(k).getLong();
				if (!cruce){
					itinerario.get(k).entraVehiculo(id);
					++k;
				}
			}
		}
	}
	
	public void moverASiguienteCarretera(){
		k++;
		distTotal += localizacion;
		localizacion = 0;
		if (k == itinerario.size()) haLlegado = true;
	}
	
	public void setTiempoAveria(int n) {
		tiempoAveria += n;
	}
	
	public void setVelocidadActual(int v){
		if (v > velMaxima) velActual = velMaxima;
		else velActual = v;
	}
	
	public String generaInforme (){
		String s = "[vehicle_report]\nid = " + id + "\ntime = " + time + "\nspeed = " + velActual;
		s += "\nkilometrage = "+ distTotal + "\nfaulty = " + tiempoAveria + "\nlocation = ";
		if (!haLlegado) {
			s += "(" + itinerario.get(k).getID() + ", " + localizacion + ")\n";
		}
		else s += "arrived\n";
		return s;
	}
}