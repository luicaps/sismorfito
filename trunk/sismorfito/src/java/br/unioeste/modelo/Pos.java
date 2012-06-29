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
@Table(name = "pos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Pos.findAll", query = "SELECT p FROM Pos p"),
	@NamedQuery(name = "Pos.findByIdPos", query = "SELECT p FROM Pos p WHERE p.idPos = :idPos"),
	@NamedQuery(name = "Pos.findByLatitude", query = "SELECT p FROM Pos p WHERE p.latitude = :latitude"),
	@NamedQuery(name = "Pos.findByLongitde", query = "SELECT p FROM Pos p WHERE p.longitde = :longitde"),
	@NamedQuery(name = "Pos.findByComentario", query = "SELECT p FROM Pos p WHERE p.comentario = :comentario")})
public class Pos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pos")
	private Long idPos;
	@Size(max = 15)
    @Column(name = "latitude")
	private String latitude;
	@Size(max = 15)
    @Column(name = "longitde")
	private String longitde;
	@Size(max = 128)
    @Column(name = "comentario")
	private String comentario;
	@JoinColumn(name = "fk_id_csolo", referencedColumnName = "id_csolo")
    @ManyToOne(optional = false)
	private Csolo fkIdCsolo;
	@JoinColumn(name = "fk_id_cidade", referencedColumnName = "id_cidade")
    @ManyToOne(optional = false)
	private Cidade fkIdCidade;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPos")
	private List<Planta> plantaList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPos")
	private List<Fitos> fitosList;

	public Pos() {
	}

	public Pos(Long idPos) {
		this.idPos = idPos;
	}

	public Long getIdPos() {
		return idPos;
	}

	public void setIdPos(Long idPos) {
		this.idPos = idPos;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitde() {
		return longitde;
	}

	public void setLongitde(String longitde) {
		this.longitde = longitde;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Csolo getFkIdCsolo() {
		return fkIdCsolo;
	}

	public void setFkIdCsolo(Csolo fkIdCsolo) {
		this.fkIdCsolo = fkIdCsolo;
	}

	public Cidade getFkIdCidade() {
		return fkIdCidade;
	}

	public void setFkIdCidade(Cidade fkIdCidade) {
		this.fkIdCidade = fkIdCidade;
	}

	@XmlTransient
	public List<Planta> getPlantaList() {
		return plantaList;
	}

	public void setPlantaList(List<Planta> plantaList) {
		this.plantaList = plantaList;
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
		hash += (idPos != null ? idPos.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Pos)) {
			return false;
		}
		Pos other = (Pos) object;
		if ((this.idPos == null && other.idPos != null) || (this.idPos != null && !this.idPos.equals(other.idPos))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Pos[ idPos=" + idPos + " ]";
	}
	
}
