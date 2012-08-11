/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Fitos;
import br.unioeste.modelo.Fpfoto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Caps
 */
@Stateless
public class FitosFacade extends AbstractFacade<Fitos> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FitosFacade() {
        super(Fitos.class);
    }

    public long nextId() {
        List<Fitos> lista = findAll();
        if (lista.size() < 1) {
            return 1;
        }

        long saida = Integer.MIN_VALUE;

        for (Fitos fitos : lista) {
            if (fitos.getIdFitos() > saida) {
                saida = fitos.getIdFitos();
            }
        }

        return saida + 1;
    }
}
