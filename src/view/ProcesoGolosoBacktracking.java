package view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import presenter.Presenter;

public class ProcesoGolosoBacktracking extends SwingWorker<ArrayList<ArrayList<Integer>>,Long>  
{
	Presenter _presenter;
	JProgressBar _barraProgreso;
	JFrame _interfazPresentacion;
	public ProcesoGolosoBacktracking(Presenter presenter, JProgressBar barraProgreso,JFrame interfazPresentacion) 
	{
		_presenter = presenter;
		_barraProgreso = barraProgreso;
		_interfazPresentacion = interfazPresentacion;
	}
	@Override
	protected ArrayList<ArrayList<Integer>> doInBackground() throws Exception 
	{
		ArrayList<ArrayList<Integer>> solucionesCGM = new ArrayList<ArrayList<Integer>>();
		_barraProgreso.setIndeterminate(true);
		Thread.sleep(1500);
		ArrayList<Integer> cgmGoloso = _presenter.calcularCGMGoloso();
		ArrayList<Integer> cgmBacktracking = _presenter.calcularCGMBacktracking();
		solucionesCGM.add(cgmGoloso);
		solucionesCGM.add(cgmBacktracking);
		return solucionesCGM;
	}
	@Override
	protected void done() 
	{
		try
		{
			if (!this.isCancelled()) 
			{
				_barraProgreso.setIndeterminate(false);
				PantallaCargarCGMBackvsGoloso pantallaCargarCGMBackvsGoloso = new PantallaCargarCGMBackvsGoloso(_presenter.get_setConVecinos(),get(),_interfazPresentacion);
				pantallaCargarCGMBackvsGoloso.getPantallaCargarCGM().setVisible(true);
				_interfazPresentacion.setVisible(false);
			}
		} catch (ExecutionException | InterruptedException e) 
		{
			System.out.println("Formato del JSON incorrecto: " + e.getMessage());
		}
	}
}