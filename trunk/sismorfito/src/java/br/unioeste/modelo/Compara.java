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
@Table(name = "compara")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Compara.findAll", query = "SELECT c FROM Compara c"),
	@NamedQuery(name = "Compara.findByFkIdFitos", query = "SELECT c FROM Compara c WHERE c.fkIdFitos = :fkIdFitos")})
public class Compara implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_id_fitos")
	private Long fkIdFitos;
	@JoinColumn(name = "fk_id_usu", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
	private Usuario fkIdUsu;
	@JoinColumn(name = "fk_id_fitos", referencedColumnName = "id_fitos", insertable = false, updatable = false)
    @OneToOne(optional = false)
	private Fitos fitos;
	@JoinColumn(name = "fk_id_fitop", referencedColumnName = "id_fitop")
    @ManyToOne(optional = false)
	private Fitop fkIdFitop;

	public Compara() {
	}

	public Compara(Long fkIdFitos) {
		this.fkIdFitos = fkIdFitos;
	}

	public Long getFkIdFitos() {
		return fkIdFitos;
	}

	public void setFkIdFitos(Long fkIdFitos) {
		this.fkIdFitos = fkIdFitos;
	}

	public Usuario getFkIdUsu() {
		return fkIdUsu;
	}

	public void setFkIdUsu(Usuario fkIdUsu) {
		this.fkIdUsu = fkIdUsu;
	}

	public Fitos getFitos() {
		return fitos;
	}

	public void setFitos(Fitos fitos) {
		this.fitos = fitos;
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
		hash += (fkIdFitos != null ? fkIdFitos.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Compara)) {
			return false;
		}
		Compara other = (Compara) object;
		if ((this.fkIdFitos == null && other.fkIdFitos != null) || (this.fkIdFitos != null && !this.fkIdFitos.equals(other.fkIdFitos))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Compara[ fkIdFitos=" + fkIdFitos + " ]";
	}
	
}
