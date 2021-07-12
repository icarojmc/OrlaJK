package com.icaroelucas.restauranteorlajk.salao.service.salao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Setor;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

@Service
public class SalaoService {

	BuscadorDeDadosDaMesa buscadorDeDadosDaMesa = null;

	public SalaoService iniciar(MesaRepository mesaRepository) {
		if (!foiIniciado()) {
			buscadorDeDadosDaMesa = new BuscadorDeDadosDaMesa(mesaRepository);
		}
		return this;
	}

	private boolean foiIniciado() {
		if (buscadorDeDadosDaMesa != null)
			return true;
		return false;
	}

	public Model popularModel(Model model) {
		List<Mesa> listaDeMesas = buscadorDeDadosDaMesa.buscarMesas();
		model.addAttribute("mesas", listaDeMesas);
		return model;
	}

	public Model popularModel(Model model, String setor) {
		List<Mesa> listaDeMesas = buscadorDeDadosDaMesa.bucarMesas(Setor.valueOf(setor));
		return model.addAttribute("mesas", listaDeMesas);
	}
	
	public void fechaAbreMesa(long id) {
		Mesa mesa = buscadorDeDadosDaMesa.buscarMesa(id);
		if(mesa.isFechada()) mesa.setFechada(false);
		else mesa.setFechada(true);
		buscadorDeDadosDaMesa.salva(mesa);
	}

}
