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
	ArrayList<Integer> cm;
	JFrame _interfazPresentacion;

	
	public ProcesoGoloso(Presenter _presenter, JProgressBar _barraProgreso, JFrame _interfazPresentacion) {
		this._presenter = _presenter;
		this._barraProgreso = _barraProgreso;
		this._interfazPresentacion = _interfazPresentacion;
	}


	public ProcesoGoloso() {
		super();
	}


	@Override
	protected ArrayList<Integer> doInBackground() throws Exception
	{
		_barraProgreso.setStringPainted(true);
		for (long carga= 1; carga <= 100;carga++) 
		{
			if (carga % 10 == 0) Thread.sleep(100);
			_barraProgreso.setValue((int) carga);
			_barraProgreso.setString(carga + "%");
		}
		ArrayList<Integer> cm = _presenter.calcularCGMGoloso();
		
		PantallaCargarGrupos pantallaCargarGrupos = new PantallaCargarGrupos(_presenter.get_setConVecinos(),cm,_presenter.listaDeVecinos(),_interfazPresentacion);
		pantallaCargarGrupos.getInterfazGrafos().setVisible(true);

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
				
				_interfazPresentacion.setVisible(false);

			}
		} catch (ExecutionException | InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}

