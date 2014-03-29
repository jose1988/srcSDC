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
    @NamedQuery(name = "Sede.findByDireccionsed", query = "SELECT s FROM Sede s WHERE s.direccionsed = :direccionsed"),
    @NamedQuery(name = "Sede.findByTelefonosed", query = "SELECT s FROM Sede s WHERE s.telefonosed = :telefonosed"),
    @NamedQuery(name = "Sede.findByTelefono2sed", query = "SELECT s FROM Sede s WHERE s.telefono2sed = :telefono2sed")})
public class Sede implements Serializable {

    @Size(max = 20)
    @Column(name = "CODIGOSED")
    private String codigosed;
    @OneToMany(mappedBy = "idsed")
    private Collection<Proveedorsede> proveedorsedeCollection;
    @Size(max = 20)
    @Column(name = "BORRADOSED")
    private String borradosed;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEDE")
    @SequenceGenerator(name = "SEQ_SEDE", sequenceName = "SEQ_SEDE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDSED")
    private BigDecimal idsed;
    @Size(max = 20)
    @Column(name = "NOMBRESED")
    private String nombresed;
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
    @OneToMany(mappedBy = "idsed")
    private Collection<Areatrabajo> areatrabajoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinoval")
    private Collection<Valija> valijaCollection;
    @JoinColumn(name = "IDORG", referencedColumnName = "IDORG")
    @ManyToOne(optional = false)
    private Organizacion idorg;
    @OneToMany(mappedBy = "idsed")
    private Collection<Bitacora> bitacoraCollection;
    @OneToMany(mappedBy = "idsed")
    private Collection<Paquete> paqueteCollection;

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
    public Collection<Areatrabajo> getAreatrabajoCollection() {
        return areatrabajoCollection;
    }

    public void setAreatrabajoCollection(Collection<Areatrabajo> areatrabajoCollection) {
        this.areatrabajoCollection = areatrabajoCollection;
    }

    @XmlTransient
    public Collection<Valija> getValijaCollection() {
        return valijaCollection;
    }

    public void setValijaCollection(Collection<Valija> valijaCollection) {
        this.valijaCollection = valijaCollection;
    }

    public Organizacion getIdorg() {
        return idorg;
    }

    public void setIdorg(Organizacion idorg) {
        this.idorg = idorg;
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

    public String getBorradosed() {
        return borradosed;
    }

    public void setBorradosed(String borradosed) {
        this.borradosed = borradosed;
    }

    public String getCodigosed() {
        return codigosed;
    }

    public void setCodigosed(String codigosed) {
        this.codigosed = codigosed;
    }

    @XmlTransient
    public Collection<Proveedorsede> getProveedorsedeCollection() {
        return proveedorsedeCollection;
    }

    public void setProveedorsedeCollection(Collection<Proveedorsede> proveedorsedeCollection) {
        this.proveedorsedeCollection = proveedorsedeCollection;
    }
}
