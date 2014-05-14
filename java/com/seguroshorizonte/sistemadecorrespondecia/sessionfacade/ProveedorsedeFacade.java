/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedor;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedorsede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
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

    public List<Proveedor> consultarProveedorXSede(Sede sede) {
        List<Proveedor> Resultado = null;
        Query consulta = em.createNamedQuery("Proveedorsede.ProveedorfindXSede").setParameter("sede", sede);
        Resultado = consulta.getResultList();
        return Resultado;
    }
}
