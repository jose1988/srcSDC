/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BANDEJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bandeja.findAll", query = "SELECT b FROM Bandeja b"),
    @NamedQuery(name = "Bandeja.findByIdban", query = "SELECT b FROM Bandeja b WHERE b.idban = :idban"),
    @NamedQuery(name = "Bandeja.findByLeidoban", query = "SELECT b FROM Bandeja b WHERE b.leidoban = :leidoban"),
    @NamedQuery(name = "Bandeja.findByIdiba", query = "SELECT b FROM Bandeja b WHERE b.idiba = :idiba")})
public class Bandeja implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDBAN")
    private BigDecimal idban;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LEIDOBAN")
    private String leidoban;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDIBA")
    private BigInteger idiba;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusu;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaq;
    @JoinColumn(name = "IDBAN", referencedColumnName = "IDIBA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Infobandeja infobandeja;

    public Bandeja() {
    }

    public Bandeja(BigDecimal idban) {
        this.idban = idban;
    }

    public Bandeja(BigDecimal idban, String leidoban, BigInteger idiba) {
        this.idban = idban;
        this.leidoban = leidoban;
        this.idiba = idiba;
    }

    public BigDecimal getIdban() {
        return idban;
    }

    public void setIdban(BigDecimal idban) {
        this.idban = idban;
    }

    public String getLeidoban() {
        return leidoban;
    }

    public void setLeidoban(String leidoban) {
        this.leidoban = leidoban;
    }

    public BigInteger getIdiba() {
        return idiba;
    }

    public void setIdiba(BigInteger idiba) {
        this.idiba = idiba;
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

    public Infobandeja getInfobandeja() {
        return infobandeja;
    }

    public void setInfobandeja(Infobandeja infobandeja) {
        this.infobandeja = infobandeja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idban != null ? idban.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bandeja)) {
            return false;
        }
        Bandeja other = (Bandeja) object;
        if ((this.idban == null && other.idban != null) || (this.idban != null && !this.idban.equals(other.idban))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja[ idban=" + idban + " ]";
    }
    
}
