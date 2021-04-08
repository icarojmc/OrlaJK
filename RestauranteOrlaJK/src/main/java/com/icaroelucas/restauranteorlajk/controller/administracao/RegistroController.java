package com.icaroelucas.restauranteorlajk.controller.administracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.model.RegistroDiario;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;

@Controller
@RequestMapping("/administracao/registro")
public class RegistroController {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	@GetMapping("")
	public String registro(Model model) {
	
		List<RegistroDiario> registro = registroDiarioRepository.findAll();
		model.addAttribute("registros", registro);
		return "administracao/registro/registro";
	}
	
	@GetMapping("/deleta")
	public String deletaRegistro(Model model, @RequestParam String id) {
		
		try {
			registroDiarioRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
				
		List<RegistroDiario> registro = registroDiarioRepository.findAll();
		model.addAttribute("registros", registro);
		return "administracao/registro/registro";
	}
	
}
