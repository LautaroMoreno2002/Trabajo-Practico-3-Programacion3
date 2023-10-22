package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Grafo {
	private int[][] _MA;
//	private ArrayList<HashSet<Integer>> _vecinos;
	private ArrayList<Vertice> _verticesConVecinos;
	
	public Grafo(int cantVertices) {
		if (cantVertices < 0) 
			throw new IllegalArgumentException("La cantidad de vértices para un grafo debe ser mayor o igual a cero");
		_MA = new int[cantVertices][cantVertices];
//		_vecinos = new ArrayList<HashSet<Integer>>();
		_verticesConVecinos = new ArrayList<Vertice>();
		inicializarGrafo();
	}
	public void agregarArista(int verticeA,int verticeB) {
		validarIndices(verticeA, verticeB);
		_MA[verticeA][verticeB] = _MA[verticeB][verticeA] = 1;
		//_vecinos.get(verticeA).add(verticeB);
		//_vecinos.get(verticeB).add(verticeA);
		agregarVecinosEnLosVertices(verticeA,verticeB);
		System.out.println();
		System.out.println(imprimirMatriz());
	}
	private void agregarVecinosEnLosVertices(int verticeA, int verticeB) {
		for (Vertice vertice : _verticesConVecinos) {
			if (vertice.get_idVertice() == verticeA)
				vertice.agregarVecino(verticeB);
			if (vertice.get_idVertice() == verticeB)
				vertice.agregarVecino(verticeA);
		}	
	}
	public boolean existeArista(int verticeA,int verticeB) {
		validarIndices(verticeA, verticeB);
		return _MA[verticeA][verticeB] == 1;
	}
	public void borrarArista(int verticeA,int verticeB) {
		validarIndices(verticeA, verticeB);
		_MA[verticeA][verticeB] = _MA[verticeB][verticeA] = 0;
//		_vecinos.get(verticeA).remove(verticeB);
//		_vecinos.get(verticeB).remove(verticeA);
		eliminarVecinoDeLosVertices(verticeA,verticeB);
		System.out.println(imprimirMatriz());
	}
	private void eliminarVecinoDeLosVertices(int verticeA, int verticeB) {
		for (Vertice vertice : _verticesConVecinos) {
			if (vertice.get_idVertice() == verticeA)
				vertice.eliminarVecino(verticeB);
			if (vertice.get_idVertice() == verticeB)
				vertice.eliminarVecino(verticeA);
		}	
	}
	public HashSet<Integer> vecinoDelVertice(int vertice){
//		return _vecinos.get(vertice);
		for (Vertice verticeActual : _verticesConVecinos)
			if (verticeActual.get_idVertice() == vertice) 
				return verticeActual.get_vecinos();
		return null;
	}
	public int cantidadVertices() {
		return _MA.length;
	}
		
	private void validarIndices(int verticeA,int verticeB) {
		if (verticeA == verticeB) 
			throw new IllegalArgumentException("Los vértices deben ser distintos para no generar un loop");
		if (verticeA < 0 || verticeB < 0) 
			throw new IllegalArgumentException("Los vértices deben ser positivos y existir dentro del grafo");
		else if (verticeA >= _MA.length || verticeB >= _MA.length) 
			throw new IllegalArgumentException("Los vértices deben existir dentro del grafo");
	}
	private void inicializarGrafo() {
		inicializarMAConCeroAristas();
//		inicializarVecinos();
		inicializarVertices();
		System.out.println(imprimirMatriz());
	}
	private void inicializarVertices() {
		for (int id = 0; id < _MA.length; id++) {
			_verticesConVecinos.get(id).set_idVertice(id);
		}
		
	}
	private void inicializarMAConCeroAristas() {
		for (int fila = 0; fila < _MA.length; fila++) {
			for (int columna = 0; columna < _MA.length; columna++) {
				_MA[fila][columna] = 0;
			}
		}
	}
//	private void inicializarVecinos() {
//		for (int vertice = 0; vertice < _MA.length; vertice++) {
//			_vecinos.add(new HashSet<Integer>());
//		}
//	}
	private String imprimirMatriz() {
		StringBuilder MA = new StringBuilder();
		MA.append("[");
		for (int fila = 0; fila < _MA.length; fila++) {
			for (int columna = 0; columna < _MA.length; columna++) {
				MA.append(_MA[fila][columna]).append(", ");
			}
			MA.append("\n");
		}
		MA.setLength(MA.length()-2);
		MA.append("]");
		return MA.toString();
	}
	
	// Prueba
	public String generarGrafoEnJSON() {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(this);
		
		return json;
	}
	// Prueba
	public void guardarGrafoEnJSON(String grafo, String nombreArchivo) {
		try {
			FileWriter writer = new FileWriter(nombreArchivo);
			writer.write(grafo);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public ArrayList<HashSet<Integer>> getVecinos() {
//		return _vecinos;
//	}
	public ArrayList<Vertice> getVerticesConVecinos(){
		return _verticesConVecinos;
	}
}
