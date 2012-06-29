/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

import java.util.ArrayList;

/**
 *
 * @author Moises
 */
public class PPlanta {
    int id_pplanta;
    String nome_pplanta;
    ArrayList<Integer> ppfoto;

    public PPlanta(int id_pplanta, String nome_pplanta, ArrayList<Integer> ppfoto) {
        this.id_pplanta = id_pplanta;
        this.nome_pplanta = nome_pplanta;
        this.ppfoto = ppfoto;
    }

    public PPlanta() {
        this.id_pplanta = 0;
        this.nome_pplanta = new String();
        this.ppfoto = new ArrayList();
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

    public ArrayList<Integer> getPpfoto() {
        return ppfoto;
    }

    public void setPpfoto(ArrayList<Integer> ppfoto) {
        this.ppfoto = ppfoto;
    }
    
}
