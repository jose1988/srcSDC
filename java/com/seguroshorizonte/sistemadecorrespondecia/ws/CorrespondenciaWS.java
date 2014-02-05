/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.DocumentoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.InfobandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuarioFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Pangea
 */
@WebService(serviceName = "CorrespondeciaWS")
public class CorrespondenciaWS {

     @EJB
    private UsuarioFacade ejbUsuario;
    @EJB
    private InfobandejaFacade ejbInfobandeja;
    @EJB
    private PaqueteFacade ejbPaquete;
    @EJB
    private BandejaFacade ejbBandeja;
     @EJB
    private PermisologiaFacade ejbPermisologia;
    @EJB
    private DocumentoFacade ejbDocumento;
   
    
    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "consultarUsuarioXUser")
    public Usuario consultarUsuarioXUser(@WebParam(name = "user") String user) {
        return ejbUsuario.consultarUsuarioXUser(user);
    }
    
    @WebMethod(operationName = "consultarBandejaXUser")
    public List<Infobandeja> consultarBandejaXUser(@WebParam(name = "user") String user) {
        
         List<Infobandeja> Registro = new  ArrayList<Infobandeja>();
      
        return Registro;
    }
    
    
     @WebMethod(operationName = "consultarPaquetesXBandeja")
    public List<Paquete> consultarPaquetesXBandeja(@WebParam(name = "user") String user,@WebParam(name = "ban") String ban) {
        
         List<Paquete> Registro = new  ArrayList<Paquete>();
       
         
        return Registro;
    }
    
    /**
     * Método creado para guardar la información de un Usuario por medio del
     * identificador(Id)
     *
     * @param UsuarioActual
     * @return
     */
    
    @WebMethod(operationName = "buscarUsuario")
    public Usuario buscarUsuario(@WebParam(name = "UsuarioActual") String UsuarioActual) {
        return ejbUsuario.find(UsuarioActual);
    }

    /**
     * Método que que retorna el número de registros existentes de la entidad
     * Usuario
     *
     * @return entero con el número de Usuarios
     */
    @WebMethod(operationName = "contarUsuario")
    public int contarUsuario() {
        return ejbUsuario.count();
    }

    /**
     * Método que lista los registros de la entidad Usuario de acuerdo a su
     * estado si es borrado o no
     *
     * @param status booleano si es 0 es desahablitado si es 1 es habilitado
     * @return lista de la entidad Usuario
     *
     */
    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> listarUsuarios(@WebParam(name = "status") String status) {
        return ejbUsuario.listarUsuarios(status);
    }

    /**
     * Método encargado de insertar registros de la entidad Usuario
     *
     * @param registroUsuario objeto de la entidad Usuario , debe tener como
     * mínimo los campos obligatorios para poder insertar
     */
    @WebMethod(operationName = "insertarUsuario")
    public void insertarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        ejbUsuario.insertar(registroUsuario);
    }

    /**
     * Método encargado de editar registros de la entidad Usuario
     *
     * @param registroUsuario objeto de la entidad Usuario
     */
    @WebMethod(operationName = "editarUsuario")
    public void editarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        ejbUsuario.editar(registroUsuario);
    }

    /**
     ** Método encargado de deshabilitar registros de usuario
     *  el status cambiara a 0
     * @param idUsuario objeto de la entidad usuario , debe tener el campo id como
     * mínimo
     */
    @WebMethod(operationName = "deshabilitarUsuario")
    public void deshabilitarUsuario(@WebParam(name = "idUsuario") String idUsuario) {
        ejbUsuario.deshabilitar(idUsuario);
    }

    /**
     *
     * Método encargado de cambiar el status de registros de la entidad
     * Usuario, el status cambiara a 1
     *
     * @param idUsuario String que contiene el id del Usuario a habilitar
     */
    @WebMethod(operationName = "habilitarUsuario")
    public void habilitarUsuario(@WebParam(name = "idUsuario") String idUsuario) {
        ejbUsuario.habilitar(idUsuario);
    }

     /**
     *
     * Método encargado de consultar un registro de Usuario de acuerdo a su id
     *
     * @param idUsuario string que contiene el id del Usuario a consultar
     * @return objeto de la entidad Usuario
     */
    @WebMethod(operationName = "consultarUsuario")
    public Usuario consultarUsuario(@WebParam(name = "idUsuario") String idUsuario) {
        Usuario Resultado;
        try {
            Resultado = ejbUsuario.consultarUsuario(idUsuario);
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }  
    
   
    
    /**
     *
     * Método encargado de consultar un registro de Permisologia de acuerdo a su id
     *
     * @param idPermisologia string que contiene el id del Permisologia a consultar
     * @return objeto de la entidad Permisologia
     *
    @WebMethod(operationName = "consultarPermisologia")
    public Permisologia consultarPermisologia(@WebParam(name = "idUsuario") String idPer) {
        Permisologia Resultado;
        try {
            Resultado = ejbPermisologia.consultarPermisologia(idPer);
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }  
    
     /**
     *
     * Método encargado de consultar un registro de Permisologia de acuerdo a su id
     *
     * @param idPermisologia string que contiene el id del Permisologia a consultar
     * @return objeto de la entidad Permisologia
     *
    @WebMethod(operationName = "listarPermisologia")
    public List<Permisologia> listarPermisologia() {
         List<Permisologia> Resultado;
        try {
            Resultado = ejbPermisologia.findAll();
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }  
    
     /**
     * Método que lista los registros de la entidad Documento 
     *
     
     */
    @WebMethod(operationName = "listarDocumentos")
    public List<Documento> listarDocumentos() {
        return ejbDocumento.listarDocumentos();
    }

    /**
     * Método encargado de insertar registros de la entidad Documento
     *
     * @param registroDocumento objeto de la entidad Documento , debe tener como
     * mínimo los campos obligatorios para poder insertar
     */
    @WebMethod(operationName = "insertarDocumento")
    public void insertarDocumento(@WebParam(name = "registroDocumento") Documento registroDocumento) {
        ejbDocumento.insertar(registroDocumento);
    }

    /**
     * Método encargado de editar registros de la entidad Documento
     *
     * @param registroDocumento objeto de la entidad Documento
     */
    @WebMethod(operationName = "editarDocumento")
    public void editarDocumento(@WebParam(name = "registroDocumento") Documento registroDocumento) {
        ejbDocumento.editar(registroDocumento);
    }

    /**
     ** Método encargado de deshabilitar registros de Documento
     *  el status cambiara a 0
     * @param idDocumento objeto de la entidad Documento , debe tener el campo id como
     * mínimo
     */
    @WebMethod(operationName = "eliminarDocumento")
    public void eliminarDocumento(@WebParam(name = "idDocumento") String idDocumento) {
        ejbDocumento.eliminar(idDocumento);
    }

    

     /**
     *
     * Método encargado de consultar un registro de Documento de acuerdo a su id
     *
     * @param idDocumento string que contiene el id del Documento a consultar
     * @return objeto de la entidad Documento
     */
    @WebMethod(operationName = "consultarDocumento")
    public Documento consultarDocumento(@WebParam(name = "idDocumento") String idDocumento) {
        Documento Resultado;
        try {
            Resultado = ejbDocumento.consultarDocumento(idDocumento);
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }  
    
     @WebMethod(operationName = "consultarDocumentoXNombre")
    public Documento consultarDocumentoXNombre(@WebParam(name = "doc") String docum) {
        return ejbDocumento.consultarDocumentoXNombre(docum);
    }
   
}
