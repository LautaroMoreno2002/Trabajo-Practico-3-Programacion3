package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import model.Vertice;
import presenter.Presenter;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class PantallaCargarGrupos {

    private JFrame interfazGrafos;
    private JMapViewer plano;
    private ArrayList<Vertice> _setConVecinos;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private ArrayList<Coordinate> coordinadas = new ArrayList<Coordinate>();
	private HashSet<Integer> _vecinos;
    /**
     * Create the application.
     */
    public PantallaCargarGrupos(ArrayList<Vertice> setConVecinos) {
		this._setConVecinos = setConVecinos;
		initialize();
	}
    
    

	public PantallaCargarGrupos(ArrayList<Vertice> _setConVecinos, HashSet<Integer> _vecinos) {
		this._setConVecinos = _setConVecinos;
		this._vecinos = _vecinos;
		initialize();
	}



	/**
     * Initialize the contents of the frame.
     */
    private void initialize() {
		interfazGrafos = new JFrame();
		interfazGrafos.setResizable(false);
		interfazGrafos.setType(Type.POPUP);
		interfazGrafos.setTitle("Trabajo Práctico 3 - Conjunto Generador Mínimo");
		interfazGrafos.getContentPane().setBackground(Color.WHITE);
		interfazGrafos.setBounds(400, 200, 800, 500);
		interfazGrafos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfazGrafos.getContentPane().setLayout(null);
		
		plano = new JMapViewer();
		
		JPanel panelMapa = new JPanel(new BorderLayout());
		panelMapa.setBounds(0, 0, 786, 412);
		
		interfazGrafos.getContentPane().add(panelMapa);
		panelMapa.add(plano, BorderLayout.CENTER); //
		plano.setZoomControlsVisible(false);
		
		
		Coordinate coordinada = new Coordinate(-39.4, -30.19);
		plano.setDisplayPosition(coordinada, 8); //PARA CAMBIAR EL ZOOM
		
		
		double latitud = -38.990380;
		double longitud = -30.197439;
		double cont= 0.0;
		
		
		for (int i = 0; i < _setConVecinos.size(); i++) {
			
			crearNuevoPuntoEnElPlano(latitud,longitud,i);

		    double deltaLat = -0.1;
		    double deltaLon = -0.2;
		    
		    if (i % 2 == 0) {
		        latitud += deltaLat;
		        longitud += deltaLon + cont;
		    } else {
		        latitud += deltaLat;
		        longitud -= deltaLon + cont;
		    }
		    
		    cont+=0.2;
		}
		
//		for (int vertice = 0; vertice < _vecinos.size(); vertice++) {
//			ArrayList<Coordinate> coordinates = armarArregloConVecinosDelVertice(vertice);
//			crearArista(coordinates, vertice);
//		}
		
    }
    
	private void crearNuevoPuntoEnElPlano(double latitud, double longitud, int i) {
	    Coordinate coordinadasPunto = new Coordinate(latitud, longitud); // Crea una nueva instancia en cada iteración
	    
	    MapMarker marker = new MapMarkerDot(_setConVecinos.get(i).toString(),coordinadasPunto);//En realidad acá tenemos que usar el getId()
	    
	    
	    //Agregamos las coordenadasConIndice al poligono
	    coordenadasConIndice.put(coordinadasPunto,i);
	    coordinadas.add(coordinadasPunto);
	    
	    marker.getStyle().setBackColor(Color.PINK);
	    marker.getStyle().setColor(Color.WHITE);
	    plano.addMapMarker(marker);
	}

    public JFrame getInterfazGrafos() {
        return interfazGrafos;
    }
    
//	private void crearArista(ArrayList<Coordinate> coordinates, int vertice) {
//		for (int i = 0; i < coordinates.size(); i += 2) {
//			if (i + 1 < coordinates.size()) {
//				List<Coordinate> route2 = new ArrayList<Coordinate>(Arrays.asList(coordinadas.get(vertice), coordinates.get(i), coordinates.get(i)));
//				plano.addMapPolygon(new MapPolygonImpl(route2));
//			}
//		}
//	}
    
//	private ArrayList<Coordinate> armarArregloConVecinosDelVertice(int vertice) {
//		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>(); //Para guardar las coordenadasConIndice de cada vecino
//		
//		HashSet<Integer> vecinosDeI = _vecinos;
//		
//		for (int vecino : vecinosDeI) {
//			// Para cada vecino en vecinosDeI, intenta encontrar la coordenada correspondiente en el HashMap coordenadasConIndice
//		    for (Map.Entry<Coordinate, Integer> entry : coordenadasConIndice.entrySet()) {
//		        if (entry.getValue().equals(vecino)) {
//		        	Coordinate coordenada = entry.getKey();
//		        	coordinates.add(coordenada);
//		        }
//		    }
//		}
//		
//		coordinates.add(coordinadas.get(vertice)); //agregamos la coordenada del vertice en el que nos paramos al array de las coordenadasConIndice de los vecinos
//		
//		return coordinates;
//	}
}
