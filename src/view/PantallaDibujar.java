package view;

import java.util.ArrayList;
import java.util.HashSet;

import model.Vertice;

public abstract class PantallaDibujar {
	public static ArrayList<HashSet<Integer>> obtenerVecinos(ArrayList<Vertice> setConVecinos) 
	{
		ArrayList<HashSet<Integer>> vecinosDeVertices = new ArrayList<>();
		for (Vertice vertice : setConVecinos) 
		{
			HashSet<Integer> vecinos = vertice.getVecinos();
			vecinosDeVertices.add(vecinos);
		}
		return vecinosDeVertices;
	}
}
