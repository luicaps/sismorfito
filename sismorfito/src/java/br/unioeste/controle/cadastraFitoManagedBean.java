/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import br.unioeste.persistencia.ClasseFacade;
import br.unioeste.persistencia.EspecieFacade;
import br.unioeste.persistencia.EstadoFacade;
import br.unioeste.persistencia.FamiliaFacade;
import br.unioeste.persistencia.FiloFacade;
import br.unioeste.persistencia.GeneroFacade;
import br.unioeste.persistencia.OrdemFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Moises
 */
@ManagedBean
@SessionScoped
public class cadastraFitoManagedBean implements Serializable {

	//Diretorios para Foto
	private static final String dirFPFoto = "FPFoto\\";
	private static final String dirPlFoto = "PlFoto\\";
	private static final String dirPPFoto = "PPFoto\\";
	//Selects
	String filo;
	String classe;
	String ordem;
	String familia;
	String genero;
	String especie;
	String Fitolito;
	double ValDeta;
	String retirada;
	boolean disponivel;
	String estado;
	String cidade;
	//Lists
	int current;
	ArrayList<String> listFilo;		//1
	ArrayList<String> listClasse;	//2
	ArrayList<String> listOrdem;	//3
	ArrayList<String> listFamilia;	//4
	ArrayList<String> listGenero;	//5
	ArrayList<String> listEspecie;	//6
	ArrayList<String> listFitolito;	//7
	ArrayList<String> listEstado;	//8
	ArrayList<String> listCidade;	//9
	//Saída para o BD
	Fitop fitop;
	Usuario responsavel;
	//Controle
	boolean isReady;
	String uploadCase;
	int uCase;
	//Facades
	@EJB
	private FiloFacade ejbFiloFacade;
	@EJB
	private ClasseFacade ejbClasseFacade;
	@EJB
	private OrdemFacade ejbOrdemFacade;
	@EJB
	private EstadoFacade ejbEstadoFacade;
	@EJB
	private FamiliaFacade ejbfFamiliaFacade;
	@EJB
	private GeneroFacade ejbGeneroFacade;
	@EJB
	private EspecieFacade ejbEspecieFacade;

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
		ValDeta = 0.0;
		retirada = new String();
		disponivel = false;
		estado = new String();
		cidade = new String();

		listFilo = new ArrayList();
		listClasse = new ArrayList();
		listOrdem = new ArrayList();
		listFamilia = new ArrayList();
		listGenero = new ArrayList();
		listEspecie = new ArrayList();
		listFitolito = new ArrayList();
		listEstado = new ArrayList();
		listCidade = new ArrayList();

		fitop = new Fitop();

