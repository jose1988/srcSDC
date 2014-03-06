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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusu", query = "SELECT u FROM Usuario u WHERE u.idusu = :idusu"),
    @NamedQuery(name = "Usuario.findByNombreusu", query = "SELECT u FROM Usuario u WHERE u.nombreusu = :nombreusu"),
    @NamedQuery(name = "Usuario.findByApellidousu", query = "SELECT u FROM Usuario u WHERE u.apellidousu = :apellidousu"),
    @NamedQuery(name = "Usuario.findByCorreousu", query = "SELECT u FROM Usuario u WHERE u.correousu = :correousu"),
    @NamedQuery(name = "Usuario.findByUserusu", query = "SELECT u FROM Usuario u WHERE u.userusu = :userusu"),
    @NamedQuery(name = "Usuario.findByStatususu", query = "SELECT u FROM Usuario u WHERE u.statususu = :statususu"),
    @NamedQuery(name = "Usuario.findByTelefonousu", query = "SELECT u FROM Usuario u WHERE u.telefonousu = :telefonousu"),
    @NamedQuery(name = "Usuario.findByTelefono2usu", query = "SELECT u FROM Usuario u WHERE u.telefono2usu = :telefono2usu"),
    @NamedQuery(name = "Usuario.findByDireccionusu", query = "SELECT u FROM Usuario u WHERE u.direccionusu = :direccionusu"),
    @NamedQuery(name = "Usuario.findByUsuxSede", query = "SELECT u FROM Usuario u, Usuariosede s WHERE u.idusu = s.idusu.idusu AND s.idsed.nombresed = :sede"),
    @NamedQuery(name = "Usuario.findMaxId", query = "SELECT MAX(u.idusu) FROM Usuario u "),
    @NamedQuery(name = "Usuario.findByDireccion2usu", query = "SELECT u FROM Usuario u WHERE u.direccion2usu = :direccion2usu")})
public class Usuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TIPOUSU")
    private String tipousu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Bitacora> bitacoraCollection;
    @OneToMany(mappedBy = "idusubuz")
    private Collection<Buzon> buzonCollection;
    @OneToMany(mappedBy = "idusu")
    private Collection<Buzon> buzonCollection1;
    @OneToMany(mappedBy = "idusu")
    private Collection<Usuariosede> usuariosedeCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDUSU")
    private BigDecimal idusu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBREUSU")
    private String nombreusu;
    @Size(max = 200)
    @Column(name = "APELLIDOUSU")
    private String apellidousu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CORREOUSU")
    private String correousu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USERUSU")
    private String userusu;
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
    @OneToMany(mappedBy = "idusu")
    private Collection<Bandeja> bandejaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusu")
    private Collection<Valija> valijaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origenpaq")
    private Collection<Paquete> paqueteCollection;

    public Usuario() {
    }

    public Usuario(BigDecimal idusu) {
        this.idusu = idusu;
    }

    public Usuario(BigDecimal idusu, String nombreusu, String correousu, String userusu, String statususu) {
        this.idusu = idusu;
        this.nombreusu = nombreusu;
        this.correousu = correousu;
        this.userusu = userusu;
        this.statususu = statususu;
    }

    public BigDecimal getIdusu() {
        return idusu;
    }

    public void setIdusu(BigDecimal idusu) {
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
    public Collection<Bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<Bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    @XmlTransient
    public Collection<Valija> getValijaCollection() {
        return valijaCollection;
    }

    public void setValijaCollection(Collection<Valija> valijaCollection) {
        this.valijaCollection = valijaCollection;
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

    @XmlTransient
    public Collection<Usuariosede> getUsuariosedeCollection() {
        return usuariosedeCollection;
    }

    public void setUsuariosedeCollection(Collection<Usuariosede> usuariosedeCollection) {
        this.usuariosedeCollection = usuariosedeCollection;
    }

    @XmlTransient
    public Collection<Buzon> getBuzonCollection() {
        return buzonCollection;
    }

    public void setBuzonCollection(Collection<Buzon> buzonCollection) {
        this.buzonCollection = buzonCollection;
    }

    @XmlTransient
    public Collection<Buzon> getBuzonCollection1() {
        return buzonCollection1;
    }

    public void setBuzonCollection1(Collection<Buzon> buzonCollection1) {
        this.buzonCollection1 = buzonCollection1;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    public String getTipousu() {
        return tipousu;
    }

    public void setTipousu(String tipousu) {
        this.tipousu = tipousu;
    }
}
