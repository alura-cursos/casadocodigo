package br.com.casadocodigo.loja.application;

import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoDTO {
	private final Calendar dataGeracao;
	private final int quantidade;
	private final List<Produto> produtos;

	public ProdutoDTO(Calendar dataGeracao, List<Produto> produtos) {
		this.dataGeracao = dataGeracao;
		this.quantidade = produtos.size();
		this.produtos = produtos;
	}

	public Calendar getDataGeracao() {
		return dataGeracao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
