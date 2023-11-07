package presenter;

import java.util.ArrayList;
import java.util.HashSet;
import model.Controlador;
import model.Vertice;

public class Presenter 
{
	private Controlador _controlador;
	
	public Presenter() 
	{
		_controlador = new Controlador();
	}
	public void elegirGrafo(int indice) 
	{
		_controlador.setIndice(indice);
	}
	public ArrayList<Integer> calcularCGMGoloso() 
	{
		return _controlador.armarCGMGoloso();
	}
	public ArrayList<Integer> calcularCGMBacktracking() 
	{
		return _controlador.armarCGMBacktracking();
	}
	public ArrayList<Vertice> get_setConVecinos() 
	{
		return _controlador.getSetConVecinos();
	}
	public HashSet<Integer> listaDeVecinos()
	{
		return _controlador.listaDeVecinos();
	}
}