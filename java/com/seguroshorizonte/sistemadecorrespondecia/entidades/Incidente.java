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
@Table(name = "INCIDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidente.findAll", query = "SELECT i FROM Incidente i"),
    @NamedQuery(name = "Incidente.findByIdinc", query = "SELECT i FROM Incidente i WHERE i.idinc = :idinc"),
    @NamedQuery(name = "Incidente.findByNombreinc", query = "SELECT i FROM Incidente i WHERE i.nombreinc = :nombreinc"),
    @NamedQuery(name = "Incidente.findByDescripcioninc", query = "SELECT i FROM Incidente i WHERE i.descripcioninc = :descripcioninc"),
    @NamedQuery(name = "Incidente.findByMaximoIdinc", query = "SELECT MAX(i.idinc) FROM Incidente i")})
public class Incidente implements Serializable {
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaq;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCIDENTESEQ")
    @SequenceGenerator(name = "INCIDENTESEQ", sequenceName = "SEQ_INCIDENTE", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDINC")
    private BigDecimal idinc;
    @Size(max = 200)
    @Column(name = "NOMBREINC")
    private String nombreinc;
    @Size(max = 2500)
    @Column(name = "DESCRIPCIONINC")
    private String descripcioninc;
    @OneToMany(mappedBy = "idinc")
    private Collection<Valija> valijaCollection;

    public Incidente() {
    }

    public Incidente(BigDecimal idinc) {
        this.idinc = idinc;
    }

    public BigDecimal getIdinc() {
        return idinc;
    }

    public void setIdinc(BigDecimal idinc) {
        this.idinc = idinc;
    }

    public String getNombreinc() {
        return nombreinc;
    }

    public void setNombreinc(String nombreinc) {
        this.nombreinc = nombreinc;
    }

    public String getDescripcioninc() {
        return descripcioninc;
    }

    public void setDescripcioninc(String descripcioninc) {
        this.descripcioninc = descripcioninc;
    }

    @XmlTransient
    public Collection<Valija> getValijaCollection() {
        return valijaCollection;
    }

    public void setValijaCollection(Collection<Valija> valijaCollection) {
        this.valijaCollection = valijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinc != null ? idinc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidente)) {
            return false;
        }
        Incidente other = (Incidente) object;
        if ((this.idinc == null && other.idinc != null) || (this.idinc != null && !this.idinc.equals(other.idinc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente[ idinc=" + idinc + " ]";
    }

    public Paquete getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(Paquete idpaq) {
        this.idpaq = idpaq;
    }
}
