package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import model.Grafo;

public class GrafoTest 
{
	@Test (expected = IllegalArgumentException.class)
	public void iniciarGrafoConCantidadVerticesNegativa() 
	{
		Grafo g = new Grafo(-1);
		g.agregarArista(0, 2);
	}
	@Test (expected = IllegalArgumentException.class)
	public void agregarAristaLoop() 
	{
		Grafo g = new Grafo(3);
		g.agregarArista(0, 0);
	}
	@Test (expected = IllegalArgumentException.class)
	public void agregarAristaConPrimerVerticeExcedido() 
	{
		Grafo g = new Grafo(3);
		g.agregarArista(5, 0);
	}
	@Test (expected = IllegalArgumentException.class)
	public void agregarAristaConSegundoVerticeExcedido() 
	{
		Grafo g = new Grafo(3);
		g.agregarArista(0, 5);
	}
	@Test
	public void vertificarSiExisteAristaEnAmbosSentidos()
	{
		Grafo g = new Grafo(3);
		g.agregarArista(1, 0);
		assertTrue(g.existeArista(0, 1) && g.existeArista(1, 0));
	}
	@Test
	public void eliminarArista()
	{
		Grafo g = new Grafo(3);
		g.agregarArista(0, 2);
		g.borrarArista(0, 2);
		assertFalse(g.existeArista(0, 2));
	}
	@Test
	public void vecinosDelVerticeUno() 
	{
		Grafo g = new Grafo(3);
		g.agregarArista(1, 0);
		g.agregarArista(1, 2);
		g.agregarArista(0, 2);
		String esperado = "[0, 2]";
		String vecinosDelUno = g.vecinoDelVertice(1).toString();
		assertEquals(esperado, vecinosDelUno);
	}	
}