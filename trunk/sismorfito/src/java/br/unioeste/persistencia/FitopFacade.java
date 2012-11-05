/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Especie;
import br.unioeste.modelo.Fitop;
import br.unioeste.modelo.Planta;
import br.unioeste.modelo.Pplanta;
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
public class FitopFacade extends AbstractFacade<Fitop> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FitopFacade() {
        super(Fitop.class);
    }

    public long nextId() {
        List<Fitop> lista = findAll();
        if (lista.size() < 1) {
            return 1;
        }

        long saida = Integer.MIN_VALUE;

        for (Fitop fitop : lista) {
            if (fitop.getIdFitop() > saida) {
                saida = fitop.getIdFitop();
            }
        }

        return saida + 1;
    }
	
	public Fitop findByName(String name) {
		TypedQuery<Fitop> q = getEntityManager().createQuery("select f from Fitop f where f.nomeFp =:arg1", Fitop.class).setParameter("arg1", name);
        q.setMaxResults(1);
        List<Fitop> lista = q.getResultList();
        if (lista == null || lista.get(0) == null) {
            return null;
        }
		return lista.get(0);
	}
	
}
