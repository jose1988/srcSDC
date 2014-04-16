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
import javax.persistence.Query;

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

    public List<Buzon> ConsultarBuzonXUsuario(BigDecimal idUsuario) {
        List<Buzon> lista = null;
        try {
            lista = em.createNamedQuery("Buzon.findByUsuario").setParameter("idusu", idUsuario).getResultList();
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

    public Buzon ConsultarBuzonXNombreSede(String idusu, String idsed) {
        Buzon Resultado;
        try {
            Resultado = (Buzon) em.createNamedQuery("Buzon.findByUsuarioSede").setParameter("idsed", new BigDecimal(idsed)).setParameter("idusu", new BigDecimal(idusu)).getSingleResult();
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

    public void editarBuzon(Buzon Registro) {
        Query q = em.createNativeQuery("UPDATE BUZON "
                + "SET NOMBREBUZ=?, DIRECCIONBUZ=?, TELEFONOBUZ=? WHERE IDBUZ=?");
        q.setParameter(1, Registro.getNombrebuz());
        q.setParameter(2, Registro.getDireccionbuz());
        q.setParameter(3, Registro.getTelefonobuz());
        q.setParameter(4, Registro.getIdbuz());
        q.executeUpdate();
    }

    public List<Buzon> buscarBuzonParaEnviar(String nombre, String apellido, String area, Buzon myBuzon, String sede) {
        List<Buzon> emp;
        List<Buzon> empexterno;
        if (!"".equals(area)) {
            emp = (List<Buzon>) em.createNamedQuery("Buzon.findByNASA").setParameter("nombre", "%" + nombre.toUpperCase() + "%").setParameter("apellido", "%" + apellido.toUpperCase() + "%").setParameter("area", new BigDecimal(area)).setParameter("idbuz", myBuzon.getIdbuz()).setParameter("idsed", new BigDecimal(sede)).getResultList();
        } else {
            emp = (List<Buzon>) em.createNamedQuery("Buzon.findByNAS").setParameter("nombre", "%" + nombre.toUpperCase() + "%").setParameter("apellido", "%" + apellido.toUpperCase() + "%").setParameter("idbuz", myBuzon.getIdbuz()).setParameter("idsed", new BigDecimal(sede)).getResultList();
        }
        empexterno = (List<Buzon>) em.createNamedQuery("Buzon.findByNBE").setParameter("nombre", "%" + nombre.toUpperCase() + "%").setParameter("idusu", myBuzon.getIdusu()).getResultList();
        if (empexterno != null) {
            emp.addAll(empexterno);
        }
        return emp;
    }

    public int myIdBuzon(Usuario registroUsuario) {
        int resultado = 0;
        BigDecimal result;
        result = (BigDecimal) em.createNamedQuery("Buzon.findMinIdMyBuzon").setParameter("idusu", registroUsuario).getSingleResult();
        resultado = result.intValue();
        return resultado;
    }
}