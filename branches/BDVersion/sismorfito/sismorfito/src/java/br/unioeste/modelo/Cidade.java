/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class Cidade {
    String nome_cidade;
    int id_cidade;
    Estado estado;

    public Cidade(String nome_cidade, int id_cidade, Estado estado) {
        this.nome_cidade = nome_cidade;
        this.id_cidade = id_cidade;
        this.estado = estado;
    }

    public Cidade() {
        this.nome_cidade = new String();
        this.id_cidade = 0;
        this.estado = new Estado();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }
    
}
