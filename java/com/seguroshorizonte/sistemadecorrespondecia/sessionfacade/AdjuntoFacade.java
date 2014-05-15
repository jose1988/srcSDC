/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Adjunto;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class AdjuntoFacade extends AbstractFacade<Adjunto> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdjuntoFacade() {
        super(Adjunto.class);
    }

    public Adjunto consultarAdjuntoXPaquete(Paquete idPaquete) {
        Adjunto Resultado;
        Query Consulta = em.createNamedQuery("Adjunto.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Adjunto) Consulta.getSingleResult();
        return Resultado;
    }

    public void insertarAdjunto(Adjunto registroAdj) {
        this.create(registroAdj);
    }
}