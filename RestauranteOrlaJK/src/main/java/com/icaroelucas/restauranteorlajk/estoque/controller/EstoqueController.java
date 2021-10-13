package com.icaroelucas.restauranteorlajk.estoque.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;
import com.icaroelucas.restauranteorlajk.estoque.dto.EdicaoProdutoDTO;
import com.icaroelucas.restauranteorlajk.estoque.dto.NovoProdutoDTO;
import com.icaroelucas.restauranteorlajk.estoque.service.EstoqueService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	EstoqueService estoqueService = new EstoqueService();

	@GetMapping("")
	public String home(Model model) {
		model = estoqueService.iniciar(produtoRepository).popularModel(model);
		return "estoque/home";
	}

	@GetMapping("/novo")
	public String novoAdiciona() {
		return "estoque/novo";
	}

	@PostMapping("/novo")
	public RedirectView novoAdicionado(Model model, NovoProdutoDTO produto) {
		estoqueService.novoProduto(produto);
		return new RedirectView("");
	}
	
	@GetMapping("/edita")
	public Object edita(Model model, @RequestParam String id) {
		try {
			Produto produto = estoqueService.editaProduto(model, id);
			estoqueService.popularModel(model, produto);
			return "estoque/edita";
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return new RedirectView("");
		}
	}
	
	@PostMapping("/edita")
	public RedirectView editado(Model model, EdicaoProdutoDTO produto) {
		try {
			estoqueService.editadoProduto(produto);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}
	
	@GetMapping("/exclui")
	public RedirectView excluiAlimento(Model model, @RequestParam String id) {
		try {
			estoqueService.excluiProduto(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

}
