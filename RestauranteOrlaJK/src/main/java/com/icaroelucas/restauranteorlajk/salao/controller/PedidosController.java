package com.icaroelucas.restauranteorlajk.salao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.salao.dto.AdicionaObservacaoDTO;
import com.icaroelucas.restauranteorlajk.salao.service.pedido.GestaoService;



@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	MesaRepository mesaRepository;
	@Autowired
	AlimentoRepository alimentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	
	private GestaoService gestorPedidoService = new GestaoService();
	
	@GetMapping("")
	public String adicionaPedido(Model model, @RequestParam long mesaid) {
		
		model = gestorPedidoService.iniciar(mesaRepository, alimentoRepository, pedidoRepository)
				.popularModel(model, mesaid);
		
		return "mesas/novopedido";
	}
	
	@GetMapping("/filtro")
	public String adicionaPedido(Model model, @RequestParam long id, @RequestParam String tipo) {
		
		model = gestorPedidoService.iniciar(mesaRepository, alimentoRepository, pedidoRepository)
				.popularModel(model, id, tipo);
		
		return "mesas/novopedido";
	}

	@GetMapping("/adiciona")
	public RedirectView adicionaAlimento(@RequestParam long id) {
		Pedido pedido = gestorPedidoService.incluiAlimento(id);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}

	@GetMapping("/removeAlimento")
	public RedirectView removeAlimento(@RequestParam long id) {
		Pedido pedido = gestorPedidoService.removeAlimento(id);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@PostMapping("/observacao")
	public RedirectView adicionaObservacao(AdicionaObservacaoDTO observacao) {
		Pedido pedido = gestorPedidoService.adicionaObservacao(observacao);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@GetMapping("/edita")
	public RedirectView editaPedido(@RequestParam long id) {
		Pedido pedido = gestorPedidoService.iniciar(mesaRepository, alimentoRepository, pedidoRepository)
				.recuperarPedido(id);
		return new RedirectView("?mesaid=" + pedido.getIdDaMesa());
	}
	
	@GetMapping("/finaliza")
	public RedirectView finalizaNovoPedido() {
		gestorPedidoService.confirmaPedido();
		return new RedirectView("/mesas");
	}
	
	@GetMapping("/remove")
	public RedirectView removePedido(Model model, @RequestParam long id) {
		gestorPedidoService.iniciar(mesaRepository, alimentoRepository, pedidoRepository)
		.removePedido(id);
		return new RedirectView("/mesas");
	}
	
	@GetMapping("/entrega")
	public RedirectView entregaPedido(Model model, @RequestParam long id) {
		gestorPedidoService.entregaPedido(id, pedidoRepository);
		return new RedirectView("/mesas");
	}
	
	
}
