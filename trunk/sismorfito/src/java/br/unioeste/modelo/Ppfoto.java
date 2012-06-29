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
@Table(name = "ppfoto")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Ppfoto.findAll", query = "SELECT p FROM Ppfoto p"),
	@NamedQuery(name = "Ppfoto.findByFoto", query = "SELECT p FROM Ppfoto p WHERE p.foto = :foto")})
public class Ppfoto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "foto")
	private Long foto;
	@JoinColumn(name = "fk_id_pplanta", referencedColumnName = "id_pplanta")
    @ManyToOne(optional = false)
	private Pplanta fkIdPplanta;

	public Ppfoto() {
	}

	public Ppfoto(Long foto) {
		this.foto = foto;
	}

	public Long getFoto() {
		return foto;
	}

	public void setFoto(Long foto) {
		this.foto = foto;
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
		hash += (foto != null ? foto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Ppfoto)) {
			return false;
		}
		Ppfoto other = (Ppfoto) object;
		if ((this.foto == null && other.foto != null) || (this.foto != null && !this.foto.equals(other.foto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Ppfoto[ foto=" + foto + " ]";
	}
	
}
