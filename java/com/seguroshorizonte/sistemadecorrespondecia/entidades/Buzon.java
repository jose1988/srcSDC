/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "BUZON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buzon.findAll", query = "SELECT b FROM Buzon b"),
    @NamedQuery(name = "Buzon.findByIdbuz", query = "SELECT b FROM Buzon b WHERE b.idbuz = :idbuz"),
    @NamedQuery(name = "Buzon.findByTipobuz", query = "SELECT b FROM Buzon b WHERE b.tipobuz = :tipobuz"),
    @NamedQuery(name = "Buzon.findByObservacionbuz", query = "SELECT b FROM Buzon b WHERE b.observacionbuz = :observacionbuz"),
    @NamedQuery(name = "Buzon.findByTelefonobuz", query = "SELECT b FROM Buzon b WHERE b.telefonobuz = :telefonobuz"),
    @NamedQuery(name = "Buzon.findByUsuario", query = "SELECT b FROM Buzon b WHERE b.idusu = :idusu"),
    @NamedQuery(name = "Buzon.findByNombrebuz", query = "SELECT b FROM Buzon b WHERE b.nombrebuz = :nombrebuz")})
public class Buzon implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDBUZ")
    private BigDecimal idbuz;
    @Size(max = 20)
    @Column(name = "TIPOBUZ")
    private String tipobuz;
    @Size(max = 2500)
    @Column(name = "OBSERVACIONBUZ")
    private String observacionbuz;
    @Size(max = 20)
    @Column(name = "TELEFONOBUZ")
    private String telefonobuz;
    @Size(max = 200)
    @Column(name = "NOMBREBUZ")
    private String nombrebuz;
    @JoinColumn(name = "IDUSUBUZ", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusubuz;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusu;

    public Buzon() {
    }

    public Buzon(BigDecimal idbuz) {
        this.idbuz = idbuz;
    }

    public BigDecimal getIdbuz() {
        return idbuz;
    }

    public void setIdbuz(BigDecimal idbuz) {
        this.idbuz = idbuz;
    }

    public String getTipobuz() {
        return tipobuz;
    }

    public void setTipobuz(String tipobuz) {
        this.tipobuz = tipobuz;
    }

    public String getObservacionbuz() {
        return observacionbuz;
    }

    public void setObservacionbuz(String observacionbuz) {
        this.observacionbuz = observacionbuz;
    }

    public String getTelefonobuz() {
        return telefonobuz;
    }

    public void setTelefonobuz(String telefonobuz) {
        this.telefonobuz = telefonobuz;
    }

    public String getNombrebuz() {
        return nombrebuz;
    }

    public void setNombrebuz(String nombrebuz) {
        this.nombrebuz = nombrebuz;
    }

    public Usuario getIdusubuz() {
        return idusubuz;
    }

    public void setIdusubuz(Usuario idusubuz) {
        this.idusubuz = idusubuz;
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
        hash += (idbuz != null ? idbuz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buzon)) {
            return false;
        }
        Buzon other = (Buzon) object;
        if ((this.idbuz == null && other.idbuz != null) || (this.idbuz != null && !this.idbuz.equals(other.idbuz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon[ idbuz=" + idbuz + " ]";
    }
}
