package com.icaroelucas.restauranteorlajk.administracao.agenda.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.AgendaVoltaDTO;
import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.EdicaoRegistroDTO;
import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.NovoRegistroDTO;
import com.icaroelucas.restauranteorlajk.administracao.agenda.service.AgendaService;
import com.icaroelucas.restauranteorlajk.entities.agenda.repository.AgendaRepository;

@Controller
@RequestMapping("/administracao/agenda")
public class AgendaController {

	@Autowired
	AgendaRepository agendaRepository;
	
	AgendaService agendaService = new AgendaService();
	
	@GetMapping("")
	public String agenda(Model model) {
		model = agendaService.iniciar(agendaRepository).
		popularModel(model, LocalDate.now());
		return "administracao/agenda/agenda";
	}
	
	@GetMapping("/dia")
	public String agendaDia(Model model, @RequestParam String dia) {
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate diaDate = LocalDate.parse(dia, pattern);
		
		model = agendaService.iniciar(agendaRepository).
		popularModel(model, diaDate);
		return "administracao/agenda/agenda";
	}
	
	@GetMapping("/novo")
	public String adicionaNovoRegistro() {
		return "administracao/agenda/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadoNovoRegistro(NovoRegistroDTO registro) {
		agendaService.novoRegistro(registro);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public Object editaRegistro(Model model, @RequestParam long id) {
		try {
			model = agendaService.popularModel(model, id);
			return "administracao/agenda/edita";
		} catch (NoSuchElementException e) {
			return new RedirectView("");
		}
	}

	@PostMapping("/edita")
	public RedirectView editadoRegistro(EdicaoRegistroDTO registro) {
		agendaService.editaRegistro(registro);
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiRegistro(@RequestParam long id) {
		agendaService.deletaregistro(id);
		return new RedirectView("");
	}
	
	@PostMapping("/voltar")
	public String agendaDiaAntes(Integer local, Model model) {
		local -= 1;
		model = agendaService.iniciar(agendaRepository).
		popularModelAgenda(model, local);
		
		return "administracao/agenda/agenda";
	}
	
	@PostMapping("/avancar")
	public String agendaDiaDepois(Integer local, Model model) {
		local += 1;
		model = agendaService.iniciar(agendaRepository).
		popularModelAgenda(model, local);
		
		return "administracao/agenda/agenda";
	}

}
