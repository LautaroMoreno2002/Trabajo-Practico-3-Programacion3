package view;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import presenter.Presenter;

public class ProcesoBacktracking extends SwingWorker<Set<Integer>,Long> 
{
	Presenter _presenter;
	JProgressBar _barraProgreso;
	public ProcesoBacktracking(Presenter p, JProgressBar bar) 
	{
		_presenter = p;
		_barraProgreso = bar;
	}

	@Override
	protected Set<Integer> doInBackground() throws Exception 
	{
		_barraProgreso.setIndeterminate(true);
		Thread.sleep(1500);
		Set<Integer> cm = _presenter.calcularCGMBacktracking();
		return cm;
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