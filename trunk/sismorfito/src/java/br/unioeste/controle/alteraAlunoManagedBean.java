/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Usuario;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Richetti
 */
@ManagedBean
@ViewScoped
public class alteraAlunoManagedBean {

    String senha;
    String senha2;
    String nome;
    String sobrenome;
    String email;
    
    public alteraAlunoManagedBean() {
        senha = new String();
        senha2 = new String();
        nome = new String();
        sobrenome = new String();
        email = new String();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public void alterar (){
        System.out.println("Entrou no Metodo");
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Senha2: " + senha2);
        System.out.println("Nome: " + nome);
        System.out.println("Sobrenome: " + sobrenome);
    }
    
    public void onRowSelect (){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-professor/altera-aluno.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(alteraAlunoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
