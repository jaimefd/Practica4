package es.ucm.fdi.AdvancedObjects;

import java.util.Map;

import es.ucm.fdi.SimulatedObjects.Junction;
import es.ucm.fdi.SimulatedObjects.Road;
import es.ucm.fdi.SimulatedObjects.Vehicle;

/** 
 * La clase MostCrowded representa un cruce congestionado del simulador.
 * @author Jaime Fernández y Brian Leiva
*/

public class MostCrowded extends Junction {
	private int intervaloDeTiempo;
	private int unidadesDeTiempoUsadas;
	
	/** 
	 * Constructor de la clase MostCrowded.
	 * @param ident : Identificador
	*/

	public MostCrowded(String ident) {
		super(ident);
		intervaloDeTiempo = 0;
		unidadesDeTiempoUsadas = 0;
	}
	
	/** 
	 * Informe de MostCrowded.
	 * @param out : Mapa para salida de datos
	*/
	protected void fillReportDetails(Map<String, String> out){
		super.fillReportDetails(out);
		out.put("type", "mc");
	}
	
	/** 
	 * Método avanza para MostCrowded.
	*/
	public void avanza(){
		if (k == -1) {
			k = 0;
		}
		else if (!entrantes.isEmpty()) {
			if (!entrantes.get(k).getQueue().isEmpty()) {
				Vehicle v = entrantes.get(k).getQueue().getFirst();
				if (!v.fin()) {
					Road r = mapSaliente.get(v.sigCruce());
					v.moverASiguienteCarretera(r);
				}
				else v.moverASiguienteCarretera(null);
				entrantes.get(k).getQueue().pop();
			}
			entrantes.get(k).setSemaforo(false);
			k++;
			if (k == entrantes.size()) k = 0;
			entrantes.get(k).setSemaforo(true);
		}
	}
	
}
