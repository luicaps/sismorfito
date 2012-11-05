/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import br.unioeste.persistencia.ClasseFacade;
import br.unioeste.persistencia.EspecieFacade;
import br.unioeste.persistencia.FamiliaFacade;
import br.unioeste.persistencia.FiloFacade;
import br.unioeste.persistencia.FitopFacade;
import br.unioeste.persistencia.GeneroFacade;
import br.unioeste.persistencia.OrdemFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Moises
 */
@ManagedBean
@ViewScoped
public class pesquisaManagedBean implements Serializable {

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
	Fitop fitop;
	//DB
	@EJB
	private FiloFacade ejbFiloFacade;
	@EJB
	private ClasseFacade ejbClasseFacade;
	@EJB
	private OrdemFacade ejbOrdemFacade;
	@EJB
	private FamiliaFacade ejbFamiliaFacade;
	@EJB
	private GeneroFacade ejbGeneroFacade;
	@EJB
	private EspecieFacade ejbEspecieFacade;
	@EJB
	private FitopFacade ejbFitopFacade;
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
		fitop = new Fitop();

		isReady = false;

//		urlFPFoto.add("1");
//		urlFPFoto.add("2");
		urlPPFoto.add("1");
		urlPPFoto.add("2");
		urlPlFoto.add("1");
		urlPlFoto.add("2");
	}

	@PostConstruct
	public void init() {
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

	public Fitop getFitop() {
		return fitop;
	}

	public void setFitop(Fitop fitop) {
		this.fitop = fitop;
	}

	public void fetchAllFilos() {
		List<Filo> aux = ejbFiloFacade.findAll();
		for (Filo filo1 : aux) {
			this.listFilo.add(filo1.getNomeFilo());
		}
		isReady = false;
	}

	public void fetchAllClasses() {
		listClasse.clear();
		List<Classe> aux = ejbFiloFacade.findClassesFromFilo(filo);
		listClasse = new ArrayList();
		if (!(aux == null)) {
			for (Classe classe1 : aux) {
				this.listClasse.add(classe1.getNomeClasse());
			}
		}
		classe = "";
		listOrdem.clear();
		listFamilia.clear();
		listGenero.clear();
		listEspecie.clear();
		listFitolito.clear();
		isReady = false;
	}

	public void fetchAllOrdens() {
		listOrdem.clear();
		List<Ordem> aux = ejbClasseFacade.findOrdemFromClasse(classe);
		listOrdem = new ArrayList();
		if (!(aux == null)) {
			for (Ordem ordem1 : aux) {
				this.listOrdem.add(ordem1.getNomeOrdem());
			}
		}
		ordem = "";
		listFamilia.clear();
		listGenero.clear();
		listEspecie.clear();
		listFitolito.clear();
		isReady = false;
	}

	public void fetchAllFamilias() {
		listFamilia.clear();
		List<Familia> aux = ejbOrdemFacade.findFamiliasFromOrdem(ordem);
		listFamilia = new ArrayList();
		if (!(aux == null)) {
			for (Familia familia1 : aux) {
				this.listFamilia.add(familia1.getNomeFamilia());
			}
		}
		familia = "";
		listGenero.clear();
		listEspecie.clear();
		listFitolito.clear();
		isReady = false;
	}

	public void fetchAllGenero() {
		listGenero.clear();
		List<Genero> aux = ejbFamiliaFacade.findGenerosFromFamilia(familia);
		listGenero = new ArrayList();
		if (!(aux == null)) {
			for (Genero genero1 : aux) {
				this.listGenero.add(genero1.getNomeGenero());
			}
		}
		genero = "";
		listEspecie.clear();
		listFitolito.clear();
		isReady = false;
	}

	public void fetchAllEspecie() {
		listEspecie.clear();
		List<Especie> aux = ejbGeneroFacade.findEspeciesFromGenero(genero);
		listEspecie = new ArrayList();
		if (!(aux == null)) {
			for (Especie especie1 : aux) {
				this.listEspecie.add(especie1.getNomeEspecie());
			}
		}
		especie = "";
		listFitolito.clear();
		isReady = false;
	}

	public void fetchAllFitolito() {
		listFitolito.clear();
		List<Fitop> aux = ejbEspecieFacade.findFitopFromEspecie(especie);
		listFitolito = new ArrayList();
		if (!(aux == null)) {
			for (Fitop fitop1 : aux) {
				this.listFitolito.add(fitop1.getNomeFp());
			}
		}
		Fitolito = "";
	}

//	public void findFitoP() {
//		//Filo
//		Filo f = new Filo();
//		f.setNomeFilo(filo);
//		//Classe
//		Classe c = new Classe();
//		c.setNomeClasse(classe);
//		c.setFkIdFilo(f);
//		//Ordem
//		Ordem o = new Ordem();
//		o.setNomeOrdem(ordem);
//		o.setFkIdClasse(c);
//		//Familia
//		Familia fa = new Familia();
//		fa.setNomeFamilia(familia);
//		fa.setFkIdOrdem(o);
//		//Genero
//		Genero g = new Genero();
//		g.setNomeGenero(genero);
//		g.setFkIdFamilia(fa);
//		//Especie
//		Especie e = new Especie();
//		e.setFkIdGenero(g);
//		e.setNomeEspecie(especie);
//		double valdelta = (double) 1.56;
//		e.setValdelta(valdelta);
//		//Planta
//		Planta planta = new Planta();
//		planta.setFkIdEspecie(e);
//		planta.setRetirada(new Date());
//		Tvege tvege = new Tvege();
//		tvege.setNomeVege("Vegetacao da loucura");
//		planta.setFkIdTvege(tvege);
//		Cidade cidade = new Cidade();
//		cidade.setCidade("Cidade1");
//		Estado estado = new Estado();
//		estado.setEstado("Parana");
//		estado.setUf("PR");
//		cidade.setFkIdEstado(estado);
//		Pos pos = new Pos();
//		pos.setFkIdCidade(cidade);
//		pos.setComentario("Comentario sobre a pos");
//		pos.setLatitude("10");
//		pos.setLongitde("15");
//		planta.setFkIdPos(pos);
//		List<Plfoto> plfoto = new ArrayList();
//		plfoto.add(new Plfoto());
//		planta.setPlfotoList(plfoto);
//		//PPlanta
//		Pplanta pplanta = new Pplanta();
//		pplanta.setFkIdPlanta(planta);
//		pplanta.setNomePplanta("PPlanta1");
//		List<Ppfoto> ppfoto = new ArrayList();
//		ppfoto.add(new Ppfoto());
//		pplanta.setPpfotoList(ppfoto);
//		//FitoP
//		fitop.setFkIdPplanta(pplanta);
//		fitop.setNomeFp(this.Fitolito);
//		List<Fpfoto> fpfoto = new ArrayList();
//		fpfoto.add(new Fpfoto());
//		fitop.setFpfotoList(fpfoto);
//		isReady = true;
//	}
	public void findFitoP() {
		fitop = ejbFitopFacade.findByName(Fitolito);
		isReady = true;
		urlFPFoto = new ArrayList();
		for (Fpfoto fpfoto : fitop.getFpfotoList()) {
			urlFPFoto.add(Long.toString(fpfoto.getFoto()));
		}
		urlPlFoto = new ArrayList();
		for (Plfoto plfoto : fitop.getFkIdPplanta().getFkIdPlanta().getPlfotoList()) {
			urlPlFoto.add(Long.toString(plfoto.getFoto()));
		}
		urlPPFoto = new ArrayList();
		for (Ppfoto ppfoto : fitop.getFkIdPplanta().getPpfotoList()) {
			urlPPFoto.add(Long.toString(ppfoto.getFoto()));
		}
	}

	public boolean isFpfoto() {
		if (urlFPFoto.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPlfoto() {
		if (urlPlFoto.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPpfoto() {
		if (urlPPFoto.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
