package com.icaroelucas.restauranteorlajk.administracao.debito.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.icaroelucas.restauranteorlajk.entities.debito.model.Debito;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.model.Fornecedor;

public class NovoDebitoDTO {

	private BigDecimal valor;
	private String vencimento;
	private String banco;
	private String descricao;
	private Fornecedor fornecedor;
	
	public Debito toDebito() {
		LocalDate data = LocalDate.parse(vencimento);
		Debito debito = new Debito();
		debito.setValor(valor);
		debito.setVencimento(data);
		debito.setBanco(banco);
		debito.setDescricao(descricao);
		debito.setFornecedor(fornecedor);
		return debito;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
