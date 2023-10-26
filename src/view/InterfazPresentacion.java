package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import presenter.Presenter;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class InterfazPresentacion {

	private JFrame interfazPresentacion;
	private Presenter presenter;
	private final String[] opciones = new String[] {"Ejemplo Grafo 1", "Ejemplo Grafo 2", "Ejemplo Grafo 3", "Grafo Personalizado"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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

	/**
	 * Create the application.
	 */
	public InterfazPresentacion() {
		presenter = new Presenter();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		interfazPresentacion = new JFrame();
		interfazPresentacion.setResizable(false);
		interfazPresentacion.setType(Type.POPUP);
		interfazPresentacion.setTitle("Trabajo Práctico 3 - Conjunto Generador Mínimo");
		interfazPresentacion.setFont(new Font("Arial", Font.PLAIN, 12));
		interfazPresentacion.getContentPane().setBackground(Color.BLACK);
		interfazPresentacion.setBounds(400, 200, 800, 500);
		interfazPresentacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfazPresentacion.getContentPane().setLayout(null);
		
		JProgressBar barraProgreso = new JProgressBar();
		barraProgreso.setToolTipText("Barra de progreso");
		barraProgreso.setStringPainted(true);
		barraProgreso.setIndeterminate(true);
		barraProgreso.setForeground(Color.RED);
		barraProgreso.setFont(new Font("Arial", Font.BOLD, 11));
		barraProgreso.setBounds(99, 386, 600, 28);
		interfazPresentacion.getContentPane().add(barraProgreso);
		
		JLabel titulo = new JLabel("Conjunto Generador Mínimo");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(235, 21, 306, 28);
		interfazPresentacion.getContentPane().add(titulo);
		
		JTextPane bienvenida = new JTextPane();
		bienvenida.setToolTipText("");
		bienvenida.setForeground(Color.WHITE);
		bienvenida.setBackground(Color.BLACK);
		bienvenida.setEditable(false);
		bienvenida.setFont(new Font("Arial", Font.BOLD, 14));
		bienvenida.setText("Bienvenido/a. En este programa, se usa un grafo, previamente ingresado por un archivo JSON, del cual se calculará un posible Conjunto Generador Mínimo. Es un \"posible\" ya que pueden haber más de un conjunto, con distintos vértices. No siempre se obtendrá la misma solución.\r\nPara calcular el conjunto se puede usar un algortimo goloso, que brinda la posible solución y luego se visualizarán los datos.\r\nHay ejemplos de grafos cargados en formato JSON para elegir y poder visualizar el programa. En caso de que el usuario lo quiera, puede hacer su propio grafo respetando su estructura (vértices y los vecinos del vértice).");
		bienvenida.setBounds(10, 70, 764, 142);
		interfazPresentacion.getContentPane().add(bienvenida);

		JLabel lblImplementar = new JLabel("Implementar el algoritmo con:");
		lblImplementar.setFont(new Font("Arial", Font.BOLD, 14));
		lblImplementar.setForeground(new Color(255, 255, 255));
		lblImplementar.setBounds(145, 248, 234, 28);
		interfazPresentacion.getContentPane().add(lblImplementar);
		
		JComboBox<String> comboBoxGrafo = new JComboBox<String>();
		comboBoxGrafo.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxGrafo.setModel(new DefaultComboBoxModel<String>(opciones));
		comboBoxGrafo.setBounds(456, 249, 182, 28);
		interfazPresentacion.getContentPane().add(comboBoxGrafo);
		
		JButton btnAlgGoloso = new JButton("CGM con algoritmo Goloso");
		btnAlgGoloso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idOpcion = comboBoxGrafo.getSelectedIndex();
				presenter.calcularCGMGoloso(idOpcion);
			}
		});
		btnAlgGoloso.setForeground(Color.BLACK);
		btnAlgGoloso.setBackground(Color.WHITE);
		btnAlgGoloso.setFont(new Font("Arial", Font.BOLD, 12));
		btnAlgGoloso.setBounds(187, 317, 192, 35);
		interfazPresentacion.getContentPane().add(btnAlgGoloso);
		
		
	}
}
