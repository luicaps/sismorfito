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
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
	@NamedQuery(name = "Cidade.findByIdCidade", query = "SELECT c FROM Cidade c WHERE c.idCidade = :idCidade"),
	@NamedQuery(name = "Cidade.findByCidade", query = "SELECT c FROM Cidade c WHERE c.cidade = :cidade")})
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cidade")
	private Long idCidade;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cidade")
	private String cidade;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdCidade")
	private List<Pos> posList;
	@JoinColumn(name = "fk_id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
	private Estado fkIdEstado;

	public Cidade() {
	}

	public Cidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public Cidade(Long idCidade, String cidade) {
		this.idCidade = idCidade;
		this.cidade = cidade;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@XmlTransient
	public List<Pos> getPosList() {
		return posList;
	}

	public void setPosList(List<Pos> posList) {
		this.posList = posList;
	}

	public Estado getFkIdEstado() {
		return fkIdEstado;
	}

	public void setFkIdEstado(Estado fkIdEstado) {
		this.fkIdEstado = fkIdEstado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCidade != null ? idCidade.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Cidade)) {
			return false;
		}
		Cidade other = (Cidade) object;
		if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Cidade[ idCidade=" + idCidade + " ]";
	}
	
}
