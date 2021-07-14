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

import com.icaroelucas.restauranteorlajk.estoque.dto.EdicaoProdutoDTO;
import com.icaroelucas.restauranteorlajk.estoque.dto.NovoProdutoDTO;
import com.icaroelucas.restauranteorlajk.estoque.service.EstoqueService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {


	@Autowired
	EstoqueService estoque;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "estoque/home";
	}

	@GetMapping("/novo")
	public String novoAdiciona() {
		return "estoque/novo";
	}
	
	@PostMapping("/novo")
	public String novoAdicionado(Model model, NovoProdutoDTO produto) {
		estoque.novoProduto(produto);
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "estoque/home";
	}
	
	@GetMapping("/edita")
	public String edita(Model model, @RequestParam String id) {
		try {
			model.addAttribute("produto", estoque.editaProduto(id));
			return "estoque/edita";
		} catch (NoSuchElementException e) {
			System.out.println(e);
			model.addAttribute("produtos", estoque.localizaProdutos());
			return "estoque/home";
		}
	}
	
	@PostMapping("/edita")
	public String editado(Model model, EdicaoProdutoDTO produto) {
		try {
			estoque.editadoProduto(produto);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "estoque/home";
	}
	
	@GetMapping("/exclui")
	public String excluiAlimento(Model model, @RequestParam String id) {
		try {
			estoque.excluiProduto(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "estoque/home";
	}

}
