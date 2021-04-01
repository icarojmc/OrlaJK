package com.icaroelucas.restauranteorlajk.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.NovoClienteDTO;
import com.icaroelucas.restauranteorlajk.model.Cliente;
import com.icaroelucas.restauranteorlajk.model.ListaDeEspera;
import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.ClienteRepository;
import com.icaroelucas.restauranteorlajk.repository.ListaDeEsperaRepository;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	@Autowired
	MesaRepository mesaRepository;

	@Autowired
	ListaDeEsperaRepository listaDeEsperaRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;

	@GetMapping("")
	public String home(Model model) {

		List<Mesa> mesas = mesaRepository.findAll();
		List<ListaDeEspera> lista = listaDeEsperaRepository.findAll();

		model.addAttribute("mesas", mesas);
		model.addAttribute("lista", lista);

		return "recepcao/home";
	}

	@GetMapping("/ocupar")
	public String ocupar(Model model, @RequestParam String id) {

		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();
		mesa.setOcupada(true);
		mesa.setFechada(false);
		mesa.setChegada(LocalTime.now());

		mesaRepository.save(mesa);

		List<Mesa> mesas = mesaRepository.findAll();
		List<ListaDeEspera> lista = listaDeEsperaRepository.findAll();

		model.addAttribute("mesas", mesas);
		model.addAttribute("lista", lista);

		return "recepcao/home";
	}

	@GetMapping("/liberar")
	public String liberar(Model model, @RequestParam String id) {

		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();

		List<Pedido> pedidos = mesa.getPedidos();

		for (Pedido pedido : pedidos) {
			pedidoRepository.delete(pedido);
		}

		mesa.limpaPedidos();
		mesa.setFechada(false);
		mesa.setOcupada(false);
		mesa.setTotalDaConta(new BigDecimal("0.0"));
		mesaRepository.save(mesa);

		List<Mesa> mesas = mesaRepository.findAll();
		List<ListaDeEspera> lista = listaDeEsperaRepository.findAll();

		model.addAttribute("mesas", mesas);
		model.addAttribute("lista", lista);

		return "recepcao/home";
	}

	@PostMapping("/adicionar")
	public String adicionaAListaDeEspera(Model model, NovoClienteDTO novoCliente) {

		Cliente cliente = novoCliente.toCliente();
		clienteRepository.save(cliente);

		ListaDeEspera listaDeEspera = new ListaDeEspera();

		listaDeEspera.setCliente(cliente);
		listaDeEspera.setHoraChegada(LocalDateTime.now());

		listaDeEsperaRepository.save(listaDeEspera);

		List<Mesa> mesas = mesaRepository.findAll();
		List<ListaDeEspera> lista = listaDeEsperaRepository.findAll();
		model.addAttribute("mesas", mesas);
		model.addAttribute("lista", lista);

		return "recepcao/home";
	}

	@GetMapping("/remover")
	public String removerDaListaDeEspera(Model model, @RequestParam String id) {

		listaDeEsperaRepository.deleteById(Long.parseLong(id));

		List<Mesa> mesas = mesaRepository.findAll();
		List<ListaDeEspera> lista = listaDeEsperaRepository.findAll();
		model.addAttribute("mesas", mesas);
		model.addAttribute("lista", lista);

		return "recepcao/home";
	}
}
