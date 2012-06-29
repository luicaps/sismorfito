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
@Table(name = "fsfoto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Fsfoto.findAll", query = "SELECT f FROM Fsfoto f"),
	@NamedQuery(name = "Fsfoto.findByFoto", query = "SELECT f FROM Fsfoto f WHERE f.foto = :foto")})
public class Fsfoto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "foto")
	private Long foto;
	@JoinColumn(name = "fk_id_fitos", referencedColumnName = "id_fitos")
    @ManyToOne(optional = false)
	private Fitos fkIdFitos;

	public Fsfoto() {
	}

	public Fsfoto(Long foto) {
		this.foto = foto;
	}

	public Long getFoto() {
		return foto;
	}

	public void setFoto(Long foto) {
		this.foto = foto;
	}

	public Fitos getFkIdFitos() {
		return fkIdFitos;
	}

	public void setFkIdFitos(Fitos fkIdFitos) {
		this.fkIdFitos = fkIdFitos;
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
		if (!(object instanceof Fsfoto)) {
			return false;
		}
		Fsfoto other = (Fsfoto) object;
		if ((this.foto == null && other.foto != null) || (this.foto != null && !this.foto.equals(other.foto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Fsfoto[ foto=" + foto + " ]";
	}
	
}
