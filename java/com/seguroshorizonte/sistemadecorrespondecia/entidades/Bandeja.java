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
@Table(name = "BANDEJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bandeja.findAll", query = "SELECT b FROM Bandeja b"),
    @NamedQuery(name = "Bandeja.findByIdban", query = "SELECT b FROM Bandeja b WHERE b.idban = :idban"),
    @NamedQuery(name = "Bandeja.findByLeidoban", query = "SELECT b FROM Bandeja b WHERE b.leidoban = :leidoban")})
public class Bandeja implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDUSU")
    private String idusu;
    @Size(max = 20)
    @Column(name = "IDGDP")
    private String idgdp;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDBAN")
    private String idban;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEIDOBAN")
    private char leidoban;
   
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaq;
    @JoinColumn(name = "IDIBA", referencedColumnName = "IDIBA")
    @ManyToOne(optional = false)
    private Infobandeja idiba;
   

    public Bandeja() {
    }

    public Bandeja(String idban) {
        this.idban = idban;
    }

    public Bandeja(String idban, char leidoban) {
        this.idban = idban;
        this.leidoban = leidoban;
    }

    public String getIdban() {
        return idban;
    }

    public void setIdban(String idban) {
        this.idban = idban;
    }

    public char getLeidoban() {
        return leidoban;
    }

    public void setLeidoban(char leidoban) {
        this.leidoban = leidoban;
    }

   

    public Paquete getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(Paquete idpaq) {
        this.idpaq = idpaq;
    }

    public Infobandeja getIdiba() {
        return idiba;
    }

    public void setIdiba(Infobandeja idiba) {
        this.idiba = idiba;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idban != null ? idban.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bandeja)) {
            return false;
        }
        Bandeja other = (Bandeja) object;
        if ((this.idban == null && other.idban != null) || (this.idban != null && !this.idban.equals(other.idban))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja[ idban=" + idban + " ]";
    }

    public String getIdusu() {
        return idusu;
    }

    public void setIdusu(String idusu) {
        this.idusu = idusu;
    }

    public String getIdgdp() {
        return idgdp;
    }

    public void setIdgdp(String idgdp) {
        this.idgdp = idgdp;
    }
    
}
