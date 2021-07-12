package com.icaroelucas.restauranteorlajk.cozinha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.cozinha.dto.UsaProdutoDTO;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;
import com.icaroelucas.restauranteorlajk.estoque.service.EstoqueService;

@Controller
@RequestMapping("/cozinha/produto")
public class ProdutoCozinhaController {
	
	@Autowired
	ProdutoRepository produtoRepository;

	EstoqueService estoqueService = new EstoqueService();
	
	@GetMapping("")
	public String escolheProduto(Model model) {
		
		model = estoqueService.iniciar(produtoRepository)
				.popularModel(model);
		return "cozinha/escolheproduto";
	}

	@PostMapping("/usar")
	public RedirectView usaProduto(Model model, UsaProdutoDTO produto) {
		estoqueService.reduzProduto(produto);
		return new RedirectView("");
	}
}
