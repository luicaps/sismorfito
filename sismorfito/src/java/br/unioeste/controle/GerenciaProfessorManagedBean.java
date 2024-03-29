/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Tusu;
import br.unioeste.modelo.Usuario;
import br.unioeste.persistencia.TusuFacade;
import br.unioeste.persistencia.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Caps
 */
@Named(value = "gerenciaProfessorManagedBean")
@SessionScoped
public class GerenciaProfessorManagedBean implements Serializable {

    //Cadastra
    String email;
    String senha;
    String senha2;
    String senha3;
    String nome;
    String sobrenome;
    Usuario admin;
    //Gerencia
    List<Usuario> profes;
    @EJB
    TusuFacade ejbTusuFacade;
    @EJB
    UsuarioFacade ejbUsuarioFacade;
    //Edita
    Usuario professor;

    /**
     * Creates a new instance of GerenciaProfessorManagedBean
     */
    public GerenciaProfessorManagedBean() {
    }

    @PostConstruct
    public void init() {
	LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
	admin = lg.getUsuario();
	Tusu tusu = ejbTusuFacade.findTusuByName("Professor");
	profes = tusu.getUsuarioList();
    }

    public Usuario getAdmin() {
	return admin;
    }

    public void setAdmin(Usuario admin) {
	this.admin = admin;
    }

    //Cadastrar
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	try {
	    this.senha = AeSimpleSHA1.SHA1(senha);
	} catch (NoSuchAlgorithmException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedEncodingException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public String getSenha2() {
	return senha2;
    }

    public void setSenha2(String senha2) {
	try {
	    this.senha2 = AeSimpleSHA1.SHA1(senha2);
	} catch (NoSuchAlgorithmException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedEncodingException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public String getSenha3() {
	return senha3;
    }

    public void setSenha3(String senha3) {
	try {
	    this.senha3 = AeSimpleSHA1.SHA1(senha3);
	} catch (NoSuchAlgorithmException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedEncodingException ex) {
	    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSobrenome() {
	return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
    }

    public String cadastrar() {

	if (!senha.equals(senha2)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Cadastrar: ", "As senhas estão incorretas."));
	    return "";
	}

	if (senha == null || senha2 == null || email == null || nome == null || sobrenome == null) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Cadastrar: ", "Os campos não estão devidamente preenchidos."));
	    return "";
	}

	Usuario us = new Usuario();
	us.setNome(nome);
	us.setIdUsuario(ejbUsuarioFacade.nextId());
	us.setSobrenome(sobrenome);
	us.setEmail(email);
	us.setSenha(senha);
	us.setFkIdUsuSup(admin);
	us.setFkIdTusu(ejbTusuFacade.findTusuByName("Professor"));
	if (ejbUsuarioFacade.findLogin(us) == null) {
	    ejbUsuarioFacade.create(us);
	    profes.add(us);
	    nome = new String();
	    sobrenome = new String();
	    email = new String();
	    senha = new String();
	    senha2 = new String();
	    return "sobre-admin";
	} else {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Cadastrar: ", "e-mail não disponível."));
	    return "";
	}
    }

    //Gerenciar
    public boolean isAtivo(Usuario usuario) {
	if (usuario.getSenha().equals("")) {
	    return false;
	}
	return true;
    }

    public List<Usuario> getProfes() {
	return profes;
    }

    public void setProfes(List<Usuario> profes) {
	this.profes = profes;
    }

    public String remover(Usuario usuario) {
	usuario.setSenha("");
	ejbUsuarioFacade.edit(usuario);
	return "../conteudo-admin/gerenciar-professor.xhtml?faces-redirect=true";
    }

    //Editar
    public Usuario getProfessor() {
	return professor;
    }

    public void setProfessor(Usuario professor) {
	this.professor = professor;
    }

    public String alterar() {

	if (!senha.equals(senha2)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar: ", "As senhas não conferem."));
	    return "";
	}

	if (senha.equals("") || senha == null) {
	} else {
	    professor.setSenha(senha);
	}

	ejbUsuarioFacade.edit(professor);
	for (int i = 0; i < profes.size(); i++) {
	    if (profes.get(i).getIdUsuario() == professor.getIdUsuario()) {
		profes.remove(i);
		profes.add(i, professor);
		break;
	    }
	}

	senha = new String();
	senha2 = new String();
	senha3 = new String();
	return "../conteudo-admin/sobre-admin.xhtml?faces-redirect=true";
    }

    public String alterarAdmin() {

	if (!senha3.equals(admin.getSenha())) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar: ", "A senha atual esta incorreta."));
	    return "";
	}

	if (!senha.equals(senha2)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar: ", "As senhas não conferem."));
	    return "";
	}

	if (senha.equals("") || senha == null) {
	} else {
	    admin.setSenha(senha);
	    ejbUsuarioFacade.edit(admin);
	}
	senha = new String();
	senha2 = new String();
	senha3 = new String();
	return "../conteudo-admin/sobre-admin.xhtml?faces-redirect=true";
    }
}
