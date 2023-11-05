package view;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiPopupMenuUI;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import model.Vertice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class PantallaCargarGrupos extends PantallaDibujar
{
    private JFrame interfazGrafos;
    private JMapViewer plano;
    private ArrayList<Vertice> _setConVecinos;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private ArrayList<Integer> _cgmGoloso;
	private JFrame _interfazPresentacion;
    /**
     * Create the application.
     @wbp.parser.constructor

     */
	public PantallaCargarGrupos(ArrayList<Vertice> _setConVecinos, ArrayList<Integer> _cgmGoloso, JFrame _interfazPresentacion) 
	{
		this._setConVecinos = _setConVecinos;
		this._cgmGoloso = _cgmGoloso;
		this._interfazPresentacion = _interfazPresentacion;
		initialize();
	}
	/**
     * Initialize the contents of the frame.
     */
    private void initialize() 
    {
		interfazGrafos = new JFrame();
		interfazGrafos.setResizable(false);
		interfazGrafos.setType(Type.POPUP);
		interfazGrafos.setTitle("Trabajo Práctico 3 - Conjunto Generador Mínimo");
		interfazGrafos.getContentPane().setBackground(new Color(0, 0, 0));
		interfazGrafos.setBounds(400, 200, 800, 500);
		interfazGrafos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfazGrafos.getContentPane().setLayout(null);
		
		plano = new JMapViewer();
		plano.setBounds(10, 11, 774, 390);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBackground(new Color(0, 0, 0));
		panelMapa.setBounds(0, 0, 784, 412);
		
		interfazGrafos.getContentPane().add(panelMapa);
		panelMapa.setLayout(null);
		panelMapa.add(plano);
		plano.setZoomControlsVisible(false);
		
		JButton btnCargarNuevoCGM = new JButton("Generar nuevo CGM goloso");
		btnCargarNuevoCGM.setBounds(0, 0, 0, 0);
		btnCargarNuevoCGM.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				interfazGrafos.setVisible(false);
				_interfazPresentacion.setVisible(true);
			}
		});
		asignarCaracteristicas(btnCargarNuevoCGM,new Font("Arial", Font.BOLD, 12),288, 422, 205, 30);
		interfazGrafos.getContentPane().add(btnCargarNuevoCGM);
		
		Coordinate coordinada = new Coordinate(-38.99, -30.19);
		plano.setDisplayPosition(coordinada, 11); //PARA CAMBIAR EL ZOOM
		
		double centroLatitud = -38.990380; // Latitud del centro del círculo
		double centroLongitud = -30.197439; // Longitud del centro del círculo
		double radio = 0.1; // Radio del círculo en grados (ajusta según tus necesidades)
		int numPuntos = _setConVecinos.size();

		for (int i = 0; i < numPuntos; i++) 
		{
		    // Calcular el ángulo para distribuir los puntos uniformemente en el círculo
		    double angulo = (2 * Math.PI * i) / numPuntos;
		    // Calcular las coordenadas del punto en el círculo
		    double latitudPunto = centroLatitud + radio * Math.sin(angulo);
		    double longitudPunto = centroLongitud + radio * Math.cos(angulo);
		    crearNuevoPuntoEnElPlano(plano,_cgmGoloso,_setConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, i);
		}
		dibujarAristasEnPlano(_setConVecinos,plano,coordenadasConIndice);
    }
    public JFrame getInterfazGrafos() 
    {
        return interfazGrafos;
    }
}