/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.*;
import br.unioeste.persistencia.CidadeFacade;
import br.unioeste.persistencia.EstadoFacade;
import br.unioeste.persistencia.FitosFacade;
import br.unioeste.persistencia.FsfotoFacade;
import br.unioeste.persistencia.PosFacade;
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
 * @author Caps
 */
@ManagedBean
@SessionScoped
public class cadastraFitoSManagedBean implements Serializable {

    //dir
    private static final String dirFSFoto = "FSFoto\\";
    //resp
    Usuario responsavel;
    //Lists
    List<Fitos> listFitos;
    List<String> listCidade;
    List<String> listEstado;
    List<String> listPos;
    //Select
    String cidade;
    String estado;
    String pos;
    Fitos fitos;
    //Cads
    String retirada;
    boolean disponivel;
    @EJB
    private FitosFacade ejbFitosFacade;
    @EJB
    private CidadeFacade ejbCidadeFacade;
    @EJB
    private EstadoFacade ejbEstadoFacade;
    @EJB
    private PosFacade ejbPosFacade;
    @EJB
    private FsfotoFacade ejbFsfotoFacade;

    /**
     * Creates a new instance of cadastraFitoManagedBean
     */
    public cadastraFitoSManagedBean() {
        listCidade = null;
    }

    @PostConstruct
    public void init() {
        fetchAllFitos();
        LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        responsavel = lg.getUsuario();
    }

    public void fetchAllFitos() {
        listFitos = ejbFitosFacade.findAll();
    }

    public void fetchAllCidade() {
        List<Cidade> list = ejbEstadoFacade.findCidadesFromEstado(estado);
        listCidade = new ArrayList<String>();
        cidade = new String();
        for (Cidade cidade1 : list) {
            listCidade.add(cidade1.getCidade());
        }
    }

    public void fetchAllEstado() {
        List<Estado> list = ejbEstadoFacade.findAll();
        listEstado = new ArrayList<String>();
        estado = new String();
        for (Estado estado1 : list) {
            listEstado.add(estado1.getUf());
        }
    }

    public void fetchAllPos() {
        List<Pos> list = ejbCidadeFacade.findPosFromCidade(cidade);
        listPos = new ArrayList<String>();
        pos = new String();
        for (Pos pos1 : list) {
            listPos.add(pos1.getLatitude() + ", " + pos1.getLongitde());
        }
    }

    public String newFitos() {
        fetchAllEstado();
        return "../conteudo-professor/cadastrar-fitos.xhtml?faces-redirect=true";
    }

    public String createFitos() {
        Fitos fitos = new Fitos(ejbFitosFacade.nextId());
        fitos.setDisponivel(disponivel);
        fitos.setFkIdPos(ejbPosFacade.findCidadesFromEstado(pos));
        fitos.setFkIdUsu(responsavel);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(retirada);
        try {
            data = df.parse(retirada);
        } catch (ParseException ex) {
            Logger.getLogger(cadastraFitoSManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        fitos.setRetirada(data);
        ejbFitosFacade.create(fitos);
        listFitos.add(fitos);
        return "../conteudo-professor/gerenciar-fitos.xhtml?faces-redirect=true";
    }

    public String changeFitos() {
        System.out.println("foifoifoifoifoifoifoifoifoifoi");
        fitos.setRetirada(new Date(retirada));
        fitos.setDisponivel(disponivel);
        ejbFitosFacade.edit(fitos);
        return "../conteudo-professor/gerenciar-fitos.xhtml?faces-redirect=true";
    }

    public void handleFileUploadFSFoto(FileUploadEvent event) {
        long fNome = ejbFsfotoFacade.nextId();
        String ext = event.getFile().getFileName().substring((event.getFile().getFileName().length() - 4), (event.getFile().getFileName().length()));


        try {

            File saida = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + dirFSFoto + fNome + ext);


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

            Fsfoto fsfoto = new Fsfoto(fNome);
            fsfoto.setFkIdFitos(fitos);
            ejbFsfotoFacade.create(fsfoto);
            fitos.getFsfotoList().add(fsfoto);
        } catch (Exception e) {

            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro:", "Arquivo " + event.getFile().getFileName() + " não foi enviado corretamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public Fitos getFitos() {
        return fitos;
    }

    public void setFitos(Fitos fitos) {
        this.fitos = fitos;
    }

    public List<String> getListPos() {
        return listPos;
    }

    public void setListPos(List<String> listPos) {
        this.listPos = listPos;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public List<String> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<String> listEstado) {
        this.listEstado = listEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getListCidade() {
        return listCidade;
    }

    public void setListCidade(List<String> listCidade) {
        this.listCidade = listCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Fitos> getListFitos() {
        return listFitos;
    }

    public void setListFitos(List<Fitos> listFitos) {
        this.listFitos = listFitos;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getRetirada() {
        return retirada;
    }

    public void setRetirada(String retirada) {
        this.retirada = retirada;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
