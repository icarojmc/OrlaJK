package com.icaroelucas.restauranteorlajk.administracao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.icaroelucas.restauranteorlajk.administracao.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class MesasAdminServiceTest extends MesasAdminService {
	
	@Autowired
	MesaRepository mesaRepository;

	MesasAdminService service = new MesasAdminService();
	
	@BeforeEach
	public void iniciar() {
		service.iniciar(mesaRepository);
	}
	
	@AfterEach
	public void finalizar() {
		mesaRepository.deleteAll();;
	}
	
	@Test
	void testIncluirMesa() {
		NovaMesaDTO mesaDTO = new CriadorDeMesa()
				.incluirCadeiras(4)
				.incluirNDaMesa(1)
				.incluirSetor("A")
				.executar();
		
		Mesa mesa = service.novaMesa(mesaDTO);
		
		assertEquals(mesaDTO.getnDeCadeiras(), mesa.getnDeCadeiras());
		assertEquals(mesaDTO.getNumeroDaMesa(), mesa.getNumeroDaMesa());
		assertEquals(mesaDTO.getSetor(), mesa.getSetor().name());
	}
	
	@Test
	void testBuscarTotalDeMesasIncluidas() {
		
		for(int i = 0; i<3; i++) {
			
			NovaMesaDTO mesaDTO = new CriadorDeMesa()
					.incluirCadeiras(4)
					.incluirNDaMesa(i)
					.incluirSetor("A")
					.executar();
			service.novaMesa(mesaDTO);
		}
		List<Mesa> mesas = service.buscaMesas();
		assertEquals(3, mesas.size());
	}

	
}