		isReady = false;
		uploadCase = "Fotos do Morfotipo do Fitólito";
		uCase = 0;
	}

	@PostConstruct
	public void init() {
		fetchAllFilos();
		fetchAllEstado();
		LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
		responsavel = lg.getUsuario();
	}

	public FiloFacade getEjbFiloFacade() {
		return ejbFiloFacade;
	}

	public void setEjbFiloFacade(FiloFacade ejbFiloFacade) {
		this.ejbFiloFacade = ejbFiloFacade;
	}

	public ClasseFacade getEjbClasseFacade() {
		return ejbClasseFacade;
	}

	public void setEjbClasseFacade(ClasseFacade ejbClasseFacade) {
		this.ejbClasseFacade = ejbClasseFacade;
	}

	public OrdemFacade getEjbOrdemFacade() {
		return ejbOrdemFacade;
	}

	public void setEjbOrdemFacade(OrdemFacade ejbOrdemFacade) {
		this.ejbOrdemFacade = ejbOrdemFacade;
	}

	public FamiliaFacade getEjbfFamiliaFacade() {
		return ejbfFamiliaFacade;
	}

	public void setEjbfFamiliaFacade(FamiliaFacade ejbfFamiliaFacade) {
		this.ejbfFamiliaFacade = ejbfFamiliaFacade;
	}

	public GeneroFacade getEjbGeneroFacade() {
		return ejbGeneroFacade;
	}

	public void setEjbGeneroFacade(GeneroFacade ejbGeneroFacade) {
		this.ejbGeneroFacade = ejbGeneroFacade;
	}

	public EspecieFacade getEjbEspecieFacade() {
		return ejbEspecieFacade;
	}

	public void setEjbEspecieFacade(EspecieFacade ejbEspecieFacade) {
		this.ejbEspecieFacade = ejbEspecieFacade;
	}

	public EstadoFacade getEjbEstadoFacade() {
		return ejbEstadoFacade;
	}

	public void setEjbEstadoFacade(EstadoFacade ejbEstadoFacade) {
		this.ejbEstadoFacade = ejbEstadoFacade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public String getRetirada() {
		return retirada;
	}

	public void setRetirada(String retirada) {
		this.retirada = retirada;
	}

	public double getValDeta() {
		return ValDeta;
	}

	public void setValDeta(double ValDeta) {
		this.ValDeta = ValDeta;
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

	public ArrayList<String> getListEstado() {
		return listEstado;
	}

	public void setListEstado(ArrayList<String> listEstado) {
		this.listEstado = listEstado;
	}

	public ArrayList<String> getListCidade() {
		return listCidade;
	}

	public void setListCidade(ArrayList<String> listCidade) {
		this.listCidade = listCidade;
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
		if (filo.equals("")) {
			return false;
		} else {
			return true;
		}
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
			System.out.println(filo1.getNomeFilo());
		}
	}

	public void fetchAllClasses() {
		List<Classe> aux = ejbFiloFacade.findClassesFromFilo(filo);
		listClasse = new ArrayList();
		if (!(aux == null)) {
			for (Classe classe1 : aux) {
				this.listClasse.add(classe1.getNomeClasse());
			}
		}
		classe = "";
	}

	public void fetchAllOrdens() {
		List<Ordem> aux = ejbClasseFacade.findOrdemFromClasse(classe);
		listOrdem = new ArrayList();
		if (!(aux == null)) {
			for (Ordem ordem1 : aux) {
				this.listOrdem.add(ordem1.getNomeOrdem());
			}
		}
		ordem = "";
	}

	public void fetchAllFamilias() {
		List<Familia> aux = ejbOrdemFacade.findFamiliasFromOrdem(ordem);
		listFamilia = new ArrayList();
		if (!(aux == null)) {
			for (Familia familia1 : aux) {
				this.listFamilia.add(familia1.getNomeFamilia());
			}
		}
		familia = "";
	}

	public void fetchAllGenero() {
		List<Genero> aux = ejbfFamiliaFacade.findGenerosFromFamilia(familia);
		listGenero = new ArrayList();
		if (!(aux == null)) {
			for (Genero genero1 : aux) {
				this.listGenero.add(genero1.getNomeGenero());
			}
		}
		genero = "";
	}

	public void fetchAllEspecie() {
		List<Especie> aux = ejbGeneroFacade.findEspeciesFromGenero(genero);
		listEspecie = new ArrayList();
		if (!(aux == null)) {
			for (Especie especie1 : aux) {
				this.listEspecie.add(especie1.getNomeEspecie());
			}
		}
		especie = "";
	}

	public void fetchAllFitolito() {
		List<Fitop> aux = ejbEspecieFacade.findFitopFromEspecie(especie);
		listFitolito = new ArrayList();
		if (!(aux == null)) {
			for (Fitop fitop1 : aux) {
				this.listFitolito.add(fitop1.getNomeFp());
			}
		}
		Fitolito = "";
		isReady = true;
	}

	public void fetchAllEstado() {
		List<Estado> oi = ejbEstadoFacade.findAll();
		for (Estado estado1 : oi) {
			listEstado.add(estado1.getUf());
		}
	}

	public void fetchAllCidade() {
		System.out.println("Estado: " + estado);
		List<Cidade> aux = ejbEstadoFacade.findCidadesFromEstado(estado);
		listCidade = new ArrayList();
		for (Cidade cidade1 : aux) {
			listCidade.add(cidade1.getCidade());
		}
		cidade = "";
	}

	public ArrayList<String> completeFilo(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listFilo) {
			if (string.equalsIgnoreCase(query)) {
				saida.add(string);
			} else {
				if (string.length() >= query.length()) {
					if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
						saida.add(string);
					}
				}
			}
		}
		return saida;
	}

	public ArrayList<String> completeClasse(String query) {
		System.out.println("chamou o metodo do piru d coco");

		ArrayList<String> saida = new ArrayList();
		if (!filo.equals(null) || !filo.equals("")) {
			listClasse = new ArrayList();
			List<Classe> aux = ejbFiloFacade.findClassesFromFilo(filo);
			if (!(aux == null)) {
				for (Classe classe1 : aux) {
					this.listClasse.add(classe1.getNomeClasse());
					System.out.println(classe1.getNomeClasse());
				}
			}

			for (String string : listClasse) {
				if (string.equalsIgnoreCase(query)) {
					saida.add(string);
				} else {
					if (string.length() >= query.length()) {
						if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
							saida.add(string);
						}
					}
				}
			}
		}
		return saida;
	}

	public ArrayList<String> completeOrdem(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listOrdem) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

	public ArrayList<String> completeFamilia(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listFamilia) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

	public ArrayList<String> completeGenero(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listGenero) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

	public ArrayList<String> completeEspecie(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listEspecie) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

	public ArrayList<String> completeFitolito(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listFitolito) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

	public ArrayList<String> completeCidade(String query) {
		ArrayList<String> saida = new ArrayList();
		for (String string : listCidade) {
			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
				saida.add(string);
			}
		}
		return saida;
	}

