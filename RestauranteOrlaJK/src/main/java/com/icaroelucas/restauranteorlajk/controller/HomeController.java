package com.icaroelucas.restauranteorlajk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icaroelucas.restauranteorlajk.view.PerfisLogadoView;

@Controller
@RequestMapping("/home")
public class HomeController {

	
	@GetMapping("")
	public String home(Model model) {
		
//		List<String> perfis = new PerfisLogadoView().mostrarPerfis();
//		
//		model.addAttribute("perfis", perfis);
		return "home";
	}
}
