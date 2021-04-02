package com.icaroelucas.restauranteorlajk.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.icaroelucas.restauranteorlajk.model.security.Perfil;

public class PerfisLogadoView {

	public List<String> mostrarPerfis() {

		List<Perfil> authorities = (List<Perfil>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		List<String> perfis = new ArrayList<>();

		for (Perfil perfil : authorities) {
			perfis.add(perfil.getNome());
		}
		
		System.out.println(perfis);
		return perfis;
	}

}
