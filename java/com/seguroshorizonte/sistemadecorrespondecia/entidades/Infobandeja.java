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
@Table(name = "INFOBANDEJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infobandeja.findAll", query = "SELECT i FROM Infobandeja i"),
    @NamedQuery(name = "Infobandeja.findByIdiba", query = "SELECT i FROM Infobandeja i WHERE i.idiba = :idiba"),
    @NamedQuery(name = "Infobandeja.findByNombreiba", query = "SELECT i FROM Infobandeja i WHERE i.nombreiba = :nombreiba"),
    @NamedQuery(name = "Infobandeja.findByDescripcioniba", query = "SELECT i FROM Infobandeja i WHERE i.descripcioniba = :descripcioniba"),
    @NamedQuery(name = "Infobandeja.findByIdusu", query = "SELECT i FROM Infobandeja i WHERE i.idusu = :idusu")})
public class Infobandeja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDIBA")
    private String idiba;
    @Size(max = 20)
    @Column(name = "NOMBREIBA")
    private String nombreiba;
    @Size(max = 250)
    @Column(name = "DESCRIPCIONIBA")
    private String descripcioniba;
    @Size(max = 20)
    @Column(name = "IDUSU")
    private String idusu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiba")
    private Collection<Bandeja> bandejaCollection;

    public Infobandeja() {
    }

    public Infobandeja(String idiba) {
        this.idiba = idiba;
    }

    public String getIdiba() {
        return idiba;
    }

    public void setIdiba(String idiba) {
        this.idiba = idiba;
    }

    public String getNombreiba() {
        return nombreiba;
    }

    public void setNombreiba(String nombreiba) {
        this.nombreiba = nombreiba;
    }

    public String getDescripcioniba() {
        return descripcioniba;
    }

    public void setDescripcioniba(String descripcioniba) {
        this.descripcioniba = descripcioniba;
    }

    public String getIdusu() {
        return idusu;
    }

    public void setIdusu(String idusu) {
        this.idusu = idusu;
    }

    @XmlTransient
    public Collection<Bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<Bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idiba != null ? idiba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infobandeja)) {
            return false;
        }
        Infobandeja other = (Infobandeja) object;
        if ((this.idiba == null && other.idiba != null) || (this.idiba != null && !this.idiba.equals(other.idiba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja[ idiba=" + idiba + " ]";
    }
    
}
