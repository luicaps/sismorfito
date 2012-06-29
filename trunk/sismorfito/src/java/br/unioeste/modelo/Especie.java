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
@Table(name = "especie")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Especie.findAll", query = "SELECT e FROM Especie e"),
	@NamedQuery(name = "Especie.findByIdEspecie", query = "SELECT e FROM Especie e WHERE e.idEspecie = :idEspecie"),
	@NamedQuery(name = "Especie.findByNomeEspecie", query = "SELECT e FROM Especie e WHERE e.nomeEspecie = :nomeEspecie"),
	@NamedQuery(name = "Especie.findByValdelta", query = "SELECT e FROM Especie e WHERE e.valdelta = :valdelta")})
public class Especie implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_especie")
	private Long idEspecie;
	@Size(max = 80)
    @Column(name = "nome_especie")
	private String nomeEspecie;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "valdelta")
	private Double valdelta;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdEspecie")
	private List<Planta> plantaList;
	@JoinColumn(name = "fk_id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
	private Genero fkIdGenero;

	public Especie() {
	}

	public Especie(Long idEspecie) {
		this.idEspecie = idEspecie;
	}

	public Long getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Long idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getNomeEspecie() {
		return nomeEspecie;
	}

	public void setNomeEspecie(String nomeEspecie) {
		this.nomeEspecie = nomeEspecie;
	}

	public Double getValdelta() {
		return valdelta;
	}

	public void setValdelta(Double valdelta) {
		this.valdelta = valdelta;
	}

	@XmlTransient
	public List<Planta> getPlantaList() {
		return plantaList;
	}

	public void setPlantaList(List<Planta> plantaList) {
		this.plantaList = plantaList;
	}

	public Genero getFkIdGenero() {
		return fkIdGenero;
	}

	public void setFkIdGenero(Genero fkIdGenero) {
		this.fkIdGenero = fkIdGenero;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEspecie != null ? idEspecie.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Especie)) {
			return false;
		}
		Especie other = (Especie) object;
		if ((this.idEspecie == null && other.idEspecie != null) || (this.idEspecie != null && !this.idEspecie.equals(other.idEspecie))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Especie[ idEspecie=" + idEspecie + " ]";
	}
	
}
