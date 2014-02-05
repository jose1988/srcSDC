/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Permisologia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class PermisologiaFacade extends AbstractFacade<Permisologia> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisologiaFacade() {
        super(Permisologia.class);
    }
    
     public Permisologia consultarPermisologia(String idUsu) {
        Permisologia Registro;
        Registro = this.find(idUsu);
        return Registro;
    }
    
}
