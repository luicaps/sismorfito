/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Pos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Caps
 */
@Stateless
public class PosFacade extends AbstractFacade<Pos> {
	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PosFacade() {
		super(Pos.class);
	}
	
 public long nextId() {
        List<Pos> lista = findAll();
        if(lista.size()<1){
            return 1;
        }
        return (lista.get(lista.size() - 1).getIdPos() + 1);
    }
	
}
