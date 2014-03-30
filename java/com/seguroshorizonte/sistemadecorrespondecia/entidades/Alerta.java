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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "ALERTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerta.findAll", query = "SELECT a FROM Alerta a"),
    @NamedQuery(name = "Alerta.findByIdale", query = "SELECT a FROM Alerta a WHERE a.idale = :idale"),
    @NamedQuery(name = "Alerta.findPaquetesVencidosXOrigen", query = "SELECT a.seguimiento.idpaq FROM Alerta a WHERE a.seguimiento.statusseg = '0' AND a.seguimiento.idpaq.origenpaq.idusu.idusu = :origen AND a.seguimiento.idpaq.origenpaq.idatr.idsed.idsed = :idsed AND a.seguimiento.idpaq.statuspaq = '0' "),
    @NamedQuery(name = "Alerta.findPaquetesVencidosXDestino", query = "SELECT a.seguimiento.idpaq FROM Alerta a WHERE a.seguimiento.statusseg = '0' AND a.seguimiento.idpaq.destinopaq.idusu.idusu = :destino AND a.seguimiento.idpaq.destinopaq.idatr.idsed.idsed = :idsed AND a.seguimiento.idpaq.statuspaq = '0' "),
    @NamedQuery(name = "Alerta.findPaquetesVencidosXSeguimiento", query = "SELECT a.seguimiento.idpaq FROM Alerta a WHERE a.seguimiento.statusseg='0' AND a.seguimiento.iduse= :usuarioSede  ANd a.seguimiento.idpaq.statuspaq ='0'"),
    @NamedQuery(name = "Alerta.findByDescripcionale", query = "SELECT a FROM Alerta a WHERE a.descripcionale = :descripcionale")})
public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDALE")
    private BigDecimal idale;
    @Size(max = 500)
    @Column(name = "DESCRIPCIONALE")
    private String descripcionale;
    @JoinColumn(name = "IDALE", referencedColumnName = "IDSEG", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Seguimiento seguimiento;

    public Alerta() {
    }

    public Alerta(BigDecimal idale) {
        this.idale = idale;
    }

    public BigDecimal getIdale() {
        return idale;
    }

    public void setIdale(BigDecimal idale) {
        this.idale = idale;
    }

    public String getDescripcionale() {
        return descripcionale;
    }

    public void setDescripcionale(String descripcionale) {
        this.descripcionale = descripcionale;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idale != null ? idale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alerta)) {
            return false;
        }
        Alerta other = (Alerta) object;
        if ((this.idale == null && other.idale != null) || (this.idale != null && !this.idale.equals(other.idale))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.Alerta[ idale=" + idale + " ]";
    }
}
