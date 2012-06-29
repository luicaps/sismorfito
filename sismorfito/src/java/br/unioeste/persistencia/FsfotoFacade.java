/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Fsfoto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Caps
 */
@Stateless
public class FsfotoFacade extends AbstractFacade<Fsfoto> {
	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public FsfotoFacade() {
		super(Fsfoto.class);
	}
	
}
