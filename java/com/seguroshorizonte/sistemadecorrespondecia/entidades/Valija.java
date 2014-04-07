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
@Table(name = "VALIJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valija.findAll", query = "SELECT v FROM Valija v"),
    @NamedQuery(name = "Valija.findByIdval", query = "SELECT v FROM Valija v WHERE v.idval = :idval"),
    @NamedQuery(name = "Valija.findByIdYAnio", query = "SELECT v FROM Valija v WHERE v.idval = :idval AND SUBSTRING( v.fechaval , 7, 2) = :anio AND v.destinoval.nombresed = :sede  AND (v.statusval = '0' OR v.statusval = '4')"),
    @NamedQuery(name = "Valija.findByIdvalXentregar", query = "SELECT v FROM Valija v WHERE v.idval = :idval AND v.destinoval.nombresed = :sede  AND (v.statusval = :status1 OR v.statusval = :status2)"),
    @NamedQuery(name = "Valija.findByOrigenval", query = "SELECT v FROM Valija v WHERE v.origenval = :origenval"),
    @NamedQuery(name = "Valija.findByAsuntoval", query = "SELECT v FROM Valija v WHERE v.asuntoval = :asuntoval"),
    @NamedQuery(name = "Valija.findByFechaval", query = "SELECT v FROM Valija v WHERE v.fechaval = :fechaval"),
    @NamedQuery(name = "Valija.findByStatusval", query = "SELECT v FROM Valija v WHERE v.statusval ='5' AND ( v.iduse.iduse= :iduse OR v.idruse.iduse = :iduser) "),
    @NamedQuery(name = "Valija.maxVal", query = "SELECT MAX(v.idval) FROM Valija v WHERE v.iduse.idusu.idusu = :idusu"),
    @NamedQuery(name = "Valija.totalValijasEnviadasXSede", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin AND v.iduse.idsed = :idsed"),
    @NamedQuery(name = "Valija.totalValijasEnviadas", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin"),
    @NamedQuery(name = "Valija.totalValijasRecibidasXSede", query = "SELECT v FROM Valija v WHERE v.fecharval BETWEEN :fechaIni AND :fechaFin AND v.iduse.idsed = :idsed AND v.statusval='1'"),
    @NamedQuery(name = "Valija.totalValijasRecibidas", query = "SELECT v FROM Valija v WHERE v.fecharval BETWEEN :fechaIni AND :fechaFin AND v.statusval='1'"),
    @NamedQuery(name = "Valija.totalValijasAnuladasXSede", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin AND v.statusval='5' AND v.iduse.idsed = :idsed"),
    @NamedQuery(name = "Valija.totalValijasAnuladas", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin AND v.statusval='5' "),
    @NamedQuery(name = "Valija.totalValijasErradasXSede", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin AND v.iduse.idsed = :idsed AND (v.statusval='2' OR v.statusval='3' OR v.statusval='4')"),
    @NamedQuery(name = "Valija.totalValijasErradas", query = "SELECT v FROM Valija v WHERE v.fechaval BETWEEN :fechaIni AND :fechaFin AND ( v.statusval='2' OR v.statusval='3' OR v.statusval='4') ")})
public class Valija implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ORIGENVAL")
    private BigDecimal origenval;
    @JoinColumn(name = "IDRUSE", referencedColumnName = "IDUSE")
    @ManyToOne
    private Usuariosede idruse;
    @Size(max = 20)
    @Column(name = "TIPOVAL")
    private String tipoval;
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
    @Column(name = "FECHARVAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharval;
    @Size(max = 20)
    @Column(name = "STATUSVAL")
    private String statusval;
    @Size(max = 20)
    @Column(name = "CODPROVEEDORVAL")
    private String codproveedorval;
    @JoinColumn(name = "IDUSE", referencedColumnName = "IDUSE")
    @ManyToOne(optional = false)
    private Usuariosede iduse;
    @JoinColumn(name = "DESTINOVAL", referencedColumnName = "IDSED")
    @ManyToOne(optional = false)
    private Sede destinoval;
    @JoinColumn(name = "IDPRO", referencedColumnName = "IDPRO")
    @ManyToOne
    private Proveedor idpro;

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

    public String getStatusval() {
        return statusval;
    }

    public void setStatusval(String statusval) {
        this.statusval = statusval;
    }

    public String getCodproveedorval() {
        return codproveedorval;
    }

    public void setCodproveedorval(String codproveedorval) {
        this.codproveedorval = codproveedorval;
    }

    public Usuariosede getIduse() {
        return iduse;
    }

    public void setIduse(Usuariosede iduse) {
        this.iduse = iduse;
    }

    public Sede getDestinoval() {
        return destinoval;
    }

    public void setDestinoval(Sede destinoval) {
        this.destinoval = destinoval;
    }

    public Proveedor getIdpro() {
        return idpro;
    }

    public void setIdpro(Proveedor idpro) {
        this.idpro = idpro;
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

    public Date getFecharval() {
        return fecharval;
    }

    public void setFecharval(Date fecharval) {
        this.fecharval = fecharval;
    }

    public String getTipoval() {
        return tipoval;
    }

    public void setTipoval(String tipoval) {
        this.tipoval = tipoval;
    }

    public BigDecimal getOrigenval() {
        return origenval;
    }

    public void setOrigenval(BigDecimal origenval) {
        this.origenval = origenval;
    }

    public Usuariosede getIdruse() {
        return idruse;
    }

    public void setIdruse(Usuariosede idruse) {
        this.idruse = idruse;
    }
}
