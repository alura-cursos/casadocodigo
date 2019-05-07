package br.com.casadocodigo.loja.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.casadocodigo.loja.dao.ProdutoDAO;

@Repository
public class Produtos {
	@Autowired
	private ProdutoDAO dao;

	public List<Produto> buscarTodosLancados() {
		return dao.listar();
	}

	public List<Produto> buscarTodosComDataPosteriorA(LocalDate dataDeLancamento) {
		Calendar dataCalendar = Calendar.getInstance();
		dataCalendar.set(dataDeLancamento.getYear(), dataDeLancamento.getMonthValue() - 1, dataDeLancamento.getDayOfMonth());
		List<Produto> produtosFiltrados = buscarTodosLancados()
				.stream()
				.filter(p -> p.getDataLancamento().compareTo(dataCalendar) > 0)
				.collect(Collectors.toList());

		return produtosFiltrados;
	}

}
