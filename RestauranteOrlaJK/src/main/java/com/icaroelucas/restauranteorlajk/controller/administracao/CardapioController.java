package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.EditadoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.dto.produtoAdicionadoAoCardapioDTO;
import com.icaroelucas.restauranteorlajk.service.administracao.CardapioService;

@Controller
@RequestMapping("/administracao/cardapio")
public class CardapioController {

	@Autowired
	CardapioService cardapio;

	@GetMapping("")
	public String cardapio(Model model) {
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/novo")
	public String adicionaNovoAlimento() {
		return "administracao/cardapio/novo";
	}

	@PostMapping("/novo")
	public String adicionadoNovoAlimento(Model model, NovoAlimentoDTO alimento) {
		cardapio.novoALimento(alimento);
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/edita")
	public String editaAlimento(Model model, @RequestParam String id) {
		try {
			model.addAttribute("alimento", cardapio.buscaAlimento(id));
			return "administracao/cardapio/edita";
		} catch (NoSuchElementException e) {
			System.out.println(e);
			model.addAttribute("alimentos", cardapio.buscaCardapio());
			return "administracao/cardapio/cardapio";
		}
	}

	@PostMapping("/edita")
	public String editadoAlimento(Model model, EditadoAlimentoDTO alimento) {
		cardapio.editaALimento(alimento);
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/exclui")
	public String excluiAlimento(Model model, @RequestParam String id) {
		cardapio.deletaAlimento(id);
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/adicionaproduto")
	public String adicionaProduto(Model model, @RequestParam String id) {
		model.addAttribute("id", id);
		return "administracao/cardapio/adicionaproduto";
	}

	@PostMapping("/adicionaproduto")
	public String adicionadoProduto(Model model, produtoAdicionadoAoCardapioDTO produto) {
		cardapio.adicionaProdutosAoAlimento(produto);
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/removeproduto")
	public String removeProduto(Model model, @RequestParam String alimentoid, @RequestParam String produtoid) {
		cardapio.removeProdutosDoAlimento(alimentoid, produtoid);
		model.addAttribute("alimentos", cardapio.buscaCardapio());
		return "administracao/cardapio/cardapio";
	}
}
