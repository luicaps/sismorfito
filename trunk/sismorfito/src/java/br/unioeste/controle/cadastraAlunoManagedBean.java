/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Usuario;
import br.unioeste.persistencia.TusuFacade;
import br.unioeste.persistencia.UsuarioFacade;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Richetti
 */
@ManagedBean
@SessionScoped
public class cadastraAlunoManagedBean implements Serializable {

    int idusuario;
    String senha;
    String senha2;
    String nome;
    String sobrenome;
    String email;
    String mensagemsucesso;
    Usuario professor;
    @EJB
    private UsuarioFacade ejbUsuarioFacade;
    @EJB
    private TusuFacade ejbTusuFacade;

    /**
     * Creates a new instance of cadastraUsuarioManagedBean
     */
    public cadastraAlunoManagedBean() {
        senha = new String();
        senha2 = new String();
        nome = new String();
        sobrenome = new String();
        email = new String();
        mensagemsucesso = new String();
        idusuario = 0;
    }

    @PostConstruct
    public void init() {
        LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        professor = lg.getUsuario();
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getMensagemsucesso() {
        return mensagemsucesso;
    }

    public void setMensagemsucesso(String mensagemsucesso) {
        this.mensagemsucesso = mensagemsucesso;
    }
    
    public String cadastrar() {
        FacesMessage msg = null;

        if (!senha.equals(senha2)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Cadastrar", "As senhas estão incorretas.");
            return "";
        }
        //TODO Verificar o e-mail

        if (senha == null || senha2 == null || email == null || nome == null || sobrenome == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Cadastrar", "Os campos não estão devidamente preenchidos.");
            return "";
        }

        System.out.println("entrou no metodo de cadastro");

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
            mensagemsucesso = "O Cadastro foi Realizado com Sucesso!";
            return "../conteudo-professor/sobre-professor.xhtml?faces-redirect=true";
        } else {
            mensagemsucesso = "Este aluno já está cadastrado!";
            return "";
        }
    }
}
