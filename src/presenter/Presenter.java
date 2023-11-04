package presenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.Controlador;
import model.Grafo;
import model.Vertice;

public class Presenter 
{
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
	
	public ArrayList<Vertice> get_setConVecinos() {
		return controlador.get_setConVecinos();
	}
	
	public HashSet<Integer> listaDeVecinos(){
		return controlador.listaDeVecinos();
	}
	
}