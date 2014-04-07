/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ValijaFacade extends AbstractFacade<Valija> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValijaFacade() {
        super(Valija.class);
    }

    public BigDecimal crearValija(Valija registro) {
        this.create(registro);
        BigDecimal max = this.ultimaValija(registro.getIduse().getIdusu().getIdusu());
        return max;
    }

    public void editarProveedorValija(BigDecimal idValija, String codProveedor, BigDecimal idPro) {
        Query q = em.createNativeQuery("UPDATE valija SET codproveedorval=?, idpro=? WHERE idval=?");
        q.setParameter(1, codProveedor);
        q.setParameter(2, idPro);
        q.setParameter(3, idValija);
        q.executeUpdate();
    }

    public BigDecimal ultimaValija(BigDecimal idusu) {
        Query consulta = em.createNamedQuery("Valija.maxVal").setParameter("idusu", idusu);
        BigDecimal Resultado = (BigDecimal) consulta.getSingleResult();
        return Resultado;
    }

    public void entregarValija(BigDecimal idValija, String Status, Usuariosede use) {
        Query q = em.createNativeQuery("UPDATE valija SET statusval=? idruse=? fecharval=? WHERE idval=?");
        q.setParameter(1, Status);
        q.setParameter(2, use);
        q.setParameter(3, FechaActual());
        q.setParameter(4, idValija);
        q.executeUpdate();
    }

    public List<Valija> estadisticasValija(Date fechaini, Date fechafin, String consulta, String idsede) {
        List<Valija> Resultado = new ArrayList<Valija>();
        if ("0".equals(idsede)) {
            if ("1".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasEnviadas").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
            if ("2".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasRecibidas").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
            if ("3".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasErradas").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
            if ("4".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasAnuladas").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).getResultList();
            }
        } else {
            Sede idSed = new Sede();
            idSed.setIdsed(new BigDecimal(idsede));
            if ("1".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasEnviadasXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
            if ("2".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasRecibidasXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
            if ("3".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasErradasXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
            if ("4".equals(consulta)) {
                Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasAnuladasXSede").setParameter("fechaIni", fechaini).setParameter("fechaFin", fechafin).setParameter("idsed", idSed).getResultList();
            }
        }
        return Resultado;
    }

    public void editarStatusValija(BigDecimal idValija, String status) {
        Query q = em.createNativeQuery("UPDATE valija SET statusval=? WHERE idval=?");
        q.setParameter(1, status);
        q.setParameter(2, idValija);
        q.executeUpdate();
    }

    public Valija consultarPaquete(BigDecimal idValija) {
        Valija Resultado;
        Query consulta = em.createNamedQuery("Valija.findByIdval").setParameter("idval", idValija);
        Resultado = (Valija) consulta.getSingleResult();
        return Resultado;
    }

    public Valija consultarValija(BigDecimal idValija, String sede) {
        Valija Resultado;
        String status1 = "0";
        String status2 = "4";
        Query consulta = em.createNamedQuery("Valija.findByIdvalXentregar").setParameter("idval", idValija).setParameter("sede", sede).setParameter("status1", status1).setParameter("status2", status2);
        Resultado = (Valija) consulta.getSingleResult();
        return Resultado;
    }

    public List<Valija> listarValijasXFechaVencimientoOrigen(BigDecimal idSedeOrigen) {
        List<Valija> Resultado = new ArrayList();
        List<BigDecimal> consulta = null;
        consulta = em.createNativeQuery("SELECT  v.idval FROM  NIVEL N, VALIJA V\n"
                + "WHERE N.OPERADORNIV='Valija' AND v.statusval='0' AND v.origenval=" + idSedeOrigen + " \n"
                + "AND horaslaborables(TO_DATE (TO_CHAR (v.fechaval, 'YYYY-MM-DD HH24:MI'),'YYYY-MM-DD HH24:MI'),TO_DATE (TO_CHAR (SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI'),'YYYY-MM-DD HH24:MI')) > N.TIEMPONIV ").getResultList();
        for (int i = 0; i < consulta.size(); i++) {
            Query consultaValija = em.createNamedQuery("Valija.findByIdval").setParameter("idval", consulta.get(i));
            Resultado.add((Valija) consultaValija.getSingleResult());
        }
        return Resultado;
    }

    public List<Valija> listarValijasXFechaVencimientoDestino(Sede idSede) {
        List<Valija> Resultado = new ArrayList();
        List<BigDecimal> consulta = null;
        consulta = em.createNativeQuery("SELECT  v.idval\n"
                + "FROM  NIVEL N, VALIJA V\n"
                + "WHERE N.OPERADORNIV='Valija' AND v.statusval='0' AND v.destinoval=" + idSede.getIdsed() + "\n"
                + "AND horaslaborables(TO_DATE (TO_CHAR (v.fechaval, 'YYYY-MM-DD HH24:MI'),'YYYY-MM-DD HH24:MI'),TO_DATE (TO_CHAR (SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI'),'YYYY-MM-DD HH24:MI')) > N.TIEMPONIV; ").getResultList();
        for (int i = 0; i < consulta.size(); i++) {
            Query consultaValija = em.createNamedQuery("Valija.findByIdval").setParameter("idval", consulta.get(i));
            Resultado.add((Valija) consultaValija.getSingleResult());
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

    public Valija consultarValijaXIdOCodigoBarras(String Codigo, String sedeVal) {
        Valija Resultado = null;
        Sede resultSede = null;
        String status1 = "0";
        String status2 = "4";
        if (Codigo.toString().length() > 8) {
            String sede = new String(Codigo.substring(0, 4));
            String año = new String(Codigo.substring(6, 8));
            BigDecimal id = new BigDecimal(Codigo.substring(8, Codigo.toString().length()));
            try {
                Query consultaSede = em.createNamedQuery("Sede.findByCodigosed").setParameter("codigo", sede);
                resultSede = (Sede) consultaSede.getSingleResult();
                Query consulta = em.createNamedQuery("Valija.findByIdYAnio").setParameter("idval", id).setParameter("anio", año).setParameter("sede", sedeVal);
                Resultado = (Valija) consulta.getSingleResult();
                if (Resultado.getOrigenval().toString().compareTo(resultSede.getIdsed().toString()) == 0) {
                    return Resultado;
                } else {
                    consulta = em.createNamedQuery("Valija.findByIdvalXentregar").setParameter("idval", new BigDecimal(Codigo)).setParameter("sede", sedeVal).setParameter("status1", status1).setParameter("status2", status2);
                    Resultado = (Valija) consulta.getSingleResult();
                }
            } catch (Exception e) {
                Query consulta = em.createNamedQuery("Valija.findByIdvalXentregar").setParameter("idval", new BigDecimal(Codigo)).setParameter("sede", sedeVal).setParameter("status1", status1).setParameter("status2", status2);
                Resultado = (Valija) consulta.getSingleResult();
            }
        } else {
            Query consulta = em.createNamedQuery("Valija.findByIdvalXentregar").setParameter("idval", new BigDecimal(Codigo)).setParameter("sede", sedeVal).setParameter("status1", status1).setParameter("status2", status2);
            Resultado = (Valija) consulta.getSingleResult();
        }
        return Resultado;
    }
    
     public Valija consultarValijaXIdOCodigoBarra(String Codigo) {
        Valija Resultado = null;
   
        if (Codigo.toString().length() > 8) {
            String sede = new String(Codigo.substring(0, 4));
            String año = new String(Codigo.substring(6, 8));
            BigDecimal id = new BigDecimal(Codigo.substring(8, Codigo.toString().length()));
           
           
               Query consulta = em.createNamedQuery("Valija.findByIdval").setParameter("idval", id);
                Resultado = (Valija) consulta.getSingleResult();
                
           
        } else {
            Query consulta = em.createNamedQuery("Valija.findByIdval").setParameter("idval", new BigDecimal(Codigo));
            Resultado = (Valija) consulta.getSingleResult();
        }
        return Resultado;
    }
     
      public List<Valija> consultarStatusValija(Usuariosede iduse) {
        List<Valija>  Resultado;
        Query consulta = em.createNamedQuery("Valija.findByStatusval").setParameter("iduse", iduse.getIduse()).setParameter("iduser", iduse.getIduse());
        Resultado = (List<Valija>)  consulta.getResultList();
        return Resultado;
    }
}