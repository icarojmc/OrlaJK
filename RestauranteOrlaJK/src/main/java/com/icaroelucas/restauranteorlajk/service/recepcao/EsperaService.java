package com.icaroelucas.restauranteorlajk.service.recepcao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dto.NovoClienteDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Cliente;
import com.icaroelucas.restauranteorlajk.entities.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.repository.ListaDeEsperaRepository;

@Service
public class EsperaService {

	private ListaDeEsperaRepository esperaRepository = null;

	public void iniciar(ListaDeEsperaRepository esperaRepository) {
		this.esperaRepository = esperaRepository;
	}

	public boolean foiIniciado() {
		if (esperaRepository != null)
			return true;
		return false;
	}

	public List<ListaDeEspera> recuperar() {
		return esperaRepository.findAll();
	}

	public void novoCliente(ClienteRepository clienteRepository, NovoClienteDTO novoCliente) {
		Cliente cliente = novoCliente.toCliente();
		clienteRepository.save(cliente);
		esperaRepository.save(RecepcaoService.adicionarCliente(cliente));
	}

	public void removeDaLista(String id) {
		try {
			esperaRepository.deleteById(Long.parseLong(id));
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Implementar log: " + e);
		}
	}

	public ListaDeEspera buscarClienteNaEspera(long clienteid) {

		ListaDeEspera clienteEmEspera = esperaRepository.findById(clienteid).orElseThrow();
		esperaRepository.deleteById(clienteid);
		return clienteEmEspera;

	}

}
