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

    public List<Buzon> ConsultarBuzonXUsuario(Usuario idUsuario, Sede idSede) {
        List<Buzon> lista;
        lista = em.createNamedQuery("Buzon.findByUsuarioYSede").setParameter("idusu", idUsuario).setParameter("idsede", idSede).getResultList();
        return lista;
    }

    public void insertarBuzon(Buzon BuzonI) {

        this.create(BuzonI);

    }

    public Buzon ConsultarBuzonXNombreUsuario(String userUsu, Usuario usuario) {
        Buzon Resultado;
        Resultado = (Buzon) em.createNamedQuery("Buzon.findByNombreUsuario").setParameter("user", userUsu).setParameter("idusu", usuario).getSingleResult();
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
