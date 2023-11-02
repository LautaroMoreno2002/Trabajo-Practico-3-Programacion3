package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class PantallaCargarGrupos {

    private JFrame interfazGrafos;
    private JMapViewer plano;

    /**
     * Create the application.
     */
    public PantallaCargarGrupos() {
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
		
		
		
		
    }

    public JFrame getInterfazGrafos() {
        return interfazGrafos;
    }
}
