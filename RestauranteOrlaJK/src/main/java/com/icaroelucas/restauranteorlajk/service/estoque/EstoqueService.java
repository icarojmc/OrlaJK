package com.icaroelucas.restauranteorlajk.service.estoque;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dto.EditadoProdutoDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoProdutoDTO;
import com.icaroelucas.restauranteorlajk.dto.UsaProdutoDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Produto;
import com.icaroelucas.restauranteorlajk.repository.ProdutoRepository;

@Service
public class EstoqueService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> localizaProdutos(){
		return produtoRepository.findAll();
	}

	
	public void novoProduto(NovoProdutoDTO produto) {
		produtoRepository.save(produto.toProduto());
	}
	
	public Produto editaProduto(String id) throws NoSuchElementException{
		return produtoRepository.findById(Long.parseLong(id)).get();	
	}
	
	public void editadoProduto(EditadoProdutoDTO produto) throws EmptyResultDataAccessException {
		produtoRepository.save(produto.toProduto());
	}
	
	public void excluiProduto(String id) throws EmptyResultDataAccessException {
		produtoRepository.deleteById(Long.parseLong(id));
	}
	
	public void reduzProduto(UsaProdutoDTO produto) {
		produtoRepository.save(produto.toProduto(produtoRepository.findById(Long.parseLong(produto.getId())).get()));
	}
	
}
