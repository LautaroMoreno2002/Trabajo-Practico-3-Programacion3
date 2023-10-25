package modelTest;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import model.CDM;
import model.Grafo;

public class ConjuntoDominanteMinimoTest {
	@Test
	public void conjuntoMinimoGrafoConexo() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(1, 4);
		g.agregarArista(4, 3);
		ArrayList<Integer> cm = CDM.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.size() == 2);
	}
	@Test
	public void conjuntoMinimoGrafoNoConexo() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(1, 2);
		g.agregarArista(4, 3);
		ArrayList<Integer> cm = CDM.conjuntoMinimo(g.getVerticesConVecinos());
		assertTrue(cm.size() == 2);
	}
}
