package com.icaroelucas.restauranteorlajk.recepcao.service;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.cliente.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.entities.espera.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.entities.espera.repository.ListaDeEsperaRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.recepcao.dto.NovoClienteDTO;

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
	
	protected boolean foiIniciado() {
		if(mesas.foiIniciado() && espera.foiIniciado()) return true;
		return false;
	}
	
	public Model popularModel(Model model) {
		model.addAttribute("mesas", mesas.recuperar());
		model.addAttribute("esperas", espera.recuperar());
		return model;
	}
	
	public void alterarOcupacaoDaMesa(long id) {
		mesas.ocuparDesocupar(id);
	}

	public void adicionarClienteAEspera(ClienteRepository clienteRepository, NovoClienteDTO novoCliente) {
		espera.novoCliente(clienteRepository, novoCliente);
		
	}

	public void removerClienteDaEspera(String id) {
		espera.removeDaLista(id);
		
	}

	public void ocuparMesa(long mesaid, long clienteid) {
		ListaDeEspera cliente = espera.buscarClienteNaEspera(clienteid);
		mesas.ocuparDesocupar(cliente, mesaid);
	}
}
