/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Fpfoto;
import br.unioeste.modelo.Fsfoto;
import java.util.List;
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

    public long nextId() {
        List<Fsfoto> lista = findAll();
        if (lista.size() < 1) {
            return 1;
        }

        long saida = Integer.MIN_VALUE;

        for (Fsfoto fsfoto : lista) {
            if (fsfoto.getFoto() > saida) {
                saida = fsfoto.getFoto();
            }
        }

        return saida + 1;
    }
}
