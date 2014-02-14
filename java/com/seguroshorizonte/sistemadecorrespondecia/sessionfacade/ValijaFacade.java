/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
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
    
     public void crearValija(Valija registro){
       
        this.create(registro);       
    }
     
     public void editarZoomValija(BigDecimal idValija, String codZoom){
        
         Query q = em.createNativeQuery("UPDATE valija SET zoomval=? WHERE idval=?");
         q.setParameter(1, codZoom);
         q.setParameter(2, idValija);
         q.executeUpdate();
    }
    
    public List<Valija> listarValijasXFecha(){
    
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
    
    public List<Valija> listarValijasNoProcesadas(){
        
        List<Valija> Resultado = null;
        
        Query consulta = em.createNamedQuery("Valija.findByNoProcesadas").setParameter("statusval1", '2').setParameter("statusval2", '3');
        Resultado = consulta.getResultList();
        return Resultado;
    }
    
    public List<Valija> listarValijasProcesadas(){
        
        List<Valija> Resultado = null;
        
        Query consulta = em.createNamedQuery("Valija.findByProcesadas").setParameter("statusval", '1');
        Resultado = consulta.getResultList();
        return Resultado;
    }
    
    public void editarIncidenteValija(BigDecimal idValija, BigDecimal idIncidente){
        
         Query q = em.createNativeQuery("UPDATE valija SET idinc=? WHERE idval=?");
         q.setParameter(1, idIncidente);
         q.setParameter(2, idValija);
         q.executeUpdate();
    }
    
}
