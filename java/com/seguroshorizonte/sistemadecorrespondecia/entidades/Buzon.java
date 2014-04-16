/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Buzon.findByTelefonobuz", query = "SELECT b FROM Buzon b WHERE b.telefonobuz = :telefonobuz"),
    @NamedQuery(name = "Buzon.findExternoByNombreUsuario", query = "SELECT b FROM Buzon b WHERE b.nombrebuz = :nombre AND b.idusu = :idusu"),
    @NamedQuery(name = "Buzon.findByNombrebuz", query = "SELECT b FROM Buzon b WHERE b.nombrebuz = :nombrebuz"),
    @NamedQuery(name = "Buzon.findByUsuarioSede", query = "SELECT b FROM Buzon b WHERE b.idatr.idsed.idsed = :idsed AND b.idusu.idusu= :idusu"),
    @NamedQuery(name = "Buzon.findByNASA", query = "SELECT b FROM Usuario u, Buzon b WHERE u.idusu=b.idusu.idusu and upper(u.nombreusu) like :nombre and upper(u.apellidousu) like :apellido and b.idatr.idatr= :area and b.tipobuz='0' AND b.idbuz != :idbuz AND b.idbuz != :idbuz AND b.idatr.idsed.idsed != :idsed"),
    @NamedQuery(name = "Buzon.findByNAS", query = "SELECT b FROM Usuario u, Buzon b WHERE u.idusu=b.idusu.idusu and upper(u.nombreusu) like :nombre and upper(u.apellidousu) like :apellido and b.tipobuz='0' AND b.idbuz != :idbuz AND b.idatr.idsed.idsed != :idsed"),
    @NamedQuery(name = "Buzon.findByNBE", query = "SELECT b FROM Usuario u, Buzon b WHERE u.idusu=b.idusu.idusu and upper(b.nombrebuz) like :nombre and b.tipobuz='1' AND b.idusu = :idusu "),
    @NamedQuery(name = "Buzon.findMinIdMyBuzon", query = "SELECT MIN(b.idbuz) FROM  Buzon b WHERE b.idusu = :idusu"),
    @NamedQuery(name = "Buzon.findByUsuario", query = "SELECT b FROM Buzon b WHERE b.idusu.idusu = :idusu")})
public class Buzon implements Serializable {

    @Size(max = 20)
    @Column(name = "IDENTIFICACIONBUZ")
    private String identificacionbuz;
    @Size(max = 20)
    @Column(name = "CORREOBUZ")
    private String correobuz;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinopaq")
    private Collection<Paquete> paqueteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origenpaq")
    private Collection<Paquete> paqueteCollection1;
    @Size(max = 20)
    @Column(name = "BORRADOBUZ")
    private String borradobuz;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BUZON")
    @SequenceGenerator(name = "SEQ_BUZON", sequenceName = "SEQ_BUZON", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDBUZ")
    private BigDecimal idbuz;
    @Size(max = 20)
    @Column(name = "TIPOBUZ")
    private String tipobuz;
    @Size(max = 2500)
    @Column(name = "DIRECCIONBUZ")
    private String direccionbuz;
    @Size(max = 20)
    @Column(name = "TELEFONOBUZ")
    private String telefonobuz;
    @Size(max = 200)
    @Column(name = "NOMBREBUZ")
    private String nombrebuz;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusu;
    @JoinColumn(name = "IDATR", referencedColumnName = "IDATR")
    @ManyToOne
    private Areatrabajo idatr;

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

    public String getDireccionbuz() {
        return direccionbuz;
    }

    public void setDireccionbuz(String direccionbuz) {
        this.direccionbuz = direccionbuz;
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

    public Usuario getIdusu() {
        return idusu;
    }

    public void setIdusu(Usuario idusu) {
        this.idusu = idusu;
    }

    public Areatrabajo getIdatr() {
        return idatr;
    }

    public void setIdatr(Areatrabajo idatr) {
        this.idatr = idatr;
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

    public String getBorradobuz() {
        return borradobuz;
    }

    public void setBorradobuz(String borradobuz) {
        this.borradobuz = borradobuz;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection1() {
        return paqueteCollection1;
    }

    public void setPaqueteCollection1(Collection<Paquete> paqueteCollection1) {
        this.paqueteCollection1 = paqueteCollection1;
    }

    public String getIdentificacionbuz() {
        return identificacionbuz;
    }

    public void setIdentificacionbuz(String identificacionbuz) {
        this.identificacionbuz = identificacionbuz;
    }

    public String getCorreobuz() {
        return correobuz;
    }

    public void setCorreobuz(String correobuz) {
        this.correobuz = correobuz;
    }
}
