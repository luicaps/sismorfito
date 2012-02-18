/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

/**
 *
 * @author Moises
 */
public class FitoP {

    Filo filo;
    Classe classe;
    Ordem ordem;
    Genero genero;
    Familia familia;
    Especie especie;
    Usuario usuario;

    public FitoP(Filo filo, Classe classe, Ordem ordem, Genero genero, Familia familia, Especie especie, Usuario usuario) {
        this.filo = filo;
        this.classe = classe;
        this.ordem = ordem;
        this.genero = genero;
        this.familia = familia;
        this.especie = especie;
        this.usuario = usuario;
    }

    public FitoP() {
        this.filo = new Filo();
        this.classe = new Classe();
        this.ordem = new Ordem();
        this.genero = new Genero();
        this.familia = new Familia();
        this.especie = new Especie();
        this.usuario = new Usuario();
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Filo getFilo() {
        return filo;
    }

    public void setFilo(Filo filo) {
        this.filo = filo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
