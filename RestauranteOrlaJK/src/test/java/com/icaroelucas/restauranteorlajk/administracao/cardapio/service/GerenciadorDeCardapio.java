package com.icaroelucas.restauranteorlajk.administracao.cardapio.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.EdicaoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.administracao.cardapio.dto.NovoAlimentoDTO;
import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.repository.AlimentoRepository;
import com.icaroelucas.restauranteorlajk.entities.produto.repository.ProdutoRepository;

public class GerenciadorDeCardapio {

	CardapioService service = new CardapioService();
	
	NovoAlimentoDTO alimentoDTO = new NovoAlimentoDTO();
	
	public void iniciar(AlimentoRepository alimentoRepository, ProdutoRepository produtoRepository) {
		service.iniciar(alimentoRepository);
		
	}
	
	public Alimento adicionarAlimento(String nome, String tipo, BigDecimal valor, String descricao) {
		
		NovoAlimentoDTO alimentoDTO = new NovoAlimentoDTO();
		alimentoDTO.setNome(nome);
		alimentoDTO.setTipo(tipo);
		alimentoDTO.setValor(valor);
		alimentoDTO.setDescricao(descricao);
		return service.novoALimento(alimentoDTO);
		
	}

	public List<Alimento> buscarAlimentos() {
		return service.buscaCardapio();
	}
	
	public Alimento buscarAlimento(long id) {
		return service.buscaAlimento(id);
	}

	public Model popularModel(Model model) {
		model = service.popularModel(model);
		return model;
	}

	public Model popularModel(Model model, long id) {
		model = service.popularModel(model, id);
		return model;
	}

	public Alimento editarAlimento(Alimento alimento) {
		
		EdicaoAlimentoDTO alimentoDTO = new EdicaoAlimentoDTO();
		alimentoDTO.setId(alimento.getId());
		alimentoDTO.setNome(alimento.getNome() + " Editado");
		alimentoDTO.setTipo(alimento.getTipo().name());
		alimentoDTO.setValor(alimento.getValor());
		service.editaALimento(alimentoDTO);
		alimento = service.buscaAlimento(alimentoDTO.getId());
		return alimento;
	}

	
}
