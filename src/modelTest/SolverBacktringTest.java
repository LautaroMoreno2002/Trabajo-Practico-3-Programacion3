package modelTest;

import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import conjuntoDominanteMinimo.SolverBacktracking;
import model.Grafo;

public class SolverBacktringTest 
{
	@Test
	public void CMDconGrafoVacio() 
	{
		Grafo grafo = new Grafo(0);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.isEmpty());
	}
	@Test
	public void CDMDeunGrafoConUnVertice()
	{
		Grafo grafo = new Grafo(1);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.size() == 1);
	}
	@Test
	public void CDMDeunGrafoConDosVertices()
	{
		Grafo grafo = new Grafo(2);
		grafo.agregarArista(0, 1);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.size() == 1);
	}
	@Test
	public void CDMGrafoConVerticesDesconectados() 
	{
		Grafo grafo = new Grafo(6);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.size() == 6);
	}
	@Test
	public void conjuntoMinimoGrafoConexo() 
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(4, 3);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.size() == 2);
	}
	@Test
	public void CDMGrafoConsigna()
	{
		Grafo grafo = crearGrafoDeLaConsigna();
		Set<Integer> conjuntoDM = new HashSet<>();
		conjuntoDM.add(1);
		conjuntoDM.add(4);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.equals(conjuntoDM));
	}
	@Test
	public void grafoConUnVerticeVecinoDeTodos() {
		Grafo grafo = new Grafo(7);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(0, 5);
		grafo.agregarArista(0, 6);
		Set<Integer> conjuntoMinimo = new SolverBacktracking(grafo).resolver();
		assertTrue(conjuntoMinimo.size() == 1);
	}
	private Grafo crearGrafoDeLaConsigna() 
	{
		return Grafo.leerGrafoJSON("EjemploGrafo.JSON");
	}
}