package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;
	
	public Preco() {
	}
	
	//para facilitar a criacao dos produtos no HomeController
	public Preco(BigDecimal valor, TipoPreco tipo) {
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.tipo.name() + " - " + this.valor;
	}
}
