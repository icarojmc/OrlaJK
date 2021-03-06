package com.icaroelucas.restauranteorlajk.estoque.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.cozinha.dto.UsaProdutoDTO;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.repository.FornecedorRepository;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;
import com.icaroelucas.restauranteorlajk.estoque.dto.EdicaoProdutoDTO;
import com.icaroelucas.restauranteorlajk.estoque.dto.NovoProdutoDTO;

public class EstoqueService {

	ProdutoRepository produtoRepository = null;
	
	public EstoqueService iniciar(ProdutoRepository produtoRepository) {
		if(!foiIniciado()) this.produtoRepository = produtoRepository;
		return this;
	}
	
	private boolean foiIniciado() {
		if(produtoRepository != null) return true;
		return false;
	}
	
	public Model popularModel(Model model) {
		List<Produto> produtos = localizaProdutos();
		model.addAttribute("produtos", produtos);
		return model;
	}
	
	public Model popularModel(Model model, Object object, FornecedorRepository fornecedorRepository) {
		model.addAttribute("fornecedores", localizarFornecedores(fornecedorRepository));
		return model.addAttribute("produto", object);
	}
	
	public Model popularModel(Model model, FornecedorRepository fornecedorRepository) {
		return model.addAttribute("fornecedores", localizarFornecedores(fornecedorRepository));
	}

	private List<Fornecedor> localizarFornecedores(FornecedorRepository fornecedorRepository) {
		return fornecedorRepository.findAll();
	}

	public List<Produto> localizaProdutos(){
		return produtoRepository.findAll();
	}
	
	public EstoqueService novoProduto(NovoProdutoDTO produto) {
		produtoRepository.save(produto.toProduto());
		return this;
	}
	
	public Produto editaProduto(Model model, String id) throws NoSuchElementException{
		return produtoRepository.findById(Long.parseLong(id)).get();
	}
	
	public void editadoProduto(EdicaoProdutoDTO produto) throws EmptyResultDataAccessException {
		produtoRepository.save(produto.toProduto());
	}
	
	public void excluiProduto(String id) throws EmptyResultDataAccessException {
		produtoRepository.deleteById(Long.parseLong(id));
	}
	
	public void reduzProduto(UsaProdutoDTO produtoDTO) {
		long id = produtoDTO.getId();
		Produto produto = produtoRepository.findById(id).get();
		produto = produtoDTO.subtrairDoProduto(produto);
		produtoRepository.save(produto);
	}

	

}
