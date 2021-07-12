package com.icaroelucas.restauranteorlajk.salao.service.pedido;

import java.util.List;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;

public class AlimentoService {

	AlimentoRepository alimentoRepository = null;
	
	public void iniciar(AlimentoRepository alimentoRepository) {
		if(!foiIniciado()) this.alimentoRepository = alimentoRepository;
		
	}

	private boolean foiIniciado() {
		if(alimentoRepository != null) return true;
		return false;
	}
	
	public List<Alimento> buscaCardapio() {
		return alimentoRepository.findAll();
	}

	public Alimento buscaAlimento(long id) {
		return alimentoRepository.findById(id).get();
	}
	
	

}
