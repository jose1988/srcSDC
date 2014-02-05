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
@Table(name = "PERMISOLOGIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisologia.findAll", query = "SELECT p FROM Permisologia p"),
    @NamedQuery(name = "Permisologia.findByIdper", query = "SELECT p FROM Permisologia p WHERE p.idper = :idper"),
    @NamedQuery(name = "Permisologia.findByNombreper", query = "SELECT p FROM Permisologia p WHERE p.nombreper = :nombreper"),
    @NamedQuery(name = "Permisologia.findByDescripcionper", query = "SELECT p FROM Permisologia p WHERE p.descripcionper = :descripcionper"),
    @NamedQuery(name = "Permisologia.findByLecturaper", query = "SELECT p FROM Permisologia p WHERE p.lecturaper = :lecturaper"),
    @NamedQuery(name = "Permisologia.findByEscrituraper", query = "SELECT p FROM Permisologia p WHERE p.escrituraper = :escrituraper"),
    @NamedQuery(name = "Permisologia.findByModificacionper", query = "SELECT p FROM Permisologia p WHERE p.modificacionper = :modificacionper"),
    @NamedQuery(name = "Permisologia.findByDeshabilitarper", query = "SELECT p FROM Permisologia p WHERE p.deshabilitarper = :deshabilitarper")})
public class Permisologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDPER")
    private String idper;
    @Size(max = 20)
    @Column(name = "NOMBREPER")
    private String nombreper;
    @Size(max = 2000)
    @Column(name = "DESCRIPCIONPER")
    private String descripcionper;
    @Size(max = 2)
    @Column(name = "LECTURAPER")
    private String lecturaper;
    @Size(max = 2)
    @Column(name = "ESCRITURAPER")
    private String escrituraper;
    @Size(max = 2)
    @Column(name = "MODIFICACIONPER")
    private String modificacionper;
    @Size(max = 2)
    @Column(name = "DESHABILITARPER")
    private String deshabilitarper;

    public Permisologia() {
    }

    public Permisologia(String idper) {
        this.idper = idper;
    }

    public String getIdper() {
        return idper;
    }

    public void setIdper(String idper) {
        this.idper = idper;
    }

    public String getNombreper() {
        return nombreper;
    }

    public void setNombreper(String nombreper) {
        this.nombreper = nombreper;
    }

    public String getDescripcionper() {
        return descripcionper;
    }

    public void setDescripcionper(String descripcionper) {
        this.descripcionper = descripcionper;
    }

    public String getLecturaper() {
        return lecturaper;
    }

    public void setLecturaper(String lecturaper) {
        this.lecturaper = lecturaper;
    }

    public String getEscrituraper() {
        return escrituraper;
    }

    public void setEscrituraper(String escrituraper) {
        this.escrituraper = escrituraper;
    }

    public String getModificacionper() {
        return modificacionper;
    }

    public void setModificacionper(String modificacionper) {
        this.modificacionper = modificacionper;
    }

    public String getDeshabilitarper() {
        return deshabilitarper;
    }

    public void setDeshabilitarper(String deshabilitarper) {
        this.deshabilitarper = deshabilitarper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idper != null ? idper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisologia)) {
            return false;
        }
        Permisologia other = (Permisologia) object;
        if ((this.idper == null && other.idper != null) || (this.idper != null && !this.idper.equals(other.idper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Permisologia[ idper=" + idper + " ]";
    }
    
}
