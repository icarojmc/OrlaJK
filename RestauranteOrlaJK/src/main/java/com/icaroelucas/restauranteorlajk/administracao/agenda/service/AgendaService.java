package com.icaroelucas.restauranteorlajk.administracao.agenda.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.EdicaoRegistroDTO;
import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.NovoRegistroDTO;
import com.icaroelucas.restauranteorlajk.administracao.agenda.dto.RegistroView;
import com.icaroelucas.restauranteorlajk.entities.agenda.model.Agenda;
import com.icaroelucas.restauranteorlajk.entities.agenda.repository.AgendaRepository;

public class AgendaService {

	AgendaRepository agendaRepository = null;

	public AgendaService iniciar(AgendaRepository agendaRepository) {
		if (!foiIniciado())
			this.agendaRepository = agendaRepository;
		return this;
	}

	private boolean foiIniciado() {
		return agendaRepository != null;
	}

	public Model popularModel(Model model, LocalDate dia) {
		model.addAttribute("agenda", buscaRegistros(dia));
		model.addAttribute("semana", selecionarDia(0));
		model.addAttribute("local", 0);
		model.addAttribute("dia", dia);
		model.addAllAttributes(montarCalendario(0));
		return model;
	}
	
	public Model popularModel(Model model, long id) {
		return model.addAttribute("registro", buscaRegistro(id));
	}
	
	public Model popularModelAgenda(Model model, int local) {
		model.addAttribute("agenda", buscaRegistros());
		model.addAttribute("semana", selecionarDia(local));
		model.addAttribute("local", local);
		model.addAttribute("dia", LocalDate.now());
		model.addAllAttributes(montarCalendario(local));
		return model;
	}

	private List<String> selecionarDia(int i) {
		List<String> semana = new ArrayList<>();

		for (int r = 0; r < 5; i++, r++) {
			LocalDate dia = LocalDate.now().plusDays(i);
			String textoDoDia = dia.getDayOfMonth() + " - ";
			int diaDaSemana = dia.getDayOfWeek().getValue();
			switch (diaDaSemana) {
			case 1:
				textoDoDia += "SEGUNDA";
				break;
			case 2:
				textoDoDia += "TERÇA";
				break;
			case 3:
				textoDoDia += "QUARTA";
				break;
			case 4:
				textoDoDia += "QUINTA";
				break;
			case 5:
				textoDoDia += "SEXTA";
				break;
			case 6:
				textoDoDia += "SÁBADO";
				break;
			case 7:
				textoDoDia += "DOMINGO";
				break;
			}
			if(i == 0 ) textoDoDia += " (HOJE)";
			semana.add(textoDoDia);
		}
		return semana;
	}

	private Map<String, List<Agenda>> montarCalendario(int i) {

		Map<String, List<Agenda>> calendar = new HashMap<>();
		
		List<Agenda> diaUm = new ArrayList<>();
		List<Agenda> diaDois = new ArrayList<>();
		List<Agenda> diaTres = new ArrayList<>();
		List<Agenda> diaQuatro = new ArrayList<>();
		List<Agenda> diaCinco = new ArrayList<>();
		
		LocalDate inicio = null;
		LocalDate fim = null;
		
		if(i<0) {
			inicio = LocalDate.now().minusDays(-i);
			fim = LocalDate.now().minusDays(-i).plusDays(5);
		}
		else {
			inicio = LocalDate.now().plusDays(i);
			fim = LocalDate.now().plusDays(i).plusDays(5);
		}
		List<Agenda> registros = agendaRepository.findBydataEntradaBetween(inicio, fim);

		for (Agenda registro : registros) {
			LocalDate dataEntrada = registro.getDataEntrada();
			int days = Period.between(inicio, dataEntrada).normalized().getDays();
			switch (days) {
			case 0:
				diaUm.add(registro);
				break;
			case 1:
				diaDois.add(registro);
				break;
			case 2:
				diaTres.add(registro);
				break;
			case 3:
				diaQuatro.add(registro);
				break;
			case 4:
				diaCinco.add(registro);
				break;
			}
		}
		calendar.put("diaUm", diaUm);
		calendar.put("diaDois", diaDois);
		calendar.put("diaTres", diaTres);
		calendar.put("diaQuatro", diaQuatro);
		calendar.put("diaCinco", diaCinco);
		
		return calendar;

	}

	

	private RegistroView buscaRegistro(long id) {
		Agenda agenda = agendaRepository.findById(id).get();
		
		return new RegistroView(agenda);
	}

	private List<Agenda> buscaRegistros() {
		List<Agenda> agenda = agendaRepository.findAll();
		agenda.sort((s1, s2) -> s1.getDataEntrada().compareTo(s2.getDataEntrada()));
		return agenda;
	}
	
	private List<Agenda> buscaRegistros(LocalDate dia) {
		List<Agenda> agenda = agendaRepository.findByDataEntrada(dia);
		agenda.sort((s1, s2) -> s1.getDataEntrada().compareTo(s2.getDataEntrada()));
		return agenda;
	}


	public Agenda novoRegistro(NovoRegistroDTO registro) {
		return agendaRepository.save(registro.toAgenda());
	}

	public Agenda editaRegistro(EdicaoRegistroDTO registro) {
		return agendaRepository.save(registro.toAgenda());
	}

	public void deletaregistro(long id) {
		agendaRepository.deleteById(id);
	}

	

}
