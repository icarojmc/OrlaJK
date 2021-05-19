package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
	
	@GetMapping("")
	public String home(Model model, Authentication authentication) {
		model.addAttribute("usuario", authentication.getPrincipal());
		return "home";
	}
	
	@GetMapping("/home")
	public String home2(Model model, Authentication authentication) {
		model.addAttribute("usuario", authentication.getPrincipal());
		return "home";
	}
}
