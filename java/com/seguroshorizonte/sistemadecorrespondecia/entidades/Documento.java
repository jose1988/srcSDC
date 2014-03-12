/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIddoc", query = "SELECT d FROM Documento d WHERE d.iddoc = :iddoc"),
    @NamedQuery(name = "Documento.findByNombredoc", query = "SELECT d FROM Documento d WHERE d.nombredoc = :nombredoc"),
    @NamedQuery(name = "Documento.findByDescripciondoc", query = "SELECT d FROM Documento d WHERE d.descripciondoc = :descripciondoc")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOC")
    private BigDecimal iddoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBREDOC")
    private String nombredoc;
    @Size(max = 2000)
    @Column(name = "DESCRIPCIONDOC")
    private String descripciondoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddoc")
    private Collection<Paquete> paqueteCollection;

    public Documento() {
    }

    public Documento(BigDecimal iddoc) {
        this.iddoc = iddoc;
    }

    public Documento(BigDecimal iddoc, String nombredoc) {
        this.iddoc = iddoc;
        this.nombredoc = nombredoc;
    }

    public BigDecimal getIddoc() {
        return iddoc;
    }

    public void setIddoc(BigDecimal iddoc) {
        this.iddoc = iddoc;
    }

    public String getNombredoc() {
        return nombredoc;
    }

    public void setNombredoc(String nombredoc) {
        this.nombredoc = nombredoc;
    }

    public String getDescripciondoc() {
        return descripciondoc;
    }

    public void setDescripciondoc(String descripciondoc) {
        this.descripciondoc = descripciondoc;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddoc != null ? iddoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.iddoc == null && other.iddoc != null) || (this.iddoc != null && !this.iddoc.equals(other.iddoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento[ iddoc=" + iddoc + " ]";
    }
}
