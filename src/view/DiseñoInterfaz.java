package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.Vertice;

public abstract class DiseñoInterfaz {
	public static ArrayList<HashSet<Integer>> obtenerVecinos(ArrayList<Vertice> setConVecinos) 
	{
		ArrayList<HashSet<Integer>> vecinosDeVertices = new ArrayList<>();
		for (Vertice vertice : setConVecinos) 
		{
			HashSet<Integer> vecinos = vertice.getVecinos();
			vecinosDeVertices.add(vecinos);
		}
		return vecinosDeVertices;
	}
	public void dibujarAristasEnPlano(ArrayList<Vertice> setConVecinos,JMapViewer plano,HashMap<Coordinate, Integer> coordenadasConIndice) 
	{
		Integer contador = 0;
		for (HashSet<Integer> conjuntoVecinosVertice : obtenerVecinos(setConVecinos)) 
		{
			for (Integer vecino : conjuntoVecinosVertice) 
			{ 
				Integer valorBuscado = vecino - 1;
				Coordinate coordenadaCorrespondiente = null;
				for (Map.Entry<Coordinate, Integer> entry : coordenadasConIndice.entrySet()) 
				{
					if (entry.getValue().equals(valorBuscado)) 
					{
						coordenadaCorrespondiente = entry.getKey();
						break;
					}
				}
				List<Coordinate> route2 = new ArrayList<Coordinate>(Arrays.asList(obtenerCoordenadaNodoActual(contador,coordenadasConIndice),
						coordenadaCorrespondiente, coordenadaCorrespondiente));
				plano.addMapPolygon(new MapPolygonImpl(route2));
			}
			contador++;
		}
	}
	public Coordinate obtenerCoordenadaNodoActual(Integer contador,HashMap<Coordinate, Integer> coordenadasConIndice)
    {
    	Integer valorBuscado = contador;
    	Coordinate coordenadaCorrespondiente = null;
		for (Map.Entry<Coordinate, Integer> entry : coordenadasConIndice.entrySet())
		{
		    if (entry.getValue().equals(valorBuscado))
		    {
		        coordenadaCorrespondiente = entry.getKey();
		        break;
		    }																
		}
		return coordenadaCorrespondiente;
    }
	public void asignarCaracteristicas(JButton btn, Font tipografiaBoton, int posX, int posY, int ancho, int largo) 
	{
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
		btn.setFont(tipografiaBoton);
		btn.setBounds(posX, posY, ancho, largo);
		btn.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				btn.setForeground(Color.WHITE);
				btn.setBackground(Color.BLACK);
				btn.setBounds(posX - 10, posY - 5, ancho + 30, largo + 10);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				btn.setForeground(Color.BLACK);
				btn.setBackground(Color.WHITE);
				btn.setBounds(posX, posY, ancho, largo);
			}
		});
	}
	public void crearNuevoPuntoEnElPlano(JMapViewer plano, ArrayList<Integer> cgm,
			ArrayList<Vertice> setConVecinos, HashMap<Coordinate, Integer> coordenadasConIndice, double latitud,
			double longitud, int punto) 
	{
		Coordinate coordinadasPunto = new Coordinate(latitud, longitud);
		MapMarker marker = new MapMarkerDot("" + setConVecinos.get(punto).getIdVertice(), coordinadasPunto);
		// Agregamos las coordenadasConIndice al poligono
		coordenadasConIndice.put(coordinadasPunto, punto);

		if (cgm.contains(setConVecinos.get(punto).getIdVertice())) 
		{
			marker.getStyle().setBackColor(Color.GREEN);
			marker.getStyle().setColor(Color.BLACK);
		} else {
			marker.getStyle().setBackColor(Color.BLACK);
			marker.getStyle().setColor(Color.WHITE);
		}
		plano.addMapMarker(marker);
	}
}
