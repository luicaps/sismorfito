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
@Table(name = "pplanta")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Pplanta.findAll", query = "SELECT p FROM Pplanta p"),
	@NamedQuery(name = "Pplanta.findByIdPplanta", query = "SELECT p FROM Pplanta p WHERE p.idPplanta = :idPplanta"),
	@NamedQuery(name = "Pplanta.findByNomePplanta", query = "SELECT p FROM Pplanta p WHERE p.nomePplanta = :nomePplanta")})
public class Pplanta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pplanta")
	private Long idPplanta;
	@Size(max = 80)
    @Column(name = "nome_pplanta")
	private String nomePplanta;
	@JoinColumn(name = "fk_id_planta", referencedColumnName = "id_planta")
    @ManyToOne(optional = false)
	private Planta fkIdPlanta;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPplanta")
	private List<Ppfoto> ppfotoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPplanta")
	private List<Fitop> fitopList;

	public Pplanta() {
	}

	public Pplanta(Long idPplanta) {
		this.idPplanta = idPplanta;
	}

	public Long getIdPplanta() {
		return idPplanta;
	}

	public void setIdPplanta(Long idPplanta) {
		this.idPplanta = idPplanta;
	}

	public String getNomePplanta() {
		return nomePplanta;
	}

	public void setNomePplanta(String nomePplanta) {
		this.nomePplanta = nomePplanta;
	}

	public Planta getFkIdPlanta() {
		return fkIdPlanta;
	}

	public void setFkIdPlanta(Planta fkIdPlanta) {
		this.fkIdPlanta = fkIdPlanta;
	}

	@XmlTransient
	public List<Ppfoto> getPpfotoList() {
		return ppfotoList;
	}

	public void setPpfotoList(List<Ppfoto> ppfotoList) {
		this.ppfotoList = ppfotoList;
	}

	@XmlTransient
	public List<Fitop> getFitopList() {
		return fitopList;
	}

	public void setFitopList(List<Fitop> fitopList) {
		this.fitopList = fitopList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPplanta != null ? idPplanta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Pplanta)) {
			return false;
		}
		Pplanta other = (Pplanta) object;
		if ((this.idPplanta == null && other.idPplanta != null) || (this.idPplanta != null && !this.idPplanta.equals(other.idPplanta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Pplanta[ idPplanta=" + idPplanta + " ]";
	}
	
}
