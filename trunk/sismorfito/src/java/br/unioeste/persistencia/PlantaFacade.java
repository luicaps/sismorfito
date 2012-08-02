/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Planta;
import br.unioeste.modelo.Pplanta;
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
public class PlantaFacade extends AbstractFacade<Planta> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PlantaFacade() {
		super(Planta.class);
	}

	public List<Pplanta> findPplantaFromPlanta(Long idPlanta) {
		TypedQuery<Planta> q = getEntityManager().createQuery("select p from Planta p where p.idPlanta =:arg1", Planta.class).setParameter("arg1", idPlanta);
		q.setMaxResults(1);
		List<Planta> lista = q.getResultList();
		if (lista == null || lista.get(0).getPplantaList() == null) {
			return null;
		}
		return lista.get(0).getPplantaList();
	}

 public long nextId() {
        List<Planta> lista = findAll();
        if(lista.size()<1){
            return 1;
        }
        return (lista.get(lista.size() - 1).getIdPlanta() + 1);
    }
}
