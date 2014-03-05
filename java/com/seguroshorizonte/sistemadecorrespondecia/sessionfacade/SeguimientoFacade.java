/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
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
    
    public List<Paquete> listaPaquetesXUsuarioYFechaProcesadas(Usuario idUsuario){
    
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
        
        Query consulta = em.createNamedQuery("Seguimiento.findByFechasegYUsuario").setParameter("idusu", idUsuario).setParameter("fechaseg", fecha);
        Resultado = consulta.getResultList();
        
        return Resultado;
    }
     public List<Paquete> listaPaquetesProcesadosXUsuarioAlDia(Usuario idUsuario) {

        List<Paquete> Resultado = null;
          Query consulta = em.createNamedQuery("Seguimiento.findPaqByFechasegYUsuario").setParameter("idusu", idUsuario).setParameter("fechaseg", FechaActual());
      
        Resultado = consulta.getResultList();

        return Resultado;
    }
     public Date FechaActual() {

        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        //  System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        return fecha;
    }
}
