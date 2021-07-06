package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.dto.AdicionaObservacaoDTO;
import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.service.mesas.PedidosService;



@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	MesaRepository mesaRepository;
	@Autowired
	AlimentoRepository alimentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	
	private PedidosService pedidos = new PedidosService();
	
	@GetMapping("")
	public String adicionaPedido(Model model, @RequestParam long mesaid) {
		
		model = pedidos.iniciar(mesaRepository, alimentoRepository, pedidoRepository)
				.popularModel(model, mesaid);
		
		return "mesas/novopedido";
	}

	@GetMapping("/adiciona")
	public RedirectView adicionaAlimento(@RequestParam long id) {
		Pedido pedido = pedidos.incluiAlimento(id);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}

	@GetMapping("/removeAlimento")
	public RedirectView removeAlimento(@RequestParam long id) {
		Pedido pedido = pedidos.removeAlimento(id);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@PostMapping("/observacao")
	public RedirectView adicionaObservacao(AdicionaObservacaoDTO observacao) {
		Pedido pedido = pedidos.adicionaObservacao(observacao);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@GetMapping("/edita")
	public RedirectView editaPedido(@RequestParam long id) {
		Pedido pedido = pedidos.recuperarPedido(id, mesaRepository, alimentoRepository, pedidoRepository);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@GetMapping("/finaliza")
	public RedirectView finalizaNovoPedido() {
		pedidos.confirmaPedido();
		return new RedirectView("/mesas");
	}
	
	@GetMapping("/remove")
	public RedirectView removePedido(Model model, @RequestParam long id) {
		pedidos.removePedido(id, mesaRepository, alimentoRepository, pedidoRepository);
		return new RedirectView("/mesas");
	}
	
	@GetMapping("/entrega")
	public RedirectView entregaPedido(Model model, @RequestParam long id) {
		pedidos.entregaPedido(id, pedidoRepository);
		return new RedirectView("/mesas");
	}
	
	
}
