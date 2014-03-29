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
@Table(name = "ORGANIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizacion.findAll", query = "SELECT o FROM Organizacion o"),
    @NamedQuery(name = "Organizacion.findByIdorg", query = "SELECT o FROM Organizacion o WHERE o.idorg = :idorg"),
    @NamedQuery(name = "Organizacion.findByNombreorg", query = "SELECT o FROM Organizacion o WHERE o.nombreorg = :nombreorg"),
    @NamedQuery(name = "Organizacion.findByDescripcionorg", query = "SELECT o FROM Organizacion o WHERE o.descripcionorg = :descripcionorg")})
public class Organizacion implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idorg")
    private Collection<Sede> sedeCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDORG")
    private BigDecimal idorg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBREORG")
    private String nombreorg;
    @Size(max = 2000)
    @Column(name = "DESCRIPCIONORG")
    private String descripcionorg;

    public Organizacion() {
    }

    public Organizacion(BigDecimal idorg) {
        this.idorg = idorg;
    }

    public Organizacion(BigDecimal idorg, String nombreorg) {
        this.idorg = idorg;
        this.nombreorg = nombreorg;
    }

    public BigDecimal getIdorg() {
        return idorg;
    }

    public void setIdorg(BigDecimal idorg) {
        this.idorg = idorg;
    }

    public String getNombreorg() {
        return nombreorg;
    }

    public void setNombreorg(String nombreorg) {
        this.nombreorg = nombreorg;
    }

    public String getDescripcionorg() {
        return descripcionorg;
    }

    public void setDescripcionorg(String descripcionorg) {
        this.descripcionorg = descripcionorg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorg != null ? idorg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizacion)) {
            return false;
        }
        Organizacion other = (Organizacion) object;
        if ((this.idorg == null && other.idorg != null) || (this.idorg != null && !this.idorg.equals(other.idorg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Organizacion[ idorg=" + idorg + " ]";
    }

    @XmlTransient
    public Collection<Sede> getSedeCollection() {
        return sedeCollection;
    }

    public void setSedeCollection(Collection<Sede> sedeCollection) {
        this.sedeCollection = sedeCollection;
    }
}
