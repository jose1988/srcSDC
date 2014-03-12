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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "BITACORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByIdbit", query = "SELECT b FROM Bitacora b WHERE b.idbit = :idbit"),
    @NamedQuery(name = "Bitacora.findByAccionbit", query = "SELECT b FROM Bitacora b WHERE b.accionbit = :accionbit"),
    @NamedQuery(name = "Bitacora.findByFechabit", query = "SELECT b FROM Bitacora b WHERE b.fechabit = :fechabit"),
    @NamedQuery(name = "Bitacora.findByObservacionbit", query = "SELECT b FROM Bitacora b WHERE b.observacionbit = :observacionbit")})
public class Bitacora implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BITACORA")
    @SequenceGenerator(name = "SEQ_BITACORA", sequenceName = "SEQ_BITACORA", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDBIT")
    private BigDecimal idbit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHABIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabit;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ACCIONBIT")
    private String accionbit;
    @Size(max = 2000)
    @Column(name = "OBSERVACIONBIT")
    private String observacionbit;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario idusu;

    public Bitacora() {
    }

    public Bitacora(BigDecimal idbit) {
        this.idbit = idbit;
    }

    public Bitacora(BigDecimal idbit, String accionbit, Date fechabit) {
        this.idbit = idbit;
        this.accionbit = accionbit;
        this.fechabit = fechabit;
    }

    public BigDecimal getIdbit() {
        return idbit;
    }

    public void setIdbit(BigDecimal idbit) {
        this.idbit = idbit;
    }

    public String getAccionbit() {
        return accionbit;
    }

    public void setAccionbit(String accionbit) {
        this.accionbit = accionbit;
    }

    public String getObservacionbit() {
        return observacionbit;
    }

    public void setObservacionbit(String observacionbit) {
        this.observacionbit = observacionbit;
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
        hash += (idbit != null ? idbit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idbit == null && other.idbit != null) || (this.idbit != null && !this.idbit.equals(other.idbit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Bitacora[ idbit=" + idbit + " ]";
    }

    public Sede getIdsed() {
        return idsed;
    }

    public void setIdsed(Sede idsed) {
        this.idsed = idsed;
    }

    public Date getFechabit() {
        return fechabit;
    }

    public void setFechabit(Date fechabit) {
        this.fechabit = fechabit;
    }
}
