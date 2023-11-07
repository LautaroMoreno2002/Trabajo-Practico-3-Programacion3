package view;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import presenter.Presenter;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

public class InterfazPresentacion extends DiseñoInterfaz
{
	private JFrame interfazPresentacion;
	private Presenter presenter;
	private final String[] opciones = new String[] {"Ejemplo Grafo 1", "Ejemplo Grafo 2", "Ejemplo Grafo 3", "Grafo Personalizado"};
	private final Font tipografiaBoton = new Font("Arial", Font.BOLD, 12);
	private final Font tipografiaComboBox = new Font("Arial", Font.BOLD, 13);
	private final Font tipografiaEtiqueta = new Font("Arial", Font.BOLD, 14);
	private final Font tipografiaTitulo = new Font("Arial", Font.BOLD, 18);
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try {
					InterfazPresentacion window = new InterfazPresentacion();
					window.interfazPresentacion.setVisible(true);
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					UIManager.getSystemLookAndFeelClassName();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public InterfazPresentacion() 
	{
		presenter = new Presenter();
		initialize();
	}
	private void initialize() 
	{
		interfazPresentacion = new JFrame();
		interfazPresentacion.setResizable(false);
		interfazPresentacion.setType(Type.POPUP);
		interfazPresentacion.setTitle("Trabajo Práctico 3 - Conjunto Generador Mínimo");
		interfazPresentacion.getContentPane().setBackground(Color.BLACK);
		interfazPresentacion.setBounds(400, 200, 800, 500);
		interfazPresentacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfazPresentacion.getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Conjunto Generador Mínimo");
		titulo.setForeground(Color.GREEN);
		titulo.setBackground(Color.WHITE);
		titulo.setFont(tipografiaTitulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(236, 39, 306, 28);
		interfazPresentacion.getContentPane().add(titulo);
		
		JTextPane bienvenida = new JTextPane();
		bienvenida.setForeground(Color.GREEN);
		bienvenida.setBackground(Color.BLACK);
		bienvenida.setEditable(false);
		bienvenida.setFont(tipografiaEtiqueta);
		bienvenida.setText("Bienvenido. Este programa se encarga de obtener un conjunto generador mínimo a partir de un grafo. \r\nHay dos formas de obtener el CGM:\r\n1) La primera es mediante un algoritmo goloso que da una solución pseudo-óptima. \r\n2) La segunda es con un algoritmo de tipo Backtracking que da la solución eficiente pero tarda más que el algoritmo goloso.\r\nPuede elegir entre 3 grafos de ejemplo o hacer su propio grafo en un archivo JSON. Luego calcular el CGM con el algoritmo goloso o ejecutar los dos para comparar los resultados.\r\nEl CGM se visualizará en la siguiente pantalla junto con el grafo que haya elegido");
		bienvenida.setBounds(31, 78, 725, 142);
		interfazPresentacion.getContentPane().add(bienvenida);

		JLabel lblImplementar = new JLabel("Implementar el algoritmo con:");
		lblImplementar.setFont(tipografiaEtiqueta);
		lblImplementar.setForeground(Color.GREEN);
		lblImplementar.setBounds(124, 275, 234, 28);
		interfazPresentacion.getContentPane().add(lblImplementar);
		
		JComboBox<String> comboBoxGrafo = new JComboBox<String>();
		comboBoxGrafo.setBackground(Color.WHITE);
		comboBoxGrafo.setFont(tipografiaComboBox);
		comboBoxGrafo.setModel(new DefaultComboBoxModel<String>(opciones));
		comboBoxGrafo.setBounds(453, 276, 182, 28);
		interfazPresentacion.getContentPane().add(comboBoxGrafo);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setBounds(31, 407, 725, 28);
		interfazPresentacion.getContentPane().add(progressBar);
		
		JButton btnAlgGoloso = new JButton("CGM con algoritmo Goloso");
		btnAlgGoloso.setToolTipText("CGM con una posible solución");
		asignarCaracteristicas(btnAlgGoloso,tipografiaBoton,166,341,192,35);
		btnAlgGoloso.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{	
				int idOpcion = comboBoxGrafo.getSelectedIndex();
				presenter.elegirGrafo(idOpcion);
				ProcesoGoloso procesoGoloso = new ProcesoGoloso(presenter, progressBar,interfazPresentacion);
				procesoGoloso.execute();
			}
		});
		interfazPresentacion.getContentPane().add(btnAlgGoloso);
		
		JButton btnAmbos = new JButton("Goloso vs Backtracking");
		btnAmbos.setToolTipText("Comparación de resultados");
		asignarCaracteristicas(btnAmbos,tipografiaBoton,410, 341, 192, 35);
		btnAmbos.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int idOpcion = comboBoxGrafo.getSelectedIndex();
				presenter.elegirGrafo(idOpcion);
				ProcesoGolosoBacktracking procesoVersus = new ProcesoGolosoBacktracking(presenter,progressBar,interfazPresentacion);
				procesoVersus.execute();
			}
		});
		interfazPresentacion.getContentPane().add(btnAmbos);
	}
	public JFrame getInterfazPresentacion() {
		return interfazPresentacion;
	}
}