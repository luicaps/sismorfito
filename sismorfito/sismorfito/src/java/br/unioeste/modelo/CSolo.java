/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class CSolo {
    int id_csolo;
    String sibcs;

    public CSolo(int id_csolo, String sibcs) {
        this.id_csolo = id_csolo;
        this.sibcs = sibcs;
    }

    public CSolo() {
        this.id_csolo = 0;
        this.sibcs = new String();
    }

    public int getId_csolo() {
        return id_csolo;
    }

    public void setId_csolo(int id_csolo) {
        this.id_csolo = id_csolo;
    }

    public String getSibcs() {
        return sibcs;
    }

    public void setSibcs(String sibcs) {
        this.sibcs = sibcs;
    }
    
}
