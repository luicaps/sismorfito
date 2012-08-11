/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Classe;
import br.unioeste.modelo.Ordem;
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
public class ClasseFacade extends AbstractFacade<Classe> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasseFacade() {
        super(Classe.class);
    }

    public List<Ordem> findOrdemFromClasse(String nomeClasse) {


        TypedQuery<Classe> q = getEntityManager().createQuery("select c from Classe c where c.nomeClasse =:arg1", Classe.class).setParameter("arg1", nomeClasse);

        q.setMaxResults(1);

        List<Classe> lista = q.getResultList();

        if (lista == null || lista.get(0).getOrdemList() == null) {
            return null;
        }

        return lista.get(0).getOrdemList();
    }

    public Classe findClasseFromName(String nomeClasse) {
        TypedQuery<Classe> q = getEntityManager().createQuery("select c from Classe c where c.nomeClasse =:arg1", Classe.class).setParameter("arg1", nomeClasse);

        q.setMaxResults(1);

        List<Classe> lista = q.getResultList();

        if (lista == null) {
            return null;
        }

        return lista.get(0);
    }
}
