package conjuntoMinimo;

import java.util.HashSet;
import java.util.Set;

import model.Grafo;

public class SolverBacktracking {
	private static Grafo _grafo;
	private static Set<Integer> _conjuntoDominanteMinimo;
	private  Set<Integer> _marcados;
	
	public SolverBacktracking(Grafo grafo) {
        _grafo = grafo;
        _conjuntoDominanteMinimo = new HashSet<>();
        _marcados = new HashSet<>();
    }
	
	public Set<Integer> resolver() {
		resolverBack(0);
		return _conjuntoDominanteMinimo;
	}
	
	// Resuelve el problema de conjunto dominante de forma recursiva, usando backtracking
	private void resolverBack(int vertice) {
		System.out.println(vertice);
		System.out.println(_marcados);
		// Llegamos a una hoja.
		if(vertice == _grafo.cantidadVertices()) 
		{
			if(esConjuntoDominante(_marcados)) 
			{
				System.out.println("Entro en el 1er if");
				if(_marcados.size() < _conjuntoDominanteMinimo.size() || _conjuntoDominanteMinimo.isEmpty()) {
						System.out.println("Entro en el 2do if");
						_conjuntoDominanteMinimo = new HashSet<>(_marcados);
				}
			
			}
			return;
		}

		// Si superamos el que tenemos, cortamos. (Backtracking)
		if(!_conjuntoDominanteMinimo.isEmpty() &&_marcados.size() >  _conjuntoDominanteMinimo.size())
			return;	
		

			System.out.println(esConjuntoDominante(_marcados));
			_marcados.add(vertice);
			resolverBack(vertice + 1);
			System.out.println("Aca voy a remover el vertice " + vertice);
			_marcados.remove(vertice);
			resolverBack(vertice + 1);

	}

	private Set<Integer> vecinosDeMarcados() {
		Set<Integer> vecinosDeMarcados = new HashSet<>();
		for(Integer vertice: _marcados) {
			vecinosDeMarcados.add(vertice);
			vecinosDeMarcados.addAll(_grafo.vecinoDelVertice(vertice));
		}
		return vecinosDeMarcados;
	}

	// Chequea que sea un conjunto dominante el conjunto que tengo hasta ahora.
	private boolean esConjuntoDominante(Set<Integer> conjunto) {
		Set<Integer> grafoCompleto = new HashSet<>();
		grafoCompleto.addAll(vecinosDeMarcados());
		return grafoCompleto.size() == _grafo.cantidadVertices();
	}
}

