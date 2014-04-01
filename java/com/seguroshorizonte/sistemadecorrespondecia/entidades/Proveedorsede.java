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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "PROVEEDORSEDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedorsede.findAll", query = "SELECT p FROM Proveedorsede p"),
    @NamedQuery(name = "Proveedorsede.ProveedorfindXSede", query = "SELECT p.idpro FROM Proveedorsede p WHERE p.idsed= :sede"),
    @NamedQuery(name = "Proveedorsede.findByIdpse", query = "SELECT p FROM Proveedorsede p WHERE p.idpse = :idpse")})
public class Proveedorsede implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPSE")
    private BigDecimal idpse;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    @JoinColumn(name = "IDPRO", referencedColumnName = "IDPRO")
    @ManyToOne
    private Proveedor idpro;

    public Proveedorsede() {
    }

    public Proveedorsede(BigDecimal idpse) {
        this.idpse = idpse;
    }

    public BigDecimal getIdpse() {
        return idpse;
    }

    public void setIdpse(BigDecimal idpse) {
        this.idpse = idpse;
    }

    public Sede getIdsed() {
        return idsed;
    }

    public void setIdsed(Sede idsed) {
        this.idsed = idsed;
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
        hash += (idpse != null ? idpse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedorsede)) {
            return false;
        }
        Proveedorsede other = (Proveedorsede) object;
        if ((this.idpse == null && other.idpse != null) || (this.idpse != null && !this.idpse.equals(other.idpse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedorsede[ idpse=" + idpse + " ]";
    }
}
