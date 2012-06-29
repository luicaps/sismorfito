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
@Table(name = "familia")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f"),
	@NamedQuery(name = "Familia.findByIdFamilia", query = "SELECT f FROM Familia f WHERE f.idFamilia = :idFamilia"),
	@NamedQuery(name = "Familia.findByNomeFamilia", query = "SELECT f FROM Familia f WHERE f.nomeFamilia = :nomeFamilia")})
public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_familia")
	private Long idFamilia;
	@Size(max = 80)
    @Column(name = "nome_familia")
	private String nomeFamilia;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFamilia")
	private List<Genero> generoList;
	@JoinColumn(name = "fk_id_ordem", referencedColumnName = "id_ordem")
    @ManyToOne(optional = false)
	private Ordem fkIdOrdem;

	public Familia() {
	}

	public Familia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public Long getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getNomeFamilia() {
		return nomeFamilia;
	}

	public void setNomeFamilia(String nomeFamilia) {
		this.nomeFamilia = nomeFamilia;
	}

	@XmlTransient
	public List<Genero> getGeneroList() {
		return generoList;
	}

	public void setGeneroList(List<Genero> generoList) {
		this.generoList = generoList;
	}

	public Ordem getFkIdOrdem() {
		return fkIdOrdem;
	}

	public void setFkIdOrdem(Ordem fkIdOrdem) {
		this.fkIdOrdem = fkIdOrdem;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idFamilia != null ? idFamilia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Familia)) {
			return false;
		}
		Familia other = (Familia) object;
		if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Familia[ idFamilia=" + idFamilia + " ]";
	}
	
}
