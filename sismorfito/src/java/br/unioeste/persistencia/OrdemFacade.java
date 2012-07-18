/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Classe;
import br.unioeste.modelo.Familia;
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
public class OrdemFacade extends AbstractFacade<Ordem> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OrdemFacade() {
		super(Ordem.class);
	}

	public List<Familia> findFamiliasFromOrdem(String nomeOrdem) {


		TypedQuery<Ordem> q = getEntityManager().createQuery("select o from Ordem o where o.nomeOrdem =:arg1", Ordem.class).setParameter("arg1", nomeOrdem);

		q.setMaxResults(1);

		List<Ordem> lista = q.getResultList();

		if (lista == null || lista.get(0).getFamiliaList() == null) {
			return null;
		}

		return lista.get(0).getFamiliaList();

	}
}
