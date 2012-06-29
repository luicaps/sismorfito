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
@Table(name = "genero")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
	@NamedQuery(name = "Genero.findByIdGenero", query = "SELECT g FROM Genero g WHERE g.idGenero = :idGenero"),
	@NamedQuery(name = "Genero.findByNomeGenero", query = "SELECT g FROM Genero g WHERE g.nomeGenero = :nomeGenero")})
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_genero")
	private Long idGenero;
	@Size(max = 80)
    @Column(name = "nome_genero")
	private String nomeGenero;
	@JoinColumn(name = "fk_id_familia", referencedColumnName = "id_familia")
    @ManyToOne(optional = false)
	private Familia fkIdFamilia;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdGenero")
	private List<Especie> especieList;

	public Genero() {
	}

	public Genero(Long idGenero) {
		this.idGenero = idGenero;
	}

	public Long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNomeGenero() {
		return nomeGenero;
	}

	public void setNomeGenero(String nomeGenero) {
		this.nomeGenero = nomeGenero;
	}

	public Familia getFkIdFamilia() {
		return fkIdFamilia;
	}

	public void setFkIdFamilia(Familia fkIdFamilia) {
		this.fkIdFamilia = fkIdFamilia;
	}

	@XmlTransient
	public List<Especie> getEspecieList() {
		return especieList;
	}

	public void setEspecieList(List<Especie> especieList) {
		this.especieList = especieList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idGenero != null ? idGenero.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Genero)) {
			return false;
		}
		Genero other = (Genero) object;
		if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Genero[ idGenero=" + idGenero + " ]";
	}
	
}
