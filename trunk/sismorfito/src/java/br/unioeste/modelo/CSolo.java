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
@Table(name = "csolo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Csolo.findAll", query = "SELECT c FROM Csolo c"),
	@NamedQuery(name = "Csolo.findByIdCsolo", query = "SELECT c FROM Csolo c WHERE c.idCsolo = :idCsolo"),
	@NamedQuery(name = "Csolo.findBySibcs", query = "SELECT c FROM Csolo c WHERE c.sibcs = :sibcs")})
public class Csolo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_csolo")
	private Long idCsolo;
	@Size(max = 45)
    @Column(name = "sibcs")
	private String sibcs;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdCsolo")
	private List<Pos> posList;

	public Csolo() {
	}

	public Csolo(Long idCsolo) {
		this.idCsolo = idCsolo;
	}

	public Long getIdCsolo() {
		return idCsolo;
	}

	public void setIdCsolo(Long idCsolo) {
		this.idCsolo = idCsolo;
	}

	public String getSibcs() {
		return sibcs;
	}

	public void setSibcs(String sibcs) {
		this.sibcs = sibcs;
	}

	@XmlTransient
	public List<Pos> getPosList() {
		return posList;
	}

	public void setPosList(List<Pos> posList) {
		this.posList = posList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCsolo != null ? idCsolo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Csolo)) {
			return false;
		}
		Csolo other = (Csolo) object;
		if ((this.idCsolo == null && other.idCsolo != null) || (this.idCsolo != null && !this.idCsolo.equals(other.idCsolo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Csolo[ idCsolo=" + idCsolo + " ]";
	}
	
}
