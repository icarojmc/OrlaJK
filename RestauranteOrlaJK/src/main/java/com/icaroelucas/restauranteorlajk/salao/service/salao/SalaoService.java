package com.icaroelucas.restauranteorlajk.salao.service.salao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

@Service
public class SalaoService {

	private MesaRepository mesaRepository = null;

	public SalaoService iniciar(MesaRepository mesaRepository) {
		if (!foiIniciado()) {
			this.mesaRepository = mesaRepository;
		}
		return this;
	}

	private boolean foiIniciado() {
		return mesaRepository != null;
	}

	public Model popularModel(Model model) {
		List<Mesa> listaDeMesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", listaDeMesas);
		return model;
	}

	public Model popularModel(Model model, String setor) {
		List<Mesa> listaDeMesas = mesaRepository.findAllByOcupadaAndSetor(true, Setor.valueOf(setor));
		return model.addAttribute("mesas", listaDeMesas);
	}
	
	public void fechaAbreMesa(long id) {
		Mesa mesa = mesaRepository.findById(id).get();
		if(mesa.isFechada()) mesa.setFechada(false);
		else mesa.setFechada(true);
		mesaRepository.save(mesa);
	}

}
