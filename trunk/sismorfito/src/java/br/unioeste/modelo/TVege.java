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
@Table(name = "tvege")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Tvege.findAll", query = "SELECT t FROM Tvege t"),
	@NamedQuery(name = "Tvege.findByIdTvege", query = "SELECT t FROM Tvege t WHERE t.idTvege = :idTvege"),
	@NamedQuery(name = "Tvege.findByNomeVege", query = "SELECT t FROM Tvege t WHERE t.nomeVege = :nomeVege")})
public class Tvege implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tvege")
	private Long idTvege;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nome_vege")
	private String nomeVege;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTvege")
	private List<Planta> plantaList;

	public Tvege() {
	}

	public Tvege(Long idTvege) {
		this.idTvege = idTvege;
	}

	public Tvege(Long idTvege, String nomeVege) {
		this.idTvege = idTvege;
		this.nomeVege = nomeVege;
	}

	public Long getIdTvege() {
		return idTvege;
	}

	public void setIdTvege(Long idTvege) {
		this.idTvege = idTvege;
	}

	public String getNomeVege() {
		return nomeVege;
	}

	public void setNomeVege(String nomeVege) {
		this.nomeVege = nomeVege;
	}

	@XmlTransient
	public List<Planta> getPlantaList() {
		return plantaList;
	}

	public void setPlantaList(List<Planta> plantaList) {
		this.plantaList = plantaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTvege != null ? idTvege.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tvege)) {
			return false;
		}
		Tvege other = (Tvege) object;
		if ((this.idTvege == null && other.idTvege != null) || (this.idTvege != null && !this.idTvege.equals(other.idTvege))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Tvege[ idTvege=" + idTvege + " ]";
	}
	
}
