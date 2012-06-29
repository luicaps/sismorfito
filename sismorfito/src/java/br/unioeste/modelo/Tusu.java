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
@Table(name = "tusu")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Tusu.findAll", query = "SELECT t FROM Tusu t"),
	@NamedQuery(name = "Tusu.findByIdTusu", query = "SELECT t FROM Tusu t WHERE t.idTusu = :idTusu"),
	@NamedQuery(name = "Tusu.findByTipo", query = "SELECT t FROM Tusu t WHERE t.tipo = :tipo")})
public class Tusu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tusu")
	private Long idTusu;
	@Size(max = 45)
    @Column(name = "tipo")
	private String tipo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTusu")
	private List<Usuario> usuarioList;

	public Tusu() {
	}

	public Tusu(Long idTusu) {
		this.idTusu = idTusu;
	}

	public Long getIdTusu() {
		return idTusu;
	}

	public void setIdTusu(Long idTusu) {
		this.idTusu = idTusu;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlTransient
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTusu != null ? idTusu.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tusu)) {
			return false;
		}
		Tusu other = (Tusu) object;
		if ((this.idTusu == null && other.idTusu != null) || (this.idTusu != null && !this.idTusu.equals(other.idTusu))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Tusu[ idTusu=" + idTusu + " ]";
	}
	
}
