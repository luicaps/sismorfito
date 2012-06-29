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
@Table(name = "fitop")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Fitop.findAll", query = "SELECT f FROM Fitop f"),
	@NamedQuery(name = "Fitop.findByIdFitop", query = "SELECT f FROM Fitop f WHERE f.idFitop = :idFitop"),
	@NamedQuery(name = "Fitop.findByNomeFp", query = "SELECT f FROM Fitop f WHERE f.nomeFp = :nomeFp"),
	@NamedQuery(name = "Fitop.findByDisponivel", query = "SELECT f FROM Fitop f WHERE f.disponivel = :disponivel")})
public class Fitop implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fitop")
	private Long idFitop;
	@Size(max = 45)
    @Column(name = "nome_fp")
	private String nomeFp;
	@Column(name = "disponivel")
	private Boolean disponivel;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFitop")
	private List<Fpfoto> fpfotoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFitop")
	private List<Compara> comparaList;
	@JoinColumn(name = "fk_id_usu", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
	private Usuario fkIdUsu;
	@JoinColumn(name = "fk_id_pplanta", referencedColumnName = "id_pplanta")
    @ManyToOne(optional = false)
	private Pplanta fkIdPplanta;

	public Fitop() {
	}

	public Fitop(Long idFitop) {
		this.idFitop = idFitop;
	}

	public Long getIdFitop() {
		return idFitop;
	}

	public void setIdFitop(Long idFitop) {
		this.idFitop = idFitop;
	}

	public String getNomeFp() {
		return nomeFp;
	}

	public void setNomeFp(String nomeFp) {
		this.nomeFp = nomeFp;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	@XmlTransient
	public List<Fpfoto> getFpfotoList() {
		return fpfotoList;
	}

	public void setFpfotoList(List<Fpfoto> fpfotoList) {
		this.fpfotoList = fpfotoList;
	}

	@XmlTransient
	public List<Compara> getComparaList() {
		return comparaList;
	}

	public void setComparaList(List<Compara> comparaList) {
		this.comparaList = comparaList;
	}

	public Usuario getFkIdUsu() {
		return fkIdUsu;
	}

	public void setFkIdUsu(Usuario fkIdUsu) {
		this.fkIdUsu = fkIdUsu;
	}

	public Pplanta getFkIdPplanta() {
		return fkIdPplanta;
	}

	public void setFkIdPplanta(Pplanta fkIdPplanta) {
		this.fkIdPplanta = fkIdPplanta;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idFitop != null ? idFitop.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Fitop)) {
			return false;
		}
		Fitop other = (Fitop) object;
		if ((this.idFitop == null && other.idFitop != null) || (this.idFitop != null && !this.idFitop.equals(other.idFitop))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Fitop[ idFitop=" + idFitop + " ]";
	}
	
}
