package model;

import java.util.ArrayList;
import java.util.Set;
import conjuntoMinimo.SolverBacktracking;
import conjuntoMinimo.SolverGoloso;

public class Controlador {
	private Grafo _grafo;
	private ArrayList<Integer> _conjuntoMinimoGoloso;
	private Set<Integer> _conjuntoMinimoBacktracking;
	
	public ArrayList<Integer> armarCGMGoloso(int idOpcion) 
	{
		switch (idOpcion) 
		{
		case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo2.JSON"); // cm = 4,5,7,10,15,17 posible
		case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo3.JSON");
		case 3 -> _grafo = Grafo.leerGrafoJSON("templateGrafo.JSON");
		}
		_conjuntoMinimoGoloso = SolverGoloso.conjuntoMinimo(_grafo.getVerticesConVecinos());
		return _conjuntoMinimoGoloso;
	}
	public Set<Integer> armarCGMBacktracking(int idOpcion)
	{
		switch (idOpcion)
		{
			case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
			case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo2.JSON");
			case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo3.JSON");
			case 3 -> _grafo = Grafo.leerGrafoJSON("TemplateGrafo.JSON");
		}
		int inicioDelGrafo = _grafo.getVerticesConVecinos().get(0).getIdVertice();
		_conjuntoMinimoBacktracking = new SolverBacktracking(_grafo).resolver(inicioDelGrafo);
		return _conjuntoMinimoBacktracking;
	}
}