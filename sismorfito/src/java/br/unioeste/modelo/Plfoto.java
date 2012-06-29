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
@Table(name = "plfoto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Plfoto.findAll", query = "SELECT p FROM Plfoto p"),
	@NamedQuery(name = "Plfoto.findByFoto", query = "SELECT p FROM Plfoto p WHERE p.foto = :foto")})
public class Plfoto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "foto")
	private Long foto;
	@JoinColumn(name = "fk_id_planta", referencedColumnName = "id_planta")
    @ManyToOne(optional = false)
	private Planta fkIdPlanta;

	public Plfoto() {
	}

	public Plfoto(Long foto) {
		this.foto = foto;
	}

	public Long getFoto() {
		return foto;
	}

	public void setFoto(Long foto) {
		this.foto = foto;
	}

	public Planta getFkIdPlanta() {
		return fkIdPlanta;
	}

	public void setFkIdPlanta(Planta fkIdPlanta) {
		this.fkIdPlanta = fkIdPlanta;
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
		if (!(object instanceof Plfoto)) {
			return false;
		}
		Plfoto other = (Plfoto) object;
		if ((this.foto == null && other.foto != null) || (this.foto != null && !this.foto.equals(other.foto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Plfoto[ foto=" + foto + " ]";
	}
	
}
