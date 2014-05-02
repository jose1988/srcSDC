/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedor;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    public Proveedor consultarProveedorXId(BigDecimal idProveedor) {
        Proveedor Resultado;
        Query consulta = em.createNamedQuery("Proveedor.findByIdpro").setParameter("idpro", idProveedor);
        Resultado = (Proveedor) consulta.getSingleResult();
        return Resultado;
    }

    public String ultimoidProveedor() {
        String Resultado;
        Query consulta = em.createNamedQuery("Proveedor.findMaxProveedor");
        Resultado = consulta.getSingleResult().toString();
        return Resultado;
    }

    public Proveedor consultarProveedorNombre(String nombre) {
        Proveedor Resultado;
        Query consulta = em.createNamedQuery("Proveedor.findByNombrepro").setParameter("nombrepro", nombre);
        Resultado = (Proveedor) consulta.getResultList();
        return Resultado;
    }

    public int consultarProveedorexistente(String nombre, String idsed) {
        int Resultado = 0;
        Query consulta = em.createNamedQuery("Proveedor.findByExistente").setParameter("nombrepro", nombre).setParameter("idsed", new BigDecimal(idsed));
        if (consulta == null) {
            Resultado = 0;
        } else {
            Resultado = 1;
        }
        return Resultado;
    }
}
