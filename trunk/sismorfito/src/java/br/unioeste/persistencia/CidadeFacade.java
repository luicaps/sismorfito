/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Cidade;
import br.unioeste.modelo.Ordem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Caps
 */
@Stateless
public class CidadeFacade extends AbstractFacade<Cidade> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CidadeFacade() {
		super(Cidade.class);
	}

	public Cidade findCidadeFromName(String nomeCidade) {
		TypedQuery<Cidade> q = getEntityManager().createQuery("select c from Cidade c where c.cidade =:arg1", Cidade.class).setParameter("arg1", nomeCidade);

		q.setMaxResults(1);

		List<Cidade> lista = q.getResultList();

		if (lista == null) {
			return null;
		}

		return lista.get(0);
	}
}
