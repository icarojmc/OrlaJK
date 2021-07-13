package com.icaroelucas.restauranteorlajk.administracao.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;


public class MesasAdminService {

	private MesaRepository mesaRepository = null;
	
	public MesasAdminService iniciar(MesaRepository mesaRepository) {
		if(!foiIniciado()) this.mesaRepository = mesaRepository;
		return this;
	}

	private boolean foiIniciado() {
		if(mesaRepository != null) return true;
		return false;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("mesas", buscaMesas());
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		model.addAttribute("mesas", buscaMesa(id));
		return model;
	}

	protected List<Mesa> buscaMesas() {
		return mesaRepository.findAll();
	}

	protected Mesa buscaMesa(long id) {
		return mesaRepository.findById(id).get();
	}

	public Mesa novaMesa(NovaMesaDTO mesa) {
		return mesaRepository.save(mesa.toMesa());
	}

	public void editaMesa(EdicaoMesaDTO mesa) {
		mesaRepository.save(mesa.toMesa());
	}

	public void excluiMesa(String id) {
		try {
			mesaRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

}
