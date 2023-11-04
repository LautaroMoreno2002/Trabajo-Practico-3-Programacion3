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

public class InterfazPresentacion 
{

	private JFrame interfazPresentacion;
	private Presenter presenter;
	private final String[] opciones = new String[] {"Ejemplo Grafo 1", "Ejemplo Grafo 2", "Ejemplo Grafo 3", "Grafo Personalizado"};
	private final Font tipografiaBoton = new Font("Arial", Font.BOLD, 12);
	private final Font tipografiaComboBox = new Font("Arial", Font.BOLD, 13);
	private final Font tipografiaEtiqueta = new Font("Arial", Font.BOLD, 14);
	private final Font tipografiaTitulo = new Font("Arial", Font.BOLD, 18);
	
	
	
	public JFrame getInterfazPresentacion() {
		return interfazPresentacion;
	}

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public InterfazPresentacion() 
	{
		presenter = new Presenter();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		bienvenida.setText("Bienvenido. Este programa se encarga de obtener un conjunto generador mínimo a partir de un grafo. \r\nHay dos formas de obtener el CGM:\r\n1) La primera es mediante un algoritmo goloso que da una solución pseudo-óptima. \r\n2) La segunda es con un algoritmo de tipo Backtracking que da la solución eficiente pero tarda más que el algoritmo goloso.\r\nPuede elegir entre 3 grafos de ejemplo o hacer su propio grafo en un archivo JSON. Luego calcular el CGM con alguno de los dos métodos o ejecutar los dos para comparar los resultados.\r\nEl CGM se visualizará en la siguiente pantalla junto con el grafo que haya elegido");
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
		asignarCaracteristicas(btnAlgGoloso,166,341,192,35);
		btnAlgGoloso.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int idOpcion = comboBoxGrafo.getSelectedIndex();
				presenter.elegirGrafo(idOpcion);
//				ArrayList<Integer> cm = presenter.calcularCGMGoloso();
//				System.out.println("Goloso: "+ cm);
				ProcesoGoloso procesoGoloso = new ProcesoGoloso(presenter, progressBar,interfazPresentacion);
				procesoGoloso.execute();
				
				//Me imprime un map del vértice con sus vecinos
				System.out.println(presenter.get_setConVecinos());

			}
		});
		interfazPresentacion.getContentPane().add(btnAlgGoloso);
		
		JButton btnAmbos = new JButton("Goloso vs Backtracking");
		btnAmbos.setToolTipText("Comparación de resultados");
		asignarCaracteristicas(btnAmbos,410, 341, 192, 35);
		btnAmbos.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int idOpcion = comboBoxGrafo.getSelectedIndex();
				presenter.elegirGrafo(idOpcion);
//				Set<Integer> cmB = presenter.calcularCGMBacktracking();
//				ArrayList<Integer> cmG = presenter.calcularCGMGoloso();
//				System.out.println("Algoritmo por cmb: "+ cmB + "\nAlgoritmo por cmG: "+ cmG);
				ProcesoGolosoBacktracking procesoVersus = new ProcesoGolosoBacktracking(presenter, progressBar);
				procesoVersus.execute();
				
				System.out.println(presenter.get_setConVecinos());
			}
		});
		interfazPresentacion.getContentPane().add(btnAmbos);
		
//		JButton btnBacktracking = new JButton("CGM con backtracking");
//		asignarCaracteristicas(btnBacktracking,546, 341, 192, 35);
//		btnBacktracking.addMouseListener(new MouseAdapter() 
//		{
//			@Override
//			public void mouseClicked(MouseEvent e)
//			{
//				int idOpcion = comboBoxGrafo.getSelectedIndex();
//				presenter.elegirGrafo(idOpcion);
////				Set<Integer> cm = presenter.calcularCGMBacktracking();
////				System.out.println("BackTracking: "+cm);
//				ProcesoBacktracking procesoBacktracking = new ProcesoBacktracking(presenter, progressBar);
//				procesoBacktracking.execute();
//			}
//		});
//		interfazPresentacion.getContentPane().add(btnBacktracking);
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
				btn.setBounds(posX-10, posY-10, ancho+35, largo+15);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setForeground(Color.BLACK);
				btn.setBackground(Color.WHITE);
				btn.setBounds(posX, posY, ancho, largo);
			}
		});
	}
}