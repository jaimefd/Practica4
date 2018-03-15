package es.ucm.fdi.SimulatedObjects;

import java.util.ArrayDeque;
import java.util.Map;

import es.ucm.fdi.util.MultiTreeMap;

public class Road extends SimObject {
	protected int longitud, maxVel;
	protected MultiTreeMap<Integer, Vehicle> vehiculos;
	private boolean semaforo;
	private ArrayDeque<Vehicle> cola;
	private Junction ini, fin;
	
	public Road(String ident, int l, int maxV, Junction junction_i, Junction junction_f){
		super(ident);
		longitud = l;
		maxVel = maxV;
		ini = junction_i;
		fin = junction_f;
		cola = new ArrayDeque<>();
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
		if (!vehiculos.isEmpty()) {
			for (int i = longitud; i >= 0; --i){
				if (vehiculos.containsKey(i)){
					for(Vehicle v: vehiculos.get(i))
						s += "(" + v.getID() + "," + v.getPos() + "),";
				}
			}
			s = s.substring(0, s.length() - 1);
		}
		out.put("state", s);
	}
	
	public void avanza (){ // ***
		int velBase = Math.min(maxVel, maxVel / Math.max((int) vehiculos.sizeOfValues(), 1) + 1);
		int factorRed = 1;
		MultiTreeMap<Integer, Vehicle> map = new MultiTreeMap<>((a, b) -> a - b);
		if (vehiculos.containsKey(longitud)){
			for(Vehicle v: vehiculos.get(longitud)){
				map.putValue(longitud, v);
			}
		}
		for (int i = longitud - 1; i >= 0; --i){
			if (vehiculos.containsKey(i)){
				for(Vehicle v: vehiculos.get(i)){
					if (v.getAveria()) {
						factorRed = 2;
						v.setVelocidadActual(0);
					}
					else v.setVelocidadActual(velBase / factorRed);
					v.avanza();
					if (v.getPos() == longitud) {
						v.setVelocidadActual(0);
						cola.add(v);
					}
					map.putValue(v.getPos(), v);
				}
			}
		}
		vehiculos = map;
	}
}
