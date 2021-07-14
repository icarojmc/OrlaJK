package com.icaroelucas.restauranteorlajk.administracao.mesas.service;

import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.mesas.service.MesasAdminService;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;

public class GerenciadorDeMesas {

	public static Mesa criarMesa(MesasAdminService service, int cadeiras, int nDaMesa, String setor) {
		NovaMesaDTO mesaDTO = new NovaMesaDTO();
		mesaDTO.setnDeCadeiras(cadeiras);
		mesaDTO.setNumeroDaMesa(nDaMesa);
		mesaDTO.setSetor(setor);
		Mesa mesa = service.novaMesa(mesaDTO);
		return mesa;
	}
}
