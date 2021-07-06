package com.icaroelucas.restauranteorlajk.service.caixa;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icaroelucas.restauranteorlajk.dao.ImpressaoDAO;
import com.icaroelucas.restauranteorlajk.entities.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;
import com.icaroelucas.restauranteorlajk.service.administracao.RegistroDiarioService;
import com.icaroelucas.restauranteorlajk.service.mesas.PedidosService;
import com.icaroelucas.restauranteorlajk.view.ContaView;

@Service
public class CaixaService {

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;
	
	@Autowired
	MesaRepository mesaRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Mesa> listaMesas(){
		return mesaRepository.findAllByFechada(true);
	}
	
	public void disponibilizaMesa(String id) {
		
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		new RegistroDiarioService().registraConsumo(mesa, registroDiarioRepository);
		List<Pedido> pedidos = mesa.getPedidos();
		for (Pedido pedido : pedidos) {
			new PedidosService().removePedido(pedido, pedidoRepository);
		}
		mesa.limpaPedidos();
		mesa.setFechada(false);
		mesa.setOcupada(false);
		mesa.setTotalDaConta(new BigDecimal("0.0"));
		mesa.setClienteOcupante(null);
		mesaRepository.save(mesa);
	}
	
	public ImpressaoDAO imprimeConta(String id) throws NoSuchElementException {
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
		return new ImpressaoDAO(mesa, conta, total,
				permanencia.toHoursPart() + ":" + permanencia.toMinutesPart() + ":" + permanencia.toSecondsPart());
	}
}
