/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class Usuario {
    int idusuario;
    String senha;
    String usuario;
    String nome;
    int tipo;
    boolean isLogedAdmin;
    boolean isLogedProfessor;

    public Usuario(int idusuario, String senha, String usuario, String nome, int tipo) {
        this.idusuario = idusuario;
        this.senha = senha;
        this.usuario = usuario;
        this.nome = nome;
        this.tipo = tipo;
        this.isLogedAdmin = false;
        this.isLogedProfessor = false;
    }

    public Usuario() {
        this.idusuario = 0;
        this.senha = new String();
        this.usuario = "Visitante";
        this.nome = new String();
        this.tipo = 0;
        this.isLogedAdmin = false;
        this.isLogedProfessor = false;
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
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
   
}