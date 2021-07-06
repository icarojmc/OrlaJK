package com.icaroelucas.restauranteorlajk.service.administracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.model.RegistroDiario;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;

@Service
public class RegistroDiarioService {
	
	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	public void registraConsumo(Mesa mesa, RegistroDiarioRepository registroDiarioRepository) {
		RegistroDiarioDTO registro = new RegistroDiarioDTO();
		registroDiarioRepository.save(registro.toRegistro(mesa));
	}
	
	public List<RegistroDiario> buscaRegistros(){
		return registroDiarioRepository.findAll();
	}
	
	public void deletaRegistro(String id) {
		try {
			registroDiarioRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}
}
