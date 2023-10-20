package model;

import java.util.ArrayList;
import java.util.HashSet;

public class ConjuntoDominante {
	private static ArrayList<Integer> _verticesOrdenados;
	private static ArrayList<Integer> _verticesMarcados;
	private static ArrayList<Integer> _conjuntoMinimo;
	
	public static ArrayList<Integer> conjuntoDominante(ArrayList<HashSet<Integer>> vecinosDeVertices) 
	{
		_verticesOrdenados = ordenarVerticesSegunCantidadVecinos(vecinosDeVertices);
		
//		Idea:
//		Ordenar los vertices en un arreglo segùn su cantidad de vecinos

		_verticesOrdenados = ordenarVerticesSegunCantidadVecinos(vecinosDeVertices); 
		int i = 0;
		
		while (i < _verticesOrdenados.size()){
			if (!_verticesMarcados.contains(_verticesOrdenados.get(i)))
				_verticesMarcados.add(_verticesOrdenados.get(i));
			fijarmeSiLosVecinosEstanAñadidos(_verticesOrdenados.get(i),_verticesMarcados, vecinosDeVertices);
				_conjuntoMinimo.add(_verticesOrdenados.get(i));
				if (_verticesMarcados.size() == _verticesOrdenados.size())
					return _conjuntoMinimo;
			
			i++;
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
	
	
	
}
