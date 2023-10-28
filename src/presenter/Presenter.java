package presenter;

import java.util.ArrayList;
import java.util.Set;
import model.Controlador;

public class Presenter {
	private Controlador controlador;
	
	public Presenter() {
		controlador = new Controlador();
	}

	public ArrayList<Integer> calcularCGMGoloso(int idOpcion) {
		return controlador.armarCGMGoloso(idOpcion);
	}

	public Set<Integer> calcularCGMBacktracking(int idOpcion) {
		return  controlador.armarCGMBacktracking(idOpcion);
	}
}