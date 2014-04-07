/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Nivel;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class NivelFacade extends AbstractFacade<Nivel> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelFacade() {
        super(Nivel.class);
    }
    
    public void editarTiempoNivel(String tiempo, String idniv) {
        Query q = em.createNativeQuery("UPDATE nivel SET tiemponiv=?,  WHERE idniv=?");
        q.setParameter(1, new BigInteger(tiempo));
        q.setParameter(2, new BigDecimal(idniv));
        q.executeUpdate();
    }
    
     public List<Nivel> consultarNivel(String pri) {
        List<Nivel> c = null;
        BigDecimal p= new BigDecimal(pri);
        c = (List<Nivel>) em.createNamedQuery("Nivel.findBypri").setParameter("idpri", p).getResultList();
        return c;
    }
}
