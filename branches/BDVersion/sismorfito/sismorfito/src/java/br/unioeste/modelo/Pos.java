/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class Pos {
    double latitude;
    double longitude;
    String comentario;
    Cidade cidade;
    CSolo csolo;

    public Pos(double latitude, double longitude, String comentario, Cidade cidade, CSolo csolo) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.comentario = comentario;
        this.cidade = cidade;
        this.csolo = csolo;
    }

    public Pos() {
        this.latitude = 0;
        this.longitude = 0;
        this.comentario = new String();
        this.cidade = new Cidade();
        this.csolo = new CSolo();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CSolo getCsolo() {
        return csolo;
    }

    public void setCsolo(CSolo csolo) {
        this.csolo = csolo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
