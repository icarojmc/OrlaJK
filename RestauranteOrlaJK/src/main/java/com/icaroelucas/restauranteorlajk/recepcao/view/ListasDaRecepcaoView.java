package com.icaroelucas.restauranteorlajk.recepcao.view;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.espera.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;

public class ListasDaRecepcaoView {

	private List<Mesa> mesas;
	private List<ListaDeEspera> esperas;
	
	public ListasDaRecepcaoView(List<Mesa> mesas, List<ListaDeEspera> esperas) {
		this.mesas = mesas;
		this.esperas = esperas;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public List<ListaDeEspera> getEsperas() {
		return esperas;
	}
}
