package com.icaroelucas.restauranteorlajk.administracao.cardapio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.EdicaoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.produtoAdicionadoAoCardapioDTO;
import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;

public class CardapioService {

	AlimentoRepository alimentoRepository = null;

	public CardapioService iniciar(AlimentoRepository alimentoRepository) {
		if (!foiIniciado())
			this.alimentoRepository = alimentoRepository;
		return this;
	}

	private boolean foiIniciado() {
		if (alimentoRepository != null)
			return true;
		return false;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("alimentos", buscaCardapio());
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		model.addAttribute("alimento", buscaAlimento(id));
		return model;
	}

	protected List<Alimento> buscaCardapio() {
		return alimentoRepository.findAll();
	}

	public Alimento novoALimento(NovoAlimentoDTO alimento) {
		return alimentoRepository.save(alimento.toAlimento());
	}

	public void editaALimento(EdicaoAlimentoDTO alimento) {
		try {
			alimentoRepository.save(alimento.toAlimento());
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	protected Alimento buscaAlimento(long id) {
		return alimentoRepository.findById(id).get();
	}

	public void deletaAlimento(long id) {
		try {
			alimentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
	}

	public void adicionaProdutosAoAlimento(ProdutoRepository produtoRepository, produtoAdicionadoAoCardapioDTO produto) {
		Alimento alimento = alimentoRepository.findById(produto.getId()).get();
		alimento.adicionaProduto(produto.toProduto());
		produtoRepository.saveAll(alimento.getProdutos());
		alimentoRepository.save(alimento);
	}

	public void removeProdutosDoAlimento(ProdutoRepository produtoRepository, long alimentoid, long produtoid) {
		try {
			Alimento alimento = alimentoRepository.findById(alimentoid).get();
			Produto produto = produtoRepository.findById(produtoid).get();
			alimento.removeProduto(produto);
			alimentoRepository.save(alimento);
			produtoRepository.delete(produto);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	

}
