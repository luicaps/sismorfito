/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Ppfoto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Caps
 */
@Stateless
public class PpfotoFacade extends AbstractFacade<Ppfoto> {

    @PersistenceContext(unitName = "sismorfitoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PpfotoFacade() {
        super(Ppfoto.class);
    }
}
