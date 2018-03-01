import java.io.OutputStream;

import javafx.event.Event;

public class TrafficSimulator {
	private int contadorTiempo;
	public void insertaEvento(Event e) {
		
	}
	public void execute(int pasosSimulacion, OutputStream o){
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		while (this.contadorTiempo <= limiteTiempo) {
		// 1. ejecutar los eventos correspondientes a ese tiempo
		// 2. invocar al método avanzar de las carreteras
		// 3. invocar al método avanzar de los cruces
			this.contadorTiempo++;
		// 5. esciribir un informe en OutputStream en caso de que no sea null
		}
	}

}
