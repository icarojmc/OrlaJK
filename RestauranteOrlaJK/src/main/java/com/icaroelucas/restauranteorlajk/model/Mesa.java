package com.icaroelucas.restauranteorlajk.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "mesa")
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int numeroDaMesa;
	private int nDeCadeiras;
	private boolean ocupada;
	private boolean fechada;
	private BigDecimal totalDaConta = new BigDecimal("0.0");
	private LocalTime chegada;
	
	@OneToMany(mappedBy = "mesa")
	private List<Pedido> pedidos;
	
	@OneToOne
	private Cliente clienteOcupante;
	
	@Enumerated(EnumType.STRING)
	private Setor setor;
	
	public void removePedido(Pedido pedido) {
		
		pedidos.remove(pedido);
	}
	
	public boolean isOcupada() {
		return ocupada;
	}
	public int getNumeroDaMesa() {
		return numeroDaMesa;
	}
	public long getId() {
		return id;
	}
	public void setOcupada(boolean situacao) {
		this.ocupada = situacao;

	}
	public int getnDeCadeiras() {
		return nDeCadeiras;
	}
	public Setor getSetor() {
		return setor;
	}
	public Cliente getClienteOcupante() {
		return clienteOcupante;
	}
	public void setClienteOcupante(Cliente clienteOcupante) {
		this.clienteOcupante = clienteOcupante;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	public void setNumeroDaMesa(int numeroDaMesa) {
		this.numeroDaMesa = numeroDaMesa;
	}
	public void setnDeCadeiras(int nDeCadeiras) {
		this.nDeCadeiras = nDeCadeiras;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public void setId(long id) {
		this.id = id;
	}

	public boolean isFechada() {
		return fechada;
	}

	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}

	public BigDecimal getTotalDaConta() {
		return totalDaConta;
	}
	
	public void setTotalDaConta(BigDecimal totalDaConta) {
		this.totalDaConta = totalDaConta;
	}

	public void adicionaAoTotalDaConta(BigDecimal totalDaConta) {
		this.totalDaConta = this.totalDaConta.add(totalDaConta);
	}
	
	public void removeDoTotalDaConta(BigDecimal totalDaConta) {
		this.totalDaConta = this.totalDaConta.subtract(totalDaConta);
	}

	public void limpaPedidos() {
		pedidos.clear();
		
	}

	public LocalTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalTime chegada) {
		this.chegada = chegada;
	}
	
	
	
}
