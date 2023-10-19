package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;




public class ConjuntoDominante {
	private static ArrayList<Integer> _verticesOrdenados;
	private ArrayList<Integer> _marcados;
	
	public static ArrayList<Integer> conjuntoDominante(ArrayList<HashSet<Integer>> vecinosDeVertices) 
	{
		_verticesOrdenados = ordenarVerticesSegunCantidadVecinos(vecinosDeVertices);
		return null;
	}

	private static ArrayList<Integer> ordenarVerticesSegunCantidadVecinos(ArrayList<HashSet<Integer>> vecinosDeVertices) 
	{
		ArrayList<Integer> vertices = new ArrayList<>();
		
		for(int i = 0; i < vecinosDeVertices.size(); i++) {
			vertices.add(i);
//			System.out.println(i);
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

	private static <T> void swap(ArrayList<T> lista, int indice1, int indice2) {
	    T aux = lista.get(indice1);
	    lista.set(indice1, lista.get(indice2));
	    lista.set(indice2, aux);
	}
	
	
}
