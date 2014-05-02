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
import javax.persistence.Query;

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

    public int consultarAreaExistente(String nombre, String sede) {
        
       int Resultado = 0;
        BigDecimal idsed = new BigDecimal(sede);
      try {
           Query consulta = em.createNamedQuery("Areatrabajo.findByNombreatr").setParameter("nombreatr", nombre).setParameter("idsed", idsed);
          Object rr = consulta.getSingleResult();
           Resultado = 1;
        } catch (Exception e) {
             Resultado = 0;
        }
      
     
        return Resultado;
    }

    public void estadoArea(String area) {
        Areatrabajo are = this.find(new BigDecimal(area));
        if ("1".equals(are.getBorradoatr())) {
            Query q = em.createNativeQuery("UPDATE AREA_TRABAJO SET borradoatr=? WHERE idatr=?");
            q.setParameter(1, "0");
            q.setParameter(2, new BigDecimal(area));
            q.executeUpdate();
        } else {
            Query q2 = em.createNativeQuery("UPDATE AREA_TRABAJO SET borradoatr=? WHERE idatr=?");
            q2.setParameter(1, "1");
            q2.setParameter(2, new BigDecimal(area));
            q2.executeUpdate();
        }
    }

    public Areatrabajo consultarAreasXId(String idArea) {
        BigDecimal idAreaTrabajo = new BigDecimal(idArea);
        Areatrabajo c = null;
        c = (Areatrabajo) em.createNamedQuery("Areatrabajo.findByIdatr").setParameter("idatr", idAreaTrabajo).getSingleResult();
        return c;
    }
}
