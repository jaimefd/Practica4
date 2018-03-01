package SimulatedObjects;

import java.util.ArrayList;
import java.util.Map;

public class Vehicle extends SimObject {
	private int velMaxima, velActual, localizacion, distTotal, k, tiempoAveria;
	private ArrayList<Road> itinerario;
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
	
	public boolean getAveria(){
		return tiempoAveria > 0;
	}
	
	public int getPos(){
		return localizacion;
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
		else {
			localizacion += velActual;
			if (localizacion >= itinerario.get(k).getLong()){
				localizacion = itinerario.get(k).getLong();
				if (!cruce){
					itinerario.get(k).getFin().entraVehiculo(id);
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
