package model;

import java.util.HashSet;

public class Vertice implements Comparable<Vertice>{
	private int _vertice;
	private HashSet<Integer> _vecinos;
	
	Vertice(int vertice, HashSet<Integer> vecinos){
		_vertice = vertice;
		_vecinos = vecinos;
	}

	public void agregarVecino(int vecino) {
		_vecinos.add(vecino);
	}
	
	public void eliminarVecinos(int vecino) {
		_vecinos.remove(vecino);
	}
	
	public int get_vertice() {
		return _vertice;
	}

	public HashSet<Integer> get_vecinos() {
		return _vecinos;
	}
	
	public int cantidadDeVecinos() {
		return _vecinos.size();
	}
	public String toString() {
		return "<"+_vertice+ ", vecinos: " +_vecinos.toString()+">";
	}

	@Override
	public int compareTo(Vertice otroVertice) {
		return this.cantidadDeVecinos() - otroVertice.cantidadDeVecinos();
	}
}
