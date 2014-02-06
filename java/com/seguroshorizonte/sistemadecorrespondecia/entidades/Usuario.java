/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusu", query = "SELECT u FROM Usuario u WHERE u.idusu = :idusu"),
    @NamedQuery(name = "Usuario.findByNombreusu", query = "SELECT u FROM Usuario u WHERE u.nombreusu = :nombreusu"),
    @NamedQuery(name = "Usuario.findByApellidousu", query = "SELECT u FROM Usuario u WHERE u.apellidousu = :apellidousu"),
    @NamedQuery(name = "Usuario.findByCorreousu", query = "SELECT u FROM Usuario u WHERE u.correousu = :correousu"),
    @NamedQuery(name = "Usuario.findByUserusu", query = "SELECT u FROM Usuario u WHERE u.userusu = :userusu"),
    @NamedQuery(name = "Usuario.findByPasswordusu", query = "SELECT u FROM Usuario u WHERE u.passwordusu = :passwordusu"),
    @NamedQuery(name = "Usuario.findByStatususu", query = "SELECT u FROM Usuario u WHERE u.statususu = :statususu"),
    @NamedQuery(name = "Usuario.findByTelefonousu", query = "SELECT u FROM Usuario u WHERE u.telefonousu = :telefonousu"),
    @NamedQuery(name = "Usuario.findByTelefono2usu", query = "SELECT u FROM Usuario u WHERE u.telefono2usu = :telefono2usu"),
    @NamedQuery(name = "Usuario.findByDireccionusu", query = "SELECT u FROM Usuario u WHERE u.direccionusu = :direccionusu"),
    @NamedQuery(name = "Usuario.findByDireccion2usu", query = "SELECT u FROM Usuario u WHERE u.direccion2usu = :direccion2usu")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDUSU")
    private String idusu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBREUSU")
    private String nombreusu;
    @Size(max = 20)
    @Column(name = "APELLIDOUSU")
    private String apellidousu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CORREOUSU")
    private String correousu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERUSU")
    private String userusu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORDUSU")
    private String passwordusu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUSUSU")
    private String statususu;
    @Size(max = 20)
    @Column(name = "TELEFONOUSU")
    private String telefonousu;
    @Size(max = 20)
    @Column(name = "TELEFONO2USU")
    private String telefono2usu;
    @Size(max = 2500)
    @Column(name = "DIRECCIONUSU")
    private String direccionusu;
    @Size(max = 2500)
    @Column(name = "DIRECCION2USU")
    private String direccion2usu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Valija> valijaCollection;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")
    @ManyToOne(optional = false)
    private Rol idrol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Bitacora> bitacoraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinopaq")
    private Collection<Paquete> paqueteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origenpaq")
    private Collection<Paquete> paqueteCollection1;

    public Usuario() {
    }

    public Usuario(String idusu) {
        this.idusu = idusu;
    }

    public Usuario(String idusu, String nombreusu, String correousu, String userusu, String passwordusu, String statususu) {
        this.idusu = idusu;
        this.nombreusu = nombreusu;
        this.correousu = correousu;
        this.userusu = userusu;
        this.passwordusu = passwordusu;
        this.statususu = statususu;
    }

    public String getIdusu() {
        return idusu;
    }

    public void setIdusu(String idusu) {
        this.idusu = idusu;
    }

    public String getNombreusu() {
        return nombreusu;
    }

    public void setNombreusu(String nombreusu) {
        this.nombreusu = nombreusu;
    }

    public String getApellidousu() {
        return apellidousu;
    }

    public void setApellidousu(String apellidousu) {
        this.apellidousu = apellidousu;
    }

    public String getCorreousu() {
        return correousu;
    }

    public void setCorreousu(String correousu) {
        this.correousu = correousu;
    }

    public String getUserusu() {
        return userusu;
    }

    public void setUserusu(String userusu) {
        this.userusu = userusu;
    }

    public String getPasswordusu() {
        return passwordusu;
    }

    public void setPasswordusu(String passwordusu) {
        this.passwordusu = passwordusu;
    }

    public String getStatususu() {
        return statususu;
    }

    public void setStatususu(String statususu) {
        this.statususu = statususu;
    }

    public String getTelefonousu() {
        return telefonousu;
    }

    public void setTelefonousu(String telefonousu) {
        this.telefonousu = telefonousu;
    }

    public String getTelefono2usu() {
        return telefono2usu;
    }

    public void setTelefono2usu(String telefono2usu) {
        this.telefono2usu = telefono2usu;
    }

    public String getDireccionusu() {
        return direccionusu;
    }

    public void setDireccionusu(String direccionusu) {
        this.direccionusu = direccionusu;
    }

    public String getDireccion2usu() {
        return direccion2usu;
    }

    public void setDireccion2usu(String direccion2usu) {
        this.direccion2usu = direccion2usu;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<Valija> getValijaCollection() {
        return valijaCollection;
    }

    public void setValijaCollection(Collection<Valija> valijaCollection) {
        this.valijaCollection = valijaCollection;
    }

    public Sede getIdsed() {
        return idsed;
    }

    public void setIdsed(Sede idsed) {
        this.idsed = idsed;
    }

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
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

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection1() {
        return paqueteCollection1;
    }

    public void setPaqueteCollection1(Collection<Paquete> paqueteCollection1) {
        this.paqueteCollection1 = paqueteCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusu != null ? idusu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusu == null && other.idusu != null) || (this.idusu != null && !this.idusu.equals(other.idusu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario[ idusu=" + idusu + " ]";
    }
    
}
