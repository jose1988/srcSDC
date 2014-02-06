/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByIdmen", query = "SELECT m FROM Mensaje m WHERE m.idmen = :idmen"),
    @NamedQuery(name = "Mensaje.findByNombremen", query = "SELECT m FROM Mensaje m WHERE m.nombremen = :nombremen"),
    @NamedQuery(name = "Mensaje.findByDescripcionmen", query = "SELECT m FROM Mensaje m WHERE m.descripcionmen = :descripcionmen")})
public class Mensaje implements Serializable {
    @OneToMany(mappedBy = "idmen")
    private Collection<Paquete> paqueteCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDMEN")
    private String idmen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBREMEN")
    private String nombremen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESCRIPCIONMEN")
    private String descripcionmen;

    public Mensaje() {
    }

    public Mensaje(String idmen) {
        this.idmen = idmen;
    }

    public Mensaje(String idmen, String nombremen, String descripcionmen) {
        this.idmen = idmen;
        this.nombremen = nombremen;
        this.descripcionmen = descripcionmen;
    }

    public String getIdmen() {
        return idmen;
    }

    public void setIdmen(String idmen) {
        this.idmen = idmen;
    }

    public String getNombremen() {
        return nombremen;
    }

    public void setNombremen(String nombremen) {
        this.nombremen = nombremen;
    }

    public String getDescripcionmen() {
        return descripcionmen;
    }

    public void setDescripcionmen(String descripcionmen) {
        this.descripcionmen = descripcionmen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmen != null ? idmen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.idmen == null && other.idmen != null) || (this.idmen != null && !this.idmen.equals(other.idmen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Mensaje[ idmen=" + idmen + " ]";
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }
    
}
