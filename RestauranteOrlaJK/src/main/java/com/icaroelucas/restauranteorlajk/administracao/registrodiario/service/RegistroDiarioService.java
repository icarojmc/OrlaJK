package com.icaroelucas.restauranteorlajk.administracao.registrodiario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.registrodiario.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.model.RegistroDiario;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;

public class RegistroDiarioService {

	RegistroDiarioRepository registroDiarioRepository = null;

	public RegistroDiarioService iniciar(RegistroDiarioRepository registroDiarioRepository) {
		if (!foiIniciado())
			this.registroDiarioRepository = registroDiarioRepository;
		return this;
	}

	private boolean foiIniciado() {
		if (registroDiarioRepository != null)
			return true;
		return false;
	}

	public Model popularModel(Model model) {
		model = model.addAttribute("registros", buscaRegistros());
		return model;
	}

	protected List<RegistroDiario> buscaRegistros() {
		return registroDiarioRepository.findAll();
	}

	public void deletaRegistro(long id) {
		try {
			registroDiarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

	public void registraConsumo(Mesa mesa, RegistroDiarioRepository registroDiarioRepository) {
		RegistroDiarioDTO registro = new RegistroDiarioDTO();
		registroDiarioRepository.save(registro.toRegistro(mesa));
	}

}
