package view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import presenter.Presenter;

public class ProcesoGoloso extends SwingWorker<ArrayList<Integer>,Long> 
{
	Presenter _presenter;
	JProgressBar _barraProgreso;
	JFrame _interfazPresentacion;

	public ProcesoGoloso(Presenter _presenter, JProgressBar _barraProgreso, JFrame _interfazPresentacion)
	{
		this._presenter = _presenter;
		this._barraProgreso = _barraProgreso;
		this._interfazPresentacion = _interfazPresentacion;
	}
	@Override
	protected ArrayList<Integer> doInBackground() throws Exception
	{
		_barraProgreso.setStringPainted(true);
		ArrayList<Integer> cgmGoloso = new ArrayList<Integer>();
		for (long carga= 1; carga <= 100;carga++) 
		{
			if (carga % 10 == 0) 
				Thread.sleep(100);
			if (carga == 50) 
				cgmGoloso = _presenter.calcularCGMGoloso();
			_barraProgreso.setValue((int) carga);
			_barraProgreso.setString(carga + "%");
		}
		return cgmGoloso;
	}
	@Override
	protected void done() 
	{
		try {
			if (!this.isCancelled()) 
			{
				_barraProgreso.setStringPainted(false);
				_barraProgreso.setValue(0);
				PantallaCargarCGMGoloso pantallaCargarGrupos = new PantallaCargarCGMGoloso(_presenter.get_setConVecinos(),get(),_interfazPresentacion);
				pantallaCargarGrupos.getPantallaCargarCGMGoloso().setVisible(true);
				_interfazPresentacion.setVisible(false);

			}
		} catch (ExecutionException | InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}