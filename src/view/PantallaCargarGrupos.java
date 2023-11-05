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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaCargarGrupos {

    private JFrame interfazGrafos;
    private JMapViewer plano;
    private ArrayList<Vertice> _setConVecinos;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private ArrayList<Coordinate> coordinadas = new ArrayList<Coordinate>();
	private ArrayList<Integer> _cgmGoloso;
	private JFrame _interfazPresentacion;
    /**
     * Create the application.
     @wbp.parser.constructor

     */

	public PantallaCargarGrupos(ArrayList<Vertice> _setConVecinos, ArrayList<Integer> _cgmGoloso, JFrame _interfazPresentacion) {
		this._setConVecinos = _setConVecinos;
		this._cgmGoloso = _cgmGoloso;
		this._interfazPresentacion = _interfazPresentacion;
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
		
		JButton btnCargarNuevoCGM = new JButton("Generar nuevo CGM goloso");
		btnCargarNuevoCGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfazGrafos.setVisible(false);
				_interfazPresentacion.setVisible(true);
			}
		});
		btnCargarNuevoCGM.setBounds(288, 422, 205, 21);
		interfazGrafos.getContentPane().add(btnCargarNuevoCGM);
		
		
		Coordinate coordinada = new Coordinate(-38.99, -30.19);
		plano.setDisplayPosition(coordinada, 11); //PARA CAMBIAR EL ZOOM
		
		
//		double latitud = -38.990380;
//		double longitud = -30.197439;
//		double cont= 0.0;
//		
//		
//		for (int i = 0; i < _setConVecinos.size(); i++) {
//			
//			crearNuevoPuntoEnElPlano(latitud,longitud,i);
//			
//
//		    double deltaLat = -0.1;
//		    double deltaLon = -0.2;
//		    
//		    if (i % 2 == 0) {
//		        latitud += deltaLat;
//		        longitud += deltaLon + cont;
//		    } else {
//		        latitud += deltaLat;
//		        longitud -= deltaLon + cont;
//		    }
//		    
//		    cont+=0.2;
//		}
		
		double centroLatitud = -38.990380; // Latitud del centro del círculo
		double centroLongitud = -30.197439; // Longitud del centro del círculo
		double radio = 0.1; // Radio del círculo en grados (ajusta según tus necesidades)
		int numPuntos = _setConVecinos.size();

		for (int i = 0; i < numPuntos; i++) {
		    // Calcular el ángulo para distribuir los puntos uniformemente en el círculo
		    double angulo = (2 * Math.PI * i) / numPuntos;

		    // Calcular las coordenadas del punto en el círculo
		    double latitudPunto = centroLatitud + radio * Math.sin(angulo);
		    double longitudPunto = centroLongitud + radio * Math.cos(angulo);

		    // Crea el punto en el plano usando latitudPunto y longitudPunto
		    crearNuevoPuntoEnElPlano(latitudPunto, longitudPunto, i);
		}
		
		Integer contador = 0;
		for (HashSet<Integer> conjuntoVecinosVertice : obtenerVecinos()) { //accedes a cada conjuntoVecinosVertice
			
			for(Integer vecino : conjuntoVecinosVertice) { //accedes a cada vecino de ese conjunto en esa pos
				
				Integer valorBuscado = vecino-1; //ACÁ ESTABA EL MALDITO PROBLEMA
				Coordinate coordenadaCorrespondiente = null;
				for (Map.Entry<Coordinate, Integer> entry : coordenadasConIndice.entrySet()) {
				    if (entry.getValue().equals(valorBuscado)) {
				        coordenadaCorrespondiente = entry.getKey();
				        System.out.println(entry.getKey());
				        System.out.println(entry.getValue());
				        break; // Si encontramos el valor, podemos salir del bucle
				    }																		//CAMBIAR
				}
				List<Coordinate> route2 = new ArrayList<Coordinate>(Arrays.asList(obtenerCoordenadaNodoActual(contador), coordenadaCorrespondiente, coordenadaCorrespondiente));
				plano.addMapPolygon(new MapPolygonImpl(route2));
			}
			contador++;
		}
    }
    
    public Coordinate obtenerCoordenadaNodoActual(Integer contador) {
    	Integer valorBuscado = contador;
    	Coordinate coordenadaCorrespondiente = null;
		for (Map.Entry<Coordinate, Integer> entry : coordenadasConIndice.entrySet()) {
		    if (entry.getValue().equals(valorBuscado)) {
		        coordenadaCorrespondiente = entry.getKey();
		        break; // Si encontramos el valor, podemos salir del bucle
		    }																		//CAMBIAR
		}
		return coordenadaCorrespondiente;
    }
    
    
	private void crearNuevoPuntoEnElPlano(double latitud, double longitud, int i) { //el i sería el punto
	    Coordinate coordinadasPunto = new Coordinate(latitud, longitud); // Crea una nueva instancia en cada iteración
	    
	    MapMarker marker = new MapMarkerDot(_setConVecinos.get(i).toString(),coordinadasPunto);//En realidad acá tenemos que usar el getId()
	    
	    
	    //Agregamos las coordenadasConIndice al poligono
	    coordenadasConIndice.put(coordinadasPunto,i); //acá se guarda las coordenadas adjuntadas al punto
	    System.out.println(coordinadasPunto + "Indice" + i);
	    coordinadas.add(coordinadasPunto);
	    
	    if(_cgmGoloso.contains(_setConVecinos.get(i).getIdVertice())) {
		    marker.getStyle().setBackColor(Color.GREEN);
		    marker.getStyle().setColor(Color.BLACK);
	    } else {
		    marker.getStyle().setBackColor(Color.BLACK);
		    marker.getStyle().setColor(Color.WHITE);
	    }

	    plano.addMapMarker(marker);
	    System.out.println(obtenerVecinos());
	}

    public JFrame getInterfazGrafos() {
        return interfazGrafos;
    }

	private ArrayList<HashSet<Integer>> obtenerVecinos(){
		ArrayList<HashSet<Integer>> vecinosDeVertices = new ArrayList<>();

		for (Vertice vertice : _setConVecinos) {
		    HashSet<Integer> vecinos = vertice.getVecinos();
		    vecinosDeVertices.add(vecinos);
		}
		
		return vecinosDeVertices;
	}

}
