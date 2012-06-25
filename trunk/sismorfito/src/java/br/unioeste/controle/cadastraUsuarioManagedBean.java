/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.AeSimpleSHA1;
import br.unioeste.modelo.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Richetti
 */
@ManagedBean
@RequestScoped
public class cadastraUsuarioManagedBean implements Serializable {
    int idusuario;
    String senha;
    String senha2;
    String usuario;
    String nome;
    String sobrenome;
    String email;
    boolean isLogedAdmin;
    boolean isLogedProfessor;

    /**
     * Creates a new instance of cadastraUsuarioManagedBean
     */
    public cadastraUsuarioManagedBean() {
        senha = new String();
        senha2 = new String();
        usuario = new String();
        nome = new String();
        sobrenome = new String();
        email = new String();
        idusuario = 0;
        isLogedAdmin = false;
        isLogedProfessor = false;
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

    public String getUsuario() {
        return usuario;
    }

        public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        this.senha2 = senha2;
    }

    public void cadastrar(){
        System.out.println("Entrou no Metodo");
        System.out.println("Usuário: " + usuario);
        System.out.println("Senha: " + senha);
        System.out.println("Nome: " + nome);
        System.out.println("Sobrenome: " + sobrenome);
        System.out.println("email: " + email);
    }
}
