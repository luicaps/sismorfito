/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caps
 */
@Entity
@Table(name = "fpfoto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Fpfoto.findAll", query = "SELECT f FROM Fpfoto f"),
	@NamedQuery(name = "Fpfoto.findByFoto", query = "SELECT f FROM Fpfoto f WHERE f.foto = :foto")})
public class Fpfoto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "foto")
	private Long foto;
	@JoinColumn(name = "fk_id_fitop", referencedColumnName = "id_fitop")
    @ManyToOne(optional = false)
	private Fitop fkIdFitop;

	public Fpfoto() {
	}

	public Fpfoto(Long foto) {
		this.foto = foto;
	}

	public Long getFoto() {
		return foto;
	}

	public void setFoto(Long foto) {
		this.foto = foto;
	}

	public Fitop getFkIdFitop() {
		return fkIdFitop;
	}

	public void setFkIdFitop(Fitop fkIdFitop) {
		this.fkIdFitop = fkIdFitop;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (foto != null ? foto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Fpfoto)) {
			return false;
		}
		Fpfoto other = (Fpfoto) object;
		if ((this.foto == null && other.foto != null) || (this.foto != null && !this.foto.equals(other.foto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Fpfoto[ foto=" + foto + " ]";
	}
	
}
