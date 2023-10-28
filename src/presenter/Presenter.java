package presenter;

import java.util.ArrayList;

import model.Controlador;

public class Presenter {
	private Controlador controlador;
	
	public Presenter() {
		controlador = new Controlador();
	}

	public void calcularCGMGoloso(int idOpcion) {
		controlador.armarCGMGoloso(idOpcion);
	}

	public ArrayList<Integer> calcularCGMBacktracking(int idOpcion) {
		ArrayList<Integer> ret = new ArrayList<>();
		return (ArrayList<Integer>) controlador.armarCGMBacktracking(idOpcion);
	}
	
}
