/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Fitop;
import br.unioeste.modelo.Pplanta;
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
public class PplantaFacade extends AbstractFacade<Pplanta> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PplantaFacade() {
		super(Pplanta.class);
	}

	public List<Fitop> findFitopFromPplanta(Long idPplanta) {
		TypedQuery<Pplanta> q = getEntityManager().createQuery("select p from Pplanta p where p.idPplanta =:arg1", Pplanta.class).setParameter("arg1", idPplanta);
		q.setMaxResults(1);
		List<Pplanta> lista = q.getResultList();
		if (lista == null || lista.get(0).getFitopList() == null) {
			return null;
		}
		return lista.get(0).getFitopList();
	}
}
