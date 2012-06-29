/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Caps
 */
@Entity
@Table(name = "planta")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Planta.findAll", query = "SELECT p FROM Planta p"),
	@NamedQuery(name = "Planta.findByIdPlanta", query = "SELECT p FROM Planta p WHERE p.idPlanta = :idPlanta"),
	@NamedQuery(name = "Planta.findByRetirada", query = "SELECT p FROM Planta p WHERE p.retirada = :retirada")})
public class Planta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_planta")
	private Long idPlanta;
	@Column(name = "retirada")
    @Temporal(TemporalType.DATE)
	private Date retirada;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPlanta")
	private List<Plfoto> plfotoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPlanta")
	private List<Pplanta> pplantaList;
	@JoinColumn(name = "fk_id_tvege", referencedColumnName = "id_tvege")
    @ManyToOne(optional = false)
	private Tvege fkIdTvege;
	@JoinColumn(name = "fk_id_pos", referencedColumnName = "id_pos")
    @ManyToOne(optional = false)
	private Pos fkIdPos;
	@JoinColumn(name = "fk_id_especie", referencedColumnName = "id_especie")
    @ManyToOne(optional = false)
	private Especie fkIdEspecie;

	public Planta() {
	}

	public Planta(Long idPlanta) {
		this.idPlanta = idPlanta;
	}

	public Long getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Long idPlanta) {
		this.idPlanta = idPlanta;
	}

	public Date getRetirada() {
		return retirada;
	}

	public void setRetirada(Date retirada) {
		this.retirada = retirada;
	}

	@XmlTransient
	public List<Plfoto> getPlfotoList() {
		return plfotoList;
	}

	public void setPlfotoList(List<Plfoto> plfotoList) {
		this.plfotoList = plfotoList;
	}

	@XmlTransient
	public List<Pplanta> getPplantaList() {
		return pplantaList;
	}

	public void setPplantaList(List<Pplanta> pplantaList) {
		this.pplantaList = pplantaList;
	}

	public Tvege getFkIdTvege() {
		return fkIdTvege;
	}

	public void setFkIdTvege(Tvege fkIdTvege) {
		this.fkIdTvege = fkIdTvege;
	}

	public Pos getFkIdPos() {
		return fkIdPos;
	}

	public void setFkIdPos(Pos fkIdPos) {
		this.fkIdPos = fkIdPos;
	}

	public Especie getFkIdEspecie() {
		return fkIdEspecie;
	}

	public void setFkIdEspecie(Especie fkIdEspecie) {
		this.fkIdEspecie = fkIdEspecie;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPlanta != null ? idPlanta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Planta)) {
			return false;
		}
		Planta other = (Planta) object;
		if ((this.idPlanta == null && other.idPlanta != null) || (this.idPlanta != null && !this.idPlanta.equals(other.idPlanta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Planta[ idPlanta=" + idPlanta + " ]";
	}
	
}
