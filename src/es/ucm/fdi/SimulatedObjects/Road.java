package es.ucm.fdi.SimulatedObjects;

import java.util.ArrayDeque;
import java.util.Map;

import es.ucm.fdi.util.MultiTreeMap;

public class Road extends SimObject {
	private int longitud, maxVel;
	private MultiTreeMap<Integer, Vehicle> vehiculos;
	private boolean semaforo;
	private ArrayDeque<Vehicle> cola;
	private Junction ini, fin;
	
	public Road(String ident, int l, int maxV, Junction junction_i, Junction junction_f){
		super(ident);
		longitud = l;
		maxVel = maxV;
		ini = junction_i;
		fin = junction_f;
		vehiculos = new MultiTreeMap<>((a, b) -> a - b);
	}
	
	public ArrayDeque<Vehicle> getQueue(){
		return cola;
	}
	
	public int getLong(){
		return longitud;
	}
	
	public Junction getIni(){
		return ini;
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
		vehiculos.putValue(0, v);
	}
	
	public void saleVehiculo (Vehicle v){
		vehiculos.removeValue(longitud, v);
	}
	
	protected String getReportHeader(){
		return "road_report";
	}
	
	protected void fillReportDetails(Map<String, String> out){
		String s = "";
		for (Vehicle v : vehiculos.innerValues())
			s += "(" + v.getID() + ", " + v.getPos() + "), ";
		s = s.substring(0, s.length() - 2);
		out.put("state", s);
	}
	
	public void avanza (){ // ***
		int velBase = Math.min(maxVel, maxVel / Math.max(vehiculos.size(), 1) + 1);
		int factorRed = 1;
		for (Vehicle v : vehiculos.innerValues()){
			if (factorRed == 1){
				if (v.getAveria()) factorRed = 2;
			}
			v.setVelocidadActual(velBase / factorRed);
			v.avanza();
		}
	}
}
