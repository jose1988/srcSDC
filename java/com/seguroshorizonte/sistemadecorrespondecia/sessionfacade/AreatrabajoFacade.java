/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Areatrabajo;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class AreatrabajoFacade extends AbstractFacade<Areatrabajo> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreatrabajoFacade() {
        super(Areatrabajo.class);
    }

    public List<Areatrabajo> consultarAreasXSede(String sede) {
        List<Areatrabajo> c = null;
        c = (List<Areatrabajo>) em.createNamedQuery("Areatrabajo.findBySedeXId").setParameter("sede", new BigDecimal(sede)).getResultList();
        return c;
    }

    public List<Areatrabajo> consultarAreasXSedeXNombre(String sede) {
        List<Areatrabajo> c = null;
        c = (List<Areatrabajo>) em.createNamedQuery("Areatrabajo.findBySedeXNombre").setParameter("sede", sede).getResultList();
        return c;
    }

    public Areatrabajo consultarAreasXNombre(String nombre) {
        Areatrabajo c = null;
        c = (Areatrabajo) em.createNamedQuery("Areatrabajo.findByNombreatr").setParameter("nombreatr", nombre).getSingleResult();
        return c;
    }

    public Areatrabajo consultarAreaExistente(String nombre, String sede) {
        Areatrabajo c = null;
        BigDecimal idsed = new BigDecimal(sede);
        c = (Areatrabajo) em.createNamedQuery("Areatrabajo.findByNombreatr").setParameter("nombreatr", nombre).setParameter("idsed", idsed).getSingleResult();
        return c;
    }
}
