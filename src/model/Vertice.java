package model;

import java.util.HashSet;

public class Vertice implements Comparable<Vertice>{
	private int _id;
	private HashSet<Integer> _vecinos;
	
	Vertice(int vertice){
		_id = vertice;
		_vecinos = new HashSet<Integer>();
	}

	public void agregarVecino(int vecino) {
		if (!_vecinos.contains(vecino))
			_vecinos.add(vecino);
	}
	
	public void eliminarVecino(int vecino) {
		if (_vecinos.contains(vecino))
			_vecinos.remove(vecino);
	}
	
	public int getIdVertice() {
		return _id;
	}

	public HashSet<Integer> getVecinos() {
		return _vecinos;
	}
	
	public int cantidadDeVecinos() {
		return _vecinos.size();
	}
	public String toString() {
		return "<"+_id+ ", vecinos: " +_vecinos.toString()+">";
	}

	@Override
	public int compareTo(Vertice otroVertice) {
		return this.cantidadDeVecinos() - otroVertice.cantidadDeVecinos();
	}
}
