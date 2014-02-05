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
@Table(name = "BITACORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByIdbit", query = "SELECT b FROM Bitacora b WHERE b.idbit = :idbit"),
    @NamedQuery(name = "Bitacora.findByAccionbit", query = "SELECT b FROM Bitacora b WHERE b.accionbit = :accionbit"),
    @NamedQuery(name = "Bitacora.findByFechabit", query = "SELECT b FROM Bitacora b WHERE b.fechabit = :fechabit"),
    @NamedQuery(name = "Bitacora.findByHorabit", query = "SELECT b FROM Bitacora b WHERE b.horabit = :horabit"),
    @NamedQuery(name = "Bitacora.findByObservacionbit", query = "SELECT b FROM Bitacora b WHERE b.observacionbit = :observacionbit")})
public class Bitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDBIT")
    private String idbit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCIONBIT")
    private String accionbit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHABIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabit;
    @Size(max = 20)
    @Column(name = "HORABIT")
    private String horabit;
    @Size(max = 20)
    @Column(name = "OBSERVACIONBIT")
    private String observacionbit;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario idusu;

    public Bitacora() {
    }

    public Bitacora(String idbit) {
        this.idbit = idbit;
    }

    public Bitacora(String idbit, String accionbit, Date fechabit) {
        this.idbit = idbit;
        this.accionbit = accionbit;
        this.fechabit = fechabit;
    }

    public String getIdbit() {
        return idbit;
    }

    public void setIdbit(String idbit) {
        this.idbit = idbit;
    }

    public String getAccionbit() {
        return accionbit;
    }

    public void setAccionbit(String accionbit) {
        this.accionbit = accionbit;
    }

    public Date getFechabit() {
        return fechabit;
    }

    public void setFechabit(Date fechabit) {
        this.fechabit = fechabit;
    }

    public String getHorabit() {
        return horabit;
    }

    public void setHorabit(String horabit) {
        this.horabit = horabit;
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
    
}
