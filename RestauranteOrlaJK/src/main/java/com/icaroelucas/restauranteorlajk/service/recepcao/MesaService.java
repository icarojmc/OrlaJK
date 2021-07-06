package com.icaroelucas.restauranteorlajk.service.recepcao;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.entities.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;

@Service
public class MesaService {

	MesaRepository mesaRepository = null;

	public void iniciar(MesaRepository mesaRepository) {
		if(!foiIniciado()) this.mesaRepository = mesaRepository;
	}

	public boolean foiIniciado() {

		if (mesaRepository != null)
			return true;
		return false;
	}

	public List<Mesa> recuperar() {
		List<Mesa> mesas = mesaRepository.findAll();
		return mesas;
	}

	public void ocuparDesocupar(long id) {
		Mesa mesa = mesaRepository.findById(id).orElseThrow();
		if (mesa.isOcupada())
			liberar(mesa);
		else
			ocupar(mesa);
		mesaRepository.save(mesa);
	}

	public void ocuparDesocupar(ListaDeEspera cliente, long mesaid) {

		Mesa mesa = mesaRepository.findById(mesaid).orElseThrow();
		if (mesa.isOcupada())
			liberar(mesa);
		else {
			ocupar(mesa);
			adicionarClienteAMesa(mesa, cliente);
		}
		mesaRepository.save(mesa);

	}

	private Mesa ocupar(Mesa mesa) {
		mesa.setOcupada(true);
		mesa.setFechada(false);
		mesa.setChegada(LocalTime.now());
		return mesa;
	}

	private Mesa liberar(Mesa mesa) {
		mesa.limpaPedidos();
		mesa.setFechada(false);
		mesa.setOcupada(false);
		mesa.setTotalDaConta(new BigDecimal("0.0"));
		mesa.setClienteOcupante(null);
		return mesa;
	}

	private Mesa adicionarClienteAMesa(Mesa mesa, ListaDeEspera clienteEmEspera) {
		mesa.setOcupada(true);
		mesa.setFechada(false);
		mesa.setChegada(LocalTime.now());
		mesa.setClienteOcupante(clienteEmEspera.getCliente());
		return mesa;
	}

	

}
