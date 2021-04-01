package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.EditadoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.model.Alimento;
import com.icaroelucas.restauranteorlajk.repository.AlimentoRepository;

@Controller
@RequestMapping("/administracao/cardapio")
public class CardapioController {

	@Autowired
	AlimentoRepository alimentoRepository;
	
	@GetMapping("")
	public String cardapio(Model model) {

		List<Alimento> alimentos = alimentoRepository.findAll();
		model.addAttribute("alimentos", alimentos);

		return "administracao/cardapio/cardapio";
	}

	@GetMapping("/novo")
	public String adicionaNovoAlimento() {

		return "administracao/cardapio/novo";
	}

	@PostMapping("/novo")
	public String adicionadoNovoAlimento(Model model, NovoAlimentoDTO alimento) {

		alimentoRepository.save(alimento.toAlimento());

		List<Alimento> alimentos = alimentoRepository.findAll();
		model.addAttribute("alimentos", alimentos);

		return "administracao/cardapio/cardapio";
	}
	
	@GetMapping("/edita")
	public String editaAlimento(Model model, @RequestParam String id) {
		
		
		Alimento alimento = alimentoRepository.findById(Long.parseLong(id)).get();
		model.addAttribute("alimento", alimento);
		
		return "administracao/cardapio/edita";
	}
	
	@PostMapping("/edita")
	public String editadoAlimento(Model model, EditadoAlimentoDTO alimento) {
		
		alimentoRepository.save(alimento.toAlimento());
		
		List<Alimento> alimentos = alimentoRepository.findAll();
		model.addAttribute("alimentos", alimentos);
		
		return "administracao/cardapio/cardapio";
	}
	
	@GetMapping("/exclui")
	public String excluiAlimento(Model model, @RequestParam String id) {

		alimentoRepository.deleteById(Long.parseLong(id));
		
		List<Alimento> alimentos = alimentoRepository.findAll();
		model.addAttribute("alimentos", alimentos);

		return "administracao/cardapio/cardapio";
	}
	
}
