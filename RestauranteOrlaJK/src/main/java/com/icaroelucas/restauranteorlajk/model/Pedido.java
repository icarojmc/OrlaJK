package com.icaroelucas.restauranteorlajk.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany()
	private List<Alimento> alimentos;
	@ManyToOne
	private Mesa mesa;
	private LocalDateTime horaDoPedido;
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	private BigDecimal totalDoPedido = new BigDecimal("0.00");
	private String observacao;
	
	public void adicionaAlimento(Alimento alimento){
		alimentos.add(alimento);
	}
	
	public void removeAlimento(Alimento alimento) {
		alimentos.remove(alimento);
	}
	
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public void setHoraDoPedido(LocalDateTime horaDoPedido) {
		this.horaDoPedido = horaDoPedido;
	}
	
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public long getId() {
		return id;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public Mesa getMesa() {
		return mesa;
	}
	
	public LocalDateTime getHoraDoPedido() {
		return horaDoPedido;
	}

	public BigDecimal getTotalDoPedido() {
		return totalDoPedido;
	}

	public void setTotalDoPedido(BigDecimal totalDoPedido) {
		this.totalDoPedido = totalDoPedido;
	}
	
}
