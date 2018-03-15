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
		itinerario = it;
		k = 1;
		tiempoAveria = 0;
		haLlegado = false;
	}
	
	public boolean getAveria(){
		return tiempoAveria > 0;
	}
	
	public boolean fin(){
		return k == itinerario.size();
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
			s = "(" + roadActual.getID() + "," + localizacion + ")";
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
				distTotal -= (localizacion - roadActual.getLong());
				localizacion = roadActual.getLong();
				++k;
			}
		}
	}
	
	public void moverASiguienteCarretera(Road r){
		if (k > 1) roadActual.saleVehiculo(this);
		roadActual = r;
		localizacion = 0;
		velActual = 0;
		if (k == itinerario.size()) haLlegado = true;
		else roadActual.entraVehiculo(this);
	}
	
	public void setTiempoAveria(int n) {
		tiempoAveria += n;
	}
	
	public void setVelocidadActual(int v){
		if (v > velMaxima) velActual = velMaxima;
		else velActual = v;
	}
	
}
