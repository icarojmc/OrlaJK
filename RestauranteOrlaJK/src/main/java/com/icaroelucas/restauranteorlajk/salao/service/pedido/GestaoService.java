package com.icaroelucas.restauranteorlajk.salao.service.pedido;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.salao.dto.AdicionaObservacaoDTO;

public class GestaoService {

	PedidoService pedidoService = new PedidoService();
	MesaService mesaService = new MesaService();
	AlimentoService alimentoService = new AlimentoService();

	public GestaoService iniciar(MesaRepository mesaRepository, AlimentoRepository alimentoRepository,
			PedidoRepository pedidoRepository) {

		mesaService.iniciar(mesaRepository);
		alimentoService.iniciar(alimentoRepository);
		pedidoService.iniciar(pedidoRepository);

		return this;
	}
	
	private void iniciar(MesaRepository mesaRepository, AlimentoRepository alimentoRepository) {
		mesaService.iniciar(mesaRepository);
		alimentoService.iniciar(alimentoRepository);
	}

	public Model popularModel(Model model, long mesaId) {

		Pedido pedido = buscarPedido(mesaId);
		List<Alimento> cardapio = alimentoService.buscaCardapio();

		model.addAttribute("pedido", pedido);
		model.addAttribute("cardapio", cardapio);

		return model;
	}

	private Pedido buscarPedido(long mesaId) {
		Pedido pedido = new Pedido();
		try {
			pedido = pedidoService.buscarPedidoEmAndamento();
		} catch (NullPointerException e) {
			Mesa mesa = mesaService.buscarMesa(mesaId);
			pedido = pedidoService.criarPedido(mesa);
		}
		return pedido;
	}

	public Pedido incluiAlimento(long id) {
		Alimento alimento = alimentoService.buscaAlimento(id);
		return pedidoService.adicionarAlimento(alimento);
	}

	public Pedido removeAlimento(long id) {
		Alimento alimento = alimentoService.buscaAlimento(id);
		return pedidoService.removerAlimento(alimento);
	}

	public Pedido adicionaObservacao(AdicionaObservacaoDTO observacaoDTO) {
		String observacao = observacaoDTO.getObservacao();
		return pedidoService.adicionarObservacao(observacao);
	}

	public Pedido recuperarPedido(long id, MesaRepository mesaRepository, AlimentoRepository alimentoRepository,
			PedidoRepository pedidoRepository) {
		iniciar(mesaRepository, alimentoRepository, pedidoRepository);
		return pedidoService.buscarPedido(id);
	}

	public void confirmaPedido() {
		pedidoService.confirmar();
		List<Alimento> alimentosConsumidos = pedidoService.buscarAlimentos();
		BigDecimal totalConsumido = pedidoService.firmarTotalDoPedido(alimentosConsumidos);
		mesaService.adicionaAoTotalDaConta(totalConsumido);
	}

	public void removePedido(long id, MesaRepository mesaRepository, AlimentoRepository alimentoRepository,
			PedidoRepository pedidoRepository) {

		iniciar(mesaRepository, alimentoRepository);

		Pedido pedido = pedidoService.buscarPedido(id);
		mesaService.removerDoTotalDaConta(pedido);
		pedidoService.removerPedido();
	}

	public void removePedido(Pedido pedido, PedidoRepository pedidoRepository) {
		pedidoService.removerPedido();
	}

	public void entregaPedido(long id, PedidoRepository pedidoRepository) {
		pedidoService.entregarPedido(id, pedidoRepository);
	}

}
