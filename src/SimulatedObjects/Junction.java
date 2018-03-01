package SimulatedObjects;

import java.util.ArrayList;
import java.util.Map;

public class Junction extends SimObject { // Usar ArrayDeque
	private int k, time;
	private ArrayList<Road> carreteras;
	
	public Junction(){
		carreteras = new ArrayList<>();
		k = 0;
	}
	
	public void entraVehiculo(String idVehiculo){
		
	}
	
	protected String getReportHeader(){
		return "junction_report";
	}
	
	protected void fillReportDetails(Map<String, String> out){
		String s = "";
		for (int i = 0; i < carreteras.size(); ++i){
			s += "(" + carreteras.get(i).getID() + ", ";
			if (carreteras.get(k).getSemaforo()) s += "green, [";
			else s += "red, [";
			for (Vehicle v : carreteras.get(i).getQueue())
				s += v.getID() + ", ";
			s = s.substring(0, s.length() - 2);
			s += "]), ";	
		}
		s = s.substring(0, s.length() - 2);
		out.put("queues", s);
	}
	
	public void avanza(){
		carreteras.get(k).getQueue().poll().moverASiguienteCarretera();
		carreteras.get(k).setSemaforo(false);
		k++;
		if (k == carreteras.size()) k = 0;
		carreteras.get(k).setSemaforo(true);
	}
	
	public String generaInforme(){
		String s = "[junction_report]\nid = " + id + "\ntime = " + time + "\nqueues = ";
		for (int i = 0; i < carreteras.size(); ++i){
			s += "(" + carreteras.get(i).getID() + ", ";
			if (carreteras.get(k).getSemaforo()) s += "green, [";
			else s += "red, [";
			for (Vehicle v : carreteras.get(i).getQueue())
				s += v.getID() + ", ";
			s = s.substring(0, s.length() - 2);
			s += "]), ";	
		}
		s = s.substring(0, s.length() - 2);
		s += "\n";
		return s;
	}
	
}
