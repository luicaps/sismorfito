/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Usuario;
import br.unioeste.persistencia.UsuarioFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Richetti
 */
@ManagedBean
@SessionScoped
public class alteraAlunoManagedBean {

    List<Usuario> alunos;
    Usuario aluno;
    String senha;
    String senha2;
    String sen;
    @EJB
    UsuarioFacade ejbUsuarioFacade;

    public alteraAlunoManagedBean() {
        aluno = new Usuario();
        aluno.setEmail("email");
        aluno.setNome("nome");
        aluno.setSobrenome("sobrenome");
        senha = new String();
        senha2 = new String();
    }

    @PostConstruct
    public void init() {
        LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        Usuario resp = lg.getUsuario();
        cadastraAlunoManagedBean cad = (cadastraAlunoManagedBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cadastraAlunoManagedBean");
        aluno = cad.getAlunoSelect();
    }

    public UsuarioFacade getEjbUsuarioFacade() {
        return ejbUsuarioFacade;
    }

    public void setEjbUsuarioFacade(UsuarioFacade ejbUsuarioFacade) {
        this.ejbUsuarioFacade = ejbUsuarioFacade;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
        System.out.println("setou");
        System.out.println(aluno.getEmail());
        System.out.println(aluno.getNome());
        System.out.println(aluno.getSobrenome());
    }

    public List<Usuario> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Usuario> alunos) {
        this.alunos = alunos;
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

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        try {
            this.sen = AeSimpleSHA1.SHA1(sen);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(alteraAlunoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(alteraAlunoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String alterar() {
        System.out.println("Fui chamado");
        if (!senha.equals(senha2)) {
            System.out.println("senhas diferentes");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar", "As senhas não conferem.");
            return "";
        }

        if (!sen.equals(aluno.getSenha())) {
            System.out.println("senha invalida");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar", "Senha incorreta.");
            return "";
        }

        if (senha.equals("") || senha == null) {
            System.out.println("vazio");
        } else {
            System.out.println("mudou nova senha");
            aluno.setSenha(senha);
        }

        System.out.println("alterou");
        ejbUsuarioFacade.edit(aluno);

        System.out.println("saiu");
        return "../conteudo-professor/altera-aluno-lista.xhtml?faces-redirect=true";
    }

    public void onRowSelect() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-professor/altera-aluno.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(alteraAlunoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
