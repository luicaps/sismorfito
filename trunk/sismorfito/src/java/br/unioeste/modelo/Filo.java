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
@Table(name = "filo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Filo.findAll", query = "SELECT f FROM Filo f"),
	@NamedQuery(name = "Filo.findByIdFilo", query = "SELECT f FROM Filo f WHERE f.idFilo = :idFilo"),
	@NamedQuery(name = "Filo.findByNomeFilo", query = "SELECT f FROM Filo f WHERE f.nomeFilo = :nomeFilo")})
public class Filo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_filo")
	private Long idFilo;
	@Size(max = 80)
    @Column(name = "nome_filo")
	private String nomeFilo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFilo")
	private List<Classe> classeList;

	public Filo() {
	}

	public Filo(Long idFilo) {
		this.idFilo = idFilo;
	}

	public Long getIdFilo() {
		return idFilo;
	}

	public void setIdFilo(Long idFilo) {
		this.idFilo = idFilo;
	}

	public String getNomeFilo() {
		return nomeFilo;
	}

	public void setNomeFilo(String nomeFilo) {
		this.nomeFilo = nomeFilo;
	}

	@XmlTransient
	public List<Classe> getClasseList() {
		return classeList;
	}

	public void setClasseList(List<Classe> classeList) {
		this.classeList = classeList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idFilo != null ? idFilo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Filo)) {
			return false;
		}
		Filo other = (Filo) object;
		if ((this.idFilo == null && other.idFilo != null) || (this.idFilo != null && !this.idFilo.equals(other.idFilo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Filo[ idFilo=" + idFilo + " ]";
	}
	
}
