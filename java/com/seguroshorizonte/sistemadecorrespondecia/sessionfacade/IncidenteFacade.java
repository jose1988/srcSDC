/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
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
public class IncidenteFacade extends AbstractFacade<Incidente> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IncidenteFacade() {
        super(Incidente.class);
    }

    public void insertarIncidente(Incidente registroIncidente) {
        this.create(registroIncidente);
    }

    public List<Incidente> consultarIncidenteXValija(Valija idValija) {
        List<Incidente> Resultado;
        Query Consulta = em.createNamedQuery("Incidente.findByValija").setParameter("idval", idValija);
        Resultado = Consulta.getResultList();
        return Resultado;
    }
}