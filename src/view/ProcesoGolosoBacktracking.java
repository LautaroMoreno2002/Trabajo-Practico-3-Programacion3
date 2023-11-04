package view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import presenter.Presenter;

public class ProcesoGolosoBacktracking extends SwingWorker<ArrayList<ArrayList<Integer>>,Long>  
{
	Presenter _presenter;
	JProgressBar _barraProgreso;
	public ProcesoGolosoBacktracking(Presenter p, JProgressBar bar) 
	{
		_presenter = p;
		_barraProgreso = bar;
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
				System.out.println(get().toString());
				_barraProgreso.setIndeterminate(false);
			}
		} catch (ExecutionException | InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}