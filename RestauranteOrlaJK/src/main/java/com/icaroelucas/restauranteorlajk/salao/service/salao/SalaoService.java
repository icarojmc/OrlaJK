package com.icaroelucas.restauranteorlajk.salao.service.salao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;

@Service
public class SalaoService {

	private MesaRepository mesaRepository = null;
	private SetorRepository setorRepository = null;

	public SalaoService iniciar(MesaRepository mesaRepository, SetorRepository setorRepository) {
		if (!foiIniciado()) {
			this.mesaRepository = mesaRepository;
			this.setorRepository = setorRepository;
		}
		return this;
	}

	private boolean foiIniciado() {
		return mesaRepository != null && setorRepository != null;
	}

	public Model popularModel(Model model) {
		List<Mesa> listaDeMesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("setores", buscarSetores());
		model.addAttribute("mesas", listaDeMesas);
		return model;
	}

	private List<Setor> buscarSetores() {
		return setorRepository.findAll();
	}

	public Model popularModel(Model model, Setor setor) {
		List<Mesa> listaDeMesas = mesaRepository.findAllByOcupadaAndSetor(true, setor);
		model.addAttribute("setores", buscarSetores());
		return model.addAttribute("mesas", listaDeMesas);
	}
	
	public void fechaAbreMesa(long id) {
		Mesa mesa = mesaRepository.findById(id).get();
		if(mesa.isFechada()) mesa.setFechada(false);
		else mesa.setFechada(true);
		mesaRepository.save(mesa);
	}

}
