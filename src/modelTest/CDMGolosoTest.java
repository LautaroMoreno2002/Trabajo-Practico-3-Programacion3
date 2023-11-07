package modelTest;

import java.util.ArrayList;
import org.junit.Test;
import conjuntoDominanteMinimo.SolverGoloso;
import static org.junit.Assert.assertTrue;
import model.Grafo;

public class CDMGolosoTest 
{
	@Test
	public void CMDDeUnGrafoVacio()
	{
		Grafo grafo = new Grafo(0);
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.isEmpty());
	}
	@Test
	public void CDMDeunGrafoConUnVertice()
	{
		Grafo grafo = new Grafo(1);
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.size() == 1);
	}
	@Test
	public void CDMDeunGrafoConDosVertices()
	{
		Grafo grafo = new Grafo(2);
		grafo.agregarArista(0, 1);
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.size() == 1);
	}
	@Test
	public void CDMGrafoConVerticesDesconectados() 
	{
		Grafo grafo = new Grafo(6);
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.size() == 6);
	}
	@Test
	public void conjuntoMinimoGrafoConexo() 
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(4, 3);
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.size() == 2);
	}
	@Test
	public void CDMGrafoConsigna()
	{
		Grafo grafo = crearGrafoDeLaConsigna();
		ArrayList<Integer> cm = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
		assertTrue(cm.size() == 2);
	}
	@Test
	public void CDMGrafoConsignaConDosConjuntosDistintos()
	{
		Grafo grafo = crearGrafoDeLaConsigna();
		ArrayList<ArrayList<Integer>> soluciones = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 20; i++)
		{
			ArrayList<Integer> solucion = new SolverGoloso(grafo).conjuntoGeneradorMinimo();
			if (!soluciones.contains(solucion))
				soluciones.add(solucion);
		}
		assertTrue(soluciones.size() == 2);
	}
	private Grafo crearGrafoDeLaConsigna() 
	{
		return Grafo.leerGrafoJSON("EjemploGrafo.JSON");
	}
}