/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Alerta;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
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
public class AlertaFacade extends AbstractFacade<Alerta> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlertaFacade() {
        super(Alerta.class);
    }

    public List<Paquete> consultarPaquetesXUsuarioOrigen(Usuario usuarioId, Sede idSede) {
        List<Paquete> Resultado = null;
        try {
            Query consulta = em.createNamedQuery("Alerta.findPaquetesVencidosXOrigen").setParameter("origen", usuarioId).setParameter("idsed", idSede);
            Resultado = consulta.getResultList();

        } catch (Exception e) {
            return null;
        }

        return Resultado;
    }

    public List<Paquete> consultarPaquetesXUsuarioDestino(Usuario usuarioId, Sede idSede) {
        List<Paquete> Resultado = null;
        try {
            Query consulta = em.createNamedQuery("Alerta.findPaquetesVencidosXDestino").setParameter("destino", usuarioId).setParameter("idsed", idSede);
            Resultado = consulta.getResultList();

        } catch (Exception e) {
            return null;
        }

        return Resultado;
    }

    public List<Paquete> consultarPaquetesXSeguimiento(Usuariosede usuarioId) {
        List<Paquete> Resultado = null;
        try {
            Query consulta = em.createNamedQuery("Alerta.findPaquetesVencidosXSeguimiento").setParameter("usuarioSede", usuarioId);
            Resultado = consulta.getResultList();

        } catch (Exception e) {
            return null;
        }

        return Resultado;
    }
}
