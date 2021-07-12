package com.icaroelucas.restauranteorlajk.administracao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.administracao.dto.EdicaoMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

@Service
public class MesasAdminService {

	@Autowired
	MesaRepository mesaRepository;

	public List<Mesa> buscaMesas() {
		return mesaRepository.findAll();
	}

	public Mesa buscaMesa(String id) {
		return mesaRepository.findById(Long.parseLong(id)).get();
	}

	public void novaMesa(NovaMesaDTO mesa) {
		mesaRepository.save(mesa.toMesa());
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
