/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Classe;
import br.unioeste.modelo.Csolo;
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
public class CsoloFacade extends AbstractFacade<Csolo> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CsoloFacade() {
		super(Csolo.class);
	}

	public Csolo findCsoloFromName(String nomeCsolo) {
		TypedQuery<Csolo> q = getEntityManager().createQuery("select cs from Csolo cs where cs.sibcs =:arg1", Csolo.class).setParameter("arg1", nomeCsolo);

		q.setMaxResults(1);

		List<Csolo> lista = q.getResultList();

		if (lista == null) {
			return null;
		}

		return lista.get(0);
	}

	public long nextId() {
		List<Csolo> cs = findAll();
		long r = Long.MIN_VALUE;
		System.out.println("iteracoes");
		System.out.println("---------------------------------------");
		for (Csolo csolo : cs) {
			System.out.println("ID: " + csolo.getIdCsolo());
			System.out.println("r: " + r);
			if (csolo.getIdCsolo() > r) {
				r = csolo.getIdCsolo();
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("O ID maior foi : " + r);
		r += 1;
		System.out.println("Com a soma ficou: " + r);
		return r;
	}
}
