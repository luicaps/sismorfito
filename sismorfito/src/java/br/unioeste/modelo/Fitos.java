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
@Table(name = "fitos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Fitos.findAll", query = "SELECT f FROM Fitos f"),
	@NamedQuery(name = "Fitos.findByIdFitos", query = "SELECT f FROM Fitos f WHERE f.idFitos = :idFitos"),
	@NamedQuery(name = "Fitos.findByDisponivel", query = "SELECT f FROM Fitos f WHERE f.disponivel = :disponivel"),
	@NamedQuery(name = "Fitos.findByRetirada", query = "SELECT f FROM Fitos f WHERE f.retirada = :retirada")})
public class Fitos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fitos")
	private Long idFitos;
	@Column(name = "disponivel")
	private Boolean disponivel;
	@Column(name = "retirada")
    @Temporal(TemporalType.DATE)
	private Date retirada;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "fitos")
	private Compara compara;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFitos")
	private List<Fsfoto> fsfotoList;
	@JoinColumn(name = "fk_id_usu", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
	private Usuario fkIdUsu;
	@JoinColumn(name = "fk_id_pos", referencedColumnName = "id_pos")
    @ManyToOne(optional = false)
	private Pos fkIdPos;

	public Fitos() {
	}

	public Fitos(Long idFitos) {
		this.idFitos = idFitos;
	}

	public Long getIdFitos() {
		return idFitos;
	}

	public void setIdFitos(Long idFitos) {
		this.idFitos = idFitos;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Date getRetirada() {
		return retirada;
	}

	public void setRetirada(Date retirada) {
		this.retirada = retirada;
	}

	public Compara getCompara() {
		return compara;
	}

	public void setCompara(Compara compara) {
		this.compara = compara;
	}

	@XmlTransient
	public List<Fsfoto> getFsfotoList() {
		return fsfotoList;
	}

	public void setFsfotoList(List<Fsfoto> fsfotoList) {
		this.fsfotoList = fsfotoList;
	}

	public Usuario getFkIdUsu() {
		return fkIdUsu;
	}

	public void setFkIdUsu(Usuario fkIdUsu) {
		this.fkIdUsu = fkIdUsu;
	}

	public Pos getFkIdPos() {
		return fkIdPos;
	}

	public void setFkIdPos(Pos fkIdPos) {
		this.fkIdPos = fkIdPos;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idFitos != null ? idFitos.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Fitos)) {
			return false;
		}
		Fitos other = (Fitos) object;
		if ((this.idFitos == null && other.idFitos != null) || (this.idFitos != null && !this.idFitos.equals(other.idFitos))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Fitos[ idFitos=" + idFitos + " ]";
	}
	
}
