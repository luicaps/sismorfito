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
public class Planta {

    int id_planta;
    String retirada;
    TVege tvege;
    ArrayList<Integer> fotos;

    public Planta(int id_planta, String retirada, TVege tvege, ArrayList<Integer> fotos) {
        this.id_planta = id_planta;
        this.retirada = retirada;
        this.tvege = tvege;
        this.fotos = fotos;
    }

    public Planta() {
        this.id_planta = 0;
        this.retirada = new String();
        this.tvege = new TVege();
        this.fotos = new ArrayList();
    }

    public ArrayList<Integer> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Integer> fotos) {
        this.fotos = fotos;
    }

    public int getId_planta() {
        return id_planta;
    }

    public void setId_planta(int id_planta) {
        this.id_planta = id_planta;
    }

    public String getRetirada() {
        return retirada;
    }

    public void setRetirada(String retirada) {
        this.retirada = retirada;
    }

    public TVege getTvege() {
        return tvege;
    }

    public void setTvege(TVege tvege) {
        this.tvege = tvege;
    }
    
}
