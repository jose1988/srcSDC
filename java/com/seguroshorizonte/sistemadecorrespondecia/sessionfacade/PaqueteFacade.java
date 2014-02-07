/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
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
public class PaqueteFacade extends AbstractFacade<Paquete> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaqueteFacade() {
        super(Paquete.class);
    }

    public List<Paquete> ConsultarPaquetesXValija(Valija idValija) {
        List<Paquete> lista;
        Query consulta = em.createNamedQuery("Paquete.findByIdadj").setParameter("idadj", idValija);
        lista = consulta.getResultList();
        return lista;
    }

    public Paquete ConsultarPaqueteXId(Paquete idPaquete) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public List<Paquete> consultarPaqueteXFechaAlerta() {
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByFechaapaq").setParameter("fechaapaq", new Date());
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> consultarPaqueteXFechaVencimiento() {
        List<Paquete> Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByVencimiento").setParameter("fechaenviopaq", new Date());
        Resultado = consulta.getResultList();
        return Resultado;
    }
}
