/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moises
 */
@ManagedBean
@ViewScoped
public class pesquisaManagedBean {

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
    ArrayList<String> urlFPFoto;
    ArrayList<String> urlPPFoto;
    ArrayList<String> urlPlFoto;
    FitoP fitop;
    //Controle
    boolean isReady;

    /**
     * Creates a new instance of pesquisaManagedBean
     */
    public pesquisaManagedBean() {
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
        urlFPFoto = new ArrayList();
        urlPPFoto = new ArrayList();
        urlPlFoto = new ArrayList();
        fitop = new FitoP();

        isReady = false;

        urlFPFoto.add("1");
        urlFPFoto.add("2");
        urlPPFoto.add("1");
        urlPPFoto.add("2");
        urlPlFoto.add("1");
        urlPlFoto.add("2");
        fetchAllFilos();
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
        isReady = true;
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

    public ArrayList<String> getUrlFPFoto() {
        return urlFPFoto;
    }

    public void setUrlFPFoto(ArrayList<String> urlFPFoto) {
        this.urlFPFoto = urlFPFoto;
    }

    public ArrayList<String> getUrlPPFoto() {
        return urlPPFoto;
    }

    public void setUrlPPFoto(ArrayList<String> urlPPFoto) {
        this.urlPPFoto = urlPPFoto;
    }

    public ArrayList<String> getUrlPlFoto() {
        return urlPlFoto;
    }

    public void setUrlPlFoto(ArrayList<String> urlPlFoto) {
        this.urlPlFoto = urlPlFoto;
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
        isReady = false;
    }

    public void fetchAllClasses() {
        listClasse.clear();
        this.listClasse.add("Classe1");
        this.listClasse.add("Classe2");
        this.listClasse.add("Classe3");
        this.listClasse.add("Classe4");
        listOrdem.clear();
        listFamilia.clear();
        listGenero.clear();
        listEspecie.clear();
        listFitolito.clear();
        isReady = false;
    }

    public void fetchAllOrdens() {
        listOrdem.clear();
        this.listOrdem.add("Ordem1");
        this.listOrdem.add("Ordem2");
        this.listOrdem.add("Ordem3");
        this.listOrdem.add("Ordem4");
        listFamilia.clear();
        listGenero.clear();
        listEspecie.clear();
        listFitolito.clear();
        isReady = false;
    }

    public void fetchAllFamilias() {
        listFamilia.clear();
        this.listFamilia.add("Familia1");
        this.listFamilia.add("Familia2");
        this.listFamilia.add("Familia3");
        this.listFamilia.add("Familia4");
        listGenero.clear();
        listEspecie.clear();
        listFitolito.clear();
        isReady = false;
    }

    public void fetchAllGenero() {
        listGenero.clear();
        this.listGenero.add("Genero1");
        this.listGenero.add("Genero2");
        this.listGenero.add("Genero3");
        this.listGenero.add("Genero4");
        listEspecie.clear();
        listFitolito.clear();
        isReady = false;
    }

    public void fetchAllEspecie() {
        listEspecie.clear();
        this.listEspecie.add("Especie1");
        this.listEspecie.add("Especie2");
        this.listEspecie.add("Especie3");
        this.listEspecie.add("Especie4");
        listFitolito.clear();
        isReady = false;
    }

    public void fetchAllFitolito() {
        listFitolito.clear();
        this.listFitolito.add("Fitolito1");
        this.listFitolito.add("Fitolito2");
        this.listFitolito.add("Fitolito3");
        this.listFitolito.add("Fitolito4");
    }

    public void findFitoP() {
        //Filo
        Filo f = new Filo();
        f.setNome_filo(filo);
        System.out.println(filo);
        //Classe
        Classe c = new Classe();
        c.setNome_classe(classe);
        System.out.println(classe);
        //Ordem
        Ordem o = new Ordem();
        o.setNome_ordem(ordem);
        System.out.println(ordem);
        //Genero
        Genero g = new Genero();
        g.setNome_genero(genero);
        System.out.println(genero);
        //Familia
        Familia fa = new Familia();
        fa.setNome_familia(familia);
        System.out.println(familia);
        //Especie
        Especie e = new Especie();
        e.setNome_especie(especie);
        float valdelta = (float) 1.56;
        e.setValdelta(valdelta);
        System.out.println(especie);
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
        System.out.println(Fitolito);
        isReady = true;
    }
}
