/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Cidade;
import br.unioeste.modelo.Estado;
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
public class EstadoFacade extends AbstractFacade<Estado> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }

    public List<Cidade> findCidadesFromEstado(String uf) {

        TypedQuery<Estado> q = getEntityManager().createQuery("select e from Estado e where e.uf =:arg1", Estado.class).setParameter("arg1", uf);

        q.setMaxResults(1);

        List<Estado> lista = q.getResultList();

        if (lista == null || lista.get(0).getCidadeList() == null) {
            return null;
        }

        return lista.get(0).getCidadeList();

    }
}
