package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Role role) {
		manager.persist(role);
	}
}
