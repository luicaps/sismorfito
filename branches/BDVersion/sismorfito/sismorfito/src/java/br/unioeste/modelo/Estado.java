/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class Estado {
    int id_estado;
    String nome_estado;
    String UF;

    public Estado(int id_estado, String nome_estado, String UF) {
        this.id_estado = id_estado;
        this.nome_estado = nome_estado;
        this.UF = UF;
    }

    public Estado() {
        this.id_estado = 0;
        this.nome_estado = new String();
        this.UF = new String();
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNome_estado() {
        return nome_estado;
    }

    public void setNome_estado(String nome_estado) {
        this.nome_estado = nome_estado;
    }
    
}
