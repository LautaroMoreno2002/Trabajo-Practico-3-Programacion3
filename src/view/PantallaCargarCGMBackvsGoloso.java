package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import model.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class PantallaCargarCGMBackvsGoloso extends DiseñoInterfaz
{
	private JMapViewer _planoGoloso;
	private JMapViewer _planoBacktracking;
	private ArrayList<Integer> _cgmGoloso;
	private ArrayList<Integer> _cgmBacktracking;
	private ArrayList<Vertice> _setConVecinos;
	private JFrame _interfazPresentacion;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private JFrame pantallaCargarCGMBackvsGoloso;
	private final Font tipografiaBoton = new Font("Arial", Font.BOLD, 12);
	private final Font tipografiaEtiqueta = new Font("Arial", Font.BOLD, 14);

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
	private void initialize() 
	{
		pantallaCargarCGMBackvsGoloso = new JFrame();
		pantallaCargarCGMBackvsGoloso.getContentPane().setBackground(Color.BLACK);
		pantallaCargarCGMBackvsGoloso.setResizable(false);
		pantallaCargarCGMBackvsGoloso.setTitle("Trabajo Práctico 3 - CGM Goloso vs CGM Backtracking");
		pantallaCargarCGMBackvsGoloso.setBounds(400, 100, 800, 650);
		pantallaCargarCGMBackvsGoloso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaCargarCGMBackvsGoloso.getContentPane().setLayout(null);

		JPanel grafoGoloso = new JPanel();
		grafoGoloso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		grafoGoloso.setBackground(Color.BLACK);
		grafoGoloso.setBounds(10, 11, 382, 497);
		pantallaCargarCGMBackvsGoloso.getContentPane().add(grafoGoloso);

		JPanel grafoBacktracking = new JPanel();
		grafoBacktracking
				.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		grafoBacktracking.setBackground(Color.BLACK);
		grafoBacktracking.setBounds(402, 11, 372, 497);
		pantallaCargarCGMBackvsGoloso.getContentPane().add(grafoBacktracking);

		JLabel lblGoloso = new JLabel("Goloso");
		lblGoloso.setForeground(Color.WHITE);
		lblGoloso.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoloso.setBounds(125, 519, 119, 17);
		lblGoloso.setFont(tipografiaEtiqueta);
		pantallaCargarCGMBackvsGoloso.getContentPane().add(lblGoloso);

		JLabel lblBacktrancking = new JLabel("Backtrancking");
		lblBacktrancking.setForeground(Color.WHITE);
		lblBacktrancking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBacktrancking.setBounds(535, 519, 119, 17);
		lblBacktrancking.setFont(tipografiaEtiqueta);
		pantallaCargarCGMBackvsGoloso.getContentPane().add(lblBacktrancking);

		JLabel lblVs = new JLabel("vs");
		lblVs.setForeground(Color.WHITE);
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(335, 519, 119, 17);
		lblVs.setFont(tipografiaEtiqueta);
		pantallaCargarCGMBackvsGoloso.getContentPane().add(lblVs);
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
		_planoGoloso.setDisplayPosition(coordinada, 11);
		_planoBacktracking.setDisplayPosition(coordinada, 11);

		JButton btnVolver = new JButton("Volver a cargar CGM");
		asignarCaracteristicas(btnVolver,tipografiaBoton, 305, 547, 188, 40);
		btnVolver.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				pantallaCargarCGMBackvsGoloso.setVisible(false);
				_interfazPresentacion.setVisible(true);
				pantallaCargarCGMBackvsGoloso.dispose();
			}
		});
		pantallaCargarCGMBackvsGoloso.getContentPane().add(btnVolver);

		double centroLatitudCirculo = -38.990380;
		double centroLongitudCirculo = -30.197439;
		double radioCirculo = 0.1;
		int numeroDePuntos = _setConVecinos.size();
		for (int indice = 0; indice < numeroDePuntos; indice++) 
		{
			double anguloDeDistribucion = (2 * Math.PI * indice) / numeroDePuntos;
			// Calcular las coordenadas del punto en el círculo
			double latitudPunto = centroLatitudCirculo + radioCirculo * Math.sin(anguloDeDistribucion);
			double longitudPunto = centroLongitudCirculo + radioCirculo * Math.cos(anguloDeDistribucion);
			// Crea el punto en el plano usando latitudPunto y longitudPunto
			crearNuevoPuntoEnElPlano(_planoGoloso,_cgmGoloso,_setConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, indice);
			crearNuevoPuntoEnElPlano(_planoBacktracking,_cgmBacktracking,_setConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, indice);
		}
		dibujarAristasEnPlano(_setConVecinos,_planoGoloso,coordenadasConIndice);
		dibujarAristasEnPlano(_setConVecinos,_planoBacktracking,coordenadasConIndice);
	}
	public JFrame getPantallaCargarCGMBackvsGoloso()
	{
		return pantallaCargarCGMBackvsGoloso;
	}
}