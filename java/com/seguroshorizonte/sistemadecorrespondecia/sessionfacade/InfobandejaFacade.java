/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Pangea
 */
@Stateless
public class InfobandejaFacade extends AbstractFacade<Infobandeja> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;
    private static Logger log = Logger.getLogger(InfobandejaFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfobandejaFacade() {
        super(Infobandeja.class);
    }

    public Infobandeja consultarBandejaXNombre(String nombre) {
        Infobandeja Info;
        try {
            Info = (Infobandeja) em.createNamedQuery("Infobandeja.findByNombreiba").setParameter("nombreiba", nombre).getSingleResult();
        } catch (Exception e) {
            log.error("Error en consultarBandejaXNombre");
            log.fatal("Error fatal en consultarBandejaXNombre");
            Info = null;
        }
        return Info;
    }
}