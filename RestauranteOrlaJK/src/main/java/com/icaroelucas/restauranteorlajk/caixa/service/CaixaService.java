package com.icaroelucas.restauranteorlajk.caixa.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.registrodiario.service.RegistroDiarioService;
import com.icaroelucas.restauranteorlajk.caixa.dto.ImpressaoDTO;
import com.icaroelucas.restauranteorlajk.caixa.view.ContaView;
import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.cliente.model.Cliente;
import com.icaroelucas.restauranteorlajk.entities.cliente.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.entities.registrodiario.repository.RegistroDiarioRepository;

public class CaixaService {

	RegistroDiarioRepository registroDiarioRepository = null;

	MesaRepository mesaRepository = null;
	
	PedidoRepository pedidoRepository = null;
	
	ClienteRepository clienteRepository = null;
	
	public CaixaService iniciar(RegistroDiarioRepository registroDiarioRepository, MesaRepository mesaRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
		
		if(!foiIniciado()) {
		this.mesaRepository = mesaRepository;
		this.pedidoRepository = pedidoRepository;
		this.registroDiarioRepository = registroDiarioRepository;
		this.clienteRepository = clienteRepository;
		}
		return this;
	}
	
	public Model popularModel(Model model) {
		return model.addAttribute("mesas", listaMesas());
	}
	
	public Model popularModel(Model model, ImpressaoDTO conta) {
		return model.addAttribute("impressao", conta);
	}
	
	private boolean foiIniciado() {
		return registroDiarioRepository != null && mesaRepository != null && pedidoRepository != null && clienteRepository != null;
	}

	public List<Mesa> listaMesas(){
		return mesaRepository.findAllByFechada(true);
	}
	
	public void disponibilizaMesa(String id) {
		
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		new RegistroDiarioService().registraConsumo(mesa, registroDiarioRepository);
		List<Pedido> pedidos = mesa.getPedidos();
		Cliente clienteOcupante = mesa.getClienteOcupante();
		for (Pedido pedido : pedidos) {
			pedidoRepository.delete(pedido);
		}
		mesa.limpaPedidos();
		mesa.setFechada(false);
		mesa.setOcupada(false);
		mesa.setTotalDaConta(new BigDecimal("0.0"));
		mesa.setClienteOcupante(null);
		mesaRepository.save(mesa);
		if(clienteOcupante != null) clienteRepository.delete(clienteOcupante);
	}
	
	public ImpressaoDTO imprimeConta(String id) throws NoSuchElementException {
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		Duration permanencia = Duration.between(mesa.getChegada(), LocalTime.now());
		List<ContaView> conta = new ArrayList<>();
		BigDecimal total = new BigDecimal("0.00");
		for (Pedido pedido : mesa.getPedidos()) {
			List<Alimento> alimentos = pedido.getAlimentos();
			for (Alimento alimento : alimentos) {
				conta.add(new ContaView(alimento.getNome(), alimento.getValor()));
				total = total.add(alimento.getValor());
			}
		}
		return new ImpressaoDTO(mesa, conta, total,
				permanencia.toHoursPart() + ":" + permanencia.toMinutesPart() + ":" + permanencia.toSecondsPart());
	}

	
}
