/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Adjunto;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bitacora;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Mensaje;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Prioridad;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Rol;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.AdjuntoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BitacoraFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BuzonFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.DocumentoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.IncidenteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.InfobandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.MensajeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PrioridadFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.RolFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SedeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SeguimientoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuarioFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuariosedeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ValijaFacade;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.Query;

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
    private BuzonFacade ejbBuzon;
    @EJB
    private UsuariosedeFacade ejbUsuariosede;
    @EJB
    private SedeFacade ejbSede;
    @EJB
    private BandejaFacade ejbBandeja;
    @EJB
    private DocumentoFacade ejbDocumento;
    @EJB
    private ValijaFacade ejbValija;
    @EJB
    private BitacoraFacade ejbBitacora;
    @EJB
    private RolFacade ejbRol;
    @EJB
    SeguimientoFacade ejbSeguimiento;
    @EJB
    IncidenteFacade ejbIncidente;
    @EJB
    MensajeFacade ejbMensaje;
    @EJB
    AdjuntoFacade ejbAdjunto;
    @EJB
    PrioridadFacade ejbPrioridad;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "consultarBandejas")
    public List<Infobandeja> consultarBandejas() {

        List<Infobandeja> Registro = new ArrayList<Infobandeja>();

        try {
            Registro = ejbInfobandeja.findAll();

        } catch (Exception e) {
            Registro = null;
        }

        return Registro;
    }

    @WebMethod(operationName = "buscarUsuario")
    public Usuario buscarUsuario(@WebParam(name = "user") String UsuarioActual) {

        BigDecimal id = new BigDecimal(UsuarioActual);
        return ejbUsuario.find(id);

    }

    @WebMethod(operationName = "consultarPaquetesXBandeja")
    public List<Paquete> consultarPaquetesXBandeja(@WebParam(name = "user") String idUser, @WebParam(name = "ban") String ban) {

        BigDecimal id = new BigDecimal(idUser);
        List<Paquete> Registro = new ArrayList<Paquete>();

        Infobandeja inBandeja = ejbInfobandeja.consultarBandejaXNombre(ban);
        Usuario usuario = ejbUsuario.find(id);

        Iterator<Bandeja> iterator = inBandeja.getBandejaCollection().iterator();
        while (iterator.hasNext()) {
            Bandeja aux = iterator.next();
            if ((aux.getIdpaq().getOrigenpaq().getIdusu() == usuario.getIdusu() || aux.getIdpaq().getDestinopaq().getIdusu().getIdusu() == usuario.getIdusu()) && aux.getIdusu().getIdusu() == usuario.getIdusu()) {
                Registro.add(aux.getIdpaq());
            } else {
                iterator.remove();
            }

        }

        return Registro;
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
    public int insertarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {

        int Resultado;
        try {
            ejbUsuario.insertar(registroUsuario);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     ** Método encargado de deshabilitar registros de usuario el status
     * cambiara a 0
     *
     * @param idUsuario objeto de la entidad usuario , debe tener el campo id
     * como mínimo
     */
    @WebMethod(operationName = "deshabilitarUsuario")
    public void deshabilitarUsuario(@WebParam(name = "idUsuario") String idUsuario) {
        ejbUsuario.deshabilitar(idUsuario);
    }

    /**
     *
     * Método encargado de cambiar el status de registros de la entidad Usuario,
     * el status cambiara a 1
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
     * Método encargado de consultar un registro de Permisologia de acuerdo a su
     * id
     *
     * @param idPermisologia string que contiene el id del Permisologia a
     * consultar
     * @return objeto de la entidad Permisologia
     *
     * @WebMethod(operationName = "consultarPermisologia") public Permisologia
     * consultarPermisologia(
     * @WebParam(name = "idUsuario") String idPer) { Permisologia Resultado; try
     * { Resultado = ejbPermisologia.consultarPermisologia(idPer); } catch
     * (Exception e) { Resultado = null; } return Resultado; } * /**
     *
     * Método encargado de consultar un registro de Permisologia de acuerdo a su
     * id
     *
     * @param idPermisologia string que contiene el id del Permisologia a
     * consultar
     * @return objeto de la entidad Permisologia
     *
     * @WebMethod(operationName = "listarPermisologia") public
     * List<Permisologia> listarPermisologia() { List<Permisologia> Resultado;
     * try { Resultado = ejbPermisologia.findAll(); } catch (Exception e) {
     * Resultado = null; } return Resultado; } *
     *
     *
     * /**
     * Método encargado de insertar registros de la entidad Documento
     *
     * @param registroDocumento objeto de la entidad Documento , debe tener como
     * mínimo los campos obligatorios para poder insertar
     */
    @WebMethod(operationName = "insertarDocumento")
    public int insertarDocumento(@WebParam(name = "registroDocumento") Documento registroDocumento) {
        int Resultado;
        try {
            ejbDocumento.insertar(registroDocumento);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método encargado de editar registros de la entidad Documento
     *
     * @param registroDocumento objeto de la entidad Documento
     */
    @WebMethod(operationName = "editarDocumento")
    public int editarDocumento(@WebParam(name = "registroDocumento") Documento registroDocumento) {
        int Resultado;
        try {

            ejbDocumento.editar(registroDocumento);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     ** Método encargado de deshabilitar registros de Documento el status
     * cambiara a 0
     *
     * @param idDocumento objeto de la entidad Documento , debe tener el campo
     * id como mínimo
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

    @WebMethod(operationName = "crearPaquete")
    public int insertarPaquete(@WebParam(name = "registroPaquete") Paquete registroPaquete) {
        int Resultado;
        try {
            ejbPaquete.crearPaquete(registroPaquete);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "insertarValija")
    public String insertarValija(@WebParam(name = "idusu") String idusu, @WebParam(name = "sorigen") String IdsedeO, @WebParam(name = "sdestino") String sedeD) {
        BigDecimal Resultado;
        BigDecimal id = new BigDecimal(IdsedeO);
        Valija registroValija = new Valija();
        Date hoy = new Date();
        Sede destino = ejbSede.ConsultarSedeXNombre(sedeD);
        Usuario usu = ejbUsuario.consultarUsuario(idusu);
        registroValija.setDestinoval(destino);
        registroValija.setOrigenval(id);
        registroValija.setIdusu(usu);
        registroValija.setFechaval(hoy);
        registroValija.setFechaalerval(hoy);
        registroValija.setStatusval("0");


        try {
            Resultado = ejbValija.crearValija(registroValija);

        } catch (Exception e) {
            Resultado = new BigDecimal(0);
        }
        return Resultado.toString();
    }

    @WebMethod(operationName = "ConsultarPaquetesXValija")
    public List<Paquete> ConsultarPaquetesXValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "sede") String sede) {
        List<Paquete> Resultado = null;

        try {
            Resultado = ejbPaquete.ConsultarPaquetesXValija(registroValija, sede);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ConsultarPaquetesParaValija")
    public List<Paquete> ConsultarPaquetesParaValija(@WebParam(name = "sede") String sede, @WebParam(name = "sedeDestino") String sedeDestino) {
        List<Paquete> Resultado = new ArrayList<Paquete>();

        try {
            List<Paquete> Resul = ejbPaquete.ConsultarPaquetesParaValija(sede);
            Iterator<Paquete> lista = Resul.iterator();
            while (lista.hasNext()) {
                Paquete aux = lista.next();
                if (aux.getDestinopaq().getIdsed().getNombresed().equals(sedeDestino)) {
                    Resultado.add(aux);
                } else {
                    lista.remove();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ConsultarSedeParaValija")
    public List<Paquete> ConsultarSedeParaValija(@WebParam(name = "sede") String sede) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.ConsultarSedeParaValija(sede);

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ConsultarSede")
    public Sede ConsultarSede(@WebParam(name = "idSede") String idSede) {
        Sede Resultado = null;
        BigDecimal id = new BigDecimal(idSede);
        try {
            Resultado = ejbSede.find(id);

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "consultarUsuariosXSede")
    public List<Usuario> consultarUsuariosXSede(@WebParam(name = "sede") String sede) {
        List<Usuario> Resultado = null;

        try {
            Resultado = ejbUsuario.consultarUsuariosXSede(sede);

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ConsultarSedeXNombre")
    public List<Paquete> ConsultarSedeXNombre(@WebParam(name = "sede") String sede) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.ConsultarSedeParaValija(sede);

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ActualizacionLocalizacionyValijaDelPaquete")
    public List<Paquete> ActualizacionLocalizacionyValijaDelPaquete(@WebParam(name = "localizacion") String Localizacion, @WebParam(name = "idpaq") String idpaq, @WebParam(name = "idval") String idval) {
        List<Paquete> Resultado = null;
        try {
            ejbPaquete.ActualizacionLocalizacionyValijaDelPaquete(Localizacion, idpaq, idval);

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "insertarBuzon")
    public int insertarBuzon(@WebParam(name = "idusu") String idusu, @WebParam(name = "idusub") String idusub, @WebParam(name = "sede") String sede) {

        try {

            Usuario usu = ejbUsuario.consultarUsuario(idusu);
            Usuario usub = ejbUsuario.consultarUsuario(idusub);
            Sede destino = ejbSede.ConsultarSedeXNombre(sede);
            Buzon buzoni = new Buzon();
            buzoni.setIdsed(destino);
            buzoni.setIdusu(usu);
            buzoni.setIdusubuz(usub);
            buzoni.setTipobuz("0");

            ejbBuzon.insertarBuzon(buzoni);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @WebMethod(operationName = "consultarRoles")
    public List<Rol> consultarRoles() {
        List<Rol> Resultado = new ArrayList<Rol>();
        try {
            Resultado = ejbRol.findAll();


        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "consultarSedeRol")
    public Usuariosede consultarSedeRol(@WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {
        Usuariosede Resultado = new Usuariosede();
        Sede sed = ejbSede.ConsultarSedeXNombre(sede);
        try {
            Resultado = ejbUsuariosede.sedeRolXId(idusu, sed);


        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "ConsultarSedes")
    public List<Sede> ConsultarSedes() {
        List<Sede> Resultado = null;

        try {
            Resultado = ejbSede.findAll();

        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "actualizacionLocalizacionRecibidoPaquete")
    public int actualizacionLocalizacionRecibidoPaquete(@WebParam(name = "idpaq") String idpaq) {
        int Resultado = 0;
        try {
            ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "actualizarBandeja")
    public int actualizarBandeja(@WebParam(name = "idpaq") String idpaq) {
        int Resultado;
        try {
            BigDecimal idu = new BigDecimal(idpaq);
            Paquete paq = ejbPaquete.find(idu);
            Usuario usu = ejbUsuario.find(paq.getOrigenpaq().getIdusu());
            Usuario usud = ejbUsuario.find(paq.getDestinopaq().getIdusubuz().getIdusu());

            if ("0".equals(paq.getRespaq())) {

                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);

            }
            if ("1".equals(paq.getRespaq())) {
                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);
            }
            if ("2".equals(paq.getRespaq())) {

                //ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido("entregado con respuesta", paq.getIdpaqres().toString());
                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);
            }

            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "entregarValija")
    public int entregarValija(@WebParam(name = "idval") String idval, @WebParam(name = "status") String status) {
        int Resultado = 0;
        BigDecimal id = new BigDecimal(idval);
        try {
            ejbValija.entregarValija(id, status);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "editarRol")
    public int editarRol(@WebParam(name = "idusu") String idusu, @WebParam(name = "rol") String rol, @WebParam(name = "sede") String sede) {
        int Resultado = 0;
        BigDecimal idu = new BigDecimal(idusu);
        BigDecimal ids = new BigDecimal(sede);
        try {
            ejbUsuariosede.editarRol(idu, rol, ids);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "editarTipoUsuario")
    public int editarTipoUsuario(@WebParam(name = "idusu") String idusu, @WebParam(name = "tipo") String tipo) {
        int Resultado = 0;
        BigDecimal idu = new BigDecimal(idusu);
        try {
            ejbPaquete.editarTipo(idu, tipo);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "asignarSede")
    public int asignarSede(@WebParam(name = "idusu") String idusu, @WebParam(name = "rol") String rol, @WebParam(name = "sede") String sede) {
        int Resultado = 0;
        BigDecimal idu = new BigDecimal(idusu);
        BigDecimal ids = new BigDecimal(sede);
        BigDecimal idr = new BigDecimal(rol);
        Usuario usu = ejbUsuario.find(idu);
        Sede sed = ejbSede.find(ids);
        Rol ro = ejbRol.find(idr);
        try {
            ejbUsuariosede.asignarSede(usu, ro, sed);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    //////////////// inicio niuska
    @WebMethod(operationName = "insertarBitacora")
    public int insertarBitacora(@WebParam(name = "idSede") String idSede, @WebParam(name = "idUsu") String idUsu, @WebParam(name = "accion") String accion, @WebParam(name = "observacion") String observacion) {
        int Resultado;
        try {
            BigDecimal idSed = new BigDecimal(idSede);
            Sede sed = ejbSede.consultarSedeXId(idSed);
            Usuario usu = ejbUsuario.consultarUsuario(idUsu);
            ejbBitacora.insertarBitacora(sed, usu, accion, observacion);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    //Listado de bitacora
    @WebMethod(operationName = "listarBitacora")
    public List<Bitacora> listarBitacora() {
        List<Bitacora> Resultado = null;
        try {
            Resultado = ejbBitacora.listarBitacora();
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Vacio de bitacora
    @WebMethod(operationName = "vaciarBitacora")
    public int vaciarBitacora() {
        List<Bitacora> bitacoras = null;
        Bitacora registroBitacora;
        int Resultado = 0;
        try {
            bitacoras = ejbBitacora.listarBitacora();
            registroBitacora = new Bitacora();
            for (int i = 0; i < bitacoras.size(); i++) {
                registroBitacora = bitacoras.get(i);
                ejbBitacora.vaciarBitacora(registroBitacora);
            }
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    //Lista de Paquete por Usuario y Fecha Procesados
    @WebMethod(operationName = "listaPaquetesXUsuarioYFechaProcesadas")
    public List<Paquete> listaPaquetesXUsuarioYFechaProcesadas(@WebParam(name = "idUsuario") String idUsuario) {

        List<Paquete> Resultado = null;

        try {
            Usuario idUsu = new Usuario();
            idUsu.setIdusu(new BigDecimal(idUsuario));
            Resultado = ejbSeguimiento.listaPaquetesXUsuarioYFechaProcesadas(idUsu);

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Confirmar Valija
    @WebMethod(operationName = "confirmarValija")
    public int confirmarValija(@WebParam(name = "idValija") String idValija, @WebParam(name = "codZoom") String codZoom) {

        int Resultado = 0;
        List<Paquete> lista;
        BigDecimal idPaquete;
        BigDecimal idVal;
        String localizacion;
        Valija idValPaq;

        try {
            idVal = new BigDecimal(idValija);
            idValPaq = new Valija();
            idValPaq.setIdval(idVal);
            Valija consulta = ejbValija.find(idVal);
            if (consulta != null) {
                ejbValija.editarZoomValija(idVal, codZoom);
                lista = ejbPaquete.listarPaquetesXValija(idValPaq);
                localizacion = "Zoom";
                for (int i = 0; i < lista.size(); i++) {
                    idPaquete = lista.get(i).getIdpaq();
                    ejbPaquete.editarLocalizacionPaquete(idPaquete, localizacion);
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
    public int reportarPaqueteExcedente(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "registroUsuario") String registroUsuario) {

        int Resultado = 0;
        String idIncidente;
        BigDecimal idVal;
        BigDecimal idMaxInci;
        BigDecimal idPaq;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;
        Paquete registroPaq;
        Usuario registroUsua;

        try {
            registroPaq = new Paquete();
            idPaq = new BigDecimal(registroPaquete);
            registroPaq = ejbPaquete.consultarPaquete(idPaq);
            idVal = registroPaq.getIdval().getIdval();
            registroUsua = new Usuario();
            registroUsua.setIdusu(new BigDecimal(registroUsuario));
            nuevoSeg = new Seguimiento();
            nuevoSeg.setFechaseg(new Date());
            nuevoSeg.setIdpaq(registroPaq);
            nuevoSeg.setIdusu(registroUsua);
            nuevoSeg.setStatusseg("2");
            nuevoSeg.setTiposeg("1");
            nuevoSeg.setNivelseg("Valija");
            ejbSeguimiento.insertarSeguimiento(nuevoSeg);

            nuevoIncidente = new Incidente();
            nuevoIncidente.setNombreinc("Paquete Excedente");
            nuevoIncidente.setDescripcioninc("Reporte de paquete excedente");
            ejbIncidente.insertarIncidente(nuevoIncidente);

            idIncidente = ejbIncidente.ultimoIncidente();
            idMaxInci = new BigDecimal(idIncidente);
            ejbValija.editarIncidenteValija(idVal, idMaxInci);

            //Cambio de Status de Paquete a Reenviado (3)
            ejbPaquete.editarStatusPaquete(idPaq, "3");

            //Cambio de Status de Valija con Paquete Excedente (3)
            ejbValija.editarStatusValija(idVal, "3");
            Resultado = 1;

        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    //Reportar Paquete Ausente
    @WebMethod(operationName = "reportarPaqueteAusente")
    public int reportarPaqueteAusente(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "datosPaquete") String datosPaquete) {

        int Resultado = 0;
        String idIncidente;
        String idMensaje;
        BigDecimal idVal;
        BigDecimal idMaxInci;
        BigDecimal idMaxMens;
        BigDecimal idPaq;
        Incidente nuevoIncidente;
        Paquete registroPaq;
        Mensaje nuevoMensaje;

        try {
            idPaq = new BigDecimal(registroPaquete);
            registroPaq = new Paquete();
            registroPaq = ejbPaquete.consultarPaquete(idPaq);
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
            ejbPaquete.editarMensajePaquete(idPaq, idMaxMens);

            //Cambio de Status de Paquete a Ausente (4)
            ejbPaquete.editarStatusPaquete(idPaq, "4");

            //Cambio de Status de Valija con Paquete Ausente(2)
            ejbValija.editarStatusValija(idVal, "2");
            Resultado = 1;

        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }
    //Reportar Valija Completa por error de Destino

    @WebMethod(operationName = "reportarValija")
    public int reportarValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "registroUsuario") String registroUsuario) {

        int Resultado = 0;
        String idIncidente;
        List<Paquete> lista;
        BigDecimal idPaq;
        BigDecimal idVal;
        BigDecimal idMaxInci;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;
        Paquete registroPaquete;
        Usuario registroUsua;
        Valija idValija;

        try {
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
            lista = ejbPaquete.listarPaquetesXValija(idValija);

            for (int i = 0; i < lista.size(); i++) {
                idPaq = lista.get(i).getIdpaq();
                registroPaquete = new Paquete();
                registroPaquete.setIdpaq(idPaq);

                nuevoSeg = new Seguimiento();
                nuevoSeg.setFechaseg(new Date());
                nuevoSeg.setIdpaq(registroPaquete);
                nuevoSeg.setIdusu(registroUsua);
                nuevoSeg.setStatusseg("2");
                nuevoSeg.setTiposeg("1");
                nuevoSeg.setNivelseg("Valija");
                ejbSeguimiento.insertarSeguimiento(nuevoSeg);

                //Cambio de Status de Paquete a Reenviado (3)
                ejbPaquete.editarStatusPaquete(idPaq, "3");
            }
            //Cambio de Status de Valija a Reenviada (4)
            ejbValija.editarStatusValija(idVal, "4");

            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }
    //Lista de Valijas con Fecha Hoy

    @WebMethod(operationName = "listarValijasXFechaYUsuarioSede")
    public List<Valija> listarValijasXFechaYUsuarioSede(@WebParam(name = "registroSede") String registroSede) {

        List<Valija> Resultado = null;
        List<Usuariosede> idUsuario;
        List<Valija> valijas;
        Sede idSede;
        Usuario idUsu;
        Valija val;

        try {
            idSede = new Sede();
            idSede.setIdsed(new BigDecimal(registroSede));
            idUsuario = ejbUsuariosede.listaUsuarios(idSede);

            for (int i = 0; i < idUsuario.size(); i++) {
                idUsu = new Usuario();
                idUsu = idUsuario.get(i).getIdusu();
                valijas = ejbValija.listarValijasXFechaYUsuario(idUsu);
                Resultado = new ArrayList<Valija>();
                int j = 0;

                if (valijas.isEmpty()) {
                    Resultado = null;
                }
                while (valijas.size() > j) {
                    val = valijas.get(j);
                    Resultado.add(val);
                    j++;
                }
            }

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Lista de Valijas que No esten Procesadas
    @WebMethod(operationName = "listarValijasNoProcesadas")
    public List<Valija> listarValijasNoProcesadas(@WebParam(name = "registroSede") String registroSede) {

        List<Valija> Resultado = null;
        List<Valija> valijas;
        Valija val;

        try {
            valijas = listarValijasXFechaYUsuarioSede(registroSede);
            Resultado = new ArrayList<Valija>();
            int j = 0;

            if (valijas.isEmpty()) {
                Resultado = null;
            }
            while (valijas.size() > j) {
                if (valijas.get(j).getStatusval().compareTo("2") == 0 || valijas.get(j).getStatusval().compareTo("3") == 0 || valijas.get(j).getStatusval().compareTo("4") == 0) {
                    val = valijas.get(j);
                    Resultado.add(val);
                }
                j++;
            }

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Lista de Valijas que esten Procesadas
    @WebMethod(operationName = "listarValijasProcesadas")
    public List<Valija> listarValijasProcesadas(@WebParam(name = "registroSede") String registroSede) {

        List<Valija> Resultado = null;
        List<Valija> valijas;
        Valija val;

        try {
            valijas = listarValijasXFechaYUsuarioSede(registroSede);
            Resultado = new ArrayList<Valija>();
            int j = 0;

            if (valijas.isEmpty()) {
                Resultado = null;
            }
            while (valijas.size() > j) {
                if (valijas.get(j).getStatusval().compareTo("1") == 0) {
                    val = valijas.get(j);
                    Resultado.add(val);
                }
                j++;
            }

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Lista de Correspondencias Enviados por el Usuario con Fecha Hoy
    @WebMethod(operationName = "listarEnviadoUsuarioXFecha")
    public List<Paquete> listarEnviadoUsuarioXFecha(@WebParam(name = "idUsuario") String idUsuario) {

        List<Paquete> Resultado = null;

        try {
            Usuario idUsu = new Usuario();
            idUsu.setIdusu(new BigDecimal(idUsuario));
            Resultado = ejbPaquete.listarEnviadoUsuarioXFecha(idUsu);

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Lista de Paquetes que esten Procesados por Respuesta
    @WebMethod(operationName = "listarPaquetesProcesadosXRespuesta")
    public List<Paquete> listarPaquetesProcesados(@WebParam(name = "idUsuario") String idUsuario, @WebParam(name = "respuesta") String respuesta) {

        List<Paquete> Resultado = null;
        List<Paquete> paquetes;
        Paquete paq;

        try {
            Usuario idUsu = new Usuario();
            idUsu.setIdusu(new BigDecimal(idUsuario));
            paquetes = ejbPaquete.listarPaquetesXOrigenYRespuesta(idUsu, respuesta);

            Resultado = new ArrayList<Paquete>();
            int j = 0;

            if (paquetes.isEmpty()) {
                Resultado = null;
            }
            while (paquetes.size() > j) {
                if (paquetes.get(j).getStatuspaq().compareTo("1") == 0) {
                    paq = paquetes.get(j);
                    Resultado.add(paq);
                }
                j++;
            }
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Lista de Paquetes que No esten Procesados por Respuesta
    @WebMethod(operationName = "listarPaquetesNoProcesadosXRespuesta")
    public List<Paquete> listarPaquetesNoProcesados(@WebParam(name = "idUsuario") String idUsuario, @WebParam(name = "respuesta") String respuesta) {

        List<Paquete> Resultado = null;
        List<Paquete> paquetes;
        Paquete paq;

        try {
            Usuario idUsu = new Usuario();
            idUsu.setIdusu(new BigDecimal(idUsuario));
            paquetes = ejbPaquete.listarPaquetesXOrigenYRespuesta(idUsu, respuesta);

            Resultado = new ArrayList<Paquete>();
            int j = 0;

            if (paquetes.isEmpty()) {
                Resultado = null;
            }
            while (paquetes.size() > j) {
                if (paquetes.get(j).getStatuspaq().compareTo("2") == 0 || paquetes.get(j).getStatuspaq().compareTo("3") == 0 || paquetes.get(j).getStatuspaq().compareTo("4") == 0) {
                    paq = paquetes.get(j);
                    Resultado.add(paq);
                }
                j++;
            }
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Consulta de Sede por Id
    @WebMethod(operationName = "consultarSedeXId")
    public Sede consultarSedeXId(@WebParam(name = "idSede") String idSede) {

        Sede Resultado = null;

        try {
            BigDecimal idSed = new BigDecimal(idSede);
            Resultado = ejbSede.consultarSedeXId(idSed);

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Ultimo Paquete ingresado para la impresión del comprobante
    @WebMethod(operationName = "ultimoPaqueteXOrigen")
    public Paquete ultimoPaqueteXOrigen(@WebParam(name = "idUsuario") String idUsuario) {

        String idPaquete;
        BigDecimal idPaq;
        Usuario idUsua;
        Paquete Resultado = null;

        try {
            idUsua = new Usuario();
            idUsua.setIdusu(new BigDecimal(idUsuario));
            idPaquete = ejbPaquete.ultimoPaqueteXOrigen(idUsua);

            if (idPaquete != null) {
                idPaq = new BigDecimal(idPaquete);
                Resultado = ejbPaquete.consultarPaquete(idPaq);
            }

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Consulta del paquete por id
    @WebMethod(operationName = "consultarPaqueteXId")
    public Paquete consultarPaqueteXId(@WebParam(name = "idPaquete") String idPaquete) {
        Paquete Resultado = null;
        BigDecimal idPaq = new BigDecimal(idPaquete);
        try {
            Resultado = ejbPaquete.consultarPaquete(idPaq);
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Ultima Valija ingresado para la impresión del comprobante
    @WebMethod(operationName = "ultimaValijaXUsuario")
    public Valija ultimaValijaXUsuario(@WebParam(name = "idUsuario") String idUsuario) {

        BigDecimal idUsu;
        Valija Resultado = null;

        try {
            idUsu = ejbValija.ultimaValija(new BigDecimal(idUsuario));

            if (idUsu != null) {
                Resultado = ejbValija.consultarPaquete(idUsu);
            }

        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    //Consulta adjunto por id del paquete
    @WebMethod(operationName = "consultarAdjuntoXPaquete")
    public Adjunto consultarAdjuntoXPaquete(@WebParam(name = "idPaquete") String idPaquete) {
        Adjunto Resultado = null;
        Paquete idPaq;
        try {
            idPaq = new Paquete();
            idPaq.setIdpaq(new BigDecimal(idPaquete));
            Resultado = ejbAdjunto.consultarAdjuntoXPaquete(idPaq);
        } catch (Exception e) {
            Resultado = null;
        }
        return Resultado;
    }

    /////////////// fin niuska
    //////////////// inicio mariela
    /**
     * Registra el seguimiento del paquete
     *
     * @param registroPaquete
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "registroSeguimiento")
    public int registroSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {
        int Resultado = 0;
        boolean reenvio = false;
        String nivelSeg = "", Tipo;
        Seguimiento nuevoSeg = new Seguimiento();
        Usuariosede usuarioSede = null;

        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
            usuarioSede = consultarUsuarioSede(registroUsuario, registroSede);
            registroPaquete = ejbPaquete.ConsultarPaqueteXId(registroPaquete.getIdpaq());
            try {
                Paquete Registro = ejbPaquete.ConsultarPaqueteXIdPaqueteYSedeDeValija(registroSede, registroPaquete.getIdpaq());
                Tipo = "1";
            } catch (Exception e) {
                Tipo = "0";
            }
            if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                nivelSeg = "Area de Trabajo";
            } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                nivelSeg = "Sede";
            } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                nivelSeg = "Emisario";
            } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0) {
                nivelSeg = "Valija";
            }

            if (RegistrosSeguimiento.isEmpty()) {
                nuevoSeg = new Seguimiento();
                nuevoSeg.setFechaseg(new Date());
                nuevoSeg.setIdpaq(registroPaquete);
                nuevoSeg.setIdusu(registroUsuario);
                nuevoSeg.setTiposeg(Tipo);
                nuevoSeg.setNivelseg(nivelSeg);
                nuevoSeg.setStatusseg("0");
                ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), nivelSeg);
                if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Area de trabajo");
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Sede");
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Emisario");
                }
                return 1;
            }


            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                //pregunto ya un envajilador habia tocado el paquete y el q recibo como parametro es envajilador quiere decir que es un reenvio de paquete
                if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo(registroPaquete.getLocalizacionpaq()) == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo(Tipo) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Emisario") == 0) {
                    reenvio = true;
                } else {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo(Tipo) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo(registroPaquete.getLocalizacionpaq()) == 0 && RegistrosSeguimiento.get(i).getIdusu().getIdusu().toString().compareTo(registroUsuario.getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Emisario") != 0) {
                        return Resultado;
                    }
                }
            }
            //Caso  Receptor nivel 1 Origen o Receptor nivel 3 Origen
            if ((usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("0") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        return Resultado;
                    }
                }
                Resultado = 1;

            } //Caso  Receptor nivel 2 Origen
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("0") == 0) {
                Resultado = 1;
            } //Caso  MultiRol Origen
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0 && Tipo.compareTo("0") == 0) {
                if (registroPaquete.getLocalizacionpaq().compareTo("Sede") == 0) {
                    nivelSeg = "Valija";
                } else {
                    nivelSeg = "Sede";
                }
                Resultado = 1;
            } //Caso  Empaquetador 
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("0") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        Resultado = 1;
                        break;
                    }
                }
            }//Caso  Desenvalijador 
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        if (registroPaquete.getIdval().getZoomval() != null) {
                            Resultado = 1;
                        }
                        break;
                    }
                }
            }//Caso  Receptor nivel 1 Destino o Caso  Receptor nivel 3 Destino
            else if ((usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                        Resultado = 1;
                        break;
                    }
                }

            } //Caso  Receptor nivel 2 Destino
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                        Resultado = 1;
                        break;
                    }
                }
            }//Caso  MultiRol Destino
            else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0 && Tipo.compareTo("0") == 0) {
                if (registroPaquete.getLocalizacionpaq().compareTo("Valija") == 0) {
                    nivelSeg = "Sede";
                } else {
                    nivelSeg = "Valija";
                }
                Resultado = 1;
            }

            if (Resultado == 1) {

                nuevoSeg = new Seguimiento();
                nuevoSeg.setFechaseg(new Date());
                nuevoSeg.setIdpaq(registroPaquete);
                nuevoSeg.setIdusu(registroUsuario);
                nuevoSeg.setTiposeg(Tipo);
                nuevoSeg.setNivelseg(nivelSeg);
                if (reenvio) {
                    nuevoSeg.setStatusseg("2");
                } else {
                    nuevoSeg.setStatusseg("0");
                }
                ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), nivelSeg);
                if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Area de trabajo");
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Sede");
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Emisario");
                    if (reenvio) {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "REENVIO", "Reenvio correspondencia");
                    }
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0) {
                    ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Valija");
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0) {
                    if (nivelSeg.compareTo("Sede") == 0) {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Sede");
                    } else {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Valija");
                    }
                }
            }
        } catch (Exception e) {
            return Resultado;

        }
        //Caso  id1: nivel 1 
        //Caso id2:  nivel 2 
        //Caso id3:  nivel 3 
        //Caso id4: Empaquetador Desempaquetador 4
        return Resultado;
    }

    /**
     * consulta el usuario sede por usuario y sede
     *
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "consultarUsuarioSede")
    public Usuariosede consultarUsuarioSede(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {
        Usuariosede Resultado = null;
        try {
            Resultado = ejbUsuariosede.ConsultarXUsuarioYSede(registroUsuario, registroSede);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * cuando el usuario destino recibe el paquete se finaliza el seguimiento
     *
     * @param registroPaquete
     * @param registroUsuario
     * @return
     */
    @WebMethod(operationName = "finalizarSeguimiento")
    public int finalizarSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
//Caso  Usuario Destino
        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
            boolean Nivel2 = false;
            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                if (RegistrosSeguimiento.get(i).getIdpaq().getOrigenpaq().getIdusu().toString().compareTo(registroPaquete.getOrigenpaq().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                    Nivel2 = true;
                    break;
                }
            }
            if (Nivel2) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    RegistrosSeguimiento.get(i).setStatusseg("1");
                    ejbSeguimiento.edit(RegistrosSeguimiento.get(i));
                }
                ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), registroUsuario.getUserusu());
                Resultado = 1;
            }

        } catch (Exception e) {
            return 0;
        }

        return Resultado;
    }

    /**
     * Editar usuario
     *
     * @param registroUsuario
     * @return
     */
    @WebMethod(operationName = "editarUsuario")
    public int editarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
        try {
            ejbUsuario.editarUsuario(registroUsuario);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    /**
     * lista paquetes que se pasaron de fecha limite o si la fecha alerta es
     * igual a la actualy el paquete no ha llegado de los paquetes que se han
     * enviado
     *
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "paquetesVencidosXOrigen")
    public List<Paquete> paquetesVencidosXOrigen(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {
        List<Paquete> ResultadoAlerta = null, ResultadoVencidas = null;
        boolean noEsta = false;
        try {
            ResultadoAlerta = ejbPaquete.consultarPaqueteXAlertaXUsuarioOrigen(registroUsuario, registroSede);
            ResultadoVencidas = ejbPaquete.consultarPaqueteXFechaVencimientoXOrigen(registroUsuario, registroSede);
            if (ResultadoAlerta != null && ResultadoVencidas != null) {
                for (int i = 0; i < ResultadoAlerta.size(); i++) {
                    noEsta = false;
                    for (int j = 0; j < ResultadoVencidas.size(); j++) {
                        if (ResultadoAlerta.get(i).getIdpaq() == ResultadoVencidas.get(j).getIdpaq()) {
                            noEsta = true;
                            break;
                        }
                    }
                    if (!noEsta) {
                        ResultadoVencidas.add(ResultadoAlerta.get(i));
                    }
                }
            } else {
                if (ResultadoAlerta == null && ResultadoVencidas != null) {
                    return ResultadoVencidas;
                } else if (ResultadoAlerta != null && ResultadoVencidas == null) {
                    return ResultadoAlerta;
                } else {
                    return null;
                }
            }

        } catch (Exception e) {
            return null;
        }
        return ResultadoVencidas;
    }

    /**
     *     * lista paquetes que se pasaron de fecha limite o si la fecha alerta es
     * igual a la actualy el paquete no ha llegado de los paquetes que debetria
     * recibir
     *
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "paquetesVencidosXDestino")
    public List<Paquete> paquetesVencidosXDestino(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {
        List<Paquete> ResultadoAlerta = null, ResultadoVencidas = null;
        boolean noEsta = false;
        try {
            ResultadoAlerta = ejbPaquete.consultarPaqueteXAlertaXUsuarioDestino(registroUsuario, registroSede);
            ResultadoVencidas = ejbPaquete.consultarPaqueteXFechaVencimientoXDestino(registroUsuario);
            if (ResultadoAlerta != null && ResultadoVencidas != null) {
                for (int i = 0; i < ResultadoAlerta.size(); i++) {
                    noEsta = false;
                    for (int j = 0; j < ResultadoVencidas.size(); j++) {
                        if (ResultadoAlerta.get(i).getIdpaq() == ResultadoVencidas.get(j).getIdpaq()) {
                            noEsta = true;
                            break;
                        }
                    }
                    if (!noEsta) {
                        ResultadoVencidas.add(ResultadoAlerta.get(i));
                    }
                }
            } else {
                if (ResultadoAlerta == null && ResultadoVencidas != null) {
                    return ResultadoVencidas;
                } else if (ResultadoAlerta != null && ResultadoVencidas == null) {
                    return ResultadoAlerta;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return ResultadoVencidas;
    }

    /**
     * consulta los contactos que el usuario tiene registrado en buzón
     *
     * @param registroUsuario
     * @return
     */
    @WebMethod(operationName = "consultarBuzonXUsuario")
    public List<Buzon> consultarBuzonXUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {
        List<Buzon> Resultado = null;
        try {
            Resultado = ejbBuzon.ConsultarBuzonXUsuario(registroUsuario, registroSede);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * lista todos los tipos de documentos
     *
     * @return
     */
    @WebMethod(operationName = "listarDocumentos")
    public List<Documento> listarDocumentos() {
        List<Documento> Resultado = null;
        try {
            Resultado = ejbDocumento.listarDocumentos();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * lista todas las prioridades
     *
     * @return
     */
    @WebMethod(operationName = "listarPrioridad")
    public List<Prioridad> listarPrioridad() {
        List<Prioridad> Resultado = null;
        try {
            Resultado = ejbPrioridad.listarPrioridades();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * consultarPaquetesXUsuario confirmados al día
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesXUsuarioProcesadasAlDia")
    public List<Paquete> consultarPaquetesXUsuarioProcesadasAlDia(@WebParam(name = "idUsuario") Usuario idUsuario) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbSeguimiento.listaPaquetesProcesadosXUsuarioAlDia(idUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * consultarPaquetesX sede confirmados al día
     *
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesConfirmadosXSedeAlDia")
    public List<Paquete> consultarPaquetesConfirmadosXSedeAlDia(@WebParam(name = "registroSede") Sede registroSede) {
        List<Paquete> paquetesXUsuario = null, paquetesConfirmados = new ArrayList<Paquete>();
        List<Usuario> resultadoUsuarios = null;
        boolean noAdd = false;
        try {
            resultadoUsuarios = ejbUsuariosede.ConsultarXRolYSede(registroSede);
            for (int i = 0; i < resultadoUsuarios.size(); i++) {
                paquetesXUsuario = ejbSeguimiento.listaPaquetesProcesadosXUsuarioAlDia(resultadoUsuarios.get(i));
                for (int j = 0; j < paquetesXUsuario.size(); j++) {
                    noAdd = false;
                    for (int k = 0; k < paquetesConfirmados.size(); k++) {
                        if (paquetesConfirmados.get(k).getIdpaq() == paquetesXUsuario.get(j).getIdpaq()) {
                            noAdd = true;
                            break;
                        }
                    }
                    if (!noAdd) {
                        paquetesConfirmados.add(paquetesXUsuario.get(j));
                    }

                }
            }
        } catch (Exception e) {
            return null;
        }
        return paquetesConfirmados;
    }

    /**
     * consulta sede de usuario sirve solo cuando tiene una sola sede
     * ..........ojo se usa en index
     *
     * @param registroUsuario
     * @return
     */
    @WebMethod(operationName = "consultarSedeDeUsuario")
    public List<Sede> consultarSedeDeUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Sede> Resultado = null;
        try {
            Resultado = ejbUsuariosede.ConsultarSedeDeUsuario(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * consulta usuario por useruse
     *
     * @param user
     * @return
     */
    @WebMethod(operationName = "consultarUsuarioXUser")
    public Usuario consultarUsuarioXUser(@WebParam(name = "user") String user) {
        Usuario Resul = null;
        try {
            Resul = ejbUsuario.consultarUsuarioXUser(user);
        } catch (Exception e) {
            return null;
        }
        return Resul;
    }

    /**
     * si agrego una imagen se usa el servicio
     *
     * @param registroAdj
     * @return
     */
    @WebMethod(operationName = "insertarAdjunto")
    public int insertarAdjunto(@WebParam(name = "registroAdj") Adjunto registroAdj) {
        int Resultado = 0;
        try {
            ejbAdjunto.insertarAdjunto(registroAdj);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }


    /**
     * lista todas las sedes
     *
     * @return
     */
    @WebMethod(operationName = "listarSedes")
    public List<Sede> listarSedes() {
        List<Sede> Resultado = null;
        try {
            Resultado = ejbSede.listarSedes();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    /**
     * caso en el cual se crea usuario
     *
     * @param registroUsuSede
     * @return
     */
    @WebMethod(operationName = "insertarUsuarioSedeXDefecto")
    public int insertarUsuarioSedeXDefecto(@WebParam(name = "registroUsuSede") Usuariosede registroUsuSede) {
        int Resultado = 0;
        try {
            Usuario RegUsu = new Usuario(ejbUsuario.consultarMAXId());
            registroUsuSede.setIdusu(RegUsu);
            ejbUsuariosede.insertarUsuarioSede(registroUsuSede);
            Resultado = 1;
        } catch (Exception e) {
        }
        return Resultado;
    }

    /**
     * consultaNombreSedeXId
     *
     * @param Id
     * @return
     */
    @WebMethod(operationName = "consultaNombreSedeXId")
    public String consultaNombreSedeXId(@WebParam(name = "Id") String Id) {
        String Resultado = "";
        try {
            Resultado = ejbSede.listarNombresXId(new BigDecimal(Id));
        } catch (Exception e) {
        }
        return Resultado;
    }

    /**
     * consultarBuzonXNombreUsuario o userusu del contacto y tambien por el
     * dueño del buzón
     *
     * @param userUsu
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "consultarBuzonXNombreUsuario")
    public Buzon consultarBuzonXNombreUsuario(@WebParam(name = "userUsu") String userUsu, @WebParam(name = "idUsuario") Usuario idUsuario) {
        Buzon Resultado = null;
        try {
            Resultado = ejbBuzon.ConsultarBuzonXNombreUsuario(userUsu, idUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "verificarExistenciaBuzon")
    public int verificarExistenciaBuzon(@WebParam(name = "dueno") Usuario dueno, @WebParam(name = "contacto") Usuario contacto, @WebParam(name = "idSede") Sede idSede) {
        int Resultado = 0;
        try {
            Buzon Result = ejbBuzon.verficarBuzon(dueno, contacto, idSede);
            Resultado = 1;
        } catch (Exception e) {
            Buzon Nuevo = new Buzon();
            Nuevo.setIdsed(idSede);
            Nuevo.setIdusu(dueno);
            Nuevo.setIdusubuz(contacto);
            Nuevo.setTipobuz("0");
            ejbBuzon.insertarBuzon(Nuevo);
            return Resultado;
        }
        return Resultado;
    }

    @WebMethod(operationName = "valijasXFechaVencidaXUsuarioOrigen")
    public List<Valija> valijasXFechaVencidaXUsuarioOrigen(@WebParam(name = "idSede") String idSede) {
        List<Valija> Resultado = null;
        try {
            Resultado = ejbValija.listarValijasXFechaVencimientoOrigen(new BigDecimal(idSede));
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "valijasXFechaVencidaXUsuarioDestino")
    public List<Valija> valijasXFechaVencidaXUsuarioDestino(@WebParam(name = "registroSede") Sede registroSede) {
        List<Valija> Resultado = null;
        try {
            Resultado = ejbValija.listarValijasXFechaVencimientoDestino(registroSede);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "insertarBandeja")
    public int insertarBandeja(@WebParam(name = "idpaq") Paquete idpaq) {
        int Resultado;
        try {
            Usuario usuOrigen = ejbUsuario.find(idpaq.getOrigenpaq().getIdusu());
            Usuario usuDestino = ejbUsuario.find(idpaq.getDestinopaq().getIdusubuz().getIdusu());
            Bandeja nuevo = new Bandeja();
            Infobandeja registroInfoB;
            registroInfoB = new Infobandeja(new BigDecimal("1"));
            nuevo.setIdiba(registroInfoB);
            nuevo.setIdpaq(idpaq);
            nuevo.setLeidoban("0");
            nuevo.setIdusu(usuOrigen);
            ejbBandeja.insertarBandeja(nuevo);
            registroInfoB = new Infobandeja(new BigDecimal("3"));
            nuevo.setIdiba(registroInfoB);
            nuevo.setLeidoban("0");
            nuevo.setIdusu(usuDestino);
            ejbBandeja.insertarBandeja(nuevo);
            Resultado = 1;
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }
    //////////////// fin mariela 
}
