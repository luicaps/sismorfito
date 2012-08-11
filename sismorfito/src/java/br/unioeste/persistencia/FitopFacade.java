/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Fitop;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
