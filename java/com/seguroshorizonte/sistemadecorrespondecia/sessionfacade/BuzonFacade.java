/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
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

    public List<Buzon> ConsultarBuzonInternoXUsuario(Usuario idUsuario, Sede idSede) {
        List<Buzon> lista = null;
        try {
            lista = em.createNamedQuery("Buzon.findInternoByUsuarioYSede").setParameter("idusu", idUsuario).setParameter("idsede", idSede).getResultList();
        } catch (Exception e) {
            return null;
        }
        return lista;
    }

    public List<Buzon> ConsultarBuzonExternoXUsuario(Usuario idUsuario) {
        List<Buzon> lista = null;
        try {
            lista = em.createNamedQuery("Buzon.findExternoByUsuario").setParameter("idusu", idUsuario).getResultList();

        } catch (Exception e) {
            return null;
        }
        return lista;
    }

    public void insertarBuzon(Buzon BuzonI) {
        this.create(BuzonI);
    }

    public Buzon ConsultarBuzonInternoXNombreUsuario(String userUsu, Usuario usuario) {
        Buzon Resultado;
        try {
            Resultado = (Buzon) em.createNamedQuery("Buzon.findInternoByNombreUsuario").setParameter("user", userUsu).setParameter("idusu", usuario).getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    public Buzon ConsultarBuzonExternoXNombreUsuario(String Nombre, Usuario usuario) {
        Buzon Resultado;
        try {
            Resultado = (Buzon) em.createNamedQuery("Buzon.findExternoByNombreUsuario").setParameter("nombre", Nombre).setParameter("idusu", usuario).getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    public Buzon verficarBuzon(Usuario dueno, Usuario contacto, Sede idSed) {
        Buzon Resultado;
        Resultado = (Buzon) em.createNamedQuery("Buzon.findByDuenoYContacto").setParameter("buzon", contacto).setParameter("idusu", dueno).setParameter("idsed", idSed).getSingleResult();
        return Resultado;
    }

    public Buzon consultarBuzonXId(String idBuzon) {
        Buzon Resultado;
        Resultado = (Buzon) em.createNamedQuery("Buzon.findByIdbuz").setParameter("idbuz", new BigDecimal(idBuzon)).getSingleResult();
        return Resultado;
    }
}