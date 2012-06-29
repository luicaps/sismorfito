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
@Table(name = "ordem")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Ordem.findAll", query = "SELECT o FROM Ordem o"),
	@NamedQuery(name = "Ordem.findByIdOrdem", query = "SELECT o FROM Ordem o WHERE o.idOrdem = :idOrdem"),
	@NamedQuery(name = "Ordem.findByNomeOrdem", query = "SELECT o FROM Ordem o WHERE o.nomeOrdem = :nomeOrdem")})
public class Ordem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ordem")
	private Long idOrdem;
	@Size(max = 80)
    @Column(name = "nome_ordem")
	private String nomeOrdem;
	@JoinColumn(name = "fk_id_classe", referencedColumnName = "id_classe")
    @ManyToOne(optional = false)
	private Classe fkIdClasse;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdOrdem")
	private List<Familia> familiaList;

	public Ordem() {
	}

	public Ordem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public Long getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public String getNomeOrdem() {
		return nomeOrdem;
	}

	public void setNomeOrdem(String nomeOrdem) {
		this.nomeOrdem = nomeOrdem;
	}

	public Classe getFkIdClasse() {
		return fkIdClasse;
	}

	public void setFkIdClasse(Classe fkIdClasse) {
		this.fkIdClasse = fkIdClasse;
	}

	@XmlTransient
	public List<Familia> getFamiliaList() {
		return familiaList;
	}

	public void setFamiliaList(List<Familia> familiaList) {
		this.familiaList = familiaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idOrdem != null ? idOrdem.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Ordem)) {
			return false;
		}
		Ordem other = (Ordem) object;
		if ((this.idOrdem == null && other.idOrdem != null) || (this.idOrdem != null && !this.idOrdem.equals(other.idOrdem))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.unioeste.modelo.Ordem[ idOrdem=" + idOrdem + " ]";
	}
	
}
