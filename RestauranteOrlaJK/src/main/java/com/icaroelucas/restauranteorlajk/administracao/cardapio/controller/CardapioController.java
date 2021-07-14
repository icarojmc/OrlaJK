package com.icaroelucas.restauranteorlajk.administracao.cardapio.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.EdicaoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.produtoAdicionadoAoCardapioDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.service.CardapioService;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;

@Controller
@RequestMapping("/administracao/cardapio")
public class CardapioController {

	@Autowired
	AlimentoRepository alimentoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	CardapioService cardapioService = new CardapioService();

	@GetMapping("")
	public String cardapio(Model model) {
		model = cardapioService.iniciar(alimentoRepository).
		popularModel(model);
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/novo")
	public String adicionaNovoAlimento() {
		return "administracao/cardapio/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadoNovoAlimento(NovoAlimentoDTO alimento) {
		cardapioService.novoALimento(alimento);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public Object editaAlimento(Model model, @RequestParam long id) {
		try {
			model = cardapioService.popularModel(model, id);
			return "administracao/cardapio/edita";
		} catch (NoSuchElementException e) {
			return new RedirectView("");
		}
	}

	@PostMapping("/edita")
	public RedirectView editadoAlimento(EdicaoAlimentoDTO alimento) {
		cardapioService.editaALimento(alimento);
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiAlimento(@RequestParam long id) {
		cardapioService.deletaAlimento(id);
		return new RedirectView("");
	}

	@GetMapping("/adicionaproduto")
	public String adicionaProduto(Model model, @RequestParam long id) {
		model.addAttribute("id", id);
		return "administracao/cardapio/adicionaproduto";
	}

	@PostMapping("/adicionaproduto")
	public RedirectView adicionadoProduto(produtoAdicionadoAoCardapioDTO produto) {
		cardapioService.adicionaProdutosAoAlimento(produtoRepository, produto);
		return new RedirectView("");
	}

	@GetMapping("/removeproduto")
	public RedirectView removeProduto(@RequestParam long alimentoid, @RequestParam long produtoid) {
		cardapioService.removeProdutosDoAlimento(produtoRepository, alimentoid, produtoid);
		return new RedirectView("");
	}
}
