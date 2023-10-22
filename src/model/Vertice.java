package model;

import java.util.HashSet;

public class Vertice implements Comparable<Vertice>{
	private int _idVertice;
	private HashSet<Integer> _vecinos;
	
	Vertice(int vertice){
		_idVertice = vertice;
		_vecinos = new HashSet<Integer>();
	}

	public void agregarVecino(int vecino) {
		_vecinos.add(vecino);
	}
	
	public void eliminarVecino(int vecino) {
		_vecinos.remove(vecino);
	}
	
	public int get_idVertice() {
		return _idVertice;
	}
	public void set_idVertice(int id) {
		_idVertice = id;
	}

	public HashSet<Integer> get_vecinos() {
		return _vecinos;
	}
	
	public int cantidadDeVecinos() {
		return _vecinos.size();
	}
	public String toString() {
		return "<"+_idVertice+ ", vecinos: " +_vecinos.toString()+">";
	}

	@Override
	public int compareTo(Vertice otroVertice) {
		return this.cantidadDeVecinos() - otroVertice.cantidadDeVecinos();
	}
}
