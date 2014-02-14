/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Mensaje;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.IncidenteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.MensajeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SeguimientoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ValijaFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author USER
 */
@WebService(serviceName = "Niuska")
public class Niuska {
    
    @EJB
    SeguimientoFacade ejbSeguimiento;
    
    @EJB
    ValijaFacade ejbValija;
    
    @EJB
    PaqueteFacade ejbPaquete;
    
    @EJB
    IncidenteFacade ejbIncidente;
    
    @EJB
    MensajeFacade ejbMensaje;

    
    //Lista de Paquete por Usuario y Fecha Procesados
    @WebMethod(operationName = "listaPaquetesXUsuarioYFechaProcesadas")
    public List<Seguimiento> listaPaquetesXUsuarioYFechaProcesadas(@WebParam(name = "idUsuario") String idUsuario){
        
        List<Seguimiento> Resultado = null;
        
        try{
            Usuario idUsu = new Usuario();
            idUsu.setIdusu(new BigDecimal(idUsuario));
            Resultado = ejbSeguimiento.listaPaquetesXUsuarioYFechaProcesadas(idUsu);
        
        }catch(Exception e){
            Resultado = null;
        }
        return Resultado;
    }
    
    //Confirmar Valija
    @WebMethod(operationName = "confirmarValija")
    public int confirmarValija(@WebParam(name = "idValija") String idValija, @WebParam(name = "codZoom") String codZoom){
        
        int Resultado = 0;
        List<Paquete> lista;
        BigDecimal idPaquete;
        BigDecimal idVal;
        Valija idValPaq;
        
        try {
            idVal = new BigDecimal(idValija);
            idValPaq = new Valija();
            idValPaq.setIdval(idVal);
            Valija consulta = ejbValija.find(idVal);            
            if(consulta!=null){
                ejbValija.editarZoomValija(idVal, codZoom);                
                lista=ejbPaquete.listarPaquetesXValija(idValPaq);                
                for(int i=0; i<lista.size(); i++){
                    idPaquete=lista.get(i).getIdpaq();
                    ejbPaquete.editarLocalizacionZoomPaquete(idPaquete);
                }
                Resultado = 1;
            }
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }
    
    //Reportar Paquete Excedente
    @WebMethod(operationName = "reportarPaqueteExcedente")
    public int reportarPaqueteExcedente(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "registroUsuario") String registroUsuario){
        
        int Resultado = 0;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;        
        Paquete registroPaq;
        Usuario registroUsua;
        String idIncidente;
        BigDecimal idVal;
        BigDecimal idMaxInci;
                
        try{
            registroPaq = new Paquete();
            registroPaq = ejbPaquete.ConsultarPaquete(new BigDecimal (registroPaquete));
            idVal = registroPaq.getIdval().getIdval();
            registroUsua = new Usuario();
            registroUsua.setIdusu(new BigDecimal(registroUsuario));
            nuevoSeg = new Seguimiento();
            nuevoSeg.setFechaseg(new Date());
            nuevoSeg.setIdpaq(registroPaq);
            nuevoSeg.setIdusu(registroUsua);
            nuevoSeg.setStatusseg("Reenvio de Paquete");
            nuevoSeg.setTiposeg("1");
            nuevoSeg.setNivelseg("4");
            ejbSeguimiento.insertarSeguimiento(nuevoSeg);
            
            nuevoIncidente = new Incidente();
            nuevoIncidente.setNombreinc("Paquete Excedente");
            nuevoIncidente.setDescripcioninc("Reporte de paquete excedente");
            ejbIncidente.insertarIncidente(nuevoIncidente);
            
            idIncidente=ejbIncidente.ultimoIncidente();
            idMaxInci = new BigDecimal(idIncidente);            
            ejbValija.editarIncidenteValija(idVal, idMaxInci);                        
            Resultado = 1;
            
        }catch(Exception e){
             Resultado = 0;
        }        
        return Resultado;
    }
    
