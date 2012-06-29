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
@Table(name = "classe")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Classe.findAll", query = "SELECT c FROM Classe c"),
	@NamedQuery(name = "Classe.findByIdClasse", query = "SELECT c FROM Classe c WHERE c.idClasse = :idClasse"),
	@NamedQuery(name = "Classe.findByNomeClasse", query = "SELECT c FROM Classe c WHERE c.nomeClasse = :nomeClasse")})
public class Classe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_classe")
	private Long idClasse;
	@Size(max = 80)
    @Column(name = "nome_classe")
	private String nomeClasse;
	@JoinColumn(name = "fk_id_filo", referencedColumnName = "id_filo")
    @ManyToOne(optional = false)
	private Filo fkIdFilo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdClasse")
	private List<Ordem> ordemList;

	public Classe() {
	}

	public Classe(Long idClasse) {
		this.idClasse = idClasse;
	}

	public Long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Long idClasse) {
		this.idClasse = idClasse;
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public Filo getFkIdFilo() {
		return fkIdFilo;
	}

	public void setFkIdFilo(Filo fkIdFilo) {
		this.fkIdFilo = fkIdFilo;
	}

	@XmlTransient
	public List<Ordem> getOrdemList() {
		return ordemList;
	}

	public void setOrdemList(List<Ordem> ordemList) {
		this.ordemList = ordemList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idClasse != null ? idClasse.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Classe)) {
			return false;
		}
		Classe other = (Classe) object;
		if ((this.idClasse == null && other.idClasse != null) || (this.idClasse != null && !this.idClasse.equals(other.idClasse))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Classe[ idClasse=" + idClasse + " ]";
	}
	
}
