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

        if (lista == null || lista.size() != 1) {
            return null;
        }

        return lista.get(0);
    }

    public List<Usuario> findPplantaFromPlanta(Usuario usuario) {
//        TypedQuery<Planta> q = getEntityManager().createQuery("select p from Planta p where p.idPlanta =:arg1", Planta.class).setParameter("arg1", idPlanta);

//        List<Planta> lista = q.getResultList();
//        if (lista == null || lista.get(0).getPplantaList() == null) {
//            return null;
//        }
//        return lista.get(0).getPplantaList();

        return null;
    }

    public long nextId() {
        List<Usuario> lista = findAll();
        if (lista.size() < 1) {
            return 1;
        }

        long saida = Integer.MIN_VALUE;

        for (Usuario usuario : lista) {
            if (usuario.getIdUsuario() > saida) {
                saida = usuario.getIdUsuario();
            }
        }

        return saida + 1;
    }
}
