/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "VALIJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valija.findAll", query = "SELECT v FROM Valija v"),
    @NamedQuery(name = "Valija.findByIdval", query = "SELECT v FROM Valija v WHERE v.idval = :idval"),
    @NamedQuery(name = "Valija.findByOrigenval", query = "SELECT v FROM Valija v WHERE v.origenval = :origenval"),
    @NamedQuery(name = "Valija.findByDestinoval", query = "SELECT v FROM Valija v WHERE v.destinoval = :destinoval"),
    @NamedQuery(name = "Valija.findByFechaval", query = "SELECT v FROM Valija v WHERE v.fechaval = :fechaval"),
    @NamedQuery(name = "Valija.findByAsuntoval", query = "SELECT v FROM Valija v WHERE v.asuntoval = :asuntoval"),
    @NamedQuery(name = "Valija.findByIddval", query = "SELECT v FROM Valija v WHERE v.iddval = :iddval")})
public class Valija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDVAL")
    private String idval;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORIGENVAL")
    private String origenval;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESTINOVAL")
    private String destinoval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaval;
    @Size(max = 20)
    @Column(name = "ASUNTOVAL")
    private String asuntoval;
    @Size(max = 20)
    @Column(name = "IDDVAL")
    private String iddval;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario idusu;

    public Valija() {
    }

    public Valija(String idval) {
        this.idval = idval;
    }

    public Valija(String idval, String origenval, String destinoval, Date fechaval) {
        this.idval = idval;
        this.origenval = origenval;
        this.destinoval = destinoval;
        this.fechaval = fechaval;
    }

    public String getIdval() {
        return idval;
    }

    public void setIdval(String idval) {
        this.idval = idval;
    }

    public String getOrigenval() {
        return origenval;
    }

    public void setOrigenval(String origenval) {
        this.origenval = origenval;
    }

    public String getDestinoval() {
        return destinoval;
    }

    public void setDestinoval(String destinoval) {
        this.destinoval = destinoval;
    }

    public Date getFechaval() {
        return fechaval;
    }

    public void setFechaval(Date fechaval) {
        this.fechaval = fechaval;
    }

    public String getAsuntoval() {
        return asuntoval;
    }

    public void setAsuntoval(String asuntoval) {
        this.asuntoval = asuntoval;
    }

    public String getIddval() {
        return iddval;
    }

    public void setIddval(String iddval) {
        this.iddval = iddval;
    }

    public Usuario getIdusu() {
        return idusu;
    }

    public void setIdusu(Usuario idusu) {
        this.idusu = idusu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idval != null ? idval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valija)) {
            return false;
        }
        Valija other = (Valija) object;
        if ((this.idval == null && other.idval != null) || (this.idval != null && !this.idval.equals(other.idval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija[ idval=" + idval + " ]";
    }
    
}
