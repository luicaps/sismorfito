/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Usuario;
import br.unioeste.persistencia.UsuarioFacade;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Moises
 */
@ManagedBean
@SessionScoped
public class LoginBean {

	Usuario usuario;
	String senha;
	boolean LogedProfessor;
	boolean LogedAdmin;
	@EJB
	UsuarioFacade ejbFacade;

	/**
	 * Creates a new instance of LoginBean
	 */
	public LoginBean() {
		this.usuario = new Usuario();
		LogedProfessor = false;
		LogedAdmin = false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isLogedProfessor() {
		return LogedProfessor;
	}

	public void setLogedProfessor(boolean LogedProfessor) {
		this.LogedProfessor = LogedProfessor;
	}

	public boolean isLogedAdmin() {
		return LogedAdmin;
	}

	public void setLogedAdmin(boolean LogedAdmin) {
		this.LogedAdmin = LogedAdmin;
	}

	public UsuarioFacade getEjbFacade() {
		return ejbFacade;
	}

	public void setEjbFacade(UsuarioFacade ejbFacade) {
		this.ejbFacade = ejbFacade;
	}

	public void setSenha(String senha) {
		try {
			this.senha = AeSimpleSHA1.SHA1(senha);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		usuario.setSenha(this.senha);
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;

		Usuario us = ejbFacade.findLogin(this.usuario);

		if (usuario.getEmail() != null && usuario.getEmail().equals(us.getEmail()) && usuario.getSenha() != null && usuario.getSenha().equals(us.getSenha())) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario.getEmail());
			usuario = us;
			try {
				//Verificar quem é
				if (us.getFkIdTusu().getTipo().equals("Administrador")) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-admin/sobre-admin.xhtml");
					LogedAdmin = true;
				} else {

					if (us.getFkIdTusu().getTipo().equals("Professor")) {
						FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-professor/sobre-professor.xhtml");
						LogedProfessor = true;
					}
				}

			} catch (IOException ex) {
				Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Logar", "Usuario ou Senha incorretos.");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String logout() {
//		RequestContext context = RequestContext.getCurrentInstance();
		this.usuario = new Usuario();
		return "../conteudo/sobre.xhtml?faces-redirect=true";
	}
}
