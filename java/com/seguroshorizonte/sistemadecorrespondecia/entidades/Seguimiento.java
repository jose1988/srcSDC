/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByIdseg", query = "SELECT s FROM Seguimiento s WHERE s.idseg = :idseg"),
    @NamedQuery(name = "Seguimiento.findByIdpaq", query = "SELECT s FROM Seguimiento s WHERE s.idpaq = :idpaq"),
    @NamedQuery(name = "Seguimiento.findByFechaseg", query = "SELECT s FROM Seguimiento s WHERE s.fechaseg = :fechaseg"),
    @NamedQuery(name = "Seguimiento.findByStatusseg", query = "SELECT s FROM Seguimiento s WHERE s.statusseg = :statusseg"),
    @NamedQuery(name = "Seguimiento.Temporal", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.iduse = :idusu "),
    @NamedQuery(name = "Seguimiento.findByExtraviado", query = "SELECT s FROM Seguimiento s WHERE s.statusseg ='3' AND s.iduse.iduse = :iduse"),
    @NamedQuery(name = "Seguimiento.findByFechasegYUsuario", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.iduse.idusu.idusu = :idusu AND s.iduse.idsed.idsed = :idsed AND s.fechaseg = :fechaseg"),
    @NamedQuery(name = "Seguimiento.findPaqueteByUsuarioSede", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.iduse = :idusu AND s.nivelseg != 'Valija' AND s.nivelseg != 'Usuario' AND s.nivelseg != 'Externo'"),
    @NamedQuery(name = "Seguimiento.findPaqueteByArea", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.statusseg='0' AND s.nivelseg='Area de Trabajo' AND s.iduse.idsed = :idsed "),
    @NamedQuery(name = "Seguimiento.findPaqueteByEmisario", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.statusseg='0' AND s.nivelseg='Emisario' AND s.iduse.idsed = :idsed "),
    @NamedQuery(name = "Seguimiento.findPaqueteByRolSede", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.statusseg='0' AND s.iduse.idsed = :idsed AND s.nivelseg = 'Sede' "),
    @NamedQuery(name = "Seguimiento.findPaqByUsuario", query = "SELECT s FROM Seguimiento s WHERE s.iduse.idusu.idusu = :idusu AND s.iduse.idsed.idsed = :idsed"),
    @NamedQuery(name = "Seguimiento.findPaqByArea", query = "SELECT s FROM Seguimiento s WHERE s.idpaq.destinopaq.idatr.idatr=:idatr AND s.idpaq.destinopaq.idatr.idsed.idsed = :idsed AND s.nivelseg=:sede AND s.tiposeg='1' "),
   @NamedQuery(name = "Seguimiento.findPaqByExterno", query = "SELECT s.idpaq FROM Seguimiento s WHERE s.statusseg='0' AND s.nivelseg='Externo' AND s.iduse.idsed = :idsed"),
    @NamedQuery(name = "Seguimiento.findUltimoSegXPaq", query = "SELECT MAX(s.idseg) FROM Seguimiento s WHERE s.idpaq.idpaq= :idpaq ")})
public class Seguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEGUIMIENTOSEQ")
    @SequenceGenerator(name = "SEGUIMIENTOSEQ", sequenceName = "SEQ_SEGUIMIENTO", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSEG")
    private BigDecimal idseg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHASEG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaseg;
    @Size(max = 20)
    @Column(name = "STATUSSEG")
    private String statusseg;
    @Size(max = 20)
    @Column(name = "TIPOSEG")
    private String tiposeg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NIVELSEG")
    private String nivelseg;
    @JoinColumn(name = "IDUSE", referencedColumnName = "IDUSE")
    @ManyToOne(optional = false)
    private Usuariosede iduse;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne(optional = false)
    private Paquete idpaq;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "seguimiento")
    private Alerta alerta;

    public Seguimiento() {
    }

    public Seguimiento(BigDecimal idseg) {
        this.idseg = idseg;
    }

    public Seguimiento(BigDecimal idseg, Date fechaseg, String nivelseg) {
        this.idseg = idseg;
        this.fechaseg = fechaseg;
        this.nivelseg = nivelseg;
    }

    public BigDecimal getIdseg() {
        return idseg;
    }

    public void setIdseg(BigDecimal idseg) {
        this.idseg = idseg;
    }

    public Date getFechaseg() {
        return fechaseg;
    }

    public void setFechaseg(Date fechaseg) {
        this.fechaseg = fechaseg;
    }

    public String getStatusseg() {
        return statusseg;
    }

    public void setStatusseg(String statusseg) {
        this.statusseg = statusseg;
    }

    public String getTiposeg() {
        return tiposeg;
    }

    public void setTiposeg(String tiposeg) {
        this.tiposeg = tiposeg;
    }

    public String getNivelseg() {
        return nivelseg;
    }

    public void setNivelseg(String nivelseg) {
        this.nivelseg = nivelseg;
    }

    public Usuariosede getIduse() {
        return iduse;
    }

    public void setIduse(Usuariosede iduse) {
        this.iduse = iduse;
    }

    public Paquete getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(Paquete idpaq) {
        this.idpaq = idpaq;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseg != null ? idseg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.idseg == null && other.idseg != null) || (this.idseg != null && !this.idseg.equals(other.idseg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento[ idseg=" + idseg + " ]";
    }
}
