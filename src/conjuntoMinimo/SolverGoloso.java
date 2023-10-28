package conjuntoMinimo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import model.Vertice;

public class SolverGoloso {
	private static ArrayList<Vertice> _verticesOrdenados;
	private static ArrayList<Integer> _verticesMarcados;
	private static ArrayList<Integer> _conjuntoMinimo;
	
	public static ArrayList<Integer> conjuntoMinimo(ArrayList<Vertice> vertices2){
		ordenarVerticesSegunCantidadDeVecinos(vertices2);
		
		_verticesOrdenados = vertices2;
		_verticesMarcados = new ArrayList<Integer>();
		_conjuntoMinimo = new ArrayList<Integer>();
		
		int i = 0;
		while (_verticesMarcados.size() < _verticesOrdenados.size()) {
			marcarVecinos(_verticesOrdenados.get(i),_verticesMarcados);
			_conjuntoMinimo.add(_verticesOrdenados.get(i).getIdVertice());
			i++;
		}
		return _conjuntoMinimo;
	}
	private static void ordenarVerticesSegunCantidadDeVecinos(ArrayList<Vertice> vertices2) {
		if (revertir()) Collections.reverse(vertices2);
		Collections.sort(vertices2);
		Collections.reverse(vertices2);
	}
	private static boolean revertir() {
		Random r = new Random();
		return r.nextBoolean();
	}
	private static void marcarVecinos(Vertice vertice, ArrayList<Integer> _verticesMarcados) {
		HashSet<Integer> vecinos = vertice.getVecinos();
		for (Integer vecino : vecinos) {
			if (!_verticesMarcados.contains(vecino))
				_verticesMarcados.add(vecino);
		}
		if (!_verticesMarcados.contains(vertice.getIdVertice())) _verticesMarcados.add(vertice.getIdVertice());
	}
}