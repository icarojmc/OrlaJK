package com.icaroelucas.restauranteorlajk.administracao.mesas.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;


public class MesasAdminService {

	private MesaRepository mesaRepository = null;
	
	public MesasAdminService iniciar(MesaRepository mesaRepository) {
		if(!foiIniciado()) this.mesaRepository = mesaRepository;
		return this;
	}

	private boolean foiIniciado() {
		return mesaRepository != null;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("mesas", buscaMesas());
		return model;
	}
	
	public Model popularModel(Model model, long id, SetorRepository setorRepository) {
		model.addAttribute("setores", buscaSetor(setorRepository));
		model.addAttribute("mesa", buscaMesa(id));
		return model;
	}
	
	public Model popularModel(Model model, SetorRepository setorRepository) {
		model.addAttribute("setores", buscaSetor(setorRepository));
		return model;
	}

	private List<Setor> buscaSetor(SetorRepository setorRepository) {
		return setorRepository.findAll();
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

	public void excluiMesa(long id) {
		try {
			mesaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

}
