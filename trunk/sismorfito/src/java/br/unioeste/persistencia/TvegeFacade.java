/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.*;
import java.util.ArrayList;
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
public class TvegeFacade extends AbstractFacade<Tvege> {
	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TvegeFacade() {
		super(Tvege.class);
	}
	
	public Tvege findTvegeFromName(String nomeTvege){
				
		TypedQuery<Tvege> q = getEntityManager().createQuery("select tv from Tvege tv where tv.nomeVege =:arg1", Tvege.class).setParameter("arg1", nomeTvege);

		q.setMaxResults(1);

		List<Tvege> lista = q.getResultList();

		System.out.println("Em Busca de TVEGE");
		System.out.println(lista.get(0).getNomeVege());
		System.out.println("Deu?");
		
		if (lista == null) {
			return null;
		}
		
		return lista.get(0);
	}
	
}
