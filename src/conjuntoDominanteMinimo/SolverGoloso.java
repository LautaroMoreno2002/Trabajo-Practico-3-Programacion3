package conjuntoDominanteMinimo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import model.Grafo;
import model.Vertice;

public class SolverGoloso
{
	private Grafo _grafo;
	private ArrayList<Integer> _conjuntoDominanteMinimo;

	public SolverGoloso(Grafo grafo) 
	{
		_grafo = grafo;
	}
	public ArrayList<Integer> conjuntoGeneradorMinimo()
	{
		ArrayList<Vertice> verticesOrdenados = ordenarVerticesSegunCantidadDeVecinos();
		ArrayList<Integer> verticesMarcados = new ArrayList<Integer>();
		_conjuntoDominanteMinimo = new ArrayList<Integer>();
		int i = 0;
		while (verticesMarcados.size() < verticesOrdenados.size()) {
			marcarVecinos(verticesOrdenados.get(i),verticesMarcados);
			_conjuntoDominanteMinimo.add(verticesOrdenados.get(i).getNumeroVertice());
			i++;
		}
		return _conjuntoDominanteMinimo;
	}
	private ArrayList<Vertice> ordenarVerticesSegunCantidadDeVecinos() 
	{
		ArrayList<Vertice> vertices = clonar(_grafo.getVerticesConVecinos());
		if (revertir()) 
			Collections.reverse(vertices);
		Collections.sort(vertices);
		Collections.reverse(vertices);
		return vertices;
	}
	private ArrayList<Vertice> clonar(ArrayList<Vertice> verticesConVecinos) 
	{
		ArrayList<Vertice> copias = new ArrayList<Vertice>();
		for (Vertice v: verticesConVecinos) 
		{
			Vertice copia = new Vertice(v.getNumeroVertice());
			copia.setVecinos(v.getVecinos());
			copias.add(copia);
		}
		return copias;
	}
	private void marcarVecinos(Vertice vertice, ArrayList<Integer> _verticesMarcados) 
	{
		HashSet<Integer> vecinos = vertice.getVecinos();
		for (Integer vecino : vecinos) 
		{
			if (!_verticesMarcados.contains(vecino))
				_verticesMarcados.add(vecino);
		}
		if (!_verticesMarcados.contains(vertice.getNumeroVertice()))
			_verticesMarcados.add(vertice.getNumeroVertice());
	}
	private boolean revertir() 
	{
		return new Random().nextBoolean();
	}
	public ArrayList<Integer> getConjuntoMinimo()
	{
		return _conjuntoDominanteMinimo;
	}
}