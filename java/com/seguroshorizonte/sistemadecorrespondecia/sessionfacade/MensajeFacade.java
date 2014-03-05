/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Mensaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }
      public void insertarMensaje(Mensaje registroMensaje) {
        
        this.create(registroMensaje);
    }
    
    // Este es mio
    public String ultimoMensaje() {
        
        Query query = em.createNamedQuery("Mensaje.findByMaximoIdmen");
        Object resultList = query.getSingleResult();
        String maximoAuxiliar = resultList.toString();
        return maximoAuxiliar;
    }
    
}
