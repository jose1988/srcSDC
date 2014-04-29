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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "USUARIO_SEDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariosede.findAll", query = "SELECT u FROM Usuariosede u"),
    @NamedQuery(name = "Usuariosede.findByIduse", query = "SELECT u FROM Usuariosede u WHERE u.iduse = :iduse"),
    @NamedQuery(name = "Usuariosede.findByIdusu", query = "SELECT u FROM Usuariosede u WHERE u.idusu.idusu = :idusu AND u.idsed.idsed = :idsed"),
    @NamedQuery(name = "Usuariosede.findByIdsed", query = "SELECT u FROM Usuariosede u WHERE u.idsed = :idsed"),
    @NamedQuery(name = "Usuariosede.findByUsuarioYSede", query = "SELECT u FROM Usuariosede u WHERE u.idusu = :idusu AND u.idsed = :idsed"),
    @NamedQuery(name = "Usuariosede.findSedeByUsuario", query = "SELECT u.idsed FROM Usuariosede u WHERE u.idusu = :idusu"),
    @NamedQuery(name = "Usuariosede.findUsuarioBySede", query = "SELECT u.idusu FROM Usuariosede u WHERE  u.idsed = :idsed"),
    @NamedQuery(name = "Usuariosede.findMaxId", query = "SELECT MAX(u.idusu.idusu) FROM Usuariosede u ")})
public class Usuariosede implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduse")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduse")
    private Collection<Valija> valijaCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOSEDE")
    @SequenceGenerator(name = "SEQ_USUARIOSEDE", sequenceName = "SEQ_USUARIOSEDE", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDUSE")
    private BigDecimal iduse;
    @JoinColumn(name = "IDUSU", referencedColumnName = "IDUSU")
    @ManyToOne
    private Usuario idusu;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")
    @ManyToOne
    private Rol idrol;
    @JoinColumn(name = "IDATR", referencedColumnName = "IDATR")
    @ManyToOne
    private Areatrabajo idatr;

    public Usuariosede() {
    }

    public Usuariosede(BigDecimal iduse) {
        this.iduse = iduse;
    }

    public BigDecimal getIduse() {
        return iduse;
    }

    public void setIduse(BigDecimal iduse) {
        this.iduse = iduse;
    }

    public Usuario getIdusu() {
        return idusu;
    }

    public void setIdusu(Usuario idusu) {
        this.idusu = idusu;
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

    public Areatrabajo getIdatr() {
        return idatr;
    }

    public void setIdatr(Areatrabajo idatr) {
        this.idatr = idatr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduse != null ? iduse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariosede)) {
            return false;
        }
        Usuariosede other = (Usuariosede) object;
        if ((this.iduse == null && other.iduse != null) || (this.iduse != null && !this.iduse.equals(other.iduse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede[ iduse=" + iduse + " ]";
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
}
