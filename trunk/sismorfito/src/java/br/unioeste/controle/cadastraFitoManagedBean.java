/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moises
 */
@ManagedBean
@ViewScoped
public class cadastraFitoManagedBean {

    //Selects
    String filo;
    String classe;
    String ordem;
    String familia;
    String genero;
    String especie;
    String Fitolito;
    //Lists
    ArrayList<String> listFilo;
    ArrayList<String> listClasse;
    ArrayList<String> listOrdem;
    ArrayList<String> listFamilia;
    ArrayList<String> listGenero;
    ArrayList<String> listEspecie;
    ArrayList<String> listFitolito;
    FitoP fitop;
    //Controle
    boolean isReady;

    /**
     * Creates a new instance of cadastraFitoManagedBean
     */
    public cadastraFitoManagedBean() {
        filo = new String();
        classe = new String();
        ordem = new String();
        familia = new String();
        genero = new String();
        especie = new String();
        Fitolito = new String();

        listFilo = new ArrayList();
        listClasse = new ArrayList();
        listOrdem = new ArrayList();
        listFamilia = new ArrayList();
        listGenero = new ArrayList();
        listEspecie = new ArrayList();
        listFitolito = new ArrayList();
        fitop = new FitoP();

        isReady = false;
        fetchAllFilos();
        fetchAllClasses();
        fetchAllOrdens();
        fetchAllGenero();
        fetchAllFamilias();
        fetchAllEspecie();
        fetchAllFitolito();
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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

    public String getFitolito() {
        return Fitolito;
    }

    public void setFitolito(String Fitolito) {
        this.Fitolito = Fitolito;
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

    public ArrayList<String> getListFitolito() {
        return listFitolito;
    }

    public void setListFitolito(ArrayList<String> listFitolito) {
        this.listFitolito = listFitolito;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public FitoP getFitop() {
        return fitop;
    }

    public void setFitop(FitoP fitop) {
        this.fitop = fitop;
    }

    public void fetchAllFilos() {
        this.listFilo.add("Filo1");
        this.listFilo.add("Filo2");
        this.listFilo.add("Filo3");
        this.listFilo.add("Filo4");
    }

    public void fetchAllClasses() {
        this.listClasse.add("Classe1");
        this.listClasse.add("Classe2");
        this.listClasse.add("Classe3");
        this.listClasse.add("Classe4");
    }

    public void fetchAllOrdens() {
        this.listOrdem.add("Ordem1");
        this.listOrdem.add("Ordem2");
        this.listOrdem.add("Ordem3");
        this.listOrdem.add("Ordem4");
    }

    public void fetchAllFamilias() {
        this.listFamilia.add("Familia1");
        this.listFamilia.add("Familia2");
        this.listFamilia.add("Familia3");
        this.listFamilia.add("Familia4");
    }

    public void fetchAllGenero() {
        this.listGenero.add("Genero1");
        this.listGenero.add("Genero2");
        this.listGenero.add("Genero3");
        this.listGenero.add("Genero4");
    }

    public void fetchAllEspecie() {
        this.listEspecie.add("Especie1");
        this.listEspecie.add("Especie2");
        this.listEspecie.add("Especie3");
        this.listEspecie.add("Especie4");
    }

    public void fetchAllFitolito() {
        this.listFitolito.add("Fitolito1");
        this.listFitolito.add("Fitolito2");
        this.listFitolito.add("Fitolito3");
        this.listFitolito.add("Fitolito4");
        isReady = true;
    }

    public void findFitoP() {
        //Filo
        Filo f = new Filo();
        f.setNome_filo(filo);
        //Classe
        Classe c = new Classe();
        c.setNome_classe(classe);
        //Ordem
        Ordem o = new Ordem();
        o.setNome_ordem(ordem);
        //Genero
        Genero g = new Genero();
        g.setNome_genero(genero);
        //Familia
        Familia fa = new Familia();
        fa.setNome_familia(familia);
        //Especie
        Especie e = new Especie();
        e.setNome_especie(especie);
        float valdelta = (float) 1.56;
        e.setValdelta(valdelta);
        //Planta
        Planta planta = new Planta();
        planta.setRetirada("10-10-2010");
        TVege tvege = new TVege();
        tvege.setNome_tvege("Vegetacao da loucura");
        planta.setTvege(tvege);
        Cidade cidade = new Cidade();
        cidade.setNome_cidade("Cidade1");
        Estado estado = new Estado();
        estado.setNome_estado("Parana");
        estado.setUF("PR");
        cidade.setEstado(estado);
        Pos pos = new Pos();
        pos.setCidade(cidade);
        pos.setComentario("Comentario sobre a pos");
        pos.setLatitude(10);
        pos.setLongitude(15);
        planta.setPos(pos);
        ArrayList<Integer> foto = new ArrayList();
        int i = 1;
        foto.add(i);
        planta.setFotos(foto);
        //PPlanta
        PPlanta pplanta = new PPlanta();
        pplanta.setNome_pplanta("PPlanta1");
        pplanta.setPpfoto(foto);
        //FitoP
        fitop.setFilo(f);
        fitop.setClasse(c);
        fitop.setOrdem(o);
        fitop.setGenero(g);
        fitop.setFamilia(fa);
        fitop.setEspecie(e);
        fitop.setPlanta(planta);
        fitop.setPplanta(pplanta);
        fitop.setNome_fp(this.Fitolito);
        fitop.setFpfoto(foto);
        isReady = true;
    }
    
    public ArrayList<String> completeFilo(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listFilo) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }

    public ArrayList<String> completeClasse(String query) {
        ArrayList<String> saida = new ArrayList();
        if(filo.compareToIgnoreCase("")==0){
            saida.add("Selecione um Filo...");
            return saida;
        }
        for (String string : listClasse) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }
    
    public ArrayList<String> completeOrdem(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listOrdem) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }
    
    public ArrayList<String> completeFamilia(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listFamilia) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }
    
    public ArrayList<String> completeGenero(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listGenero) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }
    
    public ArrayList<String> completeEspecie(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listEspecie) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }
    
    public ArrayList<String> completeFitolito(String query) {
        ArrayList<String> saida = new ArrayList();
        for (String string : listFitolito) {
            if(string.substring(0, query.length()).equalsIgnoreCase(query)){
                saida.add(string);
            }
        }
        return saida;
    }    
}
