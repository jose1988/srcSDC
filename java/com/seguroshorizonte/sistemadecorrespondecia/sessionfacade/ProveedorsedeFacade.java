/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedorsede;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class ProveedorsedeFacade extends AbstractFacade<Proveedorsede> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorsedeFacade() {
        super(Proveedorsede.class);
    }
    
}
