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
	private Set<Integer> _conjuntoMinimoBacktracking;
	
	public void setIndice(int indice) {
		if (indice < 0 || indice > 3) throw new IllegalArgumentException("Indice invalido");
		_idOpcion = indice;
		iniciarGrafo();
	}
	
	public ArrayList<Integer> armarCGMGoloso() 
	{
		long initialTime = System.currentTimeMillis();
		_conjuntoMinimoGoloso = new SolverGoloso(_grafo).conjuntoGeneradorMinimo();
		System.out.println("El tiempo que le tomo calcular CGM goloso es :" + (System.currentTimeMillis()- initialTime));
		return _conjuntoMinimoGoloso;
	}		
	public Set<Integer> armarCGMBacktracking()
	{
		long initialTime = System.currentTimeMillis();
		_conjuntoMinimoBacktracking = new SolverBacktracking(_grafo).resolver();
		System.out.println("El tiempo que le tomo calcular CGM Back es :" + (System.currentTimeMillis() - initialTime));
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

	//Mi método
	public ArrayList<Vertice> get_setConVecinos() {
		return _grafo.getVerticesConVecinos();
	}
	
	public HashSet<Integer> listaDeVecinos(){
		HashSet<Integer> listaDeVecinos = new HashSet<>();
		for(Vertice vertice: get_setConVecinos()) {
			listaDeVecinos.addAll(vertice.getVecinos());
		}
		return listaDeVecinos;
	}

}