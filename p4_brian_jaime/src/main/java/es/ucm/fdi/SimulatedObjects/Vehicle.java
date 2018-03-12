package es.ucm.fdi.SimulatedObjects;

import java.util.List;
import java.util.Map;

public class Vehicle extends SimObject {
	protected int velMaxima, velActual, distTotal;
	private int localizacion, k, tiempoAveria;
	private Road roadActual;
	private List<Junction> itinerario;
	private boolean haLlegado;
	
	public Vehicle(String ident, int vmax, List<Junction> it){
		super(ident);
		velMaxima = vmax;
		velActual = 0;
		distTotal = 0;
		localizacion = 0;
		itinerario = it;
		k = 1;
		tiempoAveria = 0;
		haLlegado = false;
	}
	
	public boolean getAveria(){
		return tiempoAveria > 0;
	}
	
	public Road getRoad(){
		return roadActual;
	}
	
	public int getPos(){
		return localizacion;
	}
	
	public Road getCarretera(){
		return roadActual;
	}
	
	public Junction sigCruce() {
		return itinerario.get(k);
	}
	
	protected String getReportHeader(){
		return "vehicle_report";
	}
	
	protected void fillReportDetails(Map<String, String> out){
		String s;
		if (!haLlegado) {
			s = "(" + itinerario.get(k).getID() + ", " + localizacion + ")";
		}
		else s = "arrived";
		out.put("speed", String.valueOf(velActual));
		out.put("kilometrage", String.valueOf(distTotal));
		out.put("faulty", String.valueOf(tiempoAveria));
		out.put("location", s);
	}
	
	public void avanza(){
		if (tiempoAveria > 0) --tiempoAveria;
		else if (!haLlegado){
			localizacion += velActual;
			distTotal += velActual;
			if (localizacion >= roadActual.getLong()){
				localizacion = roadActual.getLong();
				distTotal += (roadActual.getLong() + localizacion);
				if (k < itinerario.size()){
					roadActual.entraVehiculo(this);
					++k;
				}
			}
		}
	}
	
	public void moverASiguienteCarretera(Road r){
		roadActual = r;
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
	
	public String generaInforme (int time){
		String s = "[vehicle_report]\nid = " + id + "\ntime = " + time + "\nspeed = " + velActual;
		s += "\nkilometrage = "+ distTotal + "\nfaulty = " + tiempoAveria + "\nlocation = ";
		if (!haLlegado) {
			s += "(" + itinerario.get(k).getID() + ", " + localizacion + ")\n";
		}
		else s += "arrived\n";
		return s;
	}
}
