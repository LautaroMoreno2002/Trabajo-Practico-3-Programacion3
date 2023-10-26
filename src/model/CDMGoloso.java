package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class CDMGoloso {
	private static ArrayList<Vertice> _verticesOrdenados;
	private static ArrayList<Integer> _verticesMarcados;
	private static ArrayList<Integer> _conjuntoMinimo;
	
//	public static ArrayList<Integer> conjuntoDominante(ArrayList<HashSet<Integer>> vecinosDeVertices) 
//	{	
////		Idea:
////		Ordenar los vertices en un arreglo segùn su cantidad de vecinos
//
//		System.out.println("\n Pre Orden "+vecinosDeVertices.toString());
//		_verticesOrdenados = ordenarVerticesSegunCantidadVecinos(vecinosDeVertices); 
//		System.out.println("\n Post Orden "+_verticesOrdenados.toString());
//		_verticesMarcados = new ArrayList<Integer>();
//		_conjuntoMinimo = new ArrayList<Integer>();
//		int vertice = 0;
//		
//		while (vertice < _verticesOrdenados.size()){
//			System.out.println("Inicia: "+_verticesMarcados.toString());
//			fijarmeSiLosVecinosEstanAñadidos(_verticesOrdenados.get(vertice),_verticesMarcados, vecinosDeVertices);
//			if (!_verticesMarcados.contains(_verticesOrdenados.get(vertice)))
//				_verticesMarcados.add(_verticesOrdenados.get(vertice));
//			if (_verticesMarcados.size() == _verticesOrdenados.size())
//				return _conjuntoMinimo;
//			
//			_conjuntoMinimo.add(_verticesOrdenados.get(vertice));
//			vertice++;
//			System.out.println("Termina: "+_verticesMarcados.toString());
//		}
//		return _conjuntoMinimo;
//	}
//	private static void fijarmeSiLosVecinosEstanAñadidos(Integer vertice, ArrayList<Integer> _verticesMarcados2, ArrayList<HashSet<Integer>> vecinosDeVertices) {
//		HashSet<Integer> vecinos = vecinosDeVertices.get(vertice);
//		System.out.println("vecinos de " + vertice+ " son " + vecinos.toString());
//		for (Integer vecino : vecinos)
//			if (!_verticesMarcados.contains(vecino))
//				_verticesMarcados.add(vecino);
//		
//	}
//	// OPCIONAL : IMPLEMENTARLO CON COMPARATE TO Y COLLECTIONS.SORT()
//	private static ArrayList<Integer> ordenarVerticesSegunCantidadVecinos(ArrayList<HashSet<Integer>> vecinosDeVertices) 
//	{
//		ArrayList<Integer> vertices = new ArrayList<>();
//		
//		for(int i = 0; i < vecinosDeVertices.size(); i++) {
//			vertices.add(i);
//		}
//		
//		for (int i = 0; i < vecinosDeVertices.size(); i++) {
//	        for (int j = vecinosDeVertices.size() - 1; j > i; j--) { 
//	            if (vecinosDeVertices.get(j).size() > vecinosDeVertices.get(j - 1).size()) {  
//	                swap(vecinosDeVertices, j, j - 1);
//	                swap(vertices, j, j - 1);
//	            }
//	        }
//	    }
//		return vertices;	
//	}
//
//	private static <T> void swap(ArrayList<T> lista, int indice1, int indice2) 
//	{
//	    T aux = lista.get(indice1);
//	    lista.set(indice1, lista.get(indice2));
//	    lista.set(indice2, aux);
//	}
	
//	public static void main(String[] args) {
//		Grafo g = new Grafo(6);
//		g.agregarArista(0, 1);
//		g.agregarArista(0, 4);
//		g.agregarArista(1, 4);
//		g.agregarArista(1, 2);
//		g.agregarArista(2, 3);
//		g.agregarArista(3, 5);
//		g.agregarArista(3, 4);
//		System.out.println(conjuntoDominante(g.getVecinos()).toString());
//	}
	
	/* OTRA IDEA  
	 * Usar un ArrayList<Tupla<Integer,<HashSet<Integer>>>> para guardar los vertices como
	 			Tupla<nroVertice, conjuntoVecinos>
	 * Collections.sort(verticesVecinos, comparadorDeVecinos)
	 * Implementar comparador 
	 * compareTo(Tupla a, Tupla b){
	 		return a.size() - b.size();
	 * }
	 [0,1,2,3,4,5] [2,4,5,0,...]
	 [5,4,3,2,1,0] [5,4,2,3,...]
	 */
	public static ArrayList<Integer> conjuntoMinimo(ArrayList<Vertice> vertices2){
		ordenarVerticesSegunCantidadDeVecinos(vertices2);
		
		_verticesOrdenados = vertices2;
		_verticesMarcados = new ArrayList<Integer>();
		_conjuntoMinimo = new ArrayList<Integer>();
		
		int i = 0;
		while (_verticesMarcados.size() < _verticesOrdenados.size()) {
			marcarVecinos(_verticesOrdenados.get(i),_verticesMarcados);
			_conjuntoMinimo.add(_verticesOrdenados.get(i).getIdVertice());
			i++;
		}
		return _conjuntoMinimo;
	}
	private static void ordenarVerticesSegunCantidadDeVecinos(ArrayList<Vertice> vertices2) {
		if (revertir()) Collections.reverse(vertices2);
		Collections.sort(vertices2);
		Collections.reverse(vertices2);
	}
	private static boolean revertir() {
		//return Math.floor(Math.random()*2) == 0 ? true : false;
		Random r = new Random();
		return r.nextBoolean();
	}
	private static void marcarVecinos(Vertice vertice, ArrayList<Integer> _verticesMarcados) {
		HashSet<Integer> vecinos = vertice.getVecinos();
		for (Integer vecino : vecinos) {
			if (!_verticesMarcados.contains(vecino))
				_verticesMarcados.add(vecino);
		}
		if (!_verticesMarcados.contains(vertice.getIdVertice())) _verticesMarcados.add(vertice.getIdVertice());
	}
}