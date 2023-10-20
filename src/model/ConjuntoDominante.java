package model;

import java.util.ArrayList;
import java.util.HashSet;

public class ConjuntoDominante {
	private static ArrayList<Integer> _verticesOrdenados;
	private static ArrayList<Integer> _verticesMarcados;
	private static ArrayList<Integer> _conjuntoMinimo;
	
	public static ArrayList<Integer> conjuntoDominante(ArrayList<HashSet<Integer>> vecinosDeVertices) 
	{	
//		Idea:
//		Ordenar los vertices en un arreglo segùn su cantidad de vecinos

		_verticesOrdenados = ordenarVerticesSegunCantidadVecinos(vecinosDeVertices); 
		_verticesMarcados = new ArrayList<Integer>();
		_conjuntoMinimo = new ArrayList<Integer>();
		int vertice = 0;
		
		while (vertice < _verticesOrdenados.size()){
			if (!_verticesMarcados.contains(_verticesOrdenados.get(vertice)))
				_verticesMarcados.add(_verticesOrdenados.get(vertice));
			fijarmeSiLosVecinosEstanAñadidos(_verticesOrdenados.get(vertice),_verticesMarcados, vecinosDeVertices);
			_conjuntoMinimo.add(_verticesOrdenados.get(vertice));
			if (_verticesMarcados.size() == _verticesOrdenados.size())
				return _conjuntoMinimo;
			
			vertice++;
		}
		return null;
	}
	private static void fijarmeSiLosVecinosEstanAñadidos(Integer vertice, ArrayList<Integer> _verticesMarcados2, ArrayList<HashSet<Integer>> vecinosDeVertices) {
		HashSet<Integer> vecinos = vecinosDeVertices.get(vertice);
		for (Integer vecino : vecinos)
			if (!_verticesMarcados.contains(vecino))
				_verticesMarcados.add(vecino);
		
	}

	private static ArrayList<Integer> ordenarVerticesSegunCantidadVecinos(ArrayList<HashSet<Integer>> vecinosDeVertices) 
	{
		ArrayList<Integer> vertices = new ArrayList<>();
		
		for(int i = 0; i < vecinosDeVertices.size(); i++) {
			vertices.add(i);
		}
		
		for (int i = 0; i < vecinosDeVertices.size(); i++) {
	        for (int j = vecinosDeVertices.size() - 1; j > i; j--) { 
	            if (vecinosDeVertices.get(j).size() > vecinosDeVertices.get(j - 1).size()) {  
	                swap(vecinosDeVertices, j, j - 1);
	                swap(vertices, j, j - 1);
	            }
	        }
	    }
		return vertices;	
	}

	private static <T> void swap(ArrayList<T> lista, int indice1, int indice2) 
	{
	    T aux = lista.get(indice1);
	    lista.set(indice1, lista.get(indice2));
	    lista.set(indice2, aux);
	}
	
	public static void main(String[] args) {
		Grafo g = new Grafo(6);
		g.agregarArista(0, 1);
		g.agregarArista(0, 4);
		g.agregarArista(1, 2);
		g.agregarArista(1, 4);
		g.agregarArista(2, 3);
		g.agregarArista(3, 4);
		g.agregarArista(3, 5);
		
		System.out.println(conjuntoDominante(g.getVecinos()).toString());
	}
	
}