package com.icaroelucas.restauranteorlajk.controller;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icaroelucas.restauranteorlajk.dto.RegistroDiarioDTO;
import com.icaroelucas.restauranteorlajk.model.Alimento;
import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Pedido;
import com.icaroelucas.restauranteorlajk.repository.MesaRepository;
import com.icaroelucas.restauranteorlajk.repository.PedidoRepository;
import com.icaroelucas.restauranteorlajk.repository.RegistroDiarioRepository;
import com.icaroelucas.restauranteorlajk.view.ContaView;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	@Autowired
	MesaRepository mesaRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	RegistroDiarioRepository registroDiarioRepository;

	@GetMapping("")
	public String caixa(Model model) {

		List<Mesa> mesas = mesaRepository.findAllByFechada(true);

		model.addAttribute("mesas", mesas);
		return "caixa/home";
	}

	@GetMapping("/disponibilizamesa")
	public String disponibilizaMesa(Model model, @RequestParam String id) {

		Mesa mesa = mesaRepository.findById(Long.parseLong(id)).get();

		RegistroDiarioDTO registro = new RegistroDiarioDTO();
		registroDiarioRepository.save(registro.toRegistro(mesa));

		List<Pedido> pedidos = mesa.getPedidos();

		for (Pedido pedido : pedidos) {
			pedidoRepository.delete(pedido);
		}

		mesa.limpaPedidos();
		mesa.setFechada(false);
		mesa.setOcupada(false);
		mesa.setTotalDaConta(new BigDecimal("0.0"));
		mesaRepository.save(mesa);

		List<Mesa> mesas = mesaRepository.findAllByFechada(true);
		model.addAttribute("mesas", mesas);
		return "caixa/home";
	}

	@GetMapping("/imprimeconta")
	public String imprimeConta(Model model, @RequestParam String id) {

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

		model.addAttribute("mesa", mesa);
		model.addAttribute("conta", conta);
		model.addAttribute("total", total);
		model.addAttribute("permanencia",
				permanencia.toHoursPart() + ":" + permanencia.toMinutesPart() + ":" + permanencia.toSecondsPart());

		return "caixa/conta";
	}

}
