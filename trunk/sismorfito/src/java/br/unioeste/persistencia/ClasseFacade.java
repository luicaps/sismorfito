/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Classe;
import br.unioeste.modelo.Usuario;
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
public class ClasseFacade extends AbstractFacade<Classe> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ClasseFacade() {
		super(Classe.class);
	}

	public List<Classe> findClassesFromFilo(Long idFilo) {

		TypedQuery<Classe> q = getEntityManager().createQuery("select c from Classe c where c.fkIdFilo =:arg1", Classe.class).setParameter("arg1", idFilo.longValue());

		List<Classe> lista = q.getResultList();

		return lista;
	}
}
