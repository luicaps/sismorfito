/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.persistencia;

import br.unioeste.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Caps
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

	@PersistenceContext(unitName = "sismorfitoPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	public Usuario findLogin(Usuario usuario) {

		TypedQuery<Usuario> q = getEntityManager().createQuery("select u from Usuario u where u.email =:arg1", Usuario.class).setParameter("arg1", usuario.getEmail());

		q.setMaxResults(1);
		
		List<Usuario> lista = q.getResultList();
		
                if(lista == null || lista.size()!= 1){
                    return null;
                }
                
		return lista.get(0);		
	}
}
