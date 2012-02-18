/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class PPlanta {
    int id_pplanta;
    String nome_pplanta;

    public PPlanta(int id_pplanta, String nome_pplanta) {
        this.id_pplanta = id_pplanta;
        this.nome_pplanta = nome_pplanta;
    }

    public PPlanta() {
        this.id_pplanta = 0;
        this.nome_pplanta = new String();
    }

    public int getId_pplanta() {
        return id_pplanta;
    }

    public void setId_pplanta(int id_pplanta) {
        this.id_pplanta = id_pplanta;
    }

    public String getNome_pplanta() {
        return nome_pplanta;
    }

    public void setNome_pplanta(String nome_pplanta) {
        this.nome_pplanta = nome_pplanta;
    }
    
}
