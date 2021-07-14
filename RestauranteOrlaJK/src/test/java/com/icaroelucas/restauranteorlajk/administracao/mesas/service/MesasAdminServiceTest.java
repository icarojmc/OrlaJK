package com.icaroelucas.restauranteorlajk.administracao.mesas.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.mesas.dto.NovaMesaDTO;
import com.icaroelucas.restauranteorlajk.administracao.mesas.service.MesasAdminService;
import com.icaroelucas.restauranteorlajk.entities.mesa.model.Mesa;
import com.icaroelucas.restauranteorlajk.entities.mesa.repository.MesaRepository;

import util.ClasseDeModelTeste;

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
		mesaRepository.deleteAll();
	}

	@Test
	void incluirMesa() {

		Mesa mesa = GerenciadorDeMesas.criarMesa(service, 4, 1, "A");

		assertEquals(4, mesa.getnDeCadeiras());
		assertEquals(1, mesa.getNumeroDaMesa());
		assertEquals("A", mesa.getSetor().name());
	}

	@Test
	void buscarMesa() {
		Mesa mesa = GerenciadorDeMesas.criarMesa(service, 4, 1, "A");
		assertEquals(mesa.getId(), service.buscaMesa(mesa.getId()).getId());
	}

	@Test
	void buscarTotalDeMesas() {
		for (int i = 0; i < 3; i++) {
			GerenciadorDeMesas.criarMesa(service, 4, i, "A");
		}
		List<Mesa> mesas = service.buscaMesas();
		assertEquals(3, mesas.size());
	}

	@Test
	void excluirMesa() {
		Mesa mesa = GerenciadorDeMesas.criarMesa(service, 4, 1, "A");
		service.excluiMesa(mesa.getId());
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> service.buscaMesa(mesa.getId()));
	}

	@Test
	void popularModelComListaDeMesas() {
		Model model = new ClasseDeModelTeste();
		for (int i = 0; i < 3; i++) {
			GerenciadorDeMesas.criarMesa(service,4,1,"A");
		}
		model = service.popularModel(model);
		List<Mesa> mesas = (List<Mesa>) model.getAttribute("mesas");
		assertEquals(3, mesas.size());
	}

	@Test
	void popularModelComMesa() {
		Model model = new ClasseDeModelTeste();
		Mesa mesa = GerenciadorDeMesas.criarMesa(service,4,1,"A");
		model = service.popularModel(model, mesa.getId());
		Mesa mesaModel = (Mesa) model.getAttribute("mesas");
		assertEquals(mesa.getId(), mesaModel.getId());
	}

}
