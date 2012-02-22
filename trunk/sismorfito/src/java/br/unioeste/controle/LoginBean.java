/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.controle;

import br.unioeste.modelo.Usuario;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Moises
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    Usuario usuario;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        if (usuario.getUsuario() != null && usuario.getUsuario().equals("admin") && usuario.getSenha() != null && usuario.getSenha().equals("admin")) {
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario.getUsuario());
            usuario.setNome("Professorino");
            usuario.setIsLogedProfessor(true);
            try {
                //FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-admin/sobre-admin.xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect("conteudo-professor/sobre-professor.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("La vita es dificil");
            }
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao Logar", "Usuario ou Senha incorretos.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
//        context.addCallbackParam("loggedIn", loggedIn);
    }
}
