package com.icaroelucas.restauranteorlajk.administracao.fornecedor.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.fornecedor.dto.EdicaoFornecedorDTO;
import com.icaroelucas.restauranteorlajk.administracao.fornecedor.dto.NovoFornecedorDTO;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.repository.FornecedorRepository;

public class FornecedorService {

	FornecedorRepository fornecedorRepository = null;

	public FornecedorService iniciar(FornecedorRepository fornecedorRepository) {
		if (!foiIniciado())
			this.fornecedorRepository = fornecedorRepository;
		return this;
	}

	private boolean foiIniciado() {
		return fornecedorRepository != null;
	}

	public Model popularModel(Model model) {
		model.addAttribute("fornecedores", buscaFornecedores());
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		model.addAttribute("fornecedor", buscaFornecedor(id));
		return model;
	}

	private List<Fornecedor> buscaFornecedores() {
		return fornecedorRepository.findAll();
	}
	
	private Fornecedor buscaFornecedor(long id) {
		return fornecedorRepository.findById(id).get();
	}

	public Fornecedor novoFornecedor(NovoFornecedorDTO fornecedor) {
		return fornecedorRepository.save(fornecedor.toFornecedor());
		
	}
	
	public void editaFornecedor(EdicaoFornecedorDTO fornecedor) {
		fornecedorRepository.save(fornecedor.toFornecedor());
	}

	public void excluiFornecedor(String id) throws SQLIntegrityConstraintViolationException, DataIntegrityViolationException, EmptyResultDataAccessException {
		fornecedorRepository.deleteById(Long.parseLong(id));
	}

}
