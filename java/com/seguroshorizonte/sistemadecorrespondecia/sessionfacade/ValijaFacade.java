/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class ValijaFacade extends AbstractFacade<Valija> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValijaFacade() {
        super(Valija.class);
    }

    public BigDecimal crearValija(Valija registro) {

        this.create(registro);
        BigDecimal max = this.ultimaValija(registro.getIdusu().getIdusu());

        return max;
    }

    public void editarZoomValija(BigDecimal idValija, String codZoom) {

        Query q = em.createNativeQuery("UPDATE valija SET zoomval=? WHERE idval=?");
        q.setParameter(1, codZoom);
        q.setParameter(2, idValija);
        q.executeUpdate();
    }

    public List<Valija> listarValijasXFecha() {

        List<Valija> Resultado;
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();

        Query consulta = em.createNamedQuery("Valija.findByFechaval").setParameter("fechaval", fecha);
        Resultado = consulta.getResultList();

        return Resultado;
    }

    public List<Valija> listarValijasNoProcesadas() {

        List<Valija> Resultado = null;

        Query consulta = em.createNamedQuery("Valija.findByNoProcesadas").setParameter("statusval1", '2').setParameter("statusval2", '3');
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Valija> listarValijasProcesadas() {

        List<Valija> Resultado = null;

        Query consulta = em.createNamedQuery("Valija.findByProcesadas").setParameter("statusval", '1');
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public void editarIncidenteValija(BigDecimal idValija, BigDecimal idIncidente) {

        Query q = em.createNativeQuery("UPDATE valija SET idinc=? WHERE idval=?");
        q.setParameter(1, idIncidente);
        q.setParameter(2, idValija);
        q.executeUpdate();
    }

    public BigDecimal ultimaValija(BigDecimal idusu) {

        Query consulta = em.createNamedQuery("Valija.maxVal").setParameter("idusu", idusu);
        BigDecimal Resultado = (BigDecimal) consulta.getSingleResult();
        return Resultado;
    }

    public void entregarValija(BigDecimal idValija, String Status) {

        Query q = em.createNativeQuery("UPDATE valija SET statusval=? WHERE idval=?");
        q.setParameter(1, Status);
        q.setParameter(2, idValija);
        q.executeUpdate();
    }

    public void editarStatusValija(BigDecimal idValija, String status) {

        Query q = em.createNativeQuery("UPDATE valija SET statusval=? WHERE idval=?");
        q.setParameter(1, status);
        q.setParameter(2, idValija);
        q.executeUpdate();
    }

    public List<Valija> listarValijasXFechaYUsuario(Usuario idUsuario) {

        List<Valija> Resultado = null;
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();

        Query consulta = em.createNamedQuery("Valija.findByFechavalYUsuario").setParameter("fechaval", fecha).setParameter("idusu", idUsuario);
        Resultado = consulta.getResultList();

        return Resultado;
    }

    public Valija consultarPaquete(BigDecimal idValija) {

        Valija Resultado;
        Query consulta = em.createNamedQuery("Valija.findByIdval").setParameter("idval", idValija);
        Resultado = (Valija) consulta.getSingleResult();
        return Resultado;
    }

    public List<Valija> listarValijasXFechaVencimientoOrigen(BigDecimal idSedeOrigen) {
        List<Valija> Resultado;
        Query consulta = em.createNamedQuery("Valija.findByFechaVencimientoOrigen").setParameter("fechaalerval", FechaActual()).setParameter("origen", idSedeOrigen);
        Resultado = consulta.getResultList();

        return Resultado;
    }

    public List<Valija> listarValijasXFechaVencimientoDestino(Sede idSede) {
        List<Valija> Resultado;
        Query consulta = em.createNamedQuery("Valija.findByFechaVencimientoDestino").setParameter("fechaalerval", FechaActual()).setParameter("destinoval", idSede);
        Resultado = consulta.getResultList();

        return Resultado;
    }

    public Date FechaActual() {

        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        //System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        return fecha;
    }
}
