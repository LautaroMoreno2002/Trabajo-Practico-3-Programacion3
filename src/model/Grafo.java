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

public class Grafo 
{
	private ArrayList<Vertice> _verticesConVecinos;
	
	public Grafo(int cantVertices) 
	{ // Para crear el grafo desde cero
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
			existeAristaAB |= (v.getNumeroVertice() == verticeA && v.getVecinos().contains(verticeB));
			if (v.getNumeroVertice() == verticeB && v.getVecinos().contains(verticeA))
				existeAristaBA |= (v.getNumeroVertice() == verticeB && v.getVecinos().contains(verticeA));
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
			if (verticeActual.getNumeroVertice() == vertice) 
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
		for (int numeroVertice = 0; numeroVertice < cantVertices; numeroVertice++) 
		{
			Vertice v = new Vertice(numeroVertice);
			v.setNumeroVertice(numeroVertice);
			_verticesConVecinos.add(v);
		}
	}
	private void agregarVecinosEnLosVertices(int verticeA, int verticeB) 
	{
		for (Vertice vertice : _verticesConVecinos) 
		{
			if (vertice.getNumeroVertice() == verticeA)
				vertice.agregarVecino(verticeB);
			if (vertice.getNumeroVertice() == verticeB)
				vertice.agregarVecino(verticeA);
		}	
	}
	private void eliminarVecinoDeLosVertices(int verticeA, int verticeB) 
	{
		for (Vertice vertice : _verticesConVecinos)
		{
			if (vertice.getNumeroVertice() == verticeA)
				vertice.eliminarVecino(verticeB);
			if (vertice.getNumeroVertice() == verticeB)
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
	public static Grafo leerGrafoJSON(String archivo) {
	    Gson gson = new Gson();
	    Grafo grafoJSON = null;
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(archivo));
	        String json = br.lines().collect(Collectors.joining()); // Lee todo el contenido del archivo como una sola cadena
	        br.close();
	        if (validarEstructuraJSON(json)) 
	            grafoJSON = gson.fromJson(json, Grafo.class);
	        else 
	            System.err.println("El JSON no cumple con la estructura esperada.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return grafoJSON;
	}
	public ArrayList<Vertice> getVerticesConVecinos()
	{
		return _verticesConVecinos;
	}
	private static boolean validarEstructuraJSON(String json) {
	    try {
	        Gson gson = new Gson();
	        Grafo grafo = gson.fromJson(json, Grafo.class);
	        if (grafo != null && grafo.getVerticesConVecinos() != null && !numeroVerticeRepetido(grafo) && !bucleEnElGrafo(grafo))
	            return true;
	    } catch (com.google.gson.JsonSyntaxException e) {
	        // Error de sintaxis en el JSON
	        System.err.println("Excepción de sintaxis JSON: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        // Error al convertir una cadena en un número
	        System.err.println("Excepción de formato de número: " + e.getMessage());
	    } catch (Exception e) {
	        // Otras excepciones generales
	        System.err.println("Excepción general: " + e.getMessage());
	    }
	    return false;
	}
	private static boolean numeroVerticeRepetido(Grafo grafo) 
	{
        HashSet<Integer> numeroVertices = new HashSet<>();
        for (Vertice vertice : grafo.getVerticesConVecinos()) 
        {
            if (vertice.getNumeroVertice() >= 0 && vertice.getVecinos() != null) 
            {
                if (numeroVertices.contains(vertice.getNumeroVertice()))
                {
                    System.err.println("El grafo contiene vértices con el mismo ID (" + vertice.getNumeroVertice() + ").");
                    return true;
                } else              
                    numeroVertices.add(vertice.getNumeroVertice());
            }
        }
        return false;
	}
	private static boolean bucleEnElGrafo (Grafo grafo) 
	{
        for (Vertice vertice : grafo.getVerticesConVecinos()) 
        {
            for (int vecino : vertice.getVecinos()) 
            {
                if (vecino == vertice.getNumeroVertice())
                {
                    System.err.println("El grafo contiene un bucle en el vértice " + vertice.getNumeroVertice() + ".");
                    return true;
                }
            }
        }
        return false;
	}
}