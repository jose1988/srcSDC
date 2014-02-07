/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByIdseg", query = "SELECT s FROM Seguimiento s WHERE s.idseg = :idseg"),
    @NamedQuery(name = "Seguimiento.findByFechaseg", query = "SELECT s FROM Seguimiento s WHERE s.fechaseg = :fechaseg"),
    @NamedQuery(name = "Seguimiento.findByStatusseg", query = "SELECT s FROM Seguimiento s WHERE s.statusseg = :statusseg")})
public class Seguimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSEG")
    private BigDecimal idseg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHASEG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaseg;
    @Size(max = 20)
    @Column(name = "STATUSSEG")
    private String statusseg;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario idusu;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne(optional = false)
    private Paquete idpaq;

    public Seguimiento() {
    }

    public Seguimiento(BigDecimal idseg) {
        this.idseg = idseg;
    }

    public Seguimiento(BigDecimal idseg, Date fechaseg) {
        this.idseg = idseg;
        this.fechaseg = fechaseg;
    }

    public BigDecimal getIdseg() {
        return idseg;
    }

    public void setIdseg(BigDecimal idseg) {
        this.idseg = idseg;
    }

    public Date getFechaseg() {
        return fechaseg;
    }

    public void setFechaseg(Date fechaseg) {
        this.fechaseg = fechaseg;
    }

    public String getStatusseg() {
        return statusseg;
    }

    public void setStatusseg(String statusseg) {
        this.statusseg = statusseg;
    }

    public Usuario getIdusu() {
        return idusu;
    }

    public void setIdusu(Usuario idusu) {
        this.idusu = idusu;
    }

    public Paquete getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(Paquete idpaq) {
        this.idpaq = idpaq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseg != null ? idseg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.idseg == null && other.idseg != null) || (this.idseg != null && !this.idseg.equals(other.idseg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento[ idseg=" + idseg + " ]";
    }
    
}
