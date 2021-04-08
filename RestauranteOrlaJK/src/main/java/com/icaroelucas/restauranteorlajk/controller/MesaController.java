package com.icaroelucas.restauranteorlajk.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.AdicionaObservacaoDTO;
import com.icaroelucas.restauranteorlajk.model.Alimento;
import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Pedido;
import com.icaroelucas.restauranteorlajk.model.Setor;
import com.icaroelucas.restauranteorlajk.model.Situacao;
import com.icaroelucas.restauranteorlajk.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;

@Controller
@RequestMapping("/mesas")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;

	@Autowired
	AlimentoRepository alimentoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@GetMapping("")
	public String home(Model model) {

		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);

		return "mesas/home";
	}
	
	@GetMapping("/mesasporsetor")
	public String mesasPorSetor(Model model, @RequestParam String setor) {
		
		List<Mesa> mesas = mesaRepository.findAllByOcupadaAndSetor(true, Setor.valueOf(setor));
		model.addAttribute("mesas", mesas);

		return "mesas/home";
	}

	@GetMapping("/novopedido")
	public String adicionaPedido(Model model, @RequestParam String mesaid) {

		List<Alimento> cardapio = alimentoRepository.findAll();

		Pedido pedido = new Pedido();

		pedido.setMesa(mesaRepository.findById(Long.parseLong(mesaid)).get());
		pedido.setSituacao(Situacao.EM_REALIZACAO);

		pedido = pedidoRepository.save(pedido);

		model.addAttribute("cardapio", cardapio);
		model.addAttribute("pedido", pedido);
		return "mesas/novopedido";
	}

	@GetMapping("/adiciona")
	public String continuaAdicionaPedido(Model model, @RequestParam String pedidoid, @RequestParam String id) {

		List<Alimento> cardapio = alimentoRepository.findAll();

		Pedido pedido = pedidoRepository.findById(Long.parseLong(pedidoid)).get();

		pedido.adicionaAlimento(alimentoRepository.findById(Long.parseLong(id)).get());

		pedidoRepository.save(pedido);

		model.addAttribute("cardapio", cardapio);
		model.addAttribute("pedido", pedido);
		return "mesas/novopedido";
	}

	@GetMapping("/remove")
	public String removeAlimento(Model model, @RequestParam String pedidoid, @RequestParam String id) {

		List<Alimento> cardapio = alimentoRepository.findAll();

		Pedido pedido = pedidoRepository.findById(Long.parseLong(pedidoid)).get();
		Alimento alimento = alimentoRepository.findById(Long.parseLong(id)).get();
		pedido.removeAlimento(alimento);
		pedidoRepository.save(pedido);

		model.addAttribute("cardapio", cardapio);
		model.addAttribute("pedido", pedido);
		return "mesas/novopedido";
	}
	
	@PostMapping("/adicionaobservacao")
	public String adicionaObservacao(Model model, AdicionaObservacaoDTO observacao) {
		
		List<Alimento> cardapio = alimentoRepository.findAll();
		Pedido pedido = pedidoRepository.findById(Long.parseLong(observacao.getId())).get();
		
		pedidoRepository.save(observacao.toPedido(pedido));
		
		model.addAttribute("adicionado", true);
		model.addAttribute("cardapio", cardapio);
		model.addAttribute("pedido", pedido);
		
		return "mesas/novopedido";
	}
	
	@GetMapping("/finalizanovopedido")
	public String finalizaNovoPedido(Model model, @RequestParam String id) {
		
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		pedido.setSituacao(Situacao.REALIZADO);
		
		Mesa mesa = pedido.getMesa();
		List<Alimento> alimentos = pedido.getAlimentos();
		
		BigDecimal total = BigDecimal.valueOf(0.0);
		for (Alimento alimento : alimentos) {
			total = total.add(alimento.getValor());
		}
		pedido.setTotalDoPedido(total);
		
		pedidoRepository.save(pedido);
		mesa.adicionaAoTotalDaConta(total);
		mesaRepository.save(mesa);
		
		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);
		return "mesas/home";
	}
	
	@GetMapping("/removepedido")
	public String removePedido(Model model, @RequestParam String id) {
		
		try {
			
			Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
			Mesa mesa = pedido.getMesa();
			if(pedido.getTotalDoPedido() != null) mesa.removeDoTotalDaConta(pedido.getTotalDoPedido());

			mesaRepository.save(mesa);
			pedidoRepository.delete(pedido);
			
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		
			
		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);
		return "mesas/home";
		
	}
	
	@GetMapping("/editapedido")
	public String editaPedido(Model model, @RequestParam String id) {
		
		List<Alimento> cardapio = alimentoRepository.findAll();

		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();

		model.addAttribute("cardapio", cardapio);
		model.addAttribute("pedido", pedido);
		
		 return "mesas/novopedido";
	}
	
	@GetMapping("/entregapedido")
	public String entregaPedido(Model model, @RequestParam String id) {
		
		Pedido pedido = pedidoRepository.findById(Long.parseLong(id)).get();
		
		pedido.setSituacao(Situacao.ENTREGUE);
		
		
		
		pedidoRepository.save(pedido);
		
		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);
		return "mesas/home";
	}

	@GetMapping("/fechamesa")
	public String fechaMesa(Model model, @RequestParam String id) {
		
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		mesa.setFechada(true);
		mesaRepository.save(mesa);
		
		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);
		return "mesas/home";
	}
	
	@GetMapping("/abremesa")
	public String abreMesa(Model model, @RequestParam String id) {
		
		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		mesa.setFechada(false);
		mesaRepository.save(mesa);
		
		List<Mesa> mesas = mesaRepository.findAllByOcupada(true);
		model.addAttribute("mesas", mesas);
		return "mesas/home";
	}
}
