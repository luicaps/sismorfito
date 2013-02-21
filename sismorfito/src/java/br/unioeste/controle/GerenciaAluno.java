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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luiz
 */
@Named(value = "gerenciaAluno")
@SessionScoped
public class GerenciaAluno implements Serializable {

    //Cadastra
    String email;
    String senha;
    String senha2;
    String senha3;
    String nome;
    String sobrenome;
    Usuario professor;
    //Gerencia
    List<Usuario> alunos;
    @EJB
    TusuFacade ejbTusuFacade;
    @EJB
    UsuarioFacade ejbUsuarioFacade;
    //Edita
    Usuario aluno;

    /**
     * Creates a new instance of GerenciaAluno
     */
    public GerenciaAluno() {
    }

    @PostConstruct
    public void init() {
	LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
	professor = lg.getUsuario();
	Tusu tusu = ejbTusuFacade.findTusuByName("Aluno");
	alunos = tusu.getUsuarioList();
    }

    public Usuario getAluno() {
	return aluno;
    }

    public void setAluno(Usuario aluno) {
	this.aluno = aluno;
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
	us.setFkIdUsuSup(professor);
	us.setFkIdTusu(ejbTusuFacade.findTusuByName("Aluno"));
	if (ejbUsuarioFacade.findLogin(us) == null) {
	    ejbUsuarioFacade.create(us);
	    alunos.add(us);
	    nome = new String();
	    sobrenome = new String();
	    email = new String();
	    senha = new String();
	    senha2 = new String();
	    return "sobre-professor";
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

    public List<Usuario> getAlunos() {
	return alunos;
    }

    public void setAlunos(List<Usuario> alunos) {
	this.alunos = alunos;
    }

    public String remover(Usuario usuario) {
	usuario.setSenha("");
	ejbUsuarioFacade.edit(usuario);
	return "../conteudo-professor/gerenciar-aluno.xhtml?faces-redirect=true";
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
	    aluno.setSenha(senha);
	}

	ejbUsuarioFacade.edit(aluno);
	for (int i = 0; i < alunos.size(); i++) {
	    if (alunos.get(i).getIdUsuario() == aluno.getIdUsuario()) {
		alunos.remove(i);
		alunos.add(i, aluno);
		break;
	    }
	}

	senha = new String();
	senha2 = new String();
	senha3 = new String();
	return "../conteudo-professor/sobre-professor.xhtml?faces-redirect=true";
    }

    public String alterarProfessor() {

	if (!senha3.equals(professor.getSenha())) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar: ", "A senha atual esta incorreta."));
	    return "";
	}

	if (!senha.equals(senha2)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar: ", "As senhas não conferem."));
	    return "";
	}

	if (senha.equals("") || senha == null) {
	} else {
	    professor.setSenha(senha);
	    ejbUsuarioFacade.edit(professor);
	}
	senha = new String();
	senha2 = new String();
	senha3 = new String();
	return "../conteudo-professor/sobre-professor.xhtml?faces-redirect=true";
    }
}
