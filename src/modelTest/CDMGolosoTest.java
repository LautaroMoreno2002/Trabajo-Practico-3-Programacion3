package modelTest;

import java.util.ArrayList;
import org.junit.Test;

import conjuntoMinimo.SolverGoloso;

import static org.junit.Assert.assertTrue;

import model.Grafo;
import model.Vertice;

public class CDMGolosoTest {
	@Test
	public void CMDDeUnGrafoVacio()
	{
		Grafo g = new Grafo(0);
		ArrayList<Integer> cm = SolverGoloso.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.isEmpty());
	}
	@Test
	public void CDMDeunGrafoConUnVertice()
	{
		Grafo g = new Grafo(1);
		ArrayList<Integer> cm = SolverGoloso.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.size() == 1);
	}
	@Test
	public void CDMDeunGrafoConDosVertices()
	{
		Grafo g = new Grafo(2);
		g.agregarArista(0, 1);
		ArrayList<Integer> cm = SolverGoloso.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.size() == 1);
	}
	@Test
	public void conjuntoMinimoGrafoConexo() 
	{
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(1, 4);
		g.agregarArista(4, 3);
		ArrayList<Integer> cm = SolverGoloso.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.size() == 2);
	}
	@Test
	public void CDMGrafoConsigna()
	{
		Grafo gEjemplo = crearGrafoDeLaConsigna();
		ArrayList<Integer> cm = SolverGoloso.conjuntoMinimo(gEjemplo.getVerticesConVecinos());
		assertTrue(cm.size() == 2);
	}
	@Test
	public void CDMGrafoConsignoConDosConjuntosDistintos()
	{
		Grafo gEjemplo = crearGrafoDeLaConsigna();
		ArrayList<ArrayList<Integer>> soluciones = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 10; i++)
		{
			ArrayList<Integer> solucion = SolverGoloso.conjuntoMinimo(gEjemplo.getVerticesConVecinos());
			if (!soluciones.contains(solucion)) // Corregir, {2,4} y {4,2} son el mismo conjunto
				soluciones.add(solucion);
		}
		assertTrue(soluciones.size() == 2);
	}
	private Grafo crearGrafoDeLaConsigna() 
	{
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		for (int vertice = 1; vertice < 7; vertice++)
			vertices.add(new Vertice(vertice));
		vertices.get(0).agregarVecino(2);vertices.get(0).agregarVecino(5);
		vertices.get(1).agregarVecino(3);vertices.get(1).agregarVecino(1);vertices.get(1).agregarVecino(5);
		vertices.get(2).agregarVecino(4);vertices.get(2).agregarVecino(2);
		vertices.get(3).agregarVecino(3);vertices.get(3).agregarVecino(5);vertices.get(3).agregarVecino(6);
		vertices.get(4).agregarVecino(2);vertices.get(4).agregarVecino(1);vertices.get(4).agregarVecino(4);
		vertices.get(5).agregarVecino(4);
		
		return new Grafo(vertices);
	}
}
