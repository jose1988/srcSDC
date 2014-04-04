/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<Valija> listarValijasXFecha() {
        List<Valija> Resultado;
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        System.out.print(cal);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fecha = cal.getTime();
        Query consulta = em.createNamedQuery("Valija.findByFechaval").setParameter("fechaval", fecha);
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Valija> listarValijasNoProcesadas() {
        List<Valija> Resultado = null;
        Query consulta = em.createNamedQuery("Valija.findByNoProcesadas").setParameter("statusval1", '2').setParameter("statusval2", '3');
        Resultado = consulta.getResultList();
        return Resultado;
    }

    public List<Valija> listarValijasProcesadas() {
        List<Valija> Resultado = null;
        Query consulta = em.createNamedQuery("Valija.findByProcesadas").setParameter("statusval", '1');
        Resultado = consulta.getResultList();
        return Resultado;
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
    
     public List<Valija> estadisticasValija(Date fechaini, Date fechafin,String consulta,String idsede) {
        
         List<Valija> Resultado = new ArrayList<Valija>();
         if("0".equals(idsede)){
             
             if("1".equals(consulta)){
              Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasEnviadas").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).getResultList();
           
             }
              if("2".equals(consulta)){
              Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasRecibidas").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).getResultList();
           
             }
               if("3".equals(consulta)){
              Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasErradas").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).getResultList();
           
             }
                if("4".equals(consulta)){
              Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasAnuladas").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).getResultList();
           
             }
           
         }else{
             
              if("1".equals(consulta)){
                  
              Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasEnviadasXSede").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).setParameter("idsed",new BigDecimal(idsede)).getResultList();
           
             
             }
              if("2".equals(consulta)){
              
                  Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasRecibidasXSede").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).setParameter("idsed",new BigDecimal(idsede)).getResultList();
             
             }
               if("3".equals(consulta)){
                   
                   Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasEnviadasXSede").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).setParameter("idsed",new BigDecimal(idsede)).getResultList();
           
             }
                if("4".equals(consulta)){
                    
                    Resultado = (List<Valija>) em.createNamedQuery("Valija.totalValijasAnuladasXSede").setParameter("fechaIni",fechaini).setParameter("fechaFin",fechafin).setParameter("idsed",new BigDecimal(idsede)).getResultList();
            
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

    public List<Valija> listarValijasXFechaYUsuario(Usuario idUsuario, String fechaInicio, String fechaFin) {
        List<Valija> Resultado = null;
        Date fechaI;
        Date fechaF;
        fechaI=ParseFecha(fechaInicio);
        fechaF=ParseFecha(fechaFin);
        Query consulta = em.createNamedQuery("Valija.findByFechavalYUsuario").setParameter("fechaIni", fechaI).setParameter("fechaFin", fechaF).setParameter("idusu", idUsuario);
        Resultado = consulta.getResultList();
        return Resultado;
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
    
    public Date ParseFecha(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex){
            System.out.println(ex);
        }
        return fechaDate;
    }
}