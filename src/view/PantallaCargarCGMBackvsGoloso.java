package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.Vertice;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class PantallaCargarCGMBackvsGoloso {
	private JMapViewer _planoGoloso;
	private JMapViewer _planoBacktracking;
	private ArrayList<Integer> _cgmGoloso;
	private ArrayList<Integer> _cgmBacktracking;
	private ArrayList<Vertice> _setConVecinos;
	private JFrame _interfazPresentacion;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private JFrame pantallaCargarCGM;
	private final Font tipografiaBoton = new Font("Arial", Font.BOLD, 12);
	private final Font tipografiaEtiqueta = new Font("Arial", Font.BOLD, 14);

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public PantallaCargarCGMBackvsGoloso(ArrayList<Vertice> setConVecinos,ArrayList<ArrayList<Integer>> solucionesCGM,JFrame interfazPresentacion) {
		_setConVecinos = setConVecinos;
		_cgmGoloso = solucionesCGM.get(0);
		_cgmBacktracking = solucionesCGM.get(1);
		_interfazPresentacion = interfazPresentacion;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pantallaCargarCGM = new JFrame();
		pantallaCargarCGM.getContentPane().setBackground(new Color(0, 0, 0));
		pantallaCargarCGM.setResizable(false);
		pantallaCargarCGM.setTitle("CGM Goloso vs CGM Backtracking");
		pantallaCargarCGM.setBounds(400, 100, 800, 650);
		pantallaCargarCGM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaCargarCGM.getContentPane().setLayout(null);
		
		
		JPanel grafoGoloso = new JPanel();
		grafoGoloso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		grafoGoloso.setBackground(new Color(0, 0, 0));
		grafoGoloso.setBounds(10, 11, 382, 497);
		pantallaCargarCGM.getContentPane().add(grafoGoloso);
		
		JPanel grafoBacktracking = new JPanel();
		grafoBacktracking.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		grafoBacktracking.setBackground(new Color(0, 0, 0));
		grafoBacktracking.setBounds(402, 11, 372, 497);
		pantallaCargarCGM.getContentPane().add(grafoBacktracking);

		JLabel lblGoloso = new JLabel("Goloso");
		lblGoloso.setForeground(new Color(255, 255, 255));
		lblGoloso.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoloso.setBounds(125, 519, 119, 17);
		lblGoloso.setFont(tipografiaEtiqueta);
		pantallaCargarCGM.getContentPane().add(lblGoloso);
		
		JLabel lblBacktrancking = new JLabel("Backtrancking");
		lblBacktrancking.setForeground(new Color(255, 255, 255));
		lblBacktrancking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBacktrancking.setBounds(535, 519, 119, 17);
		lblBacktrancking.setFont(tipografiaEtiqueta);
		pantallaCargarCGM.getContentPane().add(lblBacktrancking);
		
		JLabel lblVs = new JLabel("vs");
		lblVs.setForeground(new Color(255, 255, 255));
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(335, 519, 119, 17);
		lblVs.setFont(tipografiaEtiqueta);
		pantallaCargarCGM.getContentPane().add(lblVs);
		grafoGoloso.setLayout(null);
		
		_planoGoloso = new JMapViewer();
		_planoGoloso.setBounds(10, 11, 362, 475);
		grafoGoloso.add(_planoGoloso);
		grafoBacktracking.setLayout(null);
		_planoBacktracking = new JMapViewer();
		_planoBacktracking.setBounds(10, 11, 352, 475);
		grafoBacktracking.add(_planoBacktracking);
		_planoGoloso.setZoomControlsVisible(false);
		_planoBacktracking.setZoomControlsVisible(false);
		
		Coordinate coordinada = new Coordinate(-38.99, -30.19);
        _planoGoloso.setDisplayPosition(coordinada, 11); //PARA CAMBIAR EL ZOOM
        _planoBacktracking.setDisplayPosition(coordinada, 11); //PARA CAMBIAR EL ZOOM
		
		JButton btnVolver = new JButton("Volver a cargar CGM");
		asignarCaracteristicas(btnVolver, 305, 547, 188, 40);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pantallaCargarCGM.setVisible(false);
				_interfazPresentacion.setVisible(true);
			}
		});
		pantallaCargarCGM.getContentPane().add(btnVolver);
		
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
		    crearNuevoPuntoEnElPlanoG(latitudPunto, longitudPunto, i);
		    crearNuevoPuntoEnElPlanoB(latitudPunto, longitudPunto, i);
		    dibujarAristasEnPlanoGoloso();
		}
		
	}
	private void crearNuevoPuntoEnElPlanoG(double latitud, double longitud, int i) { //el i sería el punto
	    Coordinate coordinadasPunto = new Coordinate(latitud, longitud); // Crea una nueva instancia en cada iteración
	    
	    MapMarker marker = new MapMarkerDot(""+_setConVecinos.get(i).getIdVertice(),coordinadasPunto);//En realidad acá tenemos que usar el getId()
	    
	    
	    //Agregamos las coordenadasConIndice al poligono
	    coordenadasConIndice.put(coordinadasPunto,i); //acá se guarda las coordenadas adjuntadas al punto
	    System.out.println(coordinadasPunto + "Indice" + i);
	    
	    if(_cgmGoloso.contains(_setConVecinos.get(i).getIdVertice())) {
		    marker.getStyle().setBackColor(Color.GREEN);
		    marker.getStyle().setColor(Color.BLACK);
	    } else {
		    marker.getStyle().setBackColor(Color.BLACK);
		    marker.getStyle().setColor(Color.WHITE);
	    }

	    _planoGoloso.addMapMarker(marker);
	    System.out.println(obtenerVecinos());
	}
	private void crearNuevoPuntoEnElPlanoB(double latitud, double longitud, int i) { //el i sería el punto
	    Coordinate coordinadasPunto = new Coordinate(latitud, longitud); // Crea una nueva instancia en cada iteración
	    
	    MapMarker marker = new MapMarkerDot(""+_setConVecinos.get(i).getIdVertice(),coordinadasPunto);//En realidad acá tenemos que usar el getId()
	    
	    
	    //Agregamos las coordenadasConIndice al poligono
	    coordenadasConIndice.put(coordinadasPunto,i); //acá se guarda las coordenadas adjuntadas al punto
	    System.out.println(coordinadasPunto + "Indice" + i);
	    
	    if(_cgmBacktracking.contains(_setConVecinos.get(i).getIdVertice())) {
		    marker.getStyle().setBackColor(Color.GREEN);
		    marker.getStyle().setColor(Color.BLACK);
	    } else {
		    marker.getStyle().setBackColor(Color.BLACK);
		    marker.getStyle().setColor(Color.WHITE);
	    }

	    _planoBacktracking.addMapMarker(marker);
	    System.out.println(obtenerVecinos());
	}
	private ArrayList<HashSet<Integer>> obtenerVecinos(){
		ArrayList<HashSet<Integer>> vecinosDeVertices = new ArrayList<>();

		for (Vertice vertice : _setConVecinos) {
		    HashSet<Integer> vecinos = vertice.getVecinos();
		    vecinosDeVertices.add(vecinos);
		}
		
		return vecinosDeVertices;
	}
	private void dibujarAristasEnPlanoGoloso() {
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
				    }
				}
				List<Coordinate> route2 = new ArrayList<Coordinate>(Arrays.asList(obtenerCoordenadaNodoActual(contador), coordenadaCorrespondiente, coordenadaCorrespondiente));
				_planoGoloso.addMapPolygon(new MapPolygonImpl(route2));
				_planoBacktracking.addMapPolygon(new MapPolygonImpl(route2));
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
	 private void asignarCaracteristicas(JButton btn, int posX, int posY, int ancho, int largo) 
		{
			btn.setForeground(Color.BLACK);
			btn.setBackground(Color.WHITE);
			btn.setFont(tipografiaBoton);
			btn.setBounds(posX, posY, ancho, largo);
			btn.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseEntered(MouseEvent e) {
					btn.setForeground(Color.WHITE);
					btn.setBackground(Color.BLACK);
					btn.setBounds(posX-10, posY, ancho+35, largo+15);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btn.setForeground(Color.BLACK);
					btn.setBackground(Color.WHITE);
					btn.setBounds(posX, posY, ancho, largo);
				}
			});
		}
	public JFrame getPantallaCargarCGM() {
		return pantallaCargarCGM;
	}
	
}