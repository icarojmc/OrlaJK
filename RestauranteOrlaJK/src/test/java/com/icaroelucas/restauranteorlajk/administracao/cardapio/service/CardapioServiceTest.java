package com.icaroelucas.restauranteorlajk.administracao.cardapio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;

import util.ClasseDeModelTeste;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class CardapioServiceTest {

	@Autowired
	AlimentoRepository alimentoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	GerenciadorDeCardapio gerenciador = new GerenciadorDeCardapio();

	@BeforeEach
	void iniciar() {
		gerenciador.iniciar(alimentoRepository, produtoRepository);
	}

	@AfterEach
	void finalizar() {
		alimentoRepository.deleteAll();
	}

	@Test
	void adicionarNovoALimento() {
		String nome = "Bebida X";
		String tipo = "BEBIDA";
		BigDecimal valor = new BigDecimal(10.0);
		String descricao = "Uma bebida de teste";

		Alimento alimento = gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		assertEquals(nome, alimento.getNome());
		assertEquals(tipo, alimento.getTipo().name());
		assertEquals(valor, alimento.getValor());
		assertEquals(descricao, alimento.getDescricao());
	}
	
	@Test
	void testBuscaAlimento() {
		String nome = "Bebida X";
		String tipo = "BEBIDA";
		BigDecimal valor = new BigDecimal(10.0);
		String descricao = "Uma bebida de teste";

		Alimento alimento = gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		
		Alimento alimentoBuscado = gerenciador.buscarAlimento(alimento.getId());
		
		assertEquals(alimento.getId(), alimentoBuscado.getId());
		assertEquals(alimento.getNome(), alimentoBuscado.getNome());
		assertEquals(alimento.getTipo(), alimentoBuscado.getTipo());
		assertEquals(alimento.getDescricao(), alimentoBuscado.getDescricao());
		assertEquals(alimento.getValor(), alimentoBuscado.getValor());
	}

	@Test
	void verificarNumeroDeItensNoCardapio() {
		
		for(int i = 0; i<3; i++) {
			String nome = "Bebida " + i;
			String tipo = "BEBIDA";
			BigDecimal valor = new BigDecimal(10.0);
			String descricao = "Uma bebida de teste";
			gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		}
		List<Alimento> cardapio = gerenciador.buscarAlimentos();
		assertEquals(3, cardapio.size());
	}

	@Test
	void popularModelcomCardapio() {
		int nDeIteracoes = 3;
		for(int i = 0; i<nDeIteracoes; i++) {
			String nome = "Bebida " + i;
			String tipo = "BEBIDA";
			BigDecimal valor = new BigDecimal(10.0);
			String descricao = "Uma bebida de teste";
			gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		}
		Model model = new ClasseDeModelTeste();
		model = gerenciador.popularModel(model);
		List<Alimento> cardapio = (List<Alimento>) model.getAttribute("alimentos");
		assertEquals(nDeIteracoes, cardapio.size());
	}

	@Test
	void popularModelcomAlimento() {
		
		String nome = "Bebida X";
		String tipo = "BEBIDA";
		BigDecimal valor = new BigDecimal(10.0);
		String descricao = "Uma bebida de teste";
		Alimento alimento = gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		
		Model model = new ClasseDeModelTeste();
		model = gerenciador.popularModel(model, alimento.getId());
		Alimento alimentoModel = (Alimento) model.getAttribute("alimento");
		assertEquals(alimento.getId(), alimentoModel.getId());
		
	}
	
	@Test
	void testEditaALimento() {
		String nome = "Bebida X";
		String tipo = "BEBIDA";
		BigDecimal valor = new BigDecimal(10.0);
		String descricao = "Uma bebida de teste";
		Alimento alimento = gerenciador.adicionarAlimento(nome, tipo, valor, descricao);
		long idAntigo = alimento.getId();
		alimento = gerenciador.editarAlimento(alimento);
		assertEquals(nome + " Editado", alimento.getNome());
		assertEquals(idAntigo, alimento.getId());
	}

//	@Test
//	void testDeletaAlimento() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testAdicionaProdutosAoAlimento() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemoveProdutosDoAlimento() {
//		fail("Not yet implemented");
//	}

}
