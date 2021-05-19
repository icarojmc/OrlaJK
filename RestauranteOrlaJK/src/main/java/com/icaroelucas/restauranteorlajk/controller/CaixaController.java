package com.icaroelucas.restauranteorlajk.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.service.caixa.CaixaService;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	@Autowired
	CaixaService caixa;

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping("")
	public String caixa(Model model) {
		model.addAttribute("mesas", caixa.listaMesas());
		return "caixa/home";
	}

	@GetMapping("/disponibilizamesa")
	public String disponibilizaMesa(Model model, @RequestParam String id) {
		try {
			caixa.disponibilizaMesa(id);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		model.addAttribute("mesas", caixa.listaMesas());
		return "caixa/home";
	}

	@GetMapping("/imprimeconta")
	public String imprimeConta(Model model, @RequestParam String id) {

		try {			
			model.addAttribute("impressao", caixa.imprimeConta(id));
			return "caixa/conta";
		} catch (NoSuchElementException e) {
			System.out.println(e);

			model.addAttribute("mesas", caixa.listaMesas());
			return "caixa/home";
		}
	}

}
