/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Caps
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
	@NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
	@NamedQuery(name = "Usuario.findBySobrenome", query = "SELECT u FROM Usuario u WHERE u.sobrenome = :sobrenome"),
	@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
	@NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha")})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
	private Long idUsuario;
	@Size(max = 80)
    @Column(name = "nome")
	private String nome;
	@Size(max = 80)
    @Column(name = "sobrenome")
	private String sobrenome;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	@Size(max = 180)
    @Column(name = "email")
	private String email;
	@Size(max = 40)
    @Column(name = "senha")
	private String senha;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
	private List<Compara> comparaList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsuSup")
	private List<Usuario> usuarioList;
	@JoinColumn(name = "fk_id_usu_sup", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
	private Usuario fkIdUsuSup;
	@JoinColumn(name = "fk_id_tusu", referencedColumnName = "id_tusu")
    @ManyToOne(optional = false)
	private Tusu fkIdTusu;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
	private List<Fitop> fitopList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
	private List<Fitos> fitosList;

	public Usuario() {
	}

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@XmlTransient
	public List<Compara> getComparaList() {
		return comparaList;
	}

	public void setComparaList(List<Compara> comparaList) {
		this.comparaList = comparaList;
	}

	@XmlTransient
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public Usuario getFkIdUsuSup() {
		return fkIdUsuSup;
	}

	public void setFkIdUsuSup(Usuario fkIdUsuSup) {
		this.fkIdUsuSup = fkIdUsuSup;
	}

	public Tusu getFkIdTusu() {
		return fkIdTusu;
	}

	public void setFkIdTusu(Tusu fkIdTusu) {
		this.fkIdTusu = fkIdTusu;
	}

	@XmlTransient
	public List<Fitop> getFitopList() {
		return fitopList;
	}

	public void setFitopList(List<Fitop> fitopList) {
		this.fitopList = fitopList;
	}

	@XmlTransient
	public List<Fitos> getFitosList() {
		return fitosList;
	}

	public void setFitosList(List<Fitos> fitosList) {
		this.fitosList = fitosList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Usuario[ idUsuario=" + idUsuario + " ]";
	}
	
}
