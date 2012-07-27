/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Familia;
import br.unioeste.modelo.Genero;
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
public class FamiliaFacade extends AbstractFacade<Familia> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public FamiliaFacade() {
		super(Familia.class);
	}

	
	public List<Genero> findGenerosFromFamilia(String nomeFamilia) {

		TypedQuery<Familia> q = getEntityManager().createQuery("select f from Familia f where f.nomeFamilia =:arg1", Familia.class).setParameter("arg1", nomeFamilia);

		q.setMaxResults(1);

		List<Familia> lista = q.getResultList();

		if (lista == null || lista.get(0).getGeneroList() == null) {
			return null;
		}

		return lista.get(0).getGeneroList();

	}
	
	public Familia findFamiliaFromNamme(String nomeFamilia){
				TypedQuery<Familia> q = getEntityManager().createQuery("select f from Familia f where f.nomeFamilia =:arg1", Familia.class).setParameter("arg1", nomeFamilia);

		q.setMaxResults(1);

		List<Familia> lista = q.getResultList();

		if (lista == null) {
			return null;
		}

		return lista.get(0);

	}
}