    //Reportar Paquete Ausente
    @WebMethod(operationName = "reportarPaqueteAusente")
    public int reportarPaqueteAusente(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "datosPaquete") String datosPaquete){
        
        int Resultado = 0;
        Incidente nuevoIncidente;
        Paquete registroPaq;
        Mensaje nuevoMensaje;
        String idIncidente;
        String idMensaje;
        BigDecimal idVal;
        BigDecimal idMaxInci;
        BigDecimal idMaxMens;
        BigDecimal idPaquete;
        
        try{
            idPaquete = new BigDecimal(registroPaquete);
            registroPaq = new Paquete();
            registroPaq = ejbPaquete.ConsultarPaquete(idPaquete);
            idVal = registroPaq.getIdval().getIdval();
            
            nuevoIncidente = new Incidente();
            nuevoIncidente.setNombreinc("Paquete Ausente");
            nuevoIncidente.setDescripcioninc("Reporte de paquete ausente");
            ejbIncidente.insertarIncidente(nuevoIncidente);
            
            nuevoMensaje = new Mensaje();
            nuevoMensaje.setNombremen("Paquete Ausente");
            nuevoMensaje.setDescripcionmen(datosPaquete);
            ejbMensaje.insertarMensaje(nuevoMensaje);
            
            idIncidente = ejbIncidente.ultimoIncidente();            
            idMaxInci = new BigDecimal(idIncidente);
            ejbValija.editarIncidenteValija(idVal, idMaxInci);
            
            idMensaje = ejbMensaje.ultimoMensaje();
            idMaxMens = new BigDecimal(idMensaje);
            ejbPaquete.editarMensajePaquete(idPaquete, idMaxMens);
            Resultado = 1;
            
        }catch(Exception e){
             Resultado = 0;
        }        
        return Resultado;
    }
    
    //Reportar Valija Completa por error de Destino
    @WebMethod(operationName = "reportarValija")
    public int reportarValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "registroUsuario") String registroUsuario){
        
        int Resultado = 0;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;
        Paquete registroPaquete;
        Usuario registroUsua;
        Valija idValija;
        String idIncidente;
        List<Paquete> lista;
        BigDecimal idPaquete;        
        BigDecimal idVal;
        BigDecimal idMaxInci;
        
        try{
            registroUsua = new Usuario();
            registroUsua.setIdusu(new BigDecimal(registroUsuario));            
            nuevoIncidente = new Incidente();
            nuevoIncidente.setNombreinc("Valija Incorrecta");
            nuevoIncidente.setDescripcioninc("Reporte de valija con destino incorrecto");
            ejbIncidente.insertarIncidente(nuevoIncidente);
            
            idIncidente = ejbIncidente.ultimoIncidente();
            idMaxInci = new BigDecimal(idIncidente);
            idVal = new BigDecimal(registroValija);
            idValija = new Valija();
            idValija.setIdval(idVal);            
            ejbValija.editarIncidenteValija(idVal, idMaxInci);            
            lista=ejbPaquete.listarPaquetesXValija(idValija);
            
                for(int i=0; i<lista.size(); i++){
                    idPaquete=lista.get(i).getIdpaq();
                    registroPaquete = new Paquete();
                    registroPaquete.setIdpaq(idPaquete);
                    
                    nuevoSeg = new Seguimiento();
                    nuevoSeg.setFechaseg(new Date());
                    nuevoSeg.setIdpaq(registroPaquete);
                    nuevoSeg.setIdusu(registroUsua);
                    nuevoSeg.setStatusseg("Reenvio de Valija");
                    nuevoSeg.setTiposeg("1");
                    nuevoSeg.setNivelseg("4");
                    ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                }
                        
            Resultado = 1;
        }catch(Exception e){
             Resultado = 0;
        }        
        return Resultado;
    }
    
    //Lista de Valijas con Fecha Hoy
    @WebMethod(operationName = "listarValijasXFecha")
    public List<Valija> listarValijasXFecha(){
        
        List<Valija> Resultado = null;
        
        try{            
            Resultado = ejbValija.listarValijasXFecha();
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
    
    //Lista de Valijas que No esten Procesadas
    @WebMethod(operationName = "listarValijasNoProcesadas")
    public List<Valija> listarValijasNoProcesadas(){
    
        List<Valija> Resultado = null;
        
        try{            
            Resultado = ejbValija.listarValijasNoProcesadas();
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
    
    //Lista de Valijas que esten Procesadas
    @WebMethod(operationName = "listarValijasProcesadas")
    public List<Valija> listarValijasProcesadas(){
    
        List<Valija> Resultado = null;
        
        try{            
            Resultado = ejbValija.listarValijasProcesadas();
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
    
    //Lista de Correspondencias Enviados por el Usuario con Fecha Hoy
    @WebMethod(operationName = "listarEnviadoUsuarioXFecha")
    public List<Paquete> listarEnviadoUsuarioXFecha(@WebParam(name = "idUsuario") Usuario idUsuario){
        
        List<Paquete> Resultado = null;
        
        try{            
            Resultado = ejbPaquete.listarEnviadoUsuarioXFecha(idUsuario);
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
    
    //Lista de Paquetes que esten Procesados por Respuesta
    @WebMethod(operationName = "listarPaquetesProcesadosXRespuesta")
    public List<Paquete> listarPaquetesProcesados(@WebParam(name = "respuesta") String respuesta){
    
        List<Paquete> Resultado = null;
        
        try{            
            Resultado = ejbPaquete.listarPaquetesProcesados(respuesta);
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
    
    //Lista de Paquetes que No esten Procesados por Respuesta
    @WebMethod(operationName = "listarPaquetesNoProcesadosXRespuesta")
    public List<Paquete> listarPaquetesNoProcesados(@WebParam(name = "respuesta") String respuesta){
    
        List<Paquete> Resultado = null;
        
        try{            
            Resultado = ejbPaquete.listarPaquetesNoProcesados(respuesta);
        
        }catch(Exception e){
            Resultado = null;
        }        
        return Resultado;
    }
}
