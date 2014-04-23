/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
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

    public String ultimoSegXPaq(String idpaq) {
        Query Resultad = null;
        Resultad = em.createNamedQuery("Seguimiento.findUltimoSegXPaq").setParameter("idpaq", new BigDecimal(idpaq));
        String Resultado = (String) Resultad.getSingleResult().toString();
        return Resultado;
    }

    public void editarSeguimiento(BigDecimal idpaq, String status) {
        Query q = em.createNativeQuery("UPDATE Seguimiento SET statusseg=? WHERE idseg=?");
        q.setParameter(1, status);
        q.setParameter(2, idpaq);
        q.executeUpdate();
    }

    public List<Paquete> consultarPaquetesConfirmadosXRol(Usuariosede Registro) {
        List<Paquete> Resultado = null;
        Query consulta;
        //caso multirol o sede
        if (Registro.getIdrol().getIdrol().toString().compareTo("5") == 0 || Registro.getIdrol().getIdrol().toString().compareTo("2") == 0) {
            consulta = em.createNamedQuery("Seguimiento.findPaqueteByRolSede").setParameter("idsed", Registro.getIdsed());
            Resultado = consulta.getResultList();
        }
        if (Registro.getIdrol().getIdrol().toString().compareTo("1") == 0) { //caso area

            consulta = em.createNamedQuery("Seguimiento.findPaqueteByArea").setParameter("idsed", Registro.getIdsed());
            Resultado = consulta.getResultList();
        }
        if (Registro.getIdrol().getIdrol().toString().compareTo("3") == 0) { //caso emisario

            consulta = em.createNamedQuery("Seguimiento.findPaqueteByEmisario").setParameter("idsed", Registro.getIdsed());
            Resultado = consulta.getResultList();
        }

        return Resultado;
    }

    public List<Paquete> consultarPaquetesXConfirmarExternos(Sede sede) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Seguimiento.findPaqByExterno").setParameter("idsed", sede);
        Resultado = consulta.getResultList();

        return Resultado;
    }

    public void actualizacionEstadoEnvioExterno(Paquete idPaq) {
        Query q = em.createNativeQuery("UPDATE SEGUIMIENTO SET STATUSSEG=1 WHERE idpaq = " + idPaq.getIdpaq() + " AND NIVELSEG ='Externo' ");
        q.executeUpdate();

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