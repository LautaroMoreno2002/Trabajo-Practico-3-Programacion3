package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Grafo 
{
	private ArrayList<Vertice> _verticesConVecinos;
	private static Boolean jsonEsCorrecto;
	
	public Grafo(int cantVertices) 
	{ // Para crear el grafo de 0
		if (cantVertices < 0) 
			throw new IllegalArgumentException("La cantidad de vértices para un grafo debe ser mayor o igual a cero");
		_verticesConVecinos = new ArrayList<Vertice>();
		inicializarVertices(cantVertices);
	}
	public Grafo(ArrayList<Vertice> vertices) 
	{ // Para crear el grafo a partir del ingreso de los  vértices con un archivo JSON
		_verticesConVecinos = vertices;
	}
	public void agregarArista(int verticeA,int verticeB) 
	{
		validarIndices(verticeA, verticeB);
		agregarVecinosEnLosVertices(verticeA,verticeB);
	}
	public boolean existeArista(int verticeA,int verticeB) 
	{
		validarIndices(verticeA, verticeB);
		boolean existeAristaAB = false;
		boolean existeAristaBA = false;
		for (Vertice v : _verticesConVecinos)
		{
				existeAristaAB |= (v.getIdVertice() == verticeA && v.getVecinos().contains(verticeB));
			if (v.getIdVertice() == verticeB && v.getVecinos().contains(verticeA))
				existeAristaBA |= (v.getIdVertice() == verticeB && v.getVecinos().contains(verticeA));
		}
		return (existeAristaAB && existeAristaBA);
	}
	public void borrarArista(int verticeA,int verticeB) 
	{
		validarIndices(verticeA, verticeB);
		eliminarVecinoDeLosVertices(verticeA,verticeB);
	}
	public HashSet<Integer> vecinoDelVertice(int vertice)
	{
		for (Vertice verticeActual : _verticesConVecinos)
			if (verticeActual.getIdVertice() == vertice) 
				return verticeActual.getVecinos();
		return null;
	}
	public int cantidadVertices() 
	{
		return _verticesConVecinos.size();
	}
	private void validarIndices(int verticeA,int verticeB) 
	{
		if (verticeA == verticeB) 
			throw new IllegalArgumentException("Los vértices deben ser distintos para no generar un loop");
		if (verticeA < 0 || verticeB < 0) 
			throw new IllegalArgumentException("Los vértices deben ser positivos y existir dentro del grafo");
		if (verticeA >= cantidadVertices() || verticeB >= cantidadVertices()) 
			throw new IllegalArgumentException("Los vértices deben existir dentro del grafo");
	}
	private void inicializarVertices(int cantVertices)
	{
		for (int id = 0; id < cantVertices; id++) 
		{
			Vertice v = new Vertice(id);
			v.setIdVertice(id);
			_verticesConVecinos.add(v);
		}
	}
	private void agregarVecinosEnLosVertices(int verticeA, int verticeB) 
	{
		for (Vertice vertice : _verticesConVecinos) 
		{
			if (vertice.getIdVertice() == verticeA)
				vertice.agregarVecino(verticeB);
			if (vertice.getIdVertice() == verticeB)
				vertice.agregarVecino(verticeA);
		}	
	}
	private void eliminarVecinoDeLosVertices(int verticeA, int verticeB) 
	{
		for (Vertice vertice : _verticesConVecinos)
		{
			if (vertice.getIdVertice() == verticeA)
				vertice.eliminarVecino(verticeB);
			if (vertice.getIdVertice() == verticeB)
				vertice.eliminarVecino(verticeA);
		}	
	}
	public String generarGrafoEnJSON() 
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		
		return json;
	}
	public static void guardarGrafoEnJSON(String grafo, String nombreArchivo)
	{
		try {
			FileWriter writer = new FileWriter(nombreArchivo);
			writer.write(grafo);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public static Grafo leerGrafoJSON(String archivo) 
//	{
//		Gson gson = new Gson();
//		Grafo ret = null;
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(archivo));
//			ret = gson.fromJson(br, Grafo.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}
	
	public static Grafo leerGrafoJSON(String archivo) {
	    Gson gson = new Gson();
	    Grafo ret = null;
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivo));
	        String json = br.lines().collect(Collectors.joining()); // Lee todo el contenido del archivo como una sola cadena
	        br.close();
	        
	        if (validarEstructuraJSON(json)) {
	            ret = gson.fromJson(json, Grafo.class);
	            jsonEsCorrecto = true;
	        } else {
	            System.err.println("El JSON no cumple con la estructura esperada.");
	            jsonEsCorrecto = false;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return ret;
	}
	

	
	public ArrayList<Vertice> getVerticesConVecinos()
	{
		return _verticesConVecinos;
	}
	
	public static boolean validarEstructuraJSON(String json) {
	    try {
	        // Parsea el JSON a un objeto Java usando Gson
	        Gson gson = new Gson();
	        Grafo grafo = gson.fromJson(json, Grafo.class);

	        if (grafo != null && grafo.getVerticesConVecinos() != null) {
	            for (Vertice vertice : grafo.getVerticesConVecinos()) {
	                if (vertice.getIdVertice() >= 0 && vertice.getVecinos() != null) {
	                    // La estructura es correcta
	                    return true;
	                }
	            }
	        }
	    } catch (JsonSyntaxException e) {
	        // Error de sintaxis en el JSON
	        System.err.println("Excepción de sintaxis JSON: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        // Error al convertir una cadena en un número
	        System.err.println("Excepción de formato de número: " + e.getMessage());
	    } catch (Exception e) {
	        // Otras excepciones generales
	        System.err.println("Excepción general: " + e.getMessage());
	    }
	    
	    // La estructura no es correcta
	    return false;
	}
	
	
	public static Boolean getJsonEsCorrecto() {
		return jsonEsCorrecto;
	}
}