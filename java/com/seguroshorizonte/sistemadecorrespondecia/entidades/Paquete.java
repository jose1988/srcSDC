/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdpaq", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq"),
    @NamedQuery(name = "Paquete.findByOrigenpaq", query = "SELECT p FROM Paquete p WHERE p.origenpaq = :origenpaq"),
    @NamedQuery(name = "Paquete.findByDestinopaq", query = "SELECT p FROM Paquete p WHERE p.destinopaq = :destinopaq"),
    @NamedQuery(name = "Paquete.findByTextopaq", query = "SELECT p FROM Paquete p WHERE p.textopaq = :textopaq"),
    @NamedQuery(name = "Paquete.findByFechapaq", query = "SELECT p FROM Paquete p WHERE p.fechapaq = :fechapaq"),
    @NamedQuery(name = "Paquete.findByAsuntopaq", query = "SELECT p FROM Paquete p WHERE p.asuntopaq = :asuntopaq"),
    @NamedQuery(name = "Paquete.findByIdadj", query = "SELECT p FROM Paquete p WHERE p.idadj = :idadj")})
public class Paquete implements Serializable {
    @Column(name = "FECHAPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapaq;
    @Column(name = "FECHAAPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaapaq;
    @Size(max = 20)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 20)
    @Column(name = "IDPRI")
    private String idpri;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "paquete")
    private Seguimiento seguimiento;
    @JoinColumn(name = "ORIGENPAQ", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario origenpaq;
    @JoinColumn(name = "DESTINOPAQ", referencedColumnName = "IDUSU")
    @ManyToOne(optional = false)
    private Usuario destinopaq;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPRI")
    @OneToOne(optional = false)
    private Prioridad prioridad;
    @JoinColumn(name = "IDDOC", referencedColumnName = "IDDOC")
    @ManyToOne(optional = false)
    private Documento iddoc;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Adjunto> adjuntoCollection;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Bandeja> bandejaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDPAQ")
    private String idpaq;
    
    @Size(max = 4000)
    @Column(name = "TEXTOPAQ")
    private String textopaq;
    @Size(max = 20)
    @Column(name = "ASUNTOPAQ")
    private String asuntopaq;
    @Size(max = 20)
    @Column(name = "IDADJ")
    private String idadj;
   
    @JoinColumn(name = "IDMEN", referencedColumnName = "IDMEN")
    @ManyToOne
    private Mensaje idmen;

    public Paquete() {
    }

    public Paquete(String idpaq) {
        this.idpaq = idpaq;
    }

    public Paquete(String idpaq, Usuario origenpaq, Usuario destinopaq) {
        this.idpaq = idpaq;
        this.origenpaq = origenpaq;
        this.destinopaq = destinopaq;
    }

    public String getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(String idpaq) {
        this.idpaq = idpaq;
    }

    public Usuario getOrigenpaq() {
        return origenpaq;
    }

    public void setOrigenpaq(Usuario origenpaq) {
        this.origenpaq = origenpaq;
    }

    public Usuario getDestinopaq() {
        return destinopaq;
    }

    public void setDestinopaq(Usuario destinopaq) {
        this.destinopaq = destinopaq;
    }

    public String getTextopaq() {
        return textopaq;
    }

    public void setTextopaq(String textopaq) {
        this.textopaq = textopaq;
    }

    public String getAsuntopaq() {
        return asuntopaq;
    }

    public void setAsuntopaq(String asuntopaq) {
        this.asuntopaq = asuntopaq;
    }

    public String getIdadj() {
        return idadj;
    }

    public void setIdadj(String idadj) {
        this.idadj = idadj;
    }

    public String getIdpri() {
        return idpri;
    }

    public void setIdpri(String idpri) {
        this.idpri = idpri;
    }

    public Mensaje getIdmen() {
        return idmen;
    }

    public void setIdmen(Mensaje idmen) {
        this.idmen = idmen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaq != null ? idpaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idpaq == null && other.idpaq != null) || (this.idpaq != null && !this.idpaq.equals(other.idpaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete[ idpaq=" + idpaq + " ]";
    }

    @XmlTransient
    public Collection<Bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<Bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    @XmlTransient
    public Collection<Adjunto> getAdjuntoCollection() {
        return adjuntoCollection;
    }

    public void setAdjuntoCollection(Collection<Adjunto> adjuntoCollection) {
        this.adjuntoCollection = adjuntoCollection;
    }

    public Date getFechapaq() {
        return fechapaq;
    }

    public void setFechapaq(Date fechapaq) {
        this.fechapaq = fechapaq;
    }

    public Date getFechaapaq() {
        return fechaapaq;
    }

    public void setFechaapaq(Date fechaapaq) {
        this.fechaapaq = fechaapaq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

  

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Documento getIddoc() {
        return iddoc;
    }

    public void setIddoc(Documento iddoc) {
        this.iddoc = iddoc;
    }
    
}
