package presenter;

import model.Controlador;

public class Presenter {
	private Controlador controlador;
	
	public Presenter() {
		controlador = new Controlador();
	}

	public void calcularCGMGoloso(int idOpcion) {
		controlador.armarCGMGoloso(idOpcion);
	}

	public void calcularCGMBacktracking(int idOpcion) {
		controlador.armarCGMBacktracking(idOpcion);
	}
	
}
