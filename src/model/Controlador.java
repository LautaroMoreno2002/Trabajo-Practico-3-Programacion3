package model;

import java.util.Set;

import conjuntoMinimo.SolverBacktracking;
import conjuntoMinimo.SolverGoloso;

public class Controlador {
	private Grafo _grafo;
	private SolverBacktracking _solverB;
	
	
	public static void main(String[] args) {
		Grafo gEjemploTP = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		System.out.println("El grafo ingresado tiene : "+ gEjemploTP.cantidadVertices()+ " vertices");
		System.out.println("El grafo esta compuesto por: \n"+gEjemploTP.getVerticesConVecinos());
		System.out.println("El Conjunto Dominante Minimo del grafo ingresado es: \n"+SolverGoloso.conjuntoMinimo(gEjemploTP.getVerticesConVecinos()));
		System.out.println("Luego de calcular el conjunto minimo, el grafo queda ordenado de esta forma: \n"+gEjemploTP.getVerticesConVecinos());
	
//		Grafo grafo = new Grafo(6);
//		grafo.agregarArista(0, 1);
//		grafo.agregarArista(0, 4);
//		grafo.agregarArista(1, 4);
//		grafo.agregarArista(1, 2);
//		grafo.agregarArista(2, 1);
//		grafo.agregarArista(2, 3);
//		grafo.agregarArista(3, 4);
//		grafo.agregarArista(3, 5);
//		
//		SolverBacktracking sol = new SolverBacktracking(grafo);
//		System.out.println(sol.resolver());
		
		Grafo grafo = Grafo.leerGrafoJSON("EjemploGrafo2.JSON");
		SolverBacktracking sol = new SolverBacktracking(grafo);
		System.out.println(sol.resolver(0));
	}
	
	public void armarCGMGoloso(int idOpcion) {
		switch (idOpcion) {
		case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo2.JSON");
		case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo3.JSON");
		case 3 -> _grafo = Grafo.leerGrafoJSON("templateGrafo.JSON");
		}
	}
	public Set<Integer> armarCGMBacktracking(int idOpcion) {
		switch (idOpcion){
			case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
			case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
			case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
			case 3 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		}
		return new SolverBacktracking(_grafo).resolver(0);
	}
	
	

//	public void calcularCGMGoloso() {
//		grafo = leerGrafoDelJSON();
//	}
	
}