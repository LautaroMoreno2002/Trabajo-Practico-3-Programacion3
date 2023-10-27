package conjuntoMinimo;
import model.Grafo;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo(6);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(1,2);
		grafo.agregarArista(2, 1);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);
		
		SolverBacktracking sol = new SolverBacktracking(grafo);
		System.out.println(sol.resolver());
		
	}

}
