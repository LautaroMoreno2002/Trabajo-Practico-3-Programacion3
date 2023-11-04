package presenter;

import java.util.ArrayList;
import java.util.HashSet;
import model.Controlador;
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

	public ArrayList<Integer> calcularCGMBacktracking() 
	{
		return controlador.armarCGMBacktracking();
	}
	
	public ArrayList<Vertice> get_setConVecinos() 
	{
		return controlador.get_setConVecinos();
	}
	
	public HashSet<Integer> listaDeVecinos()
	{
		return controlador.listaDeVecinos();
	}
}