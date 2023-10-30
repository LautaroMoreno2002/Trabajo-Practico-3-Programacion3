package model;

import java.util.ArrayList;
import java.util.Set;
import conjuntoMinimo.SolverBacktracking;
import conjuntoMinimo.SolverGoloso;

public class Controlador implements Runnable {
	private int _idOpcion;
	private Grafo _grafo;
	private ArrayList<Integer> _conjuntoMinimoGoloso;
	private Set<Integer> _conjuntoMinimoBacktracking;
	
	public void setIndice(int indice) {
		if (indice < 0 || indice > 3) throw new IllegalArgumentException("Indice inválido");
		_idOpcion = indice;
		iniciarGrafo();
	}
	
	public ArrayList<Integer> armarCGMGoloso() 
	{
		long initialTime = System.currentTimeMillis();
		_conjuntoMinimoGoloso = SolverGoloso.conjuntoMinimo(_grafo.getVerticesConVecinos());
		System.out.println("El tiempo que le tomó calcular CGM goloso es :" + (System.currentTimeMillis()- initialTime));
		return _conjuntoMinimoGoloso;
	}		
	public Set<Integer> armarCGMBacktracking()
	{
		int inicioDelGrafo = _grafo.getVerticesConVecinos().get(0).getIdVertice();
		long initialTime = System.currentTimeMillis();
		_conjuntoMinimoBacktracking = new SolverBacktracking(_grafo).resolver(inicioDelGrafo);
		System.out.println("El tiempo que le tomó calcular CGM Back es :" + (System.currentTimeMillis() - initialTime));
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
	@Override
	public void run() 
	{	
		
	}
	
}