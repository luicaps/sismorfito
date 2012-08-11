/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Tusu;
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
public class TusuFacade extends AbstractFacade<Tusu> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TusuFacade() {
        super(Tusu.class);
    }

    public Tusu findTusuByName(String tipo) {
        TypedQuery<Tusu> q = getEntityManager().createQuery("select t from Tusu t where t.tipo =:arg", Tusu.class).setParameter("arg", tipo);

        q.setMaxResults(1);

        List<Tusu> lista = q.getResultList();

        return lista.get(0);
    }
}
