package view;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import model.Vertice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaCargarCGMGoloso extends DiseñoInterfaz
{
    private JFrame pantallaCargarCGMGoloso;
    private JMapViewer plano;
    private ArrayList<Vertice> _verticesConVecinos;
	private HashMap<Coordinate, Integer> coordenadasConIndice = new HashMap<>();
	private ArrayList<Integer> _cgmGoloso;
	private JFrame _interfazPresentacion;
    /**
     @wbp.parser.constructor
     */
	public PantallaCargarCGMGoloso(ArrayList<Vertice> setConVecinos, ArrayList<Integer> cgmGoloso, JFrame interfazPresentacion) 
	{
		this._verticesConVecinos = setConVecinos;
		this._cgmGoloso = cgmGoloso;
		this._interfazPresentacion = interfazPresentacion;
		initialize();
	}
    private void initialize() 
    {
		pantallaCargarCGMGoloso = new JFrame();
		pantallaCargarCGMGoloso.setResizable(false);
		pantallaCargarCGMGoloso.setType(Type.POPUP);
		pantallaCargarCGMGoloso.setTitle("Trabajo Práctico 3 - Conjunto Generador Mínimo con algoritmo Goloso");
		pantallaCargarCGMGoloso.getContentPane().setBackground(new Color(0, 0, 0));
		pantallaCargarCGMGoloso.setBounds(400, 200, 800, 500);
		pantallaCargarCGMGoloso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaCargarCGMGoloso.getContentPane().setLayout(null);
		
		plano = new JMapViewer();
		plano.setBounds(10, 11, 774, 390);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBackground(Color.BLACK);
		panelMapa.setBounds(0, 0, 784, 412);
		
		pantallaCargarCGMGoloso.getContentPane().add(panelMapa);
		panelMapa.setLayout(null);
		panelMapa.add(plano);
		plano.setZoomControlsVisible(false);
		
		JButton btnCargarNuevoCGM = new JButton("Generar nuevo CGM goloso");
		btnCargarNuevoCGM.setBounds(0, 0, 0, 0);
		btnCargarNuevoCGM.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				pantallaCargarCGMGoloso.setVisible(false);
				_interfazPresentacion.setVisible(true);
				pantallaCargarCGMGoloso.dispose();
			}
		});
		asignarCaracteristicas(btnCargarNuevoCGM,new Font("Arial", Font.BOLD, 12),288, 422, 205, 30);
		pantallaCargarCGMGoloso.getContentPane().add(btnCargarNuevoCGM);
		
		Coordinate coordinada = new Coordinate(-38.99, -30.19);
		plano.setDisplayPosition(coordinada, 11);
		
		double centroLatitudCirculo = -38.990380;
		double centroLongitudCirculo = -30.197439;
		double radioCirculo = 0.1;
		int numeroDePuntos = _verticesConVecinos.size();

		for (int i = 0; i < numeroDePuntos; i++) 
		{
		    // Calcular el ángulo para distribuir los puntos uniformemente en el círculo
		    double anguloDeDistribucion = (2 * Math.PI * i) / numeroDePuntos;
		    // Calcular las coordenadas del punto en el círculo
		    double latitudPunto = centroLatitudCirculo + radioCirculo * Math.sin(anguloDeDistribucion);
		    double longitudPunto = centroLongitudCirculo + radioCirculo * Math.cos(anguloDeDistribucion);
		    crearNuevoPuntoEnElPlano(plano,_cgmGoloso,_verticesConVecinos,coordenadasConIndice,latitudPunto, longitudPunto, i);
		}
		dibujarAristasEnPlano(_verticesConVecinos,plano,coordenadasConIndice);
    }
    public JFrame getPantallaCargarCGMGoloso() 
    {
        return pantallaCargarCGMGoloso;
    }
}