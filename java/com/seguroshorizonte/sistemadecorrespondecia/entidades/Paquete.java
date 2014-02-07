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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdpaq", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq"),
    @NamedQuery(name = "Paquete.findByAsuntopaq", query = "SELECT p FROM Paquete p WHERE p.asuntopaq = :asuntopaq"),
    @NamedQuery(name = "Paquete.findByTextopaq", query = "SELECT p FROM Paquete p WHERE p.textopaq = :textopaq"),
    @NamedQuery(name = "Paquete.findByFechapaq", query = "SELECT p FROM Paquete p WHERE p.fechapaq = :fechapaq"),
    @NamedQuery(name = "Paquete.findByFechaenviopaq", query = "SELECT p FROM Paquete p WHERE p.fechaenviopaq = :fechaenviopaq"),
    @NamedQuery(name = "Paquete.findByFechaapaq", query = "SELECT p FROM Paquete p WHERE p.fechaapaq = :fechaapaq"),
    @NamedQuery(name = "Paquete.findByStatuspaq", query = "SELECT p FROM Paquete p WHERE p.statuspaq = :statuspaq"),
    @NamedQuery(name = "Paquete.findByLocalizacionpaq", query = "SELECT p FROM Paquete p WHERE p.localizacionpaq = :localizacionpaq"),
    @NamedQuery(name = "Paquete.findByIdadj", query = "SELECT p FROM Paquete p WHERE p.idadj = :idadj"),
    @NamedQuery(name = "Paquete.findByRespaq", query = "SELECT p FROM Paquete p WHERE p.respaq = :respaq")})
public class Paquete implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPAQ")
    private BigDecimal idpaq;
    @Size(max = 200)
    @Column(name = "ASUNTOPAQ")
    private String asuntopaq;
    @Size(max = 4000)
    @Column(name = "TEXTOPAQ")
    private String textopaq;
    @Column(name = "FECHAPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapaq;
    @Column(name = "FECHAENVIOPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaenviopaq;
    @Column(name = "FECHAAPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaapaq;
    @Size(max = 20)
    @Column(name = "STATUSPAQ")
    private String statuspaq;
    @Size(max = 250)
    @Column(name = "LOCALIZACIONPAQ")
    private String localizacionpaq;
    @Column(name = "IDADJ")
    private BigInteger idadj;
    @Size(max = 20)
    @Column(name = "RESPAQ")
    private String respaq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaq")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Adjunto> adjuntoCollection;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Bandeja> bandejaCollection;
    @JoinColumn(name = "IDVAL", referencedColumnName = "IDVAL")
    @ManyToOne
    private Valija idval;
    @JoinColumn(name = "ORIGENPAQ", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario origenpaq;
    @JoinColumn(name = "DESTINOPAQ", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario destinopaq;
    @JoinColumn(name = "IDPRI", referencedColumnName = "IDPRI")
    @ManyToOne
    private Prioridad idpri;
    @JoinColumn(name = "IDMEN", referencedColumnName = "IDMEN")
    @ManyToOne
    private Mensaje idmen;
    @JoinColumn(name = "IDDOC", referencedColumnName = "IDDOC")
    @ManyToOne(optional = false)
    private Documento iddoc;

    public Paquete() {
    }

    public Paquete(BigDecimal idpaq) {
        this.idpaq = idpaq;
    }

    public BigDecimal getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(BigDecimal idpaq) {
        this.idpaq = idpaq;
    }

    public String getAsuntopaq() {
        return asuntopaq;
    }

    public void setAsuntopaq(String asuntopaq) {
        this.asuntopaq = asuntopaq;
    }

    public String getTextopaq() {
        return textopaq;
    }

    public void setTextopaq(String textopaq) {
        this.textopaq = textopaq;
    }

    public Date getFechapaq() {
        return fechapaq;
    }

    public void setFechapaq(Date fechapaq) {
        this.fechapaq = fechapaq;
    }

    public Date getFechaenviopaq() {
        return fechaenviopaq;
    }

    public void setFechaenviopaq(Date fechaenviopaq) {
        this.fechaenviopaq = fechaenviopaq;
    }

    public Date getFechaapaq() {
        return fechaapaq;
    }

    public void setFechaapaq(Date fechaapaq) {
        this.fechaapaq = fechaapaq;
    }

    public String getStatuspaq() {
        return statuspaq;
    }

    public void setStatuspaq(String statuspaq) {
        this.statuspaq = statuspaq;
    }

    public String getLocalizacionpaq() {
        return localizacionpaq;
    }

    public void setLocalizacionpaq(String localizacionpaq) {
        this.localizacionpaq = localizacionpaq;
    }

    public BigInteger getIdadj() {
        return idadj;
    }

    public void setIdadj(BigInteger idadj) {
        this.idadj = idadj;
    }

    public String getRespaq() {
        return respaq;
    }

    public void setRespaq(String respaq) {
        this.respaq = respaq;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<Adjunto> getAdjuntoCollection() {
        return adjuntoCollection;
    }

    public void setAdjuntoCollection(Collection<Adjunto> adjuntoCollection) {
        this.adjuntoCollection = adjuntoCollection;
    }

    @XmlTransient
    public Collection<Bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<Bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    public Valija getIdval() {
        return idval;
    }

    public void setIdval(Valija idval) {
        this.idval = idval;
    }

    public Usuario getOrigenpaq() {
        return origenpaq;
    }

    public void setOrigenpaq(Usuario origenpaq) {
        this.origenpaq = origenpaq;
    }

    public Usuario getDestinopaq() {
        return destinopaq;
    }

    public void setDestinopaq(Usuario destinopaq) {
        this.destinopaq = destinopaq;
    }

    public Prioridad getIdpri() {
        return idpri;
    }

    public void setIdpri(Prioridad idpri) {
        this.idpri = idpri;
    }

    public Mensaje getIdmen() {
        return idmen;
    }

    public void setIdmen(Mensaje idmen) {
        this.idmen = idmen;
    }

    public Documento getIddoc() {
        return iddoc;
    }

    public void setIddoc(Documento iddoc) {
        this.iddoc = iddoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaq != null ? idpaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idpaq == null && other.idpaq != null) || (this.idpaq != null && !this.idpaq.equals(other.idpaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete[ idpaq=" + idpaq + " ]";
    }
    
}
