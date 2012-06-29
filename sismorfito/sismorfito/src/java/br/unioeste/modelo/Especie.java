/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author mah_gia
 */
public class Especie {
    
    int id_especie;
    String nome_especie;
    float valdelta;

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public String getNome_especie() {
        return nome_especie;
    }

    public void setNome_especie(String nome_especie) {
        this.nome_especie = nome_especie;
    }

    public float getValdelta() {
        return valdelta;
    }

    public void setValdelta(float valdelta) {
        this.valdelta = valdelta;
    }
    
}
