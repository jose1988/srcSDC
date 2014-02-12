/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class BuzonFacade extends AbstractFacade<Buzon> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BuzonFacade() {
        super(Buzon.class);
    }

    public List<Buzon> ConsultarBuzonXUsuario(Usuario idUsuario) {
        List<Buzon> lista;
        lista = em.createNamedQuery("Buzon.findByUsuario").setParameter("idusu", idUsuario).getResultList();
        return lista;
    }
}
