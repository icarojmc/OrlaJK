package com.icaroelucas.restauranteorlajk.administracao.setor.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.setor.dto.EdicaoSetorDTO;
import com.icaroelucas.restauranteorlajk.administracao.setor.dto.NovoSetorDTO;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.setor.repository.SetorRepository;


public class SetorService {

	private SetorRepository setorRepository = null;
	
	public SetorService iniciar(SetorRepository setorRepository) {
		if(!foiIniciado()) this.setorRepository = setorRepository;
		return this;
	}

	private boolean foiIniciado() {
		return setorRepository != null;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("setores", buscaSetor());
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		model.addAttribute("setor", buscaSetor(id));
		return model;
	}

	protected List<Setor> buscaSetor() {
		return setorRepository.findAll();
	}

	protected Setor buscaSetor(long id) {
		return setorRepository.findById(id).get();
	}

	public Setor novoSetor(NovoSetorDTO setor) {
		return setorRepository.save(setor.toSetor());
	}

	public void editaSetor(EdicaoSetorDTO setor) {
		setorRepository.save(setor.toSetor());
	}

	public void excluiSetor(long id) {
		try {
			setorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

}
