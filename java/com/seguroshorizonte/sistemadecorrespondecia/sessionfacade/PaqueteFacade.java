/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Pangea
 */
@Stateless
public class PaqueteFacade extends AbstractFacade<Paquete> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;
    private static Logger log = Logger.getLogger(PaqueteFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaqueteFacade() {
        super(Paquete.class);
    }

    public List<Paquete> ConsultarPaquetesXValija(String idValija, String sede) {
        List<Paquete> lista;
        BigDecimal idval = new BigDecimal(idValija);
        Query consulta = em.createNamedQuery("Paquete.findByIdval").setParameter("idval", idval);
        lista = consulta.getResultList();
        return lista;
    }

    public Paquete ConsultarPaqueteXId(BigDecimal idPaquete) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public Paquete consultarPaquete(BigDecimal idPaquete) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", idPaquete);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public List<Paquete> consultarStatusPaquete(Usuariosede use) {
        List<Paquete> Resultado = new ArrayList<Paquete>();
        Query consulta = em.createNamedQuery("Seguimiento.findByExtraviado").setParameter("iduse", use.getIduse());
        List<Seguimiento> Resultad = (List<Seguimiento>) consulta.getResultList();
        Iterator<Seguimiento> lista = Resultad.iterator();
        while (lista.hasNext()) {
            Seguimiento aux = lista.next();
            Resultado.add(aux.getIdpaq());
        }
        return Resultado;
    }

    public Date FechaActual() {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        return fecha;
    }

    public void crearPaquete(Paquete registro) {
        this.create(registro);
    }

    public List<Paquete> listarPaquetesXValija(Valija idValija) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByPaqYValija").setParameter("idval", idValija);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> ConsultarPaquetesParaValija(String sede) {
        String sed = "Sede";
        String tipo = "0";
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.paqBySede").setParameter("sede", sede).setParameter("sed", sed).setParameter("tipo", tipo);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Paquete> ConsultarPaquetesExternos(Sede sede) {
        List<Paquete> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.findByExternos").setParameter("sede", sede);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<String> ConsultarSedeParaValija(String sede) {
        String sed = "Sede";
        String tipo = "0";
        List<String> Resultado = null;
        Query consulta = em.createNamedQuery("Paquete.SedeByValija").setParameter("sede", sede).setParameter("sed", sed).setParameter("tipo", tipo);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public void ActualizacionLocalizacionyValijaDelPaquete(String idPaq, String idVal) {
        Query q = em.createNativeQuery("UPDATE paquete SET idval=?  WHERE idpaq=?");
        q.setParameter(1, idVal);
        q.setParameter(2, idPaq);
        q.executeUpdate();
    }

    public void ActualizacionLocalizacionyDelPaqueteRecibido(String idPaq) {
        BigDecimal id = new BigDecimal(idPaq);
        Paquete paq = this.find(id);
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=?, statuspaq=?  WHERE idpaq=?");
        q.setParameter(1, paq.getDestinopaq().getIdusu().getNombreusu());
        q.setParameter(2, "1");
        q.setParameter(3, paq.getIdpaq());
        q.executeUpdate();
    }

    public void ActualizacionLocalizacionyDelPaqueteExterno(Paquete idPaq) {
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=?, statuspaq=?  WHERE idpaq=?");
        q.setParameter(1, "Externo: " + idPaq.getDestinopaq().getNombrebuz());
        q.setParameter(2, "1");
        q.setParameter(3, idPaq.getIdpaq());
        q.executeUpdate();
    }

    public void ActualizacionPaqueteExtraviado(String idPaq) {
        BigDecimal id = new BigDecimal(idPaq);
        Paquete paq = this.find(id);
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=?, statuspaq=?  WHERE idpaq=?");
        q.setParameter(1, "Extraviado");
        q.setParameter(2, "4");
        q.setParameter(3, id);
        q.executeUpdate();
    }

    public void actualizacionPaqueteDeVuelta(String idPaq, String idRes) {
        Query q = em.createNativeQuery("UPDATE paquete SET idpaqres=?,  WHERE paquete.idpaq=?");
        q.setParameter(1, idPaq);
        q.setParameter(2, idRes);
        q.executeUpdate();
    }

    public void editarLocalizacionPaquete(BigDecimal idPaquete, String localizacion) {
        Query q = em.createNativeQuery("UPDATE paquete SET localizacionpaq=? WHERE idpaq=?");
        q.setParameter(1, localizacion);
        q.setParameter(2, idPaquete);
        q.executeUpdate();
    }

    public void editarStatusPaquete(BigDecimal idPaquete, String status) {
        Query q = em.createNativeQuery("UPDATE paquete SET statuspaq=? WHERE idpaq=?");
        q.setParameter(1, status);
        q.setParameter(2, idPaquete);
        q.executeUpdate();
    }

    public void editarRespaqDePaquete(BigDecimal idPaquete) {
        Query q = em.createNativeQuery("UPDATE paquete SET respaq='2' WHERE idpaq=?");
        q.setParameter(1, idPaquete);
        q.executeUpdate();
    }

    public String ultimoPaqueteXOrigen(Usuario idUsuario) {
        String Resultado;
        Query consulta = em.createNamedQuery("Paquete.findMaxPaqXOrigen").setParameter("origenpaq", idUsuario);
        Resultado = consulta.getSingleResult().toString();
        return Resultado;
    }

    public List<Paquete> BuscarArea(BigDecimal idatr, BigDecimal idsede) {
        String sede = "Sede";
        List<Paquete> Resultad = new ArrayList<Paquete>();
        Query consulta = em.createNamedQuery("Seguimiento.findPaqByArea").setParameter("idatr", idatr).setParameter("idsed", idsede).setParameter("sede", sede);
        List<Seguimiento> Resultado = consulta.getResultList();
        Iterator<Seguimiento> lista = Resultado.iterator();
        while (lista.hasNext()) {
            Seguimiento aux = lista.next();
            Resultad.add(aux.getIdpaq());
        }
        return Resultad;
    }

    public Paquete ConsultarPaqueteXIdPaqueteYSedeDeValija(Sede sede, BigDecimal idPaq) {
        Paquete Resultado;
        Query consulta = em.createNamedQuery("Paquete.findByidPaqueteYSedeDeValija").setParameter("idSede", sede).setParameter("idpaq", idPaq);
        Resultado = (Paquete) consulta.getSingleResult();
        return Resultado;
    }

    public void editarTipo(BigDecimal idusu, String tipo) {
        Query q = em.createNativeQuery("UPDATE Usuario SET tipousu=? WHERE idusu=?");
        q.setParameter(1, tipo);
        q.setParameter(2, idusu);
        q.executeUpdate();
    }

    public List<Paquete> estadisticasPaquete(Date fechaini, Date fechafin, String consulta, String idsede) {
        List<Paquete> Resultado = new ArrayList<Paquete>();
        if ("0".equals(idsede)) {
            if ("1".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesEnviados").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
            if ("2".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesRecibidos").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
            if ("3".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesPorEntregar").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
        } else {
            Sede idSed = new Sede();
            idSed.setIdsed(new BigDecimal(idsede));
            if ("1".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesEnviadosXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
            if ("2".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesRecibidosXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
            if ("3".equals(consulta)) {
                Resultado = (List<Paquete>) em.createNamedQuery("Paquete.totalPaquetesPorEntregarXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
        }
        return Resultado;
    }

    public Paquete consultarPaqueteXIdOCodigoBarras(String Codigo) {
        Paquete Resultado = null;
        if (Codigo.toString().length() > 8) {
            String sede = new String(Codigo.substring(0, 4));
            String año = new String(Codigo.substring(6, 8));
            BigDecimal id = new BigDecimal(Codigo.substring(8, Codigo.toString().length()));
            try {
                Query consulta = em.createNamedQuery("Paquete.findByCodigoBarras").setParameter("idpaq", id).setParameter("anio", año).setParameter("idsed", sede);
                Resultado = (Paquete) consulta.getSingleResult();
            } catch (Exception e) {
                Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", new BigDecimal(Codigo));
                Resultado = (Paquete) consulta.getSingleResult();
            }
        } else {
            Query consulta = em.createNamedQuery("Paquete.findByIdpaq").setParameter("idpaq", new BigDecimal(Codigo));
            Resultado = (Paquete) consulta.getSingleResult();
        }
        return Resultado;
    }
}