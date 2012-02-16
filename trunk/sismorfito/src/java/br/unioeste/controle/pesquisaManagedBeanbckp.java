/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mah_gia
 */
@ManagedBean
@RequestScoped
public class pesquisaManagedBeanbckp {

    //Selects
    String filo;
    String classe;
    String ordem;
    String familia;
    String genero;
    String especie;
    
    //Lists
    ArrayList<String> listFilo;
    ArrayList<String> listClasse;
    ArrayList<String> listOrdem;
    ArrayList<String> listFamilia;
    ArrayList<String> listGenero;
    ArrayList<String> listEspecie;
    
    
    
    /**
     * Creates a new instance of pesquisaManagedBean
     */
    public pesquisaManagedBeanbckp() {
        filo = new String();
        classe = new String();
        ordem = new String();
        familia = new String();
        genero = new String();
        especie = new String();
		
        listFilo = new ArrayList();
        listClasse = new ArrayList();
        listOrdem = new ArrayList();
        listFamilia = new ArrayList();
        listGenero = new ArrayList();
        listEspecie = new ArrayList();
        fetchAllFilos();
    }

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
                this.fetchAllOrdens();
		System.out.println("setou classe;;;;;;;;;;;;;;;;");
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getFilo() {
		return filo;
	}

	public void setFilo(String filo) {
		this.filo = filo;
                this.fetchAllClasses();
		System.out.println("setou Filo;;;;;;;;;;;;;;;");
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

    public ArrayList<String> getListClasse() {
        return listClasse;
    }

    public void setListClasse(ArrayList<String> listClasse) {
        this.listClasse = listClasse;
    }

    public ArrayList<String> getListEspecie() {
        return listEspecie;
    }

    public void setListEspecie(ArrayList<String> listEspecie) {
        this.listEspecie = listEspecie;
    }

    public ArrayList<String> getListFamilia() {
        return listFamilia;
    }

    public void setListFamilia(ArrayList<String> listFamilia) {
        this.listFamilia = listFamilia;
    }

    public ArrayList<String> getListFilo() {
        return listFilo;
    }

    public void setListFilo(ArrayList<String> listFilo) {
        this.listFilo = listFilo;
    }

    public ArrayList<String> getListGenero() {
        return listGenero;
    }

    public void setListGenero(ArrayList<String> listGenero) {
        this.listGenero = listGenero;
    }

    public ArrayList<String> getListOrdem() {
        return listOrdem;
    }

    public void setListOrdem(ArrayList<String> listOrdem) {
        this.listOrdem = listOrdem;
    }
    
    public void fetchAllFilos(){
        this.listFilo.add("Filo1");
        this.listFilo.add("Filo2");
        this.listFilo.add("Filo3");
        this.listFilo.add("Filo4");
    }
    
    public void fetchAllClasses(){
        this.listClasse.add("Classe1");
        this.listClasse.add("Classe2");
        this.listClasse.add("Classe3");
        this.listClasse.add("Classe4");
    }
    
    public void fetchAllOrdens(){
        this.listOrdem.add("Ordem1");
        this.listOrdem.add("Ordem2");
        this.listOrdem.add("Ordem3");
        this.listOrdem.add("Ordem4");
		System.out.println("Chamou FetchOrdem");
    }
    
    public void fetchAllFamilias(){
        this.listFamilia.add("Familia1");
        this.listFamilia.add("Familia2");
        this.listFamilia.add("Familia3");
        this.listFamilia.add("Familia4");
    }
    
    public void fetchAllGenero(){
        this.listGenero.add("Genero1");
        this.listGenero.add("Genero2");
        this.listGenero.add("Genero3");
        this.listGenero.add("Genero4");
    }
    
    public void fetchAllEspecie(){
        this.listEspecie.add("Especie1");
        this.listEspecie.add("Especie2");
        this.listEspecie.add("Especie3");
        this.listEspecie.add("Especie4");
    }
    
}
