/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdpaq", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq"),
    @NamedQuery(name = "Paquete.findByAsuntopaq", query = "SELECT p FROM Paquete p WHERE p.asuntopaq = :asuntopaq"),
    @NamedQuery(name = "Paquete.findByTextopaq", query = "SELECT p FROM Paquete p WHERE p.textopaq = :textopaq"),
    @NamedQuery(name = "Paquete.findByFechapaq", query = "SELECT p FROM Paquete p WHERE p.fechapaq = :fechapaq"),
    @NamedQuery(name = "Paquete.findByFechaapaq", query = "SELECT p FROM Paquete p WHERE p.fechaapaq = :fechaapaq"),
    @NamedQuery(name = "Paquete.findByStatus", query = "SELECT p FROM Paquete p WHERE p.status = :status"),
    @NamedQuery(name = "Paquete.findByIdadj", query = "SELECT p FROM Paquete p WHERE p.idadj = :idadj"),
    @NamedQuery(name = "Paquete.findByIdpri", query = "SELECT p FROM Paquete p WHERE p.idpri = :idpri")})
public class Paquete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDPAQ")
    private String idpaq;
    @Size(max = 20)
    @Column(name = "ASUNTOPAQ")
    private String asuntopaq;
    @Size(max = 4000)
    @Column(name = "TEXTOPAQ")
    private String textopaq;
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
    @Column(name = "IDADJ")
    private String idadj;
    @Size(max = 20)
    @Column(name = "IDPRI")
    private String idpri;

    public Paquete() {
    }

    public Paquete(String idpaq) {
        this.idpaq = idpaq;
    }

    public String getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(String idpaq) {
        this.idpaq = idpaq;
    }

    public String getAsuntopaq() {
        return asuntopaq;
    }

    public void setAsuntopaq(String asuntopaq) {
        this.asuntopaq = asuntopaq;
    }

    public String getTextopaq() {
        return textopaq;
    }

    public void setTextopaq(String textopaq) {
        this.textopaq = textopaq;
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
    
}
