package com.icaroelucas.restauranteorlajk.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.EditadoProdutoDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoProdutoDTO;
import com.icaroelucas.restauranteorlajk.model.Produto;
import com.icaroelucas.restauranteorlajk.repository.ProdutoRepository;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("")
	public String home(Model model) {

		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		return "estoque/home";
	}

	@GetMapping("/novo")
	public String novoAdiciona() {
		
		return "estoque/novo";
	}
	
	@PostMapping("/novo")
	public String novoAdicionado(Model model, NovoProdutoDTO produto) {

		produtoRepository.save(produto.toProduto());

		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		return "estoque/home";
	}
	
	@GetMapping("/edita")
	public String edita(Model model, @RequestParam String id) {
		
		String retorno = "estoque/home";
		try {
			
			Produto produto = produtoRepository.findById(Long.parseLong(id)).get();
			model.addAttribute("produto", produto);
			
			retorno = "estoque/edita";
			
		} catch (NoSuchElementException e) {

			System.out.println(e);
			List<Produto> produtos = produtoRepository.findAll();
			model.addAttribute("produtos", produtos);
			retorno = "estoque/home";
		}
		
		return retorno;
	}
	
	@PostMapping("/edita")
	public String editado(Model model, EditadoProdutoDTO produto) {
		
		try {
			produtoRepository.save(produto.toProduto());
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}

		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		return "estoque/home";
	}
	
	@GetMapping("/exclui")
	public String excluiAlimento(Model model, @RequestParam String id) {

		try {
			produtoRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}

		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		return "estoque/home";
	}

}
