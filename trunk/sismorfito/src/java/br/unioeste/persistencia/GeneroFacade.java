/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Especie;
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
public class GeneroFacade extends AbstractFacade<Genero> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneroFacade() {
        super(Genero.class);
    }

    public List<Especie> findEspeciesFromGenero(String nomeGenero) {

        TypedQuery<Genero> q = getEntityManager().createQuery("select g from Genero g where g.nomeGenero =:arg1", Genero.class).setParameter("arg1", nomeGenero);

        q.setMaxResults(1);

        List<Genero> lista = q.getResultList();

        if (lista == null || lista.get(0).getEspecieList() == null) {
            return null;
        }

        return lista.get(0).getEspecieList();

    }

    public Genero findGeneroFromName(String nomeGenero) {

        TypedQuery<Genero> q = getEntityManager().createQuery("select g from Genero g where g.nomeGenero =:arg1", Genero.class).setParameter("arg1", nomeGenero);

        q.setMaxResults(1);

        List<Genero> lista = q.getResultList();

        if (lista == null) {
            return null;
        }

        return lista.get(0);

    }
}
