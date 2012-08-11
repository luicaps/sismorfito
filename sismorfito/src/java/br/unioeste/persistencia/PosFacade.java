/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Cidade;
import br.unioeste.modelo.Estado;
import br.unioeste.modelo.Pos;
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
        if (lista.size() < 1) {
            return 1;
        }
        return (lista.get(lista.size() - 1).getIdPos() + 1);
    }

    public Pos findCidadesFromEstado(String longlat) {

        String[] st = longlat.split(", ");
        String latitude = st[0];
        String longitude = st[1];

        TypedQuery<Pos> q = getEntityManager().createQuery("select p from Pos p where p.latitude =:arg1 and p.longitde =:arg2", Pos.class);
        q.setParameter("arg1", latitude);
        q.setParameter("arg2", longitude);

        q.setMaxResults(1);

        List<Pos> lista = q.getResultList();

        if (lista == null) {
            return null;
        }

        return lista.get(0);

    }
}
