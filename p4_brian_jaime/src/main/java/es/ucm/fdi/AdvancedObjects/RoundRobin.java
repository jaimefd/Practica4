package es.ucm.fdi.AdvancedObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.Vehicle;

/** 
 * La clase RoundRobin representa un cruce circular.
 * @author Jaime Fernández y Brian Leiva
*/

public class RoundRobin extends Junction{
	private int maxValorIntervalo, minValorIntervalo, unidadesDeTiempoUsadas, usos;
	private List<Integer> intervaloDeTiempo;

	/** 
	 * Constructor de la clase RoundRobin.
	 * @param ident : Identificador
	*/

	public RoundRobin(String ident, int max, int min) {
		super(ident);
		maxValorIntervalo = max;
		minValorIntervalo = min;
		unidadesDeTiempoUsadas = 0;
		usos = 0;
		intervaloDeTiempo = new ArrayList<>();
	}
	
	/** 
	 * Añade una carretera entrante.
	 * @param r : Carretera
	*/
	public void addEntra(Road r) {
		intervaloDeTiempo.add(maxValorIntervalo);
		super.addEntra(r);
	}
	
	/**
	 * Informe de RoundRobin
	 * @param out : Mapa con los datos de RoundRobin
	 */
	protected void fillReportDetails(Map<String, String> out){
		String s = "";
		out.put("type", "rr");
		if (!entrantes.isEmpty()) {
			for (int i = 0; i < entrantes.size(); ++i){
				s += "(" + entrantes.get(i).getID() + ",";
				if (entrantes.get(i).getSemaforo()) s += "green,:" + (intervaloDeTiempo.get(i) - unidadesDeTiempoUsadas) + "[";
				else s += "red,[";
				if (!entrantes.get(i).getQueue().isEmpty()) {
					for (Vehicle v : entrantes.get(i).getQueue())
						s += v.getID() + ",";
					s = s.substring(0, s.length() - 1);
				}
				s += "]),";
			}
			s = s.substring(0, s.length() - 1);
		}
		out.put("queues", s);
	}
	
	/** 
	 * Método avanza para RoundRobin.
	*/	
	public void avanza(){
		if (k == -1) {
			k = 0;
		}
		else if (!entrantes.isEmpty()) {
			if (!entrantes.get(k).getQueue().isEmpty()) {
				++usos;
				Vehicle v = entrantes.get(k).getQueue().getFirst();
				if (!v.fin()) {
					Road r = road(v);
					v.moverASiguienteCarretera(r);
				}
				else v.moverASiguienteCarretera(null);
				entrantes.get(k).getQueue().pop();
			}
			if (unidadesDeTiempoUsadas == intervaloDeTiempo.get(k)) {
				entrantes.get(k).setSemaforo(false);
				if (usos == unidadesDeTiempoUsadas) 
					intervaloDeTiempo.set(k, Math.max((int) intervaloDeTiempo.get(k) - 1, minValorIntervalo));
				else if (usos == 0) 
					intervaloDeTiempo.set(k, Math.max((int) intervaloDeTiempo.get(k) - 1, minValorIntervalo));
				unidadesDeTiempoUsadas = 0;
				usos = 0;
				k++;
				if (k == entrantes.size()) k = 0;
				entrantes.get(k).setSemaforo(true);
			}
			else ++unidadesDeTiempoUsadas;
		}
	}
	
}
