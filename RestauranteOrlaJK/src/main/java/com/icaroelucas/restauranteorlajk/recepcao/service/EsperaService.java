package com.icaroelucas.restauranteorlajk.recepcao.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.entities.cliente.model.Cliente;
import com.icaroelucas.restauranteorlajk.entities.cliente.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.entities.espera.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.entities.espera.repository.ListaDeEsperaRepository;
import com.icaroelucas.restauranteorlajk.recepcao.dto.NovoClienteDTO;

@Service
public class EsperaService {

	private ListaDeEsperaRepository esperaRepository = null;
	private ClienteRepository clienteRepository = null;

	public void iniciar(ListaDeEsperaRepository esperaRepository, ClienteRepository clienteRepository) {
		if (!foiIniciado()) {
			this.esperaRepository = esperaRepository;
			this.clienteRepository = clienteRepository;
		}
	}

	public boolean foiIniciado() {
		return esperaRepository != null && clienteRepository != null;
	}

	public List<ListaDeEspera> recuperar() {
		return esperaRepository.findAll();
	}

	public void novoCliente(NovoClienteDTO novoCliente) {
		Cliente cliente = novoCliente.toCliente();
		clienteRepository.save(cliente);
		esperaRepository.save(adicionarCliente(cliente));
	}

	public void removeDaLista(String id) {
		try {
			
			ListaDeEspera espera = esperaRepository.findById(Long.parseLong(id)).orElseThrow();
			Cliente cliente = espera.getCliente();
			esperaRepository.delete(espera);
			clienteRepository.delete(cliente);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Implementar log: " + e);
		}
	}

	public static ListaDeEspera adicionarCliente(Cliente cliente) {
		ListaDeEspera lista = new ListaDeEspera();
		lista.setCliente(cliente);
		lista.setHoraChegada(LocalDateTime.now());
		return lista;
	}

	public ListaDeEspera buscarClienteNaEspera(long clienteid) {

		ListaDeEspera clienteEmEspera = esperaRepository.findById(clienteid).orElseThrow();
		esperaRepository.deleteById(clienteid);
		return clienteEmEspera;

	}

}
