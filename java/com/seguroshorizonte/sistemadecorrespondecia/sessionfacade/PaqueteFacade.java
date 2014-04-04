/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
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

    public List<Paquete> ConsultarPaquetesXValija(String idValija, String sede) {
        List<Paquete> lista;
        BigDecimal idval = new BigDecimal(idValija);
        Query consulta = em.createNamedQuery("Paquete.findByIdval").setParameter("idval", idval);
        lista = consulta.getResultList();
        return lista;
    }

    public Paquete ConsultarPaqueteXId(BigDecimal idPaquete) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public Paquete consultarPaquete(BigDecimal idPaquete) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
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


    public void crearPaquete(Paquete registro) {
        this.create(registro);
    }

    public List<Paquete> listarEnviadoUsuarioXFecha(Usuario idUsuario) {
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
        Query consulta = em.createNamedQuery("Paquete.findByFechapaqYOrigen").setParameter("origenpaq", idUsuario).setParameter("fechapaq", fecha);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> listarPaquetesXValija(Valija idValija) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByPaqYValija").setParameter("idval", idValija);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> ConsultarPaquetesParaValija(String sede) {
        String sed = "Sede";
        String tipo = "0";
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.paqBySede").setParameter("sede", sede).setParameter("sed", sed).setParameter("tipo", tipo);
        Resultado = consulta.getResultList();
        return Resultado;
    }
    public List<Paquete> ConsultarPaquetesExternos(Sede sede) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByExternos").setParameter("sede", sede);
        Resultado = consulta.getResultList();
        return Resultado;
    }
      

    public List<String> ConsultarSedeParaValija(String sede) {
        String sed = "Sede";
        String tipo = "0";
        List<String> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.SedeByValija").setParameter("sede", sede).setParameter("sed", sed).setParameter("tipo", tipo);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public void ActualizacionLocalizacionyValijaDelPaquete(String idPaq, String idVal) {
        Query q = em.createNativeQuery("UPDATE paquete SET idval=?  WHERE idpaq=?");
      
        q.setParameter(1, idVal);
        q.setParameter(2, idPaq);
        q.executeUpdate();
    }

    public void ActualizacionLocalizacionyDelPaqueteRecibido(String idPaq) {
        BigDecimal id = new BigDecimal(idPaq);
        Paquete paq = this.find(id);
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=?, statuspaq=?  WHERE idpaq=?");
        q.setParameter(1, paq.getDestinopaq().getIdusu().getNombreusu());
        q.setParameter(2, "1");
        q.setParameter(3, paq.getIdpaq());
        q.executeUpdate();
    }

     public void ActualizacionPaqueteExtraviado(String idPaq) {
        BigDecimal id = new BigDecimal(idPaq);
        Paquete paq = this.find(id);
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=?, statuspaq=?  WHERE idpaq=?");
        q.setParameter(1, "Extraviado");
        q.setParameter(2, "4");
        q.setParameter(3, id);
        q.executeUpdate();        
    }
    
    public void actualizacionPaqueteDeVuelta(String idPaq, String idRes) {
        Query q = em.createNativeQuery("UPDATE paquete SET idpaqres=?,  WHERE paquete.idpaq=?");
        q.setParameter(1, idPaq);
        q.setParameter(2, idRes);
        q.executeUpdate();
    }

    public void editarLocalizacionPaquete(BigDecimal idPaquete, String localizacion) {
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=? WHERE idpaq=?");
        q.setParameter(1, localizacion);
        q.setParameter(2, idPaquete);
        q.executeUpdate();
    }

    public void editarStatusPaquete(BigDecimal idPaquete, String status) {
        Query q = em.createNativeQuery("UPDATE paquete SET statuspaq=? WHERE idpaq=?");
        q.setParameter(1, status);
        q.setParameter(2, idPaquete);
        q.executeUpdate();
    }
    

    public List<Paquete> listarPaquetesXOrigenYRespuesta(Usuario idUsuario, String respuesta) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByOrigenYRespaq").setParameter("respaq", respuesta).setParameter("origenpaq", idUsuario);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public String ultimoPaqueteXOrigen(Usuario idUsuario) {
        String Resultado;
        Query consulta = em.createNamedQuery("Paquete.findMaxPaqXOrigen").setParameter("origenpaq", idUsuario);
        Resultado = consulta.getSingleResult().toString();
        return Resultado;
    }

    public List<Paquete> BuscarArea(BigDecimal idatr, BigDecimal idsede) {

        String sede = "Sede Destino";
        Query consulta = em.createNamedQuery("Paquete.findPaqXBuscarArea").setParameter("idatr", idatr).setParameter("idsed", idsede).setParameter("sede", sede);
        List<Paquete> Resultado = consulta.getResultList();
        return Resultado;
    }

    public Paquete ConsultarPaqueteXIdPaqueteYSedeDeValija(Sede sede, BigDecimal idPaq) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByidPaqueteYSedeDeValija").setParameter("idSede", sede).setParameter("idpaq", idPaq);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public void editarTipo(BigDecimal idusu, String tipo) {
        Query q = em.createNativeQuery("UPDATE Usuario SET tipousu=? WHERE idusu=?");
        q.setParameter(1, tipo);
        q.setParameter(2, idusu);
        q.executeUpdate();
    }
}