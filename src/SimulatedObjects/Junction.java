package SimulatedObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Junction extends SimObject { // Usar ArrayDeque
	private int k; //***
	private List<Road> entrantes;
	private List<Road> salientes;
	private Map<Junction, Road> mapSaliente;
	
	public Junction(String ident){
		super(ident);
		k = 0;
		entrantes = new ArrayList<>();
		salientes = new ArrayList<>();
		mapSaliente = new HashMap<>();
	}
	
	public void entraVehiculo(Vehicle v){
		for (Road r : entrantes)
			if (r == v.getRoad()) r.getQueue().add(v);
	}
	
	public void addSale(Road r) {
		salientes.add(r);
		mapSaliente.put(r.getFin(), r);
	}

	public void addEntra(Road r) {
		entrantes.add(r);
	}
	
	protected String getReportHeader(){
		return "junction_report";
	}
	
	protected void fillReportDetails(Map<String, String> out){
		String s = "";
		for (int i = 0; i < entrantes.size(); ++i){
			s += "(" + entrantes.get(i).getID() + ", ";
			if (entrantes.get(i).getSemaforo()) s += "green, [";
			else s += "red, [";
			for (Vehicle v : entrantes.get(i).getQueue())
				s += v.getID() + ", ";
			s = s.substring(0, s.length() - 2);
			s += "]), ";	
		}
		s = s.substring(0, s.length() - 2);
		out.put("queues", s);
	}
	
	public void avanza(){
		Vehicle v = entrantes.get(k).getQueue().getFirst();
		Road r = mapSaliente.get(v.sigCruce());
		v.moverASiguienteCarretera(r);
		entrantes.get(k).getQueue().pop();
		entrantes.get(k).setSemaforo(false);
		k++;
		if (k == entrantes.size()) k = 0;
		entrantes.get(k).setSemaforo(true);
	}
	
}
