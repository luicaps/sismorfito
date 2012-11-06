/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.Tusu;
import br.unioeste.modelo.Usuario;
import br.unioeste.persistencia.TusuFacade;
import br.unioeste.persistencia.UsuarioFacade;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Caps
 */
@Named(value = "gerenciarUsuarioManagedBean")
@Dependent
public class gerenciarUsuarioManagedBean {

	Usuario admin;
	//Gerencia
	LinkedList<Usuario> usuarios;
	@EJB
	TusuFacade ejbTusuFacade;
	@EJB
	UsuarioFacade ejbUsuarioFacade;
	//Edita
	Usuario professor;

	/**
	 * Creates a new instance of GerenciaProfessorManagedBean
	 */
	public gerenciarUsuarioManagedBean() {
	}

	@PostConstruct
	public void init() {
		LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
		admin = lg.getUsuario();
//		Tusu tusu = ejbTusuFacade.findTusuByName("Professor");
		List<Tusu> tusu = ejbTusuFacade.findAll();
		usuarios = new LinkedList();
		for (Tusu tusu1 : tusu) {
			usuarios.addAll(tusu1.getUsuarioList());
		}
	}
	
	//Gerenciar
	public boolean isAtivo(Usuario usuario) {
		if (usuario.getSenha().equals("")) {
			return false;
		}
		return true;
	}

	public boolean isProfessor(Usuario usuario) {
		if (usuario.getFkIdTusu().getTipo().equals("Professor")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAluno(Usuario usuario) {
		if (usuario.getFkIdTusu().getTipo().equals("Aluno")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAdmin(Usuario usuario) {
		if (usuario.getFkIdTusu().getTipo().equals("Administrador")) {
			return true;
		} else {
			return false;
		}
	}

	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(LinkedList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String remover(Usuario usuario) {
		usuario.setSenha("");
		ejbUsuarioFacade.edit(usuario);
		return "../conteudo-admin/gerenciar-usuario.xhtml?faces-redirect=true";
	}

	//Editar
	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

}
