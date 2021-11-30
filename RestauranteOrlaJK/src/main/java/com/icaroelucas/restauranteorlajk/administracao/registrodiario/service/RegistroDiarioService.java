package com.icaroelucas.restauranteorlajk.administracao.registrodiario.service;

import java.time.LocalDate;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		return registroDiarioRepository != null;
	}

	public Model popularModel(Model model, LocalDate dia, int nPagina) {
		Page<RegistroDiario> page = buscaRegistros(dia, nPagina);
		model.addAttribute("registros", page);
		model.addAttribute("nPagina", nPagina);
		model.addAttribute("totalPaginas", page.getTotalPages());
		model.addAttribute("dia", dia);
		return model;
	}

	protected Page<RegistroDiario> buscaRegistros(LocalDate dia, int nPagina) {
		Pageable pageable = PageRequest.of(nPagina -1, 10, Sort.by("dataDoRegistro").descending());
		return registroDiarioRepository.findAllByDataDoRegistro(pageable, dia);
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
