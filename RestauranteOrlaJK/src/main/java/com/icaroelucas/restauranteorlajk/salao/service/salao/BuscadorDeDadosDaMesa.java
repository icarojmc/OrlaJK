package com.icaroelucas.restauranteorlajk.salao.service.salao;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

public class BuscadorDeDadosDaMesa {

	private MesaRepository mesaRepository = null;
	
	public BuscadorDeDadosDaMesa(MesaRepository mesaRepository) {
		this.mesaRepository = mesaRepository;
	}

	public List<Mesa> buscarMesas() {
		return mesaRepository.findAllByOcupada(true);
	}
	
	public List<Mesa> bucarMesas(Setor setor) {
		return mesaRepository.findAllByOcupadaAndSetor(true, setor);
	}
	
	public Mesa buscarMesa(long id) {
		return mesaRepository.findById(id).get();
	}

	public void salva(Mesa mesa) {
		mesaRepository.save(mesa);
	}
	
	
}
