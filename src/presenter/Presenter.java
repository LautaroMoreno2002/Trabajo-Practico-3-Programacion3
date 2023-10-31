package presenter;

import java.util.ArrayList;
import java.util.Set;
import model.Controlador;

public class Presenter {
	private Controlador controlador;
	
	public Presenter() 
	{
		controlador = new Controlador();
	}
	
	public void elegirGrafo(int indice) 
	{
		controlador.setIndice(indice);
	}
	
	public ArrayList<Integer> calcularCGMGoloso() 
	{
		return controlador.armarCGMGoloso();
	}

	public Set<Integer> calcularCGMBacktracking() 
	{
		return controlador.armarCGMBacktracking();
	}
}