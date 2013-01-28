/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import br.unioeste.persistencia.CidadeFacade;
import br.unioeste.persistencia.ClasseFacade;
import br.unioeste.persistencia.CsoloFacade;
import br.unioeste.persistencia.EspecieFacade;
import br.unioeste.persistencia.EstadoFacade;
import br.unioeste.persistencia.FamiliaFacade;
import br.unioeste.persistencia.FiloFacade;
import br.unioeste.persistencia.FitopFacade;
import br.unioeste.persistencia.FpfotoFacade;
import br.unioeste.persistencia.GeneroFacade;
import br.unioeste.persistencia.OrdemFacade;
import br.unioeste.persistencia.PlantaFacade;
import br.unioeste.persistencia.PosFacade;
import br.unioeste.persistencia.PplantaFacade;
import br.unioeste.persistencia.TvegeFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    String retirada;
    boolean disponivel;
    String estado;
    String cidade;
    String tvege;
    String latitude;
    String longitude;
    String partePlanta;
    String csolo;
    //Cadastro
    String nfilo;
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
    ArrayList<String> listTvege;
    ArrayList<String> listCsolo;
    //Models
    List<Planta> listPlanta;
    List<Pplanta> listPplanta;
    List<Fitop> listFitop;
    Planta selectPlanta;
    Pplanta selectPplanta;
    Fitop selectFitop;
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
    private FamiliaFacade ejbFamiliaFacade;
    @EJB
    private GeneroFacade ejbGeneroFacade;
    @EJB
    private EspecieFacade ejbEspecieFacade;
    @EJB
    private TvegeFacade ejbTvegeFacade;
    @EJB
    private FitopFacade ejbFitopFacade;
    @EJB
    private CidadeFacade ejbCidadeFacade;
    @EJB
    private PosFacade ejbPosFacade;
    @EJB
    private PlantaFacade ejbPlantaFacade;
    @EJB
    private PplantaFacade ejbPplantaFacade;
    @EJB
    private CsoloFacade ejbCsoloFacade;
    @EJB
    private FpfotoFacade ejbFpfotoFacade;

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
        retirada = new String();
        disponivel = false;
        estado = new String();
        cidade = new String();
        tvege = new String();
        latitude = new String();
        longitude = new String();
        partePlanta = new String();
        csolo = new String();

        listFilo = new ArrayList();
        listClasse = new ArrayList();
        listOrdem = new ArrayList();
        listFamilia = new ArrayList();
        listGenero = new ArrayList();
        listEspecie = new ArrayList();
        listFitolito = new ArrayList();
        listEstado = new ArrayList();
        listCidade = new ArrayList();
        listTvege = new ArrayList();
        listCsolo = new ArrayList();

        fitop = new Fitop();

        isReady = false;
        uploadCase = "Fotos do Morfotipo do Fitólito";
        uCase = 0;
    }

    @PostConstruct
    public void init() {
        fetchAllFilos();
        fetchAllEstado();
        fetchAllTVege();
        fetchAllCsolo();
        LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        responsavel = lg.getUsuario();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTvege() {
        return tvege;
    }

    public void setTvege(String tvege) {
        this.tvege = tvege;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPartePlanta() {
        return partePlanta;
    }

    public void setPartePlanta(String partePlanta) {
        this.partePlanta = partePlanta;
    }

    public String getCsolo() {
        return csolo;
    }

    public void setCsolo(String csolo) {
        this.csolo = csolo;
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

    public ArrayList<String> getListTvege() {
        return listTvege;
    }

    public void setListTvege(ArrayList<String> listTvege) {
        this.listTvege = listTvege;
    }

    public ArrayList<String> getListCsolo() {
        return listCsolo;
    }

    public void setListCsolo(ArrayList<String> listCsolo) {
        this.listCsolo = listCsolo;
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

    public List<Fitop> getListFitop() {
        return listFitop;
    }

    public void setListFitop(List<Fitop> listFitop) {
        this.listFitop = listFitop;
    }

    public List<Planta> getListPlanta() {
        return listPlanta;
    }

    public void setListPlanta(List<Planta> listPlanta) {
        this.listPlanta = listPlanta;
    }

    public List<Pplanta> getListPplanta() {
        return listPplanta;
    }

    public void setListPplanta(List<Pplanta> listPplanta) {
        this.listPplanta = listPplanta;
    }

    public Planta getSelectPlanta() {
        return selectPlanta;
    }

    public void setSelectPlanta(Planta selectPlanta) {
        this.selectPlanta = selectPlanta;
    }

    public Pplanta getSelectPplanta() {
        return selectPplanta;
    }

    public void setSelectPplanta(Pplanta selectPplanta) {
        this.selectPplanta = selectPplanta;
    }

    public Fitop getSelectFitop() {
        return selectFitop;
    }

    public void setSelectFitop(Fitop selectFitop) {
        this.selectFitop = selectFitop;
    }

    public String getNfilo() {
        return nfilo;
    }

    public void setNfilo(String nfilo) {
        this.nfilo = nfilo;
    }

    public Fitop getFitop() {
        return fitop;
    }

    public void setFitop(Fitop fitop) {
        this.fitop = fitop;
    }

    public String newFilo() {
        Filo f = new Filo(ejbFiloFacade.nextId());
        f.setNomeFilo(nfilo);
        ejbFiloFacade.create(f);
        fetchAllFilos();
        for (String string : listFilo) {
            if (string.compareTo(nfilo) == 0) {
                filo = string;
            }
        }
        return "filocad.hide()";
    }

    public void cancelaFilo() {
        System.out.println("PIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRUPIRU");
        System.out.println("vc deveria estar escrevendo isso, ok? ok?");
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
        List<Genero> aux = ejbFamiliaFacade.findGenerosFromFamilia(familia);
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
        List<Cidade> aux = ejbEstadoFacade.findCidadesFromEstado(estado);
        listCidade = new ArrayList();
        for (Cidade cidade1 : aux) {
            listCidade.add(cidade1.getCidade());
        }
        cidade = "";
    }

    public void fetchAllTVege() {
        List<Tvege> aux = ejbTvegeFacade.findAll();
        listTvege = new ArrayList();
        for (Tvege tvege1 : aux) {
            listTvege.add(tvege1.getNomeVege());
        }
    }

    public void fetchAllCsolo() {
        List<Csolo> aux = ejbCsoloFacade.findAll();
        listCsolo = new ArrayList();
        for (Csolo csolo1 : aux) {
            listCsolo.add(csolo1.getSibcs());
        }
    }

    public boolean isAtivo(Fitop fitop) {
        if (!fitop.getDisponivel()) {
            return false;
        }
        return true;
    }

    public String changeDisponivelState(Fitop fitop) {
        System.out.println("VAMOS MUDAR O ESTADO");
        if(fitop.getDisponivel()){
            fitop.setDisponivel(false);
        } else {
            fitop.setDisponivel(true);
        }
        ejbFitopFacade.edit(fitop);
        return "../conteudo-professor/gerenciar-list-fitop.xhtml?faces-redirect=true";
    }

    public String findPlanta() {
		System.out.println("Método chamado");


        /*
         * Parada da Date
         */
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		Date dt = new Date();
//		try {
//			dt = (Date) formatter.parse(retirada);
//		} catch (ParseException ex) {
//			Logger.getLogger(cadastraFitoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//		}

        Especie e = ejbEspecieFacade.findEspecieFromName(especie);

        listPlanta = ejbEspecieFacade.findPlantaFromEspecie(especie);
        if (listPlanta == null) {
            listPlanta = new ArrayList<Planta>();
        }

        return "gerenciar-planta";
    }

    public String findPplanta() {
        if (selectPlanta == null) {
            return null;
        }
        listPplanta = ejbPlantaFacade.findPplantaFromPlanta(selectPlanta.getIdPlanta());
        if (listPplanta == null) {
            listPplanta = new ArrayList<Pplanta>();
        }
        return "../conteudo-professor/gerenciar-pplanta.xhtml?faces-redirect=true";
    }

    public String findFitop() {
        if (selectPplanta == null) {
            selectPplanta = new Pplanta();
        }
        listFitop = ejbPplantaFacade.findFitopFromPplanta(selectPplanta.getIdPplanta());
        if (listFitop == null) {
            listFitop = new ArrayList<Fitop>();
        }
        return "../conteudo-professor/gerenciar-list-fitop.xhtml?faces-redirect=true";
    }

    public String createFitop() {
        Fitop fitop1 = new Fitop(ejbFitopFacade.nextId());
        fitop1.setDisponivel(disponivel);
        fitop1.setNomeFp(Fitolito);
        fitop1.setFkIdPplanta(selectPplanta);
        fitop1.setFkIdUsu(responsavel);
        ejbFitopFacade.create(fitop1);
        if (listFitop == null) {
            listFitop = new ArrayList<Fitop>();
        }
        listFitop.add(fitop1);
        return "../conteudo-professor/gerenciar-list-fitop.xhtml?faces-redirect=true";
    }

    public String changeFitop() {
        ejbFitopFacade.edit(selectFitop);
        selectFitop = new Fitop();
        return "../conteudo-professor/gerenciar-list-fitop.xhtml?faces-redirect=true";
    }

    public void handleFileUploadFPFoto(FileUploadEvent event) {
        long fNome = ejbFpfotoFacade.nextId();
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

            Fpfoto fpfoto = new Fpfoto(fNome);
            fpfoto.setFkIdFitop(selectFitop);
            ejbFpfotoFacade.create(fpfoto);
            selectFitop.getFpfotoList().add(fpfoto);
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
