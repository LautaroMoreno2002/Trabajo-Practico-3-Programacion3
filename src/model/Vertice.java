package model;

import java.util.HashSet;

public class Vertice implements Comparable<Vertice>
{
	private int _vertice;
	private HashSet<Integer> _vecinos;
	
	public Vertice(int vertice)
	{
		_vertice = vertice;
		_vecinos = new HashSet<Integer>();
	}
	public void agregarVecino(int vecino) 
	{
		if (!_vecinos.contains(vecino))
			_vecinos.add(vecino);
	}
	public void eliminarVecino(int vecino) 
	{
		if (_vecinos.contains(vecino))
			_vecinos.remove(vecino);
	}
	public int getNumeroVertice() 
	{
		return _vertice;
	}
	public HashSet<Integer> getVecinos() 
	{
		return _vecinos;
	}
	public int cantidadDeVecinos() 
	{
		return _vecinos.size();
	}
	public String toString() 
	{
		return "<"+_vertice+ ", vecinos: " +_vecinos.toString()+">";
	}
	public void setVecinos(HashSet<Integer> vecinos)
	{
		_vecinos = vecinos;
	}
	public void setNumeroVertice(int numeroVertice) 
	{
		_vertice = numeroVertice;
	}
	@Override
	public int compareTo(Vertice otroVertice) 
	{
		return this.cantidadDeVecinos() - otroVertice.cantidadDeVecinos();
	}
}