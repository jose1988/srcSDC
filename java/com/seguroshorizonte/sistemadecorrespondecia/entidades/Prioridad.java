/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "PRIORIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prioridad.findAll", query = "SELECT p FROM Prioridad p"),
    @NamedQuery(name = "Prioridad.findByIdpri", query = "SELECT p FROM Prioridad p WHERE p.idpri = :idpri"),
    @NamedQuery(name = "Prioridad.findByNombrepri", query = "SELECT p FROM Prioridad p WHERE p.nombrepri = :nombrepri"),
    @NamedQuery(name = "Prioridad.findByDescripcionpri", query = "SELECT p FROM Prioridad p WHERE p.descripcionpri = :descripcionpri")})
public class Prioridad implements Serializable {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "prioridad")
    private Paquete paquete;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDPRI")
    private String idpri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBREPRI")
    private String nombrepri;
    @Size(max = 20)
    @Column(name = "DESCRIPCIONPRI")
    private String descripcionpri;
    @OneToMany(mappedBy = "idpri")
    private Collection<Paquete> paqueteCollection;

    public Prioridad() {
    }

    public Prioridad(String idpri) {
        this.idpri = idpri;
    }

    public Prioridad(String idpri, String nombrepri) {
        this.idpri = idpri;
        this.nombrepri = nombrepri;
    }

    public String getIdpri() {
        return idpri;
    }

    public void setIdpri(String idpri) {
        this.idpri = idpri;
    }

    public String getNombrepri() {
        return nombrepri;
    }

    public void setNombrepri(String nombrepri) {
        this.nombrepri = nombrepri;
    }

    public String getDescripcionpri() {
        return descripcionpri;
    }

    public void setDescripcionpri(String descripcionpri) {
        this.descripcionpri = descripcionpri;
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
        hash += (idpri != null ? idpri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prioridad)) {
            return false;
        }
        Prioridad other = (Prioridad) object;
        if ((this.idpri == null && other.idpri != null) || (this.idpri != null && !this.idpri.equals(other.idpri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Prioridad[ idpri=" + idpri + " ]";
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    
}
