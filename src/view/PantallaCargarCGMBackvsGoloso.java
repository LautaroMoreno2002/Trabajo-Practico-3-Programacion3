package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import model.Vertice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class PantallaCargarCGMBackvsGoloso extends PantallaDibujar
{
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
	public PantallaCargarCGMBackvsGoloso(ArrayList<Vertice> setConVecinos, ArrayList<ArrayList<Integer>> solucionesCGM,
			JFrame interfazPresentacion) 
	{
		_setConVecinos = setConVecinos;
		_cgmGoloso = solucionesCGM.get(0);
		_cgmBacktracking = solucionesCGM.get(1);
		_interfazPresentacion = interfazPresentacion;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		pantallaCargarCGM = new JFrame();
		pantallaCargarCGM.getContentPane().setBackground(Color.BLACK);
		pantallaCargarCGM.setResizable(false);
		pantallaCargarCGM.setTitle("CGM Goloso vs CGM Backtracking");
		pantallaCargarCGM.setBounds(400, 100, 800, 650);
		pantallaCargarCGM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaCargarCGM.getContentPane().setLayout(null);

		JPanel grafoGoloso = new JPanel();
		grafoGoloso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		grafoGoloso.setBackground(Color.BLACK);
		grafoGoloso.setBounds(10, 11, 382, 497);
		pantallaCargarCGM.getContentPane().add(grafoGoloso);

		JPanel grafoBacktracking = new JPanel();
		grafoBacktracking
				.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		grafoBacktracking.setBackground(Color.BLACK);
		grafoBacktracking.setBounds(402, 11, 372, 497);
		pantallaCargarCGM.getContentPane().add(grafoBacktracking);

		JLabel lblGoloso = new JLabel("Goloso");
		lblGoloso.setForeground(Color.WHITE);
		lblGoloso.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoloso.setBounds(125, 519, 119, 17);
		lblGoloso.setFont(tipografiaEtiqueta);
		pantallaCargarCGM.getContentPane().add(lblGoloso);

		JLabel lblBacktrancking = new JLabel("Backtrancking");
		lblBacktrancking.setForeground(Color.WHITE);
		lblBacktrancking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBacktrancking.setBounds(535, 519, 119, 17);
		lblBacktrancking.setFont(tipografiaEtiqueta);
		pantallaCargarCGM.getContentPane().add(lblBacktrancking);

		JLabel lblVs = new JLabel("vs");
		lblVs.setForeground(Color.WHITE);
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
		_planoGoloso.setDisplayPosition(coordinada, 11); // PARA CAMBIAR EL ZOOM
		_planoBacktracking.setDisplayPosition(coordinada, 11); // PARA CAMBIAR EL ZOOM

		JButton btnVolver = new JButton("Volver a cargar CGM");
		asignarCaracteristicas(btnVolver, 305, 547, 188, 40);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
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
	private void crearNuevoPuntoEnElPlanoG(double latitud, double longitud, int punto) 
	{
		Coordinate coordinadasPunto = new Coordinate(latitud, longitud);
		MapMarker marker = new MapMarkerDot("" + _setConVecinos.get(punto).getIdVertice(), coordinadasPunto);
		// Agregamos las coordenadasConIndice al poligono
		coordenadasConIndice.put(coordinadasPunto, punto);

		if (_cgmGoloso.contains(_setConVecinos.get(punto).getIdVertice())) 
		{
			marker.getStyle().setBackColor(Color.GREEN);
			marker.getStyle().setColor(Color.BLACK);
		} else {
			marker.getStyle().setBackColor(Color.BLACK);
			marker.getStyle().setColor(Color.WHITE);
		}
		_planoGoloso.addMapMarker(marker);
	}
	private void crearNuevoPuntoEnElPlanoB(double latitud, double longitud, int punto) 
	{
		Coordinate coordinadasPunto = new Coordinate(latitud, longitud);
		MapMarker marker = new MapMarkerDot("" + _setConVecinos.get(punto).getIdVertice(), coordinadasPunto);
		// Agregamos las coordenadasConIndice al poligono
		coordenadasConIndice.put(coordinadasPunto, punto);
		System.out.println(coordinadasPunto + "Indice" + punto);

		if (_cgmBacktracking.contains(_setConVecinos.get(punto).getIdVertice()))
{
			marker.getStyle().setBackColor(Color.GREEN);
			marker.getStyle().setColor(Color.BLACK);
		} else 
		{
			marker.getStyle().setBackColor(Color.BLACK);
			marker.getStyle().setColor(Color.WHITE);
		}
		_planoBacktracking.addMapMarker(marker);
	}
	private void dibujarAristasEnPlanoGoloso() 
	{
		Integer contador = 0;
		for (HashSet<Integer> conjuntoVecinosVertice : obtenerVecinos(_setConVecinos)) 
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
				List<Coordinate> route2 = new ArrayList<Coordinate>(Arrays.asList(obtenerCoordenadaNodoActual(contador),
						coordenadaCorrespondiente, coordenadaCorrespondiente));
				_planoGoloso.addMapPolygon(new MapPolygonImpl(route2));
				_planoBacktracking.addMapPolygon(new MapPolygonImpl(route2));
			}
			contador++;
		}
	}
	private void asignarCaracteristicas(JButton btn, int posX, int posY, int ancho, int largo) 
	{
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
		btn.setFont(tipografiaBoton);
		btn.setBounds(300, 547, ancho, largo);
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
	public Coordinate obtenerCoordenadaNodoActual(Integer contador) 
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
	public JFrame getPantallaCargarCGM()
	{
		return pantallaCargarCGM;
	}

}