package com.icaroelucas.restauranteorlajk.administracao.debito.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.debito.dto.EdicaoDebitoDTO;
import com.icaroelucas.restauranteorlajk.administracao.debito.dto.NovoDebitoDTO;
import com.icaroelucas.restauranteorlajk.entities.debito.model.Debito;
import com.icaroelucas.restauranteorlajk.entities.debito.repository.DebitoRepository;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.repository.FornecedorRepository;

public class DebitoService {

	private DebitoRepository debitoRepository = null;
	
	public DebitoService iniciar(DebitoRepository debitoRepository) {
		if(!foiIniciado()) {
			this.debitoRepository = debitoRepository;
		}
		return this;
	}

	private boolean foiIniciado() {
		return debitoRepository != null;
	}

	public Model popularModel(Model model) {
		return model.addAttribute("debitos", buscarDebitos());
	}
	
	public Model popularModel(Model model, long id, FornecedorRepository fornecedorRepository) {
		model.addAttribute("debito", buscarDebito(id));
		model.addAttribute("fornecedores", buscarFornecedores(fornecedorRepository));
		return model;
	}

	public Model popularModel(Model model, FornecedorRepository fornecedorRepository) {
		model.addAttribute("fornecedores", buscarFornecedores(fornecedorRepository));
		return model;
	}
	
	private List<Debito> buscarDebitos() {
		return debitoRepository.findAll();
	}
	
	private Debito buscarDebito(long id) {
		return debitoRepository.findById(id).get();
	}

	private List<Fornecedor> buscarFornecedores(FornecedorRepository fornecedorRepository) {
		return fornecedorRepository.findAll();
	}

	public Debito novoDebito(NovoDebitoDTO debito) {
		return debitoRepository.save(debito.toDebito());
	}

	public Debito editaDebito(EdicaoDebitoDTO debito) {
		return debitoRepository.save(debito.toDebito());
		
	}

	public void excluiDebito(long id) {
		try {
			debitoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

	

}
