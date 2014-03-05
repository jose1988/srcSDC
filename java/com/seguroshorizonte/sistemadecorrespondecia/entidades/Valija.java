/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Valija.findByAsuntoval", query = "SELECT v FROM Valija v WHERE v.asuntoval = :asuntoval"),
    @NamedQuery(name = "Valija.findByFechaval", query = "SELECT v FROM Valija v WHERE v.fechaval = :fechaval"),
    @NamedQuery(name = "Valija.findByFechaalerval", query = "SELECT v FROM Valija v WHERE v.fechaalerval = :fechaalerval"),
    @NamedQuery(name = "Valija.findByStatusval", query = "SELECT v FROM Valija v WHERE v.statusval = :statusval"),
    @NamedQuery(name = "Valija.findByZoomval", query = "SELECT v FROM Valija v WHERE v.zoomval = :zoomval"),
    @NamedQuery(name = "Valija.findByNoProcesadas", query = "SELECT v FROM Valija v WHERE v.statusval = :statusval1 OR v.statusval = :statusval2"),
    @NamedQuery(name = "Valija.maxVal", query = "SELECT MAX(v.idval) FROM Valija v WHERE v.idusu.idusu = :idusu"),
    @NamedQuery(name = "Valija.findByProcesadas", query = "SELECT v FROM Valija v WHERE v.statusval = :statusval"),
 @NamedQuery(name = "Valija.findByFechavalYUsuario", query = "SELECT v FROM Valija v WHERE v.fechaval = :fechaval AND v.idusu = :idusu")})

public class Valija implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORIGENVAL")
    private BigDecimal origenval;
    @JoinColumn(name = "IDINC", referencedColumnName = "IDINC")
    @ManyToOne
    private Incidente idinc;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VALIJA")
    @SequenceGenerator(name = "SEQ_VALIJA", sequenceName = "SEQ_VALIJA", allocationSize = 1)
    @Id
    @Basic(optional = false)
    
    @Column(name = "IDVAL")
    private BigDecimal idval;
    @Size(max = 20)
    @Column(name = "ASUNTOVAL")
    private String asuntoval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaval;
    @Column(name = "FECHAALERVAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaalerval;
    @Size(max = 20)
    @Column(name = "STATUSVAL")
    private String statusval;
    @Size(max = 20)
    @Column(name = "ZOOMVAL")
    private String zoomval;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario idusu;
    @JoinColumn(name = "DESTINOVAL", referencedColumnName = "IDSED")
    @ManyToOne(optional = false)
    private Sede destinoval;
    @OneToMany(mappedBy = "idval")
    private Collection<Paquete> paqueteCollection;

    public Valija() {
    }

    public Valija(BigDecimal idval) {
        this.idval = idval;
    }

    public Valija(BigDecimal idval, BigDecimal origenval, Date fechaval) {
        this.idval = idval;
        this.origenval = origenval;
        this.fechaval = fechaval;
    }

    public BigDecimal getIdval() {
        return idval;
    }

    public void setIdval(BigDecimal idval) {
        this.idval = idval;
    }

    public String getAsuntoval() {
        return asuntoval;
    }

    public void setAsuntoval(String asuntoval) {
        this.asuntoval = asuntoval;
    }

    public Date getFechaval() {
        return fechaval;
    }

    public void setFechaval(Date fechaval) {
        this.fechaval = fechaval;
    }

    public Date getFechaalerval() {
        return fechaalerval;
    }

    public void setFechaalerval(Date fechaalerval) {
        this.fechaalerval = fechaalerval;
    }

    public String getStatusval() {
        return statusval;
    }

    public void setStatusval(String statusval) {
        this.statusval = statusval;
    }

    public String getZoomval() {
        return zoomval;
    }

    public void setZoomval(String zoomval) {
        this.zoomval = zoomval;
    }

    public Usuario getIdusu() {
        return idusu;
    }

    public void setIdusu(Usuario idusu) {
        this.idusu = idusu;
    }

    public Sede getDestinoval() {
        return destinoval;
    }

    public void setDestinoval(Sede destinoval) {
        this.destinoval = destinoval;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
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

    public Incidente getIdinc() {
        return idinc;
    }

    public void setIdinc(Incidente idinc) {
        this.idinc = idinc;
    }

    public BigDecimal getOrigenval() {
        return origenval;
    }

    public void setOrigenval(BigDecimal origenval) {
        this.origenval = origenval;
    }
    
}
