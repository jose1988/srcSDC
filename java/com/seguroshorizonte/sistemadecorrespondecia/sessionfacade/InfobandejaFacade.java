/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class InfobandejaFacade extends AbstractFacade<Infobandeja> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfobandejaFacade() {
        super(Infobandeja.class);
    }
    
    public Infobandeja consultarBandejaXNombre(String nombre){
        Infobandeja Info;
        try{
            Info = (Infobandeja) em.createNamedQuery("Infobandeja.findByNombreiba").setParameter("Nombreiba", nombre).getSingleResult();    
        }catch(Exception e){
            Info=null;
        }
    return Info;
    }
    
}
