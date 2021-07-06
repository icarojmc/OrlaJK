package com.icaroelucas.restauranteorlajk.service.mesas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.model.Setor;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;

@Service
public class SalaoService {

	private MesaRepository mesaRepository = null;

	public SalaoService iniciar(MesaRepository mesaRepository) {
		if (!foiIniciado())
			this.mesaRepository = mesaRepository;
		return this;
	}

	private boolean foiIniciado() {
		if (mesaRepository != null)
			return true;
		return false;
	}

	public Model popularModel(Model model) {
		List<Mesa> listaDeMesas = listarMesas();
		model.addAttribute("mesas", listaDeMesas);
		return model;
	}

	public List<Mesa> listarMesas() {
		return mesaRepository.findAllByOcupada(true);
	}

	public Model popularModel(Model model, String setor) {
		List<Mesa> listaDeMesas = listarMesas(Setor.valueOf(setor));
		return model.addAttribute("mesas", listaDeMesas);
	}
	
	public List<Mesa> listarMesas(Setor setor) {
		return mesaRepository.findAllByOcupadaAndSetor(true, setor);
	}
	
	public void fechaMesa(String id) {
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		mesa.setFechada(true);
		mesaRepository.save(mesa);

	}

	public void abreMesa(String id) {
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		mesa.setFechada(false);
		mesaRepository.save(mesa);
	}

}
