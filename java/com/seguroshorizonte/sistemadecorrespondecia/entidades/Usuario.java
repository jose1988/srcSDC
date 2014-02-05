/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
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
    @NamedQuery(name = "Usuario.findByStatususu", query = "SELECT u FROM Usuario u WHERE u.statususu = :statususu")})
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
    @JoinColumn(name = "IDPER", referencedColumnName = "IDPER")
    @ManyToOne(optional = false)
    private Permisologia idper;

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

    public Permisologia getIdper() {
        return idper;
    }

    public void setIdper(Permisologia idper) {
        this.idper = idper;
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
