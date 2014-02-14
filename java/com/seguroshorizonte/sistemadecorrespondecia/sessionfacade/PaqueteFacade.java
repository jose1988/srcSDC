/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
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
public class PaqueteFacade extends AbstractFacade<Paquete> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaqueteFacade() {
        super(Paquete.class);
    }


    public List<Paquete> ConsultarPaquetesXValija(Valija idValija) {
        
        List<Paquete> lista;
        Query consulta = em.createNamedQuery("Paquete.findByIdadj").setParameter("idadj", idValija);
        lista = consulta.getResultList();
        return lista;
    }

    public Paquete ConsultarPaqueteXId(Paquete idPaquete) {
        
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public List<Paquete> consultarPaqueteXAlertaXUsuarioDestino(Usuario usuarioId) {
        
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByAlertaXUsuarioDestino").setParameter("fechaapaq", FechaActual()).setParameter("destino", usuarioId);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> consultarPaqueteXAlertaXUsuarioOrigen(Usuario usuarioId) {
        
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByAlertaXUsuarioOrigen").setParameter("fechaapaq", FechaActual()).setParameter("origen", usuarioId);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public Date FechaActual() {
        
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        return fecha;
    }

    public List<Paquete> consultarPaqueteXFechaVencimientoXOrigen(Usuario usuarioId) {
        
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByVencimientoXUsuarioOrigen").setParameter("fechaenviopaq", FechaActual()).setParameter("origen", usuarioId);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> consultarPaqueteXFechaVencimientoXDestino(Usuario usuarioId) {
        
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByVencimientoXUsuarioDestino").setParameter("fechaenviopaq", FechaActual()).setParameter("origen", usuarioId);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public void crearPaquete(Paquete registro){
       
        this.create(registro);       
    }
        
    public void editarLocalizacionZoomPaquete(BigDecimal idPaquete){
        
         Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq='En zoom' WHERE idpaq=?");
         q.setParameter(1, idPaquete);
         q.executeUpdate();
    }
    
    public List<Paquete> listarEnviadoUsuarioXFecha(Usuario idUsuario){
    
        List<Paquete> Resultado;
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        
        Query consulta = em.createNamedQuery("Paquete.findByFechaenviopaYOrigen").setParameter("origenpaq", idUsuario).setParameter("fechaenviopaq", fecha);
        Resultado = consulta.getResultList();
        
       return Resultado;
    }
    
    public List<Paquete> listarPaquetesNoProcesados(String respuesta){
        
        List<Paquete> Resultado = null;
        
        Query consulta = em.createNamedQuery("Paquete.findByStatuspaqYRespaq").setParameter("statuspaq", '2').setParameter("respaq", respuesta);
        Resultado = consulta.getResultList();
        return Resultado;
    }
    
    public List<Paquete> listarPaquetesProcesados(String respuesta){
        
        List<Paquete> Resultado = null;
        
        Query consulta = em.createNamedQuery("Paquete.findByStatuspaqYRespaq").setParameter("statuspaq", '1').setParameter("respaq", respuesta);
        Resultado = consulta.getResultList();
        return Resultado;
    }
    
    public List<Paquete> listarPaquetesXValija(Valija idValija){
        
        List<Paquete> Resultado = null;
        
        Query consulta = em.createNamedQuery("Paquete.findByPaqYValija").setParameter("idval", idValija);
        Resultado = consulta.getResultList();
        return Resultado;
    }
    
     public List<Paquete> ConsultarPaquetesParaValija(String user, String sede){
        
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByStatuspaqYRespaq").setParameter("statuspaq", '1').setParameter("respaq", user);
        Resultado = consulta.getResultList();
        return Resultado;
    }
}
