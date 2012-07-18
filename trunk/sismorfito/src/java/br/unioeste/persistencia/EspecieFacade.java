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
public class EspecieFacade extends AbstractFacade<Especie> {
	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EspecieFacade() {
		super(Especie.class);
	}
	
	public List<Fitop> findFitopFromEspecie(String nomeEspecie) {
		
		TypedQuery<Especie> q = getEntityManager().createQuery("select e from Especie e where e.nomeEspecie =:arg1", Especie.class).setParameter("arg1", nomeEspecie);

		q.setMaxResults(1);

		List<Especie> lista = q.getResultList();

		if (lista == null || lista.get(0).getPlantaList() == null) {
			return null;
		}

		List<Fitop> awea = new ArrayList();
		
		for (Planta planta : lista.get(0).getPlantaList()) {
			for (Pplanta pplanta : planta.getPplantaList()) {
				awea.addAll(pplanta.getFitopList());
			}
		}
		
		return awea;

	}
}
