package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class)
				.getResultList();
	}

	// Filtrei no repositório para aproveitar o método já existente listar()
	/*public List<Produto> listarPorData(LocalDate data) {
		Calendar dataCalendar = Calendar.getInstance();
		dataCalendar.set(data.getYear(), data.getMonthValue() - 1, data.getDayOfMonth());
		return manager.createQuery("select p from Produto p where p.dataLancamento > :data", Produto.class)
				.setParameter("data", dataCalendar, TemporalType.DATE).getResultList();
	}*/

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id",
				Produto.class).setParameter("id", id).getSingleResult();
	}

	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(preco.valor) from Produto p join p.precos preco " + "where preco.tipo = :tipoPreco",
				BigDecimal.class);
		query.setParameter("tipoPreco", tipoPreco);
		return query.getSingleResult();
	}

}