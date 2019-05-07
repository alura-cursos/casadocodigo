package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@RequestMapping("/")
	//@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}	
	
	@ResponseBody
	@RequestMapping("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn")
	@Transactional
	public String urlMagicaMaluca() {
		
		Produto produto1 = criarProduto(502, new GregorianCalendar(2016, 6, 15), 
				"Java SE 8 Programmer I", "O guia para sua certificação Oracle Certified Associate", new String[] {"29.9", "39.9", "79.9"} );
		Produto produto2 = criarProduto(172, new GregorianCalendar(2017, 11, 21), 
				"Orientação a Objetos", "Aprenda seus conceitos e suas aplicabilidades de forma efetiva", new String[] {"29.9", "49.9", "59.9"}  );
		Produto produto3 = criarProduto(260, new GregorianCalendar(2018, 2, 2), 
				"Spring MVC", "Domine o principal framework web Java", new String[] {"19.9", "29.9", "39.9"}  );
		Produto produto4 = criarProduto(234, new GregorianCalendar(2017, 11, 11), 
				"Amazon AWS", "Descomplicando a computação na nuvem", new String[] {"29.9", "69.9", "79.9"}  );
		Produto produto5 = criarProduto(355, new GregorianCalendar(2018, 7, 2), 
				"Introdução à Arquitetura e Design de Software", "Uma visão sobre a plataforma Java", new String[] {"29.9", "69.9", "79.9"}  );
		Produto produto6 = criarProduto(151, new GregorianCalendar(2017, 3, 4), 
				"Java 8 Prático", "Lambdas, Streams e os novos recursos da linguagem", new String[] {"19.9", "49.9", "69.9"}  );
		
		produtoDao.gravar(produto1);
		produtoDao.gravar(produto2);
		produtoDao.gravar(produto3);
		produtoDao.gravar(produto4);
		produtoDao.gravar(produto5);
		produtoDao.gravar(produto6);
		
		Role roleAdmin = new Role("ROLE_ADMIN");
		Role roleUser = new Role("ROLE_USER");
		Role roleComercial = new Role("ROLE_COMERCIAL");
		
		roleDao.gravar(roleAdmin);
		roleDao.gravar(roleUser);
		roleDao.gravar(roleComercial);

		Usuario usuario = new Usuario(); 
	    usuario.setNome("Admin");
	    usuario.setEmail("admin@casadocodigo.com.br");
	    usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");

	    usuario.setRoles(Arrays.asList(roleAdmin));

	    usuarioDao.gravar(usuario);

	    return "Url Mágica executada";
	}

	private Produto criarProduto(int paginas, Calendar data, String titulo, String descricao, String[] precos) {
		Produto produto = new Produto();
		produto.setPaginas(paginas);
		produto.setTitulo(titulo);
		produto.setDataLancamento(data);
		produto.setDescricao(descricao);
		Preco ebook = new Preco(new BigDecimal(precos[0]), TipoPreco.EBOOK);
		Preco impresso = new Preco(new BigDecimal(precos[0]), TipoPreco.IMPRESSO);
		Preco combo = new Preco(new BigDecimal(precos[0]), TipoPreco.COMBO);
		produto.setPrecos(Arrays.asList(ebook,impresso, combo));
		return produto;
	}
}
