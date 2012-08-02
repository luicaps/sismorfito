/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

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
        List<Csolo> lista = findAll();
        if(lista.size()<1){
            return 1;
        }
        return (lista.get(lista.size() - 1).getIdCsolo() + 1);
    }
}
