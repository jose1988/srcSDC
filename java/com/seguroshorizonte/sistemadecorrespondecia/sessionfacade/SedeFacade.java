/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import java.math.BigDecimal;
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
public class SedeFacade extends AbstractFacade<Sede> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SedeFacade() {
        super(Sede.class);
    }

    public Sede ConsultarSedeXNombre(String sede) {
        Sede Resultado = null;
        Query consulta = em.createNamedQuery("Sede.findByNombresed").setParameter("nombresed", sede);
        Resultado = (Sede) consulta.getSingleResult();
        return Resultado;
    }

    public Sede consultarSedeXId(BigDecimal idSede) {
        Sede Resultado;
        Query consulta = em.createNamedQuery("Sede.findByIdsed").setParameter("idsed", idSede);
        Resultado = (Sede) consulta.getSingleResult();
        return Resultado;
    }

    public List<Sede> listarSedes() {
        List<Sede> Registros;
        Query Consulta = em.createNamedQuery("Sede.findAll");
        Registros = Consulta.getResultList();
        return Registros;
    }

    public String listarNombresXId(BigDecimal id) {
        Sede Registro;
        String Nombre = "";
        Query Consulta = em.createNamedQuery("Sede.findByIdsed").setParameter("idsed", id);
        Registro = (Sede) Consulta.getSingleResult();
        Nombre = Registro.getNombresed();
        return Nombre;
    }
}