//	public ArrayList<String> completeEstado(String query) {
//		ArrayList<String> saida = new ArrayList();
//		for (String string : listEstado) {
//			if (string.substring(0, query.length()).equalsIgnoreCase(query)) {
//				saida.add(string);
//			}
//		}
//		return saida;
//	}
	public void findFitoP() {


		/*
		 * Parada da Date
		 */
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dt = (Date) formatter.parse(retirada);
		} catch (ParseException ex) {
			Logger.getLogger(cadastraFitoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		//        //Filo
		//        Filo f = new Filo();
		//        f.setNome_filo(filo);
		//        //Classe
		//        Classe c = new Classe();
		//        c.setNome_classe(classe);
		//        //Ordem
		//        Ordem o = new Ordem();
		//        o.setNome_ordem(ordem);
		//        //Genero
		//        Genero g = new Genero();
		//        g.setNome_genero(genero);
		//        //Familia
		//        Familia fa = new Familia();
		//        fa.setNome_familia(familia);
		//        //Especie
		//        Especie e = new Especie();
		//        e.setNome_especie(especie);
		//        float valdelta = (float) 1.56;
		//        e.setValdelta(valdelta);
		//        //Planta
		//        Planta planta = new Planta();
		//        planta.setRetirada("10-10-2010");
		//        TVege tvege = new TVege();
		//        tvege.setNome_tvege("Vegetacao da loucura");
		//        planta.setTvege(tvege);
		//        Cidade cidade = new Cidade();
		//        cidade.setNome_cidade("Cidade1");
		//        Estado estado = new Estado();
		//        estado.setNome_estado("Parana");
		//        estado.setUF("PR");
		//        cidade.setEstado(estado);
		//        Pos pos = new Pos();
		//        pos.setCidade(cidade);
		//        pos.setComentario("Comentario sobre a pos");
		//        pos.setLatitude(10);
		//        pos.setLongitude(15);
		//        planta.setPos(pos);
		//        ArrayList<Integer> foto = new ArrayList();
		//        int i = 1;
		//        foto.add(i);
		//        planta.setFotos(foto);
		//        //PPlanta
		//        PPlanta pplanta = new PPlanta();
		//        pplanta.setNome_pplanta("PPlanta1");
		//        pplanta.setPpfoto(foto);
		//        //FitoP
		//        fitop.setFilo(f);
		//        fitop.setClasse(c);
		//        fitop.setOrdem(o);
		//        fitop.setGenero(g);
		//        fitop.setFamilia(fa);
		//        fitop.setEspecie(e);
		//        fitop.setPlanta(planta);
		//        fitop.setPplanta(pplanta);
		//        fitop.setNome_fp(this.Fitolito);
		//        isReady = true;
		//        isReady = true;
	}

	/*
	 *
	 * Upload Page Methods
	 *
	 */
	public String getUploadCase() {
		return uploadCase;
	}

	public void setUploadCase(String uploadCase) {
		this.uploadCase = uploadCase;
	}

	public boolean changeUploadCaseFPFoto() {
		if (uCase == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean changeUploadCasePlFoto() {
		if (uCase == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean changeUploadCasePPFoto() {
		if (uCase == 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isVoltar() {
		if (uCase > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAvancar() {
		if (uCase < 2) {
			return true;
		} else {
			return false;
		}
	}

	public void handleAvancar() {
		uCase++;
		if (uCase == 1) {
			changeUploadStringPlFoto();
		}
		if (uCase == 2) {
			changeUploadStringPPFoto();
		}
	}

	public void handleVoltar() {
		uCase--;
		if (uCase == 0) {
			changeUploadStringFPFoto();
		}
		if (uCase == 1) {
			changeUploadStringPlFoto();
		}
	}

	public void changeUploadStringFPFoto() {
		uploadCase = "Fotos do Morfotipo do Fitólito";
	}

	public void changeUploadStringPlFoto() {
		uploadCase = "Fotos da planta extraída";
	}

	public void changeUploadStringPPFoto() {
		uploadCase = "Fotos da parte da planta extraída";
	}

	public void handleFileUploadFPFoto(FileUploadEvent event) {
		int fNome = 5;//Recebe o valor para a proxima imagem
		String ext = event.getFile().getFileName().substring((event.getFile().getFileName().length() - 4), (event.getFile().getFileName().length()));

		try {

			File saida = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + dirFPFoto + fNome + ext);
			if (!saida.exists()) {
				saida.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(saida);
			byte[] buffer = new byte[50];
			int bulk;
			InputStream is = event.getFile().getInputstream();

			while (true) {
				bulk = is.read(buffer);
				if (bulk < 0) {
					break;
				}
				fos.write(buffer, 0, bulk);
				fos.flush();
			}

			fos.close();
			is.close();

			FacesMessage msg = new FacesMessage("O arquivo" + event.getFile().getFileName() + " foi Enviado com sucesso...");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro:", "Arquivo " + event.getFile().getFileName() + " não foi enviado corretamente.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public void handleFileUploadPlFoto(FileUploadEvent event) {
		int fNome = 5;//Recebe o valor para a proxima imagem
		String ext = event.getFile().getFileName().substring((event.getFile().getFileName().length() - 4), (event.getFile().getFileName().length()));

		try {

			File saida = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + dirPlFoto + fNome + ext);
			if (!saida.exists()) {
				saida.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(saida);
			byte[] buffer = new byte[50];
			int bulk;
			InputStream is = event.getFile().getInputstream();

			while (true) {
				bulk = is.read(buffer);
				if (bulk < 0) {
					break;
				}
				fos.write(buffer, 0, bulk);
				fos.flush();
			}

			fos.close();
			is.close();

			FacesMessage msg = new FacesMessage("O arquivo" + event.getFile().getFileName() + " foi Enviado com sucesso...");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro:", "Arquivo " + event.getFile().getFileName() + " não foi enviado corretamente.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public void handleFileUploadPPFoto(FileUploadEvent event) {
		int fNome = 5;//Recebe o valor para a proxima imagem
		String ext = event.getFile().getFileName().substring((event.getFile().getFileName().length() - 4), (event.getFile().getFileName().length()));

		try {

			File saida = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + dirPPFoto + fNome + ext);
			if (!saida.exists()) {
				saida.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(saida);
			byte[] buffer = new byte[50];
			int bulk;
			InputStream is = event.getFile().getInputstream();

			while (true) {
				bulk = is.read(buffer);
				if (bulk < 0) {
					break;
				}
				fos.write(buffer, 0, bulk);
				fos.flush();
			}

			fos.close();
			is.close();

			FacesMessage msg = new FacesMessage("O arquivo" + event.getFile().getFileName() + " foi Enviado com sucesso...");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {

			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro:", "Arquivo " + event.getFile().getFileName() + " não foi enviado corretamente.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
}
