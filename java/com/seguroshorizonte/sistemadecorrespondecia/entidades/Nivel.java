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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n"),
    @NamedQuery(name = "Nivel.findByIdniv", query = "SELECT n FROM Nivel n WHERE n.idniv = :idniv"),
    @NamedQuery(name = "Nivel.findByOperadorniv", query = "SELECT n FROM Nivel n WHERE n.operadorniv = :operadorniv"),
    @NamedQuery(name = "Nivel.findByTiemponiv", query = "SELECT n FROM Nivel n WHERE n.tiemponiv = :tiemponiv"),
     @NamedQuery(name = "Nivel.findBypri", query = "SELECT n FROM Nivel n WHERE n.idpri.idpri = :idpri"),
   
    @NamedQuery(name = "Nivel.findByDescripcionniv", query = "SELECT n FROM Nivel n WHERE n.descripcionniv = :descripcionniv")})
public class Nivel implements Serializable {

    @Column(name = "TIEMPONIV")
    private BigInteger tiemponiv;
    @JoinColumn(name = "IDPRI", referencedColumnName = "IDPRI")
    @ManyToOne
    private Prioridad idpri;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDNIV")
    private BigDecimal idniv;
    @Size(max = 100)
    @Column(name = "OPERADORNIV")
    private String operadorniv;
    @Size(max = 500)
    @Column(name = "DESCRIPCIONNIV")
    private String descripcionniv;

    public Nivel() {
    }

    public Nivel(BigDecimal idniv) {
        this.idniv = idniv;
    }

    public BigDecimal getIdniv() {
        return idniv;
    }

    public void setIdniv(BigDecimal idniv) {
        this.idniv = idniv;
    }

    public String getOperadorniv() {
        return operadorniv;
    }

    public void setOperadorniv(String operadorniv) {
        this.operadorniv = operadorniv;
    }

    public String getDescripcionniv() {
        return descripcionniv;
    }

    public void setDescripcionniv(String descripcionniv) {
        this.descripcionniv = descripcionniv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idniv != null ? idniv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.idniv == null && other.idniv != null) || (this.idniv != null && !this.idniv.equals(other.idniv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Nivel[ idniv=" + idniv + " ]";
    }

    public BigInteger getTiemponiv() {
        return tiemponiv;
    }

    public void setTiemponiv(BigInteger tiemponiv) {
        this.tiemponiv = tiemponiv;
    }

    public Prioridad getIdpri() {
        return idpri;
    }

    public void setIdpri(Prioridad idpri) {
        this.idpri = idpri;
    }
}
