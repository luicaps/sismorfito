/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.Fitop;
import br.unioeste.modelo.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Caps
 */
@ManagedBean
@SessionScoped
public class alteraFitopManagedBean {
	
	List<Fitop> list;
	Fitop selection;
	Usuario responsavel;

	/**
	 * Creates a new instance of alteraFitopManagedBean
	 */
	public alteraFitopManagedBean() {
	}

	@PostConstruct
	public void init(){
		LoginBean lg = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
		responsavel = lg.getUsuario();
		list = responsavel.getFitopList();
	}
	
	public List<Fitop> getList() {
		return list;
	}

	public void setList(List<Fitop> list) {
		this.list = list;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Fitop getSelection() {
		return selection;
	}

	public void setSelection(Fitop selection) {
		this.selection = selection;
	}

}
