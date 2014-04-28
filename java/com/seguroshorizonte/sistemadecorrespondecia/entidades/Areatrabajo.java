/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "AREATRABAJO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areatrabajo.findAll", query = "SELECT a FROM Areatrabajo a"),
    @NamedQuery(name = "Areatrabajo.findByIdatr", query = "SELECT a FROM Areatrabajo a WHERE a.idatr = :idatr"),
    @NamedQuery(name = "Areatrabajo.findByNombreatr", query = "SELECT a FROM Areatrabajo a WHERE a.nombreatr = :nombreatr AND a.idsed.idsed = :idsed"),
    @NamedQuery(name = "Areatrabajo.findBySedeXId", query = "SELECT a FROM Areatrabajo a WHERE a.idsed.idsed= :sede"),
    @NamedQuery(name = "Areatrabajo.findBySedeXNombre", query = "SELECT a FROM Areatrabajo a WHERE a.idsed.nombresed= :sede"),
    @NamedQuery(name = "Areatrabajo.findByAreaExistente", query = "SELECT a FROM Areatrabajo a WHERE A.nombreatr= :nombreatr AND a.idsed.idsed= :sede")})
public class Areatrabajo implements Serializable {

    @OneToMany(mappedBy = "idatr")
    private Collection<Usuariosede> usuariosedeCollection;
    @Size(max = 20)
    @Column(name = "BORRADOATR")
    private String borradoatr;
    @OneToMany(mappedBy = "idatr")
    private Collection<Buzon> buzonCollection;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREATRABAJO")
    @SequenceGenerator(name = "SEQ_AREATRABAJO", sequenceName = "SEQ_AREATRABAJO", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDATR")
    private BigDecimal idatr;
    @Size(max = 200)
    @Column(name = "NOMBREATR")
    private String nombreatr;

    public Areatrabajo() {
    }

    public Areatrabajo(BigDecimal idatr) {
        this.idatr = idatr;
    }

    public BigDecimal getIdatr() {
        return idatr;
    }

    public void setIdatr(BigDecimal idatr) {
        this.idatr = idatr;
    }

    public String getNombreatr() {
        return nombreatr;
    }

    public void setNombreatr(String nombreatr) {
        this.nombreatr = nombreatr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatr != null ? idatr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areatrabajo)) {
            return false;
        }
        Areatrabajo other = (Areatrabajo) object;
        if ((this.idatr == null && other.idatr != null) || (this.idatr != null && !this.idatr.equals(other.idatr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Areatrabajo[ idatr=" + idatr + " ]";
    }

    public Sede getIdsed() {
        return idsed;
    }

    public void setIdsed(Sede idsed) {
        this.idsed = idsed;
    }

    public String getBorradoatr() {
        return borradoatr;
    }

    public void setBorradoatr(String borradoatr) {
        this.borradoatr = borradoatr;
    }

    @XmlTransient
    public Collection<Buzon> getBuzonCollection() {
        return buzonCollection;
    }

    public void setBuzonCollection(Collection<Buzon> buzonCollection) {
        this.buzonCollection = buzonCollection;
    }

    @XmlTransient
    public Collection<Usuariosede> getUsuariosedeCollection() {
        return usuariosedeCollection;
    }

    public void setUsuariosedeCollection(Collection<Usuariosede> usuariosedeCollection) {
        this.usuariosedeCollection = usuariosedeCollection;
    }
}
