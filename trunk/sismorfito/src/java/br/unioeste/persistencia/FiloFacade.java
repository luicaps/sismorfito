/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Classe;
import br.unioeste.modelo.Filo;
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
public class FiloFacade extends AbstractFacade<Filo> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiloFacade() {
        super(Filo.class);
    }

    public List<Classe> findClassesFromFilo(String nomefilo) {

        TypedQuery<Filo> q = getEntityManager().createQuery("select f from Filo f where f.nomeFilo =:arg1", Filo.class).setParameter("arg1", nomefilo);

        q.setMaxResults(1);

        List<Filo> lista = q.getResultList();

        if (lista == null || lista.get(0).getClasseList() == null) {
            return null;
        }

        return lista.get(0).getClasseList();
    }

    public Filo findFiloFromName(String nomefilo) {
        TypedQuery<Filo> q = getEntityManager().createQuery("select f from Filo f where f.nomeFilo =:arg1", Filo.class).setParameter("arg1", nomefilo);

        q.setMaxResults(1);

        List<Filo> lista = q.getResultList();

        if (lista == null) {
            return null;
        }

        return lista.get(0);
    }

    public long nextId() {
        List<Filo> lista = findAll();
        if (lista.size() < 1) {
            return 1;
        }

        long saida = Integer.MIN_VALUE;

        for (Filo filo : lista) {
            if (filo.getIdFilo() > saida) {
                saida = filo.getIdFilo();
            }
        }

        return saida + 1;
    }
}
