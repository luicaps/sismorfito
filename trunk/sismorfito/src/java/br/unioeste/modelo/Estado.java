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
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
	@NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
	@NamedQuery(name = "Estado.findByEstado", query = "SELECT e FROM Estado e WHERE e.estado = :estado"),
	@NamedQuery(name = "Estado.findByUf", query = "SELECT e FROM Estado e WHERE e.uf = :uf")})
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado")
	private Long idEstado;
	@Size(max = 45)
    @Column(name = "estado")
	private String estado;
	@Size(max = 2)
    @Column(name = "uf")
	private String uf;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdEstado")
	private List<Cidade> cidadeList;

	public Estado() {
	}

	public Estado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@XmlTransient
	public List<Cidade> getCidadeList() {
		return cidadeList;
	}

	public void setCidadeList(List<Cidade> cidadeList) {
		this.cidadeList = cidadeList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEstado != null ? idEstado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Estado)) {
			return false;
		}
		Estado other = (Estado) object;
		if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Estado[ idEstado=" + idEstado + " ]";
	}
	
}
