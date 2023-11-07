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
	private JFrame pantallaCargarCGM;
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
		_planoGoloso.setDisplayPosition(coordinada, 11);
		_planoBacktracking.setDisplayPosition(coordinada, 11);

		JButton btnVolver = new JButton("Volver a cargar CGM");
		asignarCaracteristicas(btnVolver,tipografiaBoton, 305, 547, 188, 40);
		btnVolver.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				pantallaCargarCGM.setVisible(false);
				_interfazPresentacion.setVisible(true);
			}
		});
		pantallaCargarCGM.getContentPane().add(btnVolver);

		double latitudCentroDelCirculo = -38.990380;
		double longitudCentroDelCirculo = -30.197439;
		double radioDelCirculo = 0.1;
		int numeroDePuntos = _setConVecinos.size();
		for (int i = 0; i < numeroDePuntos; i++) {
			// Calcular el ángulo para distribuir los puntos uniformemente en el círculo
			double anguloDeDistribucion = (2 * Math.PI * i) / numeroDePuntos;
			// Calcular las coordenadas del punto en el círculo
			double latitudPunto = latitudCentroDelCirculo + radioDelCirculo * Math.sin(anguloDeDistribucion);
			double longitudPunto = longitudCentroDelCirculo + radioDelCirculo * Math.cos(anguloDeDistribucion);
			// Crea el punto en el plano usando latitudPunto y longitudPunto
			crearNuevoPuntoEnElPlano(_planoGoloso,_cgmGoloso,_setConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, _setConVecinos.get(i).getNumeroVertice());
			crearNuevoPuntoEnElPlano(_planoBacktracking,_cgmBacktracking,_setConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, _setConVecinos.get(i).getNumeroVertice());			
		}
		dibujarAristasEnPlano(_setConVecinos,_planoGoloso,coordenadasConIndice);
		dibujarAristasEnPlano(_setConVecinos,_planoBacktracking,coordenadasConIndice);
	}
	public JFrame getPantallaCargarCGM()
	{
		return pantallaCargarCGM;
	}
}