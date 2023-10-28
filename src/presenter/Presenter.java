package presenter;

import java.util.ArrayList;
import model.Controlador;

public class Presenter {
	private Controlador controlador;
	
	public Presenter() {
		controlador = new Controlador();
	}

	public ArrayList<Integer> calcularCGMGoloso(int idOpcion) {
		return controlador.armarCGMGoloso(idOpcion);
	}

	public void calcularCGMBacktracking(int idOpcion) {
		controlador.armarCGMBacktracking(idOpcion);
	}
	
}
