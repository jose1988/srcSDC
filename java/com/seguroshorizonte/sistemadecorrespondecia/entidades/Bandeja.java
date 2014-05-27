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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
    @NamedQuery(name = "Bandeja.findByIdban", query = "SELECT b FROM Bandeja b WHERE b.idpaq.idpaq = :idpaq AND b.idusu.idusu= :idusu"),
    @NamedQuery(name = "Bandeja.findByLeidoban", query = "SELECT b FROM Bandeja b WHERE b.leidoban = :leidoban")})
public class Bandeja implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BANDEJA")
    @SequenceGenerator(name = "SEQ_BANDEJA", sequenceName = "SEQ_BANDEJA", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDBAN")
    private BigDecimal idban;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LEIDOBAN")
    private String leidoban;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusu;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaq;
    @JoinColumn(name = "IDIBA", referencedColumnName = "IDIBA")
    @ManyToOne(optional = false)
    private Infobandeja idiba;

    public Bandeja() {
    }

    public Bandeja(BigDecimal idban) {
        this.idban = idban;
    }

    public Bandeja(BigDecimal idban, String leidoban) {
        this.idban = idban;
        this.leidoban = leidoban;
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

    public Infobandeja getIdiba() {
        return idiba;
    }

    public void setIdiba(Infobandeja idiba) {
        this.idiba = idiba;
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
