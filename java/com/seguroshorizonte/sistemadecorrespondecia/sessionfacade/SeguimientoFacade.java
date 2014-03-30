/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Rol;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
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
public class SeguimientoFacade extends AbstractFacade<Seguimiento> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoFacade() {
        super(Seguimiento.class);
    }

    public List<Seguimiento> consultarSeguimientoXPaquete(Paquete idPaquete) {
        List<Seguimiento> Resultado;
        Query Consulta = em.createNamedQuery("Seguimiento.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = Consulta.getResultList();
        return Resultado;
    }

    public void insertarSeguimiento(Seguimiento RegSeguimiento) {
        this.create(RegSeguimiento);
    }

    public List<Paquete> listaPaquetesProcesadosXUsuarioSede(Usuariosede idUsuarioSede) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Seguimiento.findPaqueteByUsuarioSede").setParameter("idusu", idUsuarioSede);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> consultarPaquetesConfirmadosXRol(Usuariosede Registro) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Seguimiento.findPaqueteByRol").setParameter("idrol", Registro.getIdrol()).setParameter("idsed", Registro.getIdsed());
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public Date FechaActual() {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        return fecha;
    }
}