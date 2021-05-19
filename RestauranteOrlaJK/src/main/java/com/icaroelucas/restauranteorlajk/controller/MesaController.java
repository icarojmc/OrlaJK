package com.icaroelucas.restauranteorlajk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.AdicionaObservacaoDTO;
import com.icaroelucas.restauranteorlajk.service.mesas.MesasService;
import com.icaroelucas.restauranteorlajk.service.mesas.PedidoService;

@Controller
@RequestMapping("/mesas")
public class MesaController {

	@Autowired
	MesasService mesas;
	
	@Autowired
	PedidoService pedidos;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}
	
	@GetMapping("/mesasporsetor")
	public String mesasPorSetor(Model model, @RequestParam String setor) {
		model.addAttribute("mesas", mesas.listaMesasPorSetor(setor));
		return "mesas/home";
	}

	@GetMapping("/novopedido")
	public String adicionaPedido(Model model, @RequestParam String mesaid) {
		model.addAttribute("pedidoecardapio", pedidos.iniciaPedido(mesaid));
		return "mesas/novopedido";
	}

	@GetMapping("/adiciona")
	public String continuaAdicionaPedido(Model model, @RequestParam String pedidoid, @RequestParam String id) {
		model.addAttribute("pedidoecardapio", pedidos.adicionaAoPedido(pedidoid, id));
		return "mesas/novopedido";
	}

	@GetMapping("/remove")
	public String removeAlimento(Model model, @RequestParam String pedidoid, @RequestParam String id) {
		model.addAttribute("pedidoecardapio", pedidos.removeDoPedido(pedidoid, id));
		return "mesas/novopedido";
	}
	
	@PostMapping("/adicionaobservacao")
	public String adicionaObservacao(Model model, AdicionaObservacaoDTO observacao) {
		model.addAttribute("pedidoecardapio", pedidos.adicionaObservacaoAoPedido(observacao));
		model.addAttribute("adicionado", true);
		return "mesas/novopedido";
	}
	
	@GetMapping("/finalizanovopedido")
	public String finalizaNovoPedido(Model model, @RequestParam String id) {
		pedidos.confirmaPedido(id);
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}
	
	@GetMapping("/removepedido")
	public String removePedido(Model model, @RequestParam String id) {
		pedidos.removePedido(id);
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}
	
	@GetMapping("/editapedido")
	public String editaPedido(Model model, @RequestParam String id) {
		model.addAttribute("pedidoecardapio", pedidos.editaPedido(id));
		return "mesas/novopedido";
	}
	
	@GetMapping("/entregapedido")
	public String entregaPedido(Model model, @RequestParam String id) {
		pedidos.entregaPedido(id);
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}
	
	@GetMapping("/fechamesa")
	public String fechaMesa(Model model, @RequestParam String id) {
		mesas.fechaMesa(id);
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}	
	
	@GetMapping("/abremesa")
	public String abreMesa(Model model, @RequestParam String id) {
		mesas.abreMesa(id);
		model.addAttribute("mesas", mesas.listaMesas());
		return "mesas/home";
	}
}
