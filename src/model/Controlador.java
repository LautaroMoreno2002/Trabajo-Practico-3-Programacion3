package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import conjuntoDominanteMinimo.SolverBacktracking;
import conjuntoDominanteMinimo.SolverGoloso;

public class Controlador
{
	private int _idOpcion;
	private Grafo _grafo;
	private ArrayList<Integer> _conjuntoMinimoGoloso;
	private ArrayList<Integer> _conjuntoMinimoBacktracking;

	public void setIndice(int indice) 
	{
		if (indice < 0 || indice > 3) 
			throw new IllegalArgumentException("Indice invalido");
		_idOpcion = indice;
		iniciarGrafo();
	}
	public ArrayList<Integer> armarCGMGoloso() 
	{
		_conjuntoMinimoGoloso = new SolverGoloso(_grafo).conjuntoGeneradorMinimo();
		return _conjuntoMinimoGoloso;
	}
	public ArrayList<Integer> armarCGMBacktracking()
	{
		_conjuntoMinimoBacktracking = convertirSetEnArrayList(new SolverBacktracking(_grafo).resolver());
		return _conjuntoMinimoBacktracking;
	}
	private void iniciarGrafo() 
	{
		switch (_idOpcion) 
		{
			case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
			case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo2.JSON");
			case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo3.JSON");
			case 3 -> _grafo = Grafo.leerGrafoJSON("templateGrafo.JSON");
		}
	}
	public ArrayList<Vertice> getSetConVecinos()
	{
		return _grafo.getVerticesConVecinos();
	}
	public HashSet<Integer> listaDeVecinos()
	{
		HashSet<Integer> listaDeVecinos = new HashSet<>();
		for(Vertice vertice: getSetConVecinos()) 
		{
			listaDeVecinos.addAll(vertice.getVecinos());
		}
		return listaDeVecinos;
	}
	private ArrayList<Integer> convertirSetEnArrayList(Set<Integer> set) 
	{
		return new ArrayList<Integer>(set);
	}
}