package com.icaroelucas.restauranteorlajk.service.administracao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dto.EditadoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.dto.produtoAdicionadoAoCardapioDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.model.Produto;
import com.icaroelucas.restauranteorlajk.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.repository.ProdutoRepository;

@Service
public class CardapioService {

	@Autowired
	AlimentoRepository alimentoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Alimento> buscaCardapio(){
		return alimentoRepository.findAll();
	}
	
	public void novoALimento(NovoAlimentoDTO alimento) {
		alimentoRepository.save(alimento.toAlimento());
	}
	
	public void editaALimento(EditadoAlimentoDTO alimento) {
		try {
		alimentoRepository.save(alimento.toAlimento());
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}
	
	public Alimento buscaAlimento(String id) {
		return alimentoRepository.findById(Long.parseLong(id)).get();
	}

	public void deletaAlimento(String id) {
		try {
		alimentoRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}
	
	public void adicionaProdutosAoAlimento(produtoAdicionadoAoCardapioDTO produto) {
		Alimento alimento = alimentoRepository.findById(Long.parseLong(produto.getId())).get();
		alimento.adicionaProduto(produto.toProduto());
		produtoRepository.saveAll(alimento.getProdutos());
		alimentoRepository.save(alimento);
	}
	
	public void removeProdutosDoAlimento(String alimentoid, String produtoid) {
		try {
		Alimento alimento = alimentoRepository.findById(Long.parseLong(alimentoid)).get();
		Produto produto = produtoRepository.findById(Long.parseLong(produtoid)).get();
		alimento.removeProduto(produto);
		alimentoRepository.save(alimento);
		produtoRepository.delete(produto);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}
	
	
}
