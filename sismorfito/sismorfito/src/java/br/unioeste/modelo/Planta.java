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
    Pos pos;
    ArrayList<Integer> plfoto;

    public Planta(int id_planta, String retirada, TVege tvege, Pos pos, ArrayList<Integer> fotos) {
        this.id_planta = id_planta;
        this.retirada = retirada;
        this.tvege = tvege;
        this.pos = pos;
        this.plfoto = fotos;
    }

    public Planta() {
        this.id_planta = 0;
        this.retirada = new String();
        this.tvege = new TVege();
        this.pos = new Pos();
        this.plfoto = new ArrayList();
    }

    public ArrayList<Integer> getFotos() {
        return plfoto;
    }

    public void setFotos(ArrayList<Integer> fotos) {
        this.plfoto = fotos;
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

    public ArrayList<Integer> getPlfoto() {
        return plfoto;
    }

    public void setPlfoto(ArrayList<Integer> plfoto) {
        this.plfoto = plfoto;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }
    
}
