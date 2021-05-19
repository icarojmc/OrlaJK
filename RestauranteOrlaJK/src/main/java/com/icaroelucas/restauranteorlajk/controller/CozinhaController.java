package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.UsaProdutoDTO;
import com.icaroelucas.restauranteorlajk.service.cozinha.CozinhaService;
import com.icaroelucas.restauranteorlajk.service.estoque.EstoqueService;

@Controller
@RequestMapping("/cozinha")
public class CozinhaController {

	@Autowired
	CozinhaService cozinha;
	
	@Autowired
	EstoqueService estoque;

	@GetMapping("")
	public String cozinha(Model model) {

		model.addAttribute("pedidos", cozinha.buscaPedidos());
		return "cozinha/home";
	}

	@GetMapping("/preparapedido")
	public String preparaPedido(Model model, @RequestParam String id) {
		cozinha.preparaPedido(id);
		model.addAttribute("pedidos", cozinha.buscaPedidos());
		return "cozinha/home";
	}

	@GetMapping("/finalizapedido")
	public String finalizaPedido(Model model, @RequestParam String id) {
		cozinha.finalizaPedido(id);
		model.addAttribute("pedidos", cozinha.buscaPedidos());
		return "cozinha/home";
	}

	@GetMapping("/escolheproduto")
	public String escolheProduto(Model model) {
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "cozinha/escolheproduto";
	}

	@PostMapping("/usaproduto")
	public String usaProduto(Model model, UsaProdutoDTO produto) {
		estoque.reduzProduto(produto);
		model.addAttribute("produtos", estoque.localizaProdutos());
		return "cozinha/escolheproduto";
	}
}
