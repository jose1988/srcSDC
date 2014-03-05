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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "SEDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"),
    @NamedQuery(name = "Sede.findByIdsed", query = "SELECT s FROM Sede s WHERE s.idsed = :idsed"),
    @NamedQuery(name = "Sede.findByNombresed", query = "SELECT s FROM Sede s WHERE s.nombresed = :nombresed"),
    @NamedQuery(name = "Sede.findByDescripcionsed", query = "SELECT s FROM Sede s WHERE s.descripcionsed = :descripcionsed"),
    @NamedQuery(name = "Sede.findByDireccionsed", query = "SELECT s FROM Sede s WHERE s.direccionsed = :direccionsed"),
    @NamedQuery(name = "Sede.findByTelefonosed", query = "SELECT s FROM Sede s WHERE s.telefonosed = :telefonosed"),
    @NamedQuery(name = "Sede.findByTelefono2sed", query = "SELECT s FROM Sede s WHERE s.telefono2sed = :telefono2sed")})
public class Sede implements Serializable {
    @OneToMany(mappedBy = "idsed")
    private Collection<Bitacora> bitacoraCollection;
    @OneToMany(mappedBy = "idsed")
    private Collection<Paquete> paqueteCollection;
    @OneToMany(mappedBy = "idsed")
    private Collection<Buzon> buzonCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSED")
    private BigDecimal idsed;
    @Size(max = 20)
    @Column(name = "NOMBRESED")
    private String nombresed;
    @Size(max = 20)
    @Column(name = "DESCRIPCIONSED")
    private String descripcionsed;
    @Size(max = 20)
    @Column(name = "DIRECCIONSED")
    private String direccionsed;
    @Size(max = 20)
    @Column(name = "TELEFONOSED")
    private String telefonosed;
    @Size(max = 20)
    @Column(name = "TELEFONO2SED")
    private String telefono2sed;
    @OneToMany(mappedBy = "idsed")
    private Collection<Usuariosede> usuariosedeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinoval")
    private Collection<Valija> valijaCollection;

    public Sede() {
    }

    public Sede(BigDecimal idsed) {
        this.idsed = idsed;
    }

    public BigDecimal getIdsed() {
        return idsed;
    }

    public void setIdsed(BigDecimal idsed) {
        this.idsed = idsed;
    }

    public String getNombresed() {
        return nombresed;
    }

    public void setNombresed(String nombresed) {
        this.nombresed = nombresed;
    }

    public String getDescripcionsed() {
        return descripcionsed;
    }

    public void setDescripcionsed(String descripcionsed) {
        this.descripcionsed = descripcionsed;
    }

    public String getDireccionsed() {
        return direccionsed;
    }

    public void setDireccionsed(String direccionsed) {
        this.direccionsed = direccionsed;
    }

    public String getTelefonosed() {
        return telefonosed;
    }

    public void setTelefonosed(String telefonosed) {
        this.telefonosed = telefonosed;
    }

    public String getTelefono2sed() {
        return telefono2sed;
    }

    public void setTelefono2sed(String telefono2sed) {
        this.telefono2sed = telefono2sed;
    }

    @XmlTransient
    public Collection<Usuariosede> getUsuariosedeCollection() {
        return usuariosedeCollection;
    }

    public void setUsuariosedeCollection(Collection<Usuariosede> usuariosedeCollection) {
        this.usuariosedeCollection = usuariosedeCollection;
    }

    @XmlTransient
    public Collection<Valija> getValijaCollection() {
        return valijaCollection;
    }

    public void setValijaCollection(Collection<Valija> valijaCollection) {
        this.valijaCollection = valijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsed != null ? idsed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.idsed == null && other.idsed != null) || (this.idsed != null && !this.idsed.equals(other.idsed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede[ idsed=" + idsed + " ]";
    }

    @XmlTransient
    public Collection<Buzon> getBuzonCollection() {
        return buzonCollection;
    }

    public void setBuzonCollection(Collection<Buzon> buzonCollection) {
        this.buzonCollection = buzonCollection;
    }

    @XmlTransient
    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }
    
}
