package com.icaroelucas.restauranteorlajk.secutiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("")
	public String login() {
		return "security/login";
	}
	
}