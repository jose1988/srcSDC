/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bitacora;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
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
public class BitacoraFacade extends AbstractFacade<Bitacora> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }
    
 
    public void insertarBitacora(Sede sede, Usuario usu, String accion, String descripcion){
        Bitacora bit=new Bitacora();
        bit.setAccionbit(accion);
        bit.setIdsed(sede);
        bit.setObservacionbit(descripcion);
        bit.setIdusu(usu);
        Date fecha = new Date();
        bit.setFechabit(fecha);
        this.create(bit);        
    }
    
   
    public void vaciarBitacora(Bitacora registroBitacora){    
        this.remove(registroBitacora);
    }
    
    
    public List<Bitacora> listarBitacora(){
        
        List<Bitacora> Resultado = null;
        Query consulta = em.createNamedQuery("Bitacora.findAll");
        Resultado = consulta.getResultList();
        
        return Resultado;
    }
    
}
