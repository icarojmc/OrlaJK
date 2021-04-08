package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.EditadoMesaDTO;
import com.icaroelucas.restauranteorlajk.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;

@Controller
@RequestMapping("/administracao/mesas")
public class MesasController {

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping("")
	public String mesas(Model model) {

		List<Mesa> mesas = mesaRepository.findAll();
		model.addAttribute("mesas", mesas);
		return "administracao/mesas/mesas";
	}

	@GetMapping("/novo")
	public String adicionaNovaMesa() {

		return "administracao/mesas/novo";
	}

	@PostMapping("/novo")
	public String adicionadaNovaMesa(Model model, NovaMesaDTO mesa) {

		mesaRepository.save(mesa.toMesa());

		List<Mesa> mesas = mesaRepository.findAll();
		model.addAttribute("mesas", mesas);
		return "administracao/mesas/mesas";
	}

	@GetMapping("/edita")
	public String editaMesa(Model model, @RequestParam String id) {

		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		model.addAttribute("mesa", mesa);
		return "administracao/mesas/edita";
	}

	@PostMapping("/edita")
	public String editadaMesa(Model model, EditadoMesaDTO mesa) {

		mesaRepository.save(mesa.toMesa());

		List<Mesa> mesas = mesaRepository.findAll();
		model.addAttribute("mesas", mesas);
		return "administracao/mesas/mesas";
	}

	@GetMapping("/exclui")
	public String excluiMesa(Model model, @RequestParam String id) {

		try {
			mesaRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}

		List<Mesa> mesas = mesaRepository.findAll();
		model.addAttribute("mesas", mesas);
		return "administracao/mesas/mesas";
	}

}
