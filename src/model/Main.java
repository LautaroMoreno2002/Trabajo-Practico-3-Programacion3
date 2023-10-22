package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {
//	public static void main(String[] args) {
//		Grafo g = new Grafo(6);
//		g.agregarArista(0, 1);
//		g.agregarArista(0, 4);
//		g.agregarArista(1, 2);
//		g.agregarArista(1, 4);
//		g.agregarArista(2, 3);
//		g.agregarArista(3, 4);
//		g.agregarArista(3, 5);
//		
//		String grafoJSON = g.generarGrafoEnJSON();
//		g.guardarGrafoEnJSON(grafoJSON, "EjemploGrafo.JSON");
//	}
	public static void main(String[] args) {
		Vertice v1 = new Vertice(1,new HashSet<Integer>());
		Vertice v2 = new Vertice(2,new HashSet<Integer>());
		Vertice v3 = new Vertice(3,new HashSet<Integer>());
		Vertice v4 = new Vertice(4,new HashSet<Integer>());
		Vertice v5 = new Vertice(5,new HashSet<Integer>());
		Vertice v6 = new Vertice(6,new HashSet<Integer>());

		v1.agregarVecino(2);v1.agregarVecino(5);
		v2.agregarVecino(3);v2.agregarVecino(1);v2.agregarVecino(5);
		v5.agregarVecino(2);v5.agregarVecino(1);v5.agregarVecino(4);
		v4.agregarVecino(3);v4.agregarVecino(5);v4.agregarVecino(6);
		v3.agregarVecino(4);v3.agregarVecino(2);
		v6.agregarVecino(4);
		
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		ArrayList<Vertice> vertices2 = new ArrayList<Vertice>();
		
		vertices.add(v1);vertices.add(v2);vertices.add(v3);vertices.add(v4);vertices.add(v5);vertices.add(v6);
		vertices2.add(v1);vertices2.add(v2);vertices2.add(v3);vertices2.add(v4);vertices2.add(v5);vertices2.add(v6);
		
		System.out.println("Original: \n"+vertices);
		Collections.sort(vertices);
		System.out.println("Original ordenado de menor a mayor: \n"+vertices);
		Collections.reverse(vertices);
		System.out.println("Original ordenado de mayor a menor:  \n"+vertices);
		System.out.println();
		System.out.println("Copia: \n"+vertices2);
		Collections.reverse(vertices2);
		System.out.println("Copia inversa: \n" + vertices2);
		Collections.sort(vertices2);
		System.out.println("Copia inversa ordenada: \n"+ vertices2);
		Collections.reverse(vertices2);
		System.out.println("Copia inversa ordenada de mayor a menor: \n"+vertices2);
		
		System.out.println("Conjunto minimo del conjunto vertices1: "+ConjuntoDominante.conjuntoMinimo(vertices2).toString());
		System.out.println("Conjunto minimo del conjunto vertices2: "+ConjuntoDominante.conjuntoMinimo(vertices).toString());
		
	}
}
