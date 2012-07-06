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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Richetti
 */
@ManagedBean
@RequestScoped
public class cadastraAlunoManagedBean implements Serializable {

    int idusuario;
    String senha;
    String senha2;
    String nome;
    String sobrenome;
    String email;
    String mensagemsucesso;
    boolean isLogedAdmin;
    boolean isLogedProfessor;
    boolean sucesso;
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
        isLogedAdmin = false;
        isLogedProfessor = false;
        sucesso = false;
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

    public boolean isIsLogedAdmin() {
        return isLogedAdmin;
    }

    public void setIsLogedAdmin(boolean isLogedAdmin) {
        this.isLogedAdmin = isLogedAdmin;
    }

    public boolean isIsLogedProfessor() {
        return isLogedProfessor;
    }

    public void setIsLogedProfessor(boolean isLogedProfessor) {
        this.isLogedProfessor = isLogedProfessor;
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

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public void cadastrar() {
        Usuario us = new Usuario();
        us.setNome(nome);
        us.setSobrenome(sobrenome);
        us.setEmail(email);
        us.setSenha(senha);
        us.setFkIdUsuSup(professor);
        us.setFkIdTusu(ejbTusuFacade.findTusuByName("Aluno"));
        if (ejbUsuarioFacade.findLogin(us) == null){
//            ejbUsuarioFacade.create(us);
            sucesso = true;
            mensagemsucesso = "O Cadastro foi Realizado com Sucesso!";
        } else {
            sucesso = false;
            mensagemsucesso = "Este aluno já está cadastrado!";
        }
    }
    
    public String whichPage(){
        if (sucesso){
            System.out.println("WOLOLO DEU CERTO");
            return "../conteudo-professor/sobre-professor?faces-redirect=true";
        }else {
            return "../conteudo-professor/cadastra-aluno?faces-redirect=true";
        }
    }
}
