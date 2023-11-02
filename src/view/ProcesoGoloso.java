package view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import presenter.Presenter;

public class ProcesoGoloso extends SwingWorker<ArrayList<Integer>,Long> 
{
	Presenter _presenter;
	JProgressBar _barraProgreso;
	public ProcesoGoloso(Presenter p, JProgressBar bar)
	{
		_presenter = p;
		_barraProgreso = bar;
	}

	@Override
	protected ArrayList<Integer> doInBackground() throws Exception
	{
		_barraProgreso.setStringPainted(true);
		_barraProgreso.setMaximum(100);
		_barraProgreso.setMinimum(1);
		for (long carga= 1; carga <= 100;carga++) 
		{
			if (carga % 10 == 0) Thread.sleep(100);
			_barraProgreso.setValue((int) carga);
			_barraProgreso.setString(carga + "%");
		}
		ArrayList<Integer> cm = _presenter.calcularCGMGoloso();
		return cm;
	}
	@Override
	protected void done() 
	{
		try {
			if (!this.isCancelled()) 
			{
				System.out.println(get().toString());
				_barraProgreso.setStringPainted(false);
				_barraProgreso.setValue(0);
			}
		} catch (ExecutionException | InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}

