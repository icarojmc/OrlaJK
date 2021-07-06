package com.icaroelucas.restauranteorlajk.service.recepcao;

import java.time.LocalDateTime;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.dto.NovoClienteDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Cliente;
import com.icaroelucas.restauranteorlajk.entities.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.repository.ListaDeEsperaRepository;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;

public class RecepcaoService {
	
	MesaService mesas = new MesaService();
	EsperaService espera = new EsperaService();
	
	public RecepcaoService iniciar(MesaRepository mesaRepository, ListaDeEsperaRepository esperaRepository) {
		if(!foiIniciado()) {
			mesas.iniciar(mesaRepository);
			espera.iniciar(esperaRepository);
		}
		return this;
	}
	
	private boolean foiIniciado() {
		if(mesas.foiIniciado() && espera.foiIniciado()) return true;
		return false;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("mesas", mesas.recuperar());
		model.addAttribute("esperas", espera.recuperar());
		return model;
	}
	
	
	public static ListaDeEspera adicionarCliente(Cliente cliente) {
		ListaDeEspera lista = new ListaDeEspera();
		lista.setCliente(cliente);
		lista.setHoraChegada(LocalDateTime.now());
		return lista;
	}
	

	public void alterarOcupacao(long id) {
		mesas.ocuparDesocupar(id);
	}

	public void adicionarCliente(ClienteRepository clienteRepository, NovoClienteDTO novoCliente) {
		espera.novoCliente(clienteRepository, novoCliente);
		
	}

	public void removerCliente(String id) {
		espera.removeDaLista(id);
		
	}

	public void ocuparMesa(long mesaid, long clienteid) {
		ListaDeEspera cliente = espera.buscarClienteNaEspera(clienteid);
		mesas.ocuparDesocupar(cliente, mesaid);
	}
}
