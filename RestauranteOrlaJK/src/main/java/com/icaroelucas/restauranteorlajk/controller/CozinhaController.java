package com.icaroelucas.restauranteorlajk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.UsaProdutoDTO;
import com.icaroelucas.restauranteorlajk.model.Pedido;
import com.icaroelucas.restauranteorlajk.model.Produto;
import com.icaroelucas.restauranteorlajk.model.Situacao;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.repository.ProdutoRepository;

@Controller
@RequestMapping("/cozinha")
public class CozinhaController {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("")
	public String cozinha(Model model) {
		
		List<Pedido> pedidos = pedidoRepository.findAllBySituacao(Situacao.REALIZADO);
		pedidos.addAll(pedidoRepository.findAllBySituacao(Situacao.EM_PREPARACAO));
		model.addAttribute("pedidos", pedidos);
		return "cozinha/home";
	}
	
	@GetMapping("/preparapedido")
	public String preparaPedido(Model model, @RequestParam String id) {
		
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		pedido.setSituacao(Situacao.EM_PREPARACAO);
		pedidoRepository.save(pedido);
		
		List<Pedido> pedidos = pedidoRepository.findAllBySituacao(Situacao.REALIZADO);
		pedidos.addAll(pedidoRepository.findAllBySituacao(Situacao.EM_PREPARACAO));
		model.addAttribute("pedidos", pedidos);
		return "cozinha/home";
	}
	
	@GetMapping("/finalizapedido")
	public String finalizaPedido(Model model, @RequestParam String id) {
		
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		pedido.setSituacao(Situacao.PRONTO);
		pedidoRepository.save(pedido);
		
		List<Pedido> pedidos = pedidoRepository.findAllBySituacao(Situacao.REALIZADO);
		pedidos.addAll(pedidoRepository.findAllBySituacao(Situacao.EM_PREPARACAO));
		model.addAttribute("pedidos", pedidos);
		return "cozinha/home";
	}
	
	@GetMapping("/escolheproduto")
	public String escolheProduto(Model model) {
		
		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		
		return "cozinha/escolheproduto";
	}
	
	@PostMapping("/usaproduto")
	public String usaProduto(Model model, UsaProdutoDTO produto) {
		
		produtoRepository.save(produto.toProduto(
				produtoRepository.findById(
						Long.parseLong(
								produto.getId())).get()
				));
		
		
		List<Produto> produtos = produtoRepository.findAll();
		model.addAttribute("produtos", produtos);
		
		return "cozinha/escolheproduto";
	}
}
