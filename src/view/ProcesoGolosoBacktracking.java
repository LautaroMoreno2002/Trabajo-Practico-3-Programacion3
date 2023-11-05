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
	public ProcesoGolosoBacktracking(Presenter p, JProgressBar bar,JFrame interfazPresentacion) 
	{
		_presenter = p;
		_barraProgreso = bar;
		_interfazPresentacion = interfazPresentacion;
	}
	@Override
	protected ArrayList<ArrayList<Integer>> doInBackground() throws Exception 
	{
		ArrayList<ArrayList<Integer>> soluciones = new ArrayList<ArrayList<Integer>>();
		_barraProgreso.setIndeterminate(true);
		Thread.sleep(1500);
		ArrayList<Integer> cmG = _presenter.calcularCGMGoloso();
		ArrayList<Integer> cmB = _presenter.calcularCGMBacktracking();
		soluciones.add(cmG);
		soluciones.add(cmB);
		return soluciones;
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
			e.printStackTrace();
		}
	}
}