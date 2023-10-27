package model;

import conjuntoMinimo.SolverBacktracking;
import conjuntoMinimo.SolverGoloso;

public class Controlador {
	private Grafo _grafo;
	
	public static void main(String[] args) {
		Grafo gEjemploTP = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		System.out.println("El grafo ingresado tiene : "+ gEjemploTP.cantidadVertices()+ " vertices");
		System.out.println("El grafo esta compuesto por: \n"+gEjemploTP.getVerticesConVecinos());
		System.out.println("El Conjunto Dominante Minimo del grafo ingresado es: \n"+SolverGoloso.conjuntoMinimo(gEjemploTP.getVerticesConVecinos()));
		System.out.println("Luego de calcular el conjunto minimo, el grafo queda ordenado de esta forma: \n"+gEjemploTP.getVerticesConVecinos());
	
		Grafo grafo = new Grafo(6);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 1);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);
		
		SolverBacktracking sol = new SolverBacktracking(grafo);
		System.out.println(sol.resolver());
	}
	public void armarCGMGoloso(int idOpcion) {
		switch (idOpcion) {
		case 0 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		case 1 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		case 2 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		case 3 -> _grafo = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		}
		
	}
	public void armarCGMBacktracking(int idOpcion) {
		
	}

//	public void calcularCGMGoloso() {
//		grafo = leerGrafoDelJSON();
//	}
	
}