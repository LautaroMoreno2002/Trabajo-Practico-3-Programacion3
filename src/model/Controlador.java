package model;

public class Controlador {
	private Grafo grafo;
	public static void main(String[] args) {
		Grafo gEjemploTP = Grafo.leerGrafoJSON("EjemploGrafo.JSON");
		System.out.println("El grafo ingresado tiene : "+ gEjemploTP.cantidadVertices()+ " vertices");
		System.out.println("El grafo esta compuesto por: \n"+gEjemploTP.getVerticesConVecinos());
		System.out.println("El Conjunto Dominante Minimo del grafo ingresado es: \n"+CDMGoloso.conjuntoMinimo(gEjemploTP.getVerticesConVecinos()));
		System.out.println("Luego de calcular el conjunto minimo, el grafo queda ordenado de esta forma: \n"+gEjemploTP.getVerticesConVecinos());
	}

//	public void calcularCGMGoloso() {
//		grafo = leerGrafoDelJSON();
//	}
	
}