/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Adjunto;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Areatrabajo;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bitacora;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Incidente;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Mensaje;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Nivel;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Organizacion;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Prioridad;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedor;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedorsede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Rol;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.AdjuntoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.AlertaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.AreatrabajoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BitacoraFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BuzonFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.DocumentoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.IncidenteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.InfobandejaFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.MensajeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.NivelFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.OrganizacionFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PrioridadFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ProveedorFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ProveedorsedeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.RolFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SedeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SeguimientoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuarioFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuariosedeFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ValijaFacade;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Collections;
import java.util.Comparator;
import org.apache.log4j.*;



@WebService(serviceName = "SistemaDeCorrespondenciaWS")
public class SistemaDeCorrespondenciaWS {

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
    private SeguimientoFacade ejbSeguimiento;
    @EJB
    private IncidenteFacade ejbIncidente;
    @EJB
    private MensajeFacade ejbMensaje;
    @EJB
    private AdjuntoFacade ejbAdjunto;
    @EJB
    private PrioridadFacade ejbPrioridad;
    @EJB
    private AreatrabajoFacade ejbAreaTrabajo;
    @EJB
    private OrganizacionFacade ejbOrganizacion;
    @EJB
    private AlertaFacade ejbAlerta;
    @EJB
    private ProveedorFacade ejbProveedor;
    @EJB
    private ProveedorsedeFacade ejbProveedorSede;
    @EJB
    private NivelFacade ejbNivel;

    
    private static Logger log = Logger.getLogger(SistemaDeCorrespondenciaWS.class);
    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "consultarBandejas")
    public List<Infobandeja> consultarBandejas() {
        
       BasicConfigurator.configure(); 

        List<Infobandeja> Registro = new ArrayList<Infobandeja>();
        try {
            Registro = ejbInfobandeja.findAll();
        } catch (Exception e) {
            
            log.error("Error en Servicio consultarBandejas");
            log.fatal("Error fatal en Servicio  consultarBandejas");
            
            Registro = null;
        }
        return Registro;
    }

    /**
     *
     * @param UsuarioActual
     * @return
     */
    @WebMethod(operationName = "buscarUsuario")
    public Usuario buscarUsuario(@WebParam(name = "user") String UsuarioActual) {

        BigDecimal id = new BigDecimal(UsuarioActual);
        return ejbUsuario.find(id);
    }
    static final Comparator<Paquete> SENIORITY_ORDER =
            new Comparator<Paquete>() {
        @Override
        public int compare(Paquete e1, Paquete e2) {
            return e2.getFechapaq().compareTo(e1.getFechapaq());
        }
    };

    /**
     *
     * @param idUser
     * @param ban
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesXBandeja")
    public List<Paquete> consultarPaquetesXBandeja(@WebParam(name = "user") String idUser, @WebParam(name = "ban") String ban, @WebParam(name = "idsed") String idsed) {

        BigDecimal id = new BigDecimal(idUser);
        BigDecimal b1 = new BigDecimal("1");
        BigDecimal b2 = new BigDecimal("2");
        BigDecimal b3 = new BigDecimal("3");
        BigDecimal b4 = new BigDecimal("4");
        List<Paquete> Registro = new ArrayList<Paquete>();
        Infobandeja inBandeja = ejbInfobandeja.consultarBandejaXNombre(ban);
        Usuario usuario = ejbUsuario.find(id);
        BigDecimal banj = inBandeja.getIdiba();
        Iterator<Bandeja> iterator = inBandeja.getBandejaCollection().iterator();
        while (iterator.hasNext()) {
            Bandeja aux = iterator.next();
            if (banj.equals(b1) || banj.equals(b2)) {
               
              
                if (aux.getIdpaq().getIdsed().getIdsed().equals(new BigDecimal(idsed)) && aux.getIdpaq().getOrigenpaq().getIdusu().getIdusu().equals(usuario.getIdusu()) && aux.getIdusu().getIdusu().equals(usuario.getIdusu())) {
                    Registro.add(aux.getIdpaq());
                } else {
                    iterator.remove();
                }
            }
            if (banj.equals(b3) || banj.equals(b4)) {
                if ( aux.getIdpaq().getDestinopaq().getIdatr().getIdsed().getIdsed().equals(new BigDecimal(idsed)) && aux.getIdpaq().getDestinopaq().getIdusu().getIdusu().equals(usuario.getIdusu()) && aux.getIdusu().getIdusu().equals(usuario.getIdusu())) {
                    Registro.add(aux.getIdpaq());
                } else {
                    iterator.remove();
                }
            }
        }
        Collections.sort(Registro, SENIORITY_ORDER);
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
     * @return
     */
    @WebMethod(operationName = "insertarUsuario")
    public int insertarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {

        int Resultado;
        try {
            registroUsuario.setApellidousu(registroUsuario.getApellidousu().trim());
            registroUsuario.setCargousu(registroUsuario.getCargousu().trim());
            registroUsuario.setCorreousu(registroUsuario.getCorreousu().trim());
            registroUsuario.setDireccionusu(registroUsuario.getDireccionusu().trim());
            registroUsuario.setNombreusu(registroUsuario.getNombreusu().trim());
            registroUsuario.setTelefono2usu(registroUsuario.getTelefono2usu().trim());
            registroUsuario.setTelefonousu(registroUsuario.getTelefonousu().trim());
            registroUsuario.setUserusu(registroUsuario.getUserusu().trim());
            ejbUsuario.insertar(registroUsuario);
            Resultado = 1;
        } catch (Exception e) {
            
            log.error("Error en Servicio insertarUsuario");
            log.fatal("Error fatal en Servicio  insertarUsuario");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param registroP
     * @return
     */
    @WebMethod(operationName = "insertarProveedor")
    public int insertarProveedor(@WebParam(name = "registroProveedor") Proveedor registroP) {
        registroP.setNombrepro(registroP.getNombrepro().trim());
        registroP.setCodigopro(registroP.getCodigopro().trim());
        registroP.setTelefonopro(registroP.getTelefonopro().trim());
        int Resultado;
        try {
            ejbProveedor.create(registroP);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio insertarProveedor");
            log.fatal("Error fatal en Servicio  insertarProveedor");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "insertarProveedorSede")
    public int insertarProveedorSede(@WebParam(name = "registroSede") Sede registroSede) {
        int Resultado;
        try {
            Proveedorsede Nuevo = new Proveedorsede();
            String idProveedor = ejbProveedor.ultimoidProveedor();
            Proveedor regiPro = new Proveedor();
            regiPro = ejbProveedor.find(new BigDecimal(idProveedor));
            registroSede = ejbSede.find(registroSede.getIdsed());
            Nuevo.setIdpro(regiPro);
            Nuevo.setIdsed(registroSede);
            ejbProveedorSede.create(Nuevo);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio insertarProveedorSede");
            log.fatal("Error fatal en Servicio  insertarProveedorSede");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param registroArea
     * @param idsed
     * @return
     */
    @WebMethod(operationName = "insertarArea")
    public int insertarArea(@WebParam(name = "registroArea") Areatrabajo registroArea, @WebParam(name = "idsed") String idsed) {
        registroArea.setNombreatr(registroArea.getNombreatr().trim());
        Sede sed = ejbSede.find(new BigDecimal(idsed));
        registroArea.setIdsed(sed);
        int Resultado;
        try {
            ejbAreaTrabajo.create(registroArea);
            Resultado = 1;
        } catch (Exception e) {
            
            log.error("Error en Servicio insertarArea");
            log.fatal("Error fatal en Servicio  insertarArea");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método encargado de deshabilitar registros de usuario el status cambiara
     * a 0
     *
     * @param idUsuario objeto de la entidad usuario , debe tener el campo id
     * como mínimo
     */
    @WebMethod(operationName = "deshabilitarUsuario")
    public void deshabilitarUsuario(@WebParam(name = "idUsuario") String idUsuario) {

        ejbUsuario.deshabilitar(idUsuario);
    }

    /**
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
            log.error("Error en Servicio consultarUsuario");
            log.fatal("Error fatal en Servicio  consultarUsuario");
            Resultado = null;
        }
        return Resultado;
    }

    /**
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
            log.error("Error en Servicio consultarDocumento");
            log.fatal("Error fatal en Servicio  consultarDocumento");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param docum
     * @return
     */
    @WebMethod(operationName = "consultarDocumentoXNombre")
    public Documento consultarDocumentoXNombre(@WebParam(name = "doc") String docum) {

        return ejbDocumento.consultarDocumentoXNombre(docum);
    }

    /**
     *
     * @param registroPaquete
     * @return
     */
    @WebMethod(operationName = "crearPaquete")
    public int crearPaquete(@WebParam(name = "registroPaquete") Paquete registroPaquete) {

        int Resultado;
        try {
            registroPaquete.setAsuntopaq(registroPaquete.getAsuntopaq().trim());
            registroPaquete.setTextopaq(registroPaquete.getTextopaq());
            registroPaquete.setFechapaq(new Date());
            registroPaquete.setFechapaq(new Date());
            ejbPaquete.crearPaquete(registroPaquete);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio crearPaquete");
            log.fatal("Error fatal en Servicio  crearPaquete");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param IdsedeO
     * @param sedeD
     * @param tipo
     * @return
     * @throws ParseException
     */
    @WebMethod(operationName = "insertarValija")
    public String insertarValija(@WebParam(name = "idusu") String idusu, @WebParam(name = "sorigen") String IdsedeO, @WebParam(name = "sdestino") String sedeD, @WebParam(name = "tipoval") String tipo) throws ParseException {

        Usuario usu = ejbUsuario.consultarUsuario(idusu);
        Sede origen = ejbSede.consultarSedeXId(new BigDecimal(IdsedeO));
        Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
        BigDecimal Resultado;
        BigDecimal id = new BigDecimal(IdsedeO);
        Valija registroValija = new Valija();
        Date hoy = new Date();
        Sede destino = ejbSede.ConsultarSedeXNombre(sedeD);

        registroValija.setDestinoval(destino);
        registroValija.setOrigenval(id);
        registroValija.setIduse(use);
        registroValija.setFechaval(hoy);
        registroValija.setTipoval(tipo);
        registroValija.setStatusval("0");


        try {
            Resultado = ejbValija.crearValija(registroValija);
            ejbBitacora.insertarBitacora(destino, usu, "INSERCION", "Creacion de Valija");
        } catch (Exception e) {
            log.error("Error en Servicio insertarValija");
            log.fatal("Error fatal en Servicio  insertarValija");
            
            Resultado = new BigDecimal(0);
        }
        return Resultado.toString();
    }

    /**
     *
     * @param registroValija
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ConsultarPaquetesXValija")
    public List<Paquete> ConsultarPaquetesXValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "sede") String sede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.ConsultarPaquetesXValija(registroValija, sede);
        } catch (Exception e) {
            
            log.error("Error en Servicio ConsultarPaquetesXValija");
            log.fatal("Error fatal en Servicio  ConsultarPaquetesXValija");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param tiempo
     * @param idniv
     * @return
     */
    @WebMethod(operationName = "actualizarTiempoNivel")
    public int actualizarTiempoNivel(@WebParam(name = "tiempo") String tiempo, @WebParam(name = "idniv") String idniv) {

        int Resultado = 0;
        try {
            ejbNivel.editarTiempoNivel(tiempo, idniv);
            Resultado = 1;
        } catch (Exception e) {
            
            log.error("Error en Servicio actualizarTiempoNivel");
            log.fatal("Error fatal en Servicio  actualizarTiempoNivel");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param registroValija
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ConsultarValija")
    public Valija ConsultarValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "sede") String sede) {

        Valija valija = null;
        BigDecimal val = new BigDecimal(registroValija);
        try {
            valija = ejbValija.consultarValija(val, sede);

            if (valija.getCodproveedorval() == null) {
                valija = null;
            }
        } catch (Exception e) {
            
            log.error("Error en Servicio ConsultarValija");
            log.fatal("Error fatal en Servicio  ConsultarValija");
            return null;
        }
        return valija;
    }

    /**
     *
     * @param sede
     * @param sedeDestino
     * @return
     */
    @WebMethod(operationName = "ConsultarPaquetesParaValija")
    public List<Paquete> ConsultarPaquetesParaValija(@WebParam(name = "sede") String sede, @WebParam(name = "sedeDestino") String sedeDestino) {

        List<Paquete> Resultado = new ArrayList<Paquete>();
        try {
            List<Paquete> Resul = ejbPaquete.ConsultarPaquetesParaValija(sede);
            Iterator<Paquete> lista = Resul.iterator();
            while (lista.hasNext()) {
                Paquete aux = lista.next();
                if (aux.getDestinopaq().getIdatr().getIdsed().getNombresed().equals(sedeDestino) && aux.getIdval() == null) {
                    Resultado.add(aux);
                } else {
                    lista.remove();
                }
            }
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarPaquetesParaValija");
            log.fatal("Error fatal en Servicio  ConsultarPaquetesParaValija");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ConsultarSedeParaValija")
    public List<String> ConsultarSedeParaValija(@WebParam(name = "sede") String sede) {

        List<String> Resultado = new ArrayList();
        List<String> Resultad = null;
        try {
            Resultad = ejbPaquete.ConsultarSedeParaValija(sede);
            Iterator<String> lista = Resultad.iterator();
            while (lista.hasNext()) {
                String aux = lista.next();
                if (!sede.equals(aux)) {
                    Resultado.add(aux);
                } else {
                    lista.remove();
                }
            }
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSedeParaValija");
            log.fatal("Error fatal en Servicio  ConsultarSedeParaValija");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idSede
     * @return
     */
    @WebMethod(operationName = "ConsultarSede")
    public Sede ConsultarSede(@WebParam(name = "idSede") String idSede) {

        Sede Resultado = null;
        BigDecimal id = new BigDecimal(idSede);
        try {
            Resultado = ejbSede.find(id);
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSede");
            log.fatal("Error fatal en Servicio  ConsultarSede");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarAreasXSede")
    public List<Areatrabajo> consultarAreasXSede(@WebParam(name = "sede") String sede) {

        List<Areatrabajo> Resultado = null;
        try {
            Resultado = ejbAreaTrabajo.consultarAreasXSede(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarAreasXSede");
            log.fatal("Error fatal en Servicio  consultarAreasXSede");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param pri
     * @return
     */
    @WebMethod(operationName = "consultarNivel")
    public List<Nivel> consultarNivel(@WebParam(name = "prioridad") String pri) {

        List<Nivel> Resultado = null;
        try {
            Resultado = ejbNivel.consultarNivel(pri);
        } catch (Exception e) {
            log.error("Error en Servicio consultarNivel");
            log.fatal("Error fatal en Servicio  consultarNivel");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param area
     * @return
     */
    @WebMethod(operationName = "estadoArea")
    public int estadoArea(@WebParam(name = "area") String area) {

        int Resultado = 0;
        try {
            ejbAreaTrabajo.estadoArea(area);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio estadoArea");
            log.fatal("Error fatal en Servicio  estadoArea");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarAreasXSedeXNombre")
    public List<Areatrabajo> consultarAreasXSedeXNombre(@WebParam(name = "sede") String sede) {

        List<Areatrabajo> Resultado = null;
        try {
            Resultado = ejbAreaTrabajo.consultarAreasXSedeXNombre(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarAreasXSedeXNombre");
            log.fatal("Error fatal en Servicio  consultarAreasXSedeXNombre");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sed
     * @return
     */
    @WebMethod(operationName = "consultarUsuariosXSede")
    public List<Usuario> consultarUsuariosXSede(@WebParam(name = "sede") String sed) {

        List<Usuario> Resultado = null;
        try {
            Resultado = ejbUsuario.consultarUsuariosXSede(sed);
        } catch (Exception e) {
            log.error("Error en Servicio consultarUsuariosXSede");
            log.fatal("Error fatal en Servicio  consultarUsuariosXSede");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ConsultarSedeXNombre")
    public Sede ConsultarSedeXNombre(@WebParam(name = "sede") String sede) {

        Sede Resultado = null;
        try {
            Resultado = ejbSede.ConsultarSedeXNombre(sede);
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSedeXNombre");
            log.fatal("Error fatal en Servicio  ConsultarSedeXNombre");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarSedeExistente")
    public int consultarSedeExistente(@WebParam(name = "sede") String sede) {

        int Resultado = 0;
        try {
            Resultado = ejbSede.ConsultarSedeExistente(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarSedeExistente");
            log.fatal("Error fatal en Servicio  consultarSedeExistente");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param area
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarAreaExistente")
    public int consultarAreaExistente(@WebParam(name = "area") String area, @WebParam(name = "sede") String sede) {

        int Resultado = 0;
        try {
            Resultado = ejbAreaTrabajo.consultarAreaExistente(area, sede);
            if (Resultado == 0) {

                Areatrabajo Areas = new Areatrabajo();
                Areas.setNombreatr(area);
                Sede sed = ejbSede.find(new BigDecimal(sede));
                Areas.setIdsed(sed);
                Areas.setBorradoatr("0");
                ejbAreaTrabajo.create(Areas);
                Resultado = 2;
            } else {
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio consultarAreaExistente");
            log.fatal("Error fatal en Servicio  consultarAreaExistente");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesporArea")
    public List<Paquete> consultarPaquetesporArea(@WebParam(name = "idusu") String idusu, @WebParam(name = "idsed") String sede) {

        List<Paquete> Resultado;
        try {
            Usuario usu = ejbUsuario.consultarUsuario(idusu);
            Sede sed = ejbSede.consultarSedeXId(new BigDecimal(sede));
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, sed);
            Resultado = ejbPaquete.BuscarArea(use.getIdatr().getIdatr(), sed.getIdsed());

        } catch (Exception e) {
            log.error("Error en Servicio consultarPaquetesporArea");
            log.fatal("Error fatal en Servicio  consultarPaquetesporArea");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @param idval
     * @return
     */
    @WebMethod(operationName = "ActualizacionLocalizacionyValijaDelPaquete")
    public List<Paquete> ActualizacionLocalizacionyValijaDelPaquete(@WebParam(name = "idpaq") String idpaq, @WebParam(name = "idval") String idval) {

        List<Paquete> Resultado = null;
        try {
            ejbPaquete.ActualizacionLocalizacionyValijaDelPaquete(idpaq, idval);
        } catch (Exception e) {
            log.error("Error en Servicio ActualizacionLocalizacionyValijaDelPaquete");
            log.fatal("Error fatal en Servicio  ActualizacionLocalizacionyValijaDelPaquete");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "insertarBuzon")
    public int insertarBuzon(@WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {

        try {
            Usuario usu = ejbUsuario.consultarUsuario(idusu);
            Sede sed = ejbSede.consultarSedeXId(new BigDecimal(sede));
            Buzon buzoni = new Buzon();
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, sed);
            String nombre = usu.getNombreusu() + "." + usu.getApellidousu();
            buzoni.setIdatr(use.getIdatr());
            buzoni.setIdusu(usu);
            buzoni.setNombrebuz(nombre);
            buzoni.setDireccionbuz(usu.getDireccionusu());
            buzoni.setTipobuz("0");
            ejbBuzon.insertarBuzon(buzoni);
        } catch (Exception e) {
            log.error("Error en Servicio insertarBuzon");
            log.fatal("Error fatal en Servicio  insertarBuzon");
           
            return 0;
        }
        return 1;
    }

    /**
     *
     * @param buz
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "insertarBuzonExterno")
    public int insertarBuzonExterno(@WebParam(name = "buzon") Buzon buz, @WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {
        buz.setNombrebuz(buz.getNombrebuz().trim());
        buz.setCorreobuz(buz.getCorreobuz().trim());
        buz.setDireccionbuz(buz.getDireccionbuz().trim());
        buz.setTelefonobuz(buz.getTelefonobuz().trim());
        try {
            Usuario usu = ejbUsuario.consultarUsuario(idusu);
            buz.setIdusu(usu);
            ejbBuzon.insertarBuzon(buz);
        } catch (Exception e) {
            log.error("Error en Servicio insertarBuzonExterno");
            log.fatal("Error fatal en Servicio  insertarBuzonExterno");
            return 0;
        }
        return 1;
    }

    /**
     *
     * @return
     */
    @WebMethod(operationName = "consultarRoles")
    public List<Rol> consultarRoles() {

        List<Rol> Resultado = new ArrayList<Rol>();
        try {
            Resultado = ejbRol.findAll();
        } catch (Exception e) {
            log.error("Error en Servicio consultarRoles");
            log.fatal("Error fatal en Servicio  consultarRoles");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param idsede
     * @return
     */
    @WebMethod(operationName = "consultarStatusPaquete")
    public List<Paquete> consultarStatusPaquete(@WebParam(name = "idusu") String idusu, @WebParam(name = "idsede") String idsede) {

        List<Paquete> Resultado = new ArrayList<Paquete>();
        Usuario usu = ejbUsuario.consultarUsuario(idusu);
        Sede origen = ejbSede.consultarSedeXId(new BigDecimal(idsede));
        Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
        try {
            Resultado = ejbPaquete.consultarStatusPaquete(use);
        } catch (Exception e) {
            log.error("Error en Servicio consultarStatusPaquete");
            log.fatal("Error fatal en Servicio  consultarStatusPaquete");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param idsede
     * @return
     */
    @WebMethod(operationName = "consultarStatusValija")
    public List<Valija> consultarStatusValija(@WebParam(name = "idusu") String idusu, @WebParam(name = "idsede") String idsede) {

        List<Valija> Resultado = new ArrayList<Valija>();
        Usuario usu = ejbUsuario.consultarUsuario(idusu);
        Sede origen = ejbSede.consultarSedeXId(new BigDecimal(idsede));

        try {
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
            Resultado = ejbValija.consultarStatusValija(use);
        } catch (Exception e) {
            log.error("Error en Servicio consultarStatusValija");
            log.fatal("Error fatal en Servicio  consultarStatusValija");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarSedeRol")
    public Usuariosede consultarSedeRol(@WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {

        Usuariosede Resultado = new Usuariosede();
        Sede sed = ejbSede.ConsultarSedeXNombre(sede);
        try {
            Resultado = ejbUsuariosede.sedeRolXId(idusu, sed);
        } catch (Exception e) {
            log.error("Error en Servicio consultarSedeRol");
            log.fatal("Error fatal en Servicio  consultarSedeRol");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @return
     */
    @WebMethod(operationName = "ConsultarSedes")
    public List<Sede> ConsultarSedes() {

        List<Sede> Resultado = null;
        try {
            Resultado = ejbSede.findAll();
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSedes");
            log.fatal("Error fatal en Servicio  ConsultarSedes");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "ConsultarSedesBuzon")
    public List<Sede> ConsultarSedesBuzon(@WebParam(name = "sede") String sede) {

        List<Sede> Result = new ArrayList<Sede>();
        try {
            List<Sede> Resultado = ejbSede.findAll();
            Iterator<Sede> lista = Resultado.iterator();
            while (lista.hasNext()) {
                Sede aux = lista.next();
                if (!aux.getNombresed().equals(sede)) {
                    Result.add(aux);
                }
            }
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSedesBuzon");
            log.fatal("Error fatal en Servicio  ConsultarSedesBuzon");
            return null;
        }
        return Result;
    }

    /**
     *
     * @param idusu
     * @return
     */
    @WebMethod(operationName = "ConsultarSedesParaAsignar")
    public List<Sede> ConsultarSedesParaAsignar(@WebParam(name = "user") String idusu) {

        List<Sede> Resultado = new ArrayList<Sede>();
        Usuario usu = ejbUsuario.consultarUsuarioXUser(idusu);
        List<Sede> sed = consultarSedeDeUsuario(usu);
        try {
            List<Sede> Resultad = ejbSede.findAll();
            Iterator<Sede> lista = sed.iterator();
            Iterator<Sede> lista2 = Resultad.iterator();
            while (lista.hasNext()) {
                Sede aux = lista.next();
                while (lista2.hasNext()) {
                    Sede aux2 = lista2.next();
                    if (aux.getIdsed() == aux2.getIdsed()) {
                        lista2.remove();
                    }
                }
            }
            Resultado.addAll((Collection<? extends Sede>) lista2);
        } catch (Exception e) {
            log.error("Error en Servicio ConsultarSedesParaAsignar");
            log.fatal("Error fatal en Servicio  ConsultarSedesParaAsignar");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "actualizacionLocalizacionRecibidoPaquete")
    public int actualizacionLocalizacionRecibidoPaquete(@WebParam(name = "idpaq") String idpaq) {

        int Resultado = 0;
        try {
            ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio actualizacionLocalizacionRecibidoPaquete");
            log.fatal("Error fatal en Servicio  actualizacionLocalizacionRecibidoPaquete");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @param mensaje
     * @return
     */
    @WebMethod(operationName = "actualizacionPaqueteextraviado")
    public int actualizacionPaqueteextraviado(@WebParam(name = "idpaq") String idpaq, @WebParam(name = "mensaje") String mensaje) {

        int Resultado = 0;
        try {
            ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
            Mensaje msj = new Mensaje();
            msj.setNombremen("Extravio");
            msj.setDescripcionmen(mensaje);
            Paquete p = ejbPaquete.ConsultarPaqueteXId(new BigDecimal(idpaq));
            msj.setIdpaq(p);
            ejbMensaje.insertarMensaje(msj);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio actualizacionPaqueteextraviado");
            log.fatal("Error fatal en Servicio  actualizacionPaqueteextraviado");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @param Localizacion
     * @return
     */
    @WebMethod(operationName = "actualizacionLocalizacionRecibidoValija")
    public int actualizacionLocalizacionRecibidoValija(@WebParam(name = "idpaq") String idpaq, @WebParam(name = "Localizacion") String Localizacion) {

        int Resultado = 0;
        try {
            BigDecimal idu = new BigDecimal(idpaq);
            ejbPaquete.editarLocalizacionPaquete(idu, Localizacion);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio actualizacionLocalizacionRecibidoValija");
            log.fatal("Error fatal en Servicio  actualizacionLocalizacionRecibidoValija");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "actualizarBandeja")
    public int actualizarBandeja(@WebParam(name = "idpaq") String idpaq) {

        int Resultado;
        try {
            BigDecimal idu = new BigDecimal(idpaq);
            Paquete paq = ejbPaquete.find(idu);
            Usuario usu = ejbUsuario.consultarUsuario(paq.getOrigenpaq().getIdusu().getIdusu().toString());
            Usuario usud = ejbUsuario.consultarUsuario(paq.getDestinopaq().getIdusu().getIdusu().toString());
            if ("0".equals(paq.getRespaq())) {
                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);
                String idP = ejbSeguimiento.ultimoSegXPaq(idpaq);
                ejbSeguimiento.editarSeguimiento(new BigDecimal(idP), "1");
            }
            if ("1".equals(paq.getRespaq())) {
                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);
                String idP = ejbSeguimiento.ultimoSegXPaq(idpaq);
                ejbSeguimiento.editarSeguimiento(new BigDecimal(idP), "1");
            }
            if ("2".equals(paq.getRespaq())) {
                //ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido("entregado con respuesta", paq.getIdpaqres().toString());
                ejbPaquete.ActualizacionLocalizacionyDelPaqueteRecibido(idpaq);
                ejbBandeja.actualizacionBandeja(usu, usud, paq);
                String idP = ejbSeguimiento.ultimoSegXPaq(idpaq);
                ejbSeguimiento.editarSeguimiento(new BigDecimal(idP), "1");
            }
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio actualizarBandeja");
            log.fatal("Error fatal en Servicio  actualizarBandeja");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "localizacionPaquete")
    public int localizacionPaquete(@WebParam(name = "idpaq") String idpaq) {

        int Resultado = 0;
        try {
            String idP = ejbSeguimiento.ultimoSegXPaq(idpaq);
            Seguimiento seg = ejbSeguimiento.find(new BigDecimal(idP));
            if (seg.getTiposeg().equals("1")) {
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio localizacionPaquete");
            log.fatal("Error fatal en Servicio  localizacionPaquete");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idval
     * @param status
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "entregarValija")
    public int entregarValija(@WebParam(name = "idval") String idval, @WebParam(name = "status") String status, @WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {

        int Resultado = 0;
        BigDecimal id = new BigDecimal(idval);
        Sede destino = ejbSede.ConsultarSedeXNombre(sede);
        Usuario usu = ejbUsuario.consultarUsuario(idusu);
        Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, destino);
        try {
            ejbValija.entregarValija(id, status, use);
            ejbBitacora.insertarBitacora(destino, usu, "DESGLOSAR", "Desglozar Valija");
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio entregarValija");
            log.fatal("Error fatal en Servicio  entregarValija");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param rol
     * @param sede
     * @return
     */
    @WebMethod(operationName = "editarRol")
    public int editarRol(@WebParam(name = "idusu") String idusu, @WebParam(name = "rol") String rol, @WebParam(name = "sede") String sede) {

        int Resultado = 0;
        Sede sed = ejbSede.ConsultarSedeXNombre(sede);
        BigDecimal idu = new BigDecimal(idusu);
        BigDecimal ro = new BigDecimal(rol);
        try {
            ejbUsuariosede.editarRol(idu, ro, sed.getIdsed());
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio editarRol");
            log.fatal("Error fatal en Servicio  editarRol");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param tipo
     * @return
     */
    @WebMethod(operationName = "editarTipoUsuario")
    public int editarTipoUsuario(@WebParam(name = "idusu") String idusu, @WebParam(name = "tipo") String tipo) {

        int Resultado = 0;
        Usuario usu = ejbUsuario.consultarUsuarioXUser(idusu);
        try {
            ejbPaquete.editarTipo(usu.getIdusu(), tipo);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio editarTipoUsuario");
            log.fatal("Error fatal en Servicio  editarTipoUsuario");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "asignarSede")
    public int asignarSede(@WebParam(name = "idusu") String idusu, @WebParam(name = "sede") String sede) {

        int Resultado = 0;
        BigDecimal idr = new BigDecimal("6");
        Usuario usu = ejbUsuario.consultarUsuarioXUser(idusu);
        Sede sed = ejbSede.ConsultarSedeXNombre(sede);
        Rol ro = ejbRol.find(idr);
        try {
            ejbUsuariosede.asignarSede(usu, ro, sed);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio asignarSede");
            log.fatal("Error fatal en Servicio  asignarSede");
            return 0;
        }
        return Resultado;
    }

    /**
     * Método que inserta los datos en bitacora
     *
     * @param idSede
     * @param idUsu
     * @param accion
     * @param observacion
     * @return entero 1 si se inserto correctamente y 0 si no inserto
     */
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
            log.error("Error en Servicio insertarBitacora");
            log.fatal("Error fatal en Servicio  insertarBitacora");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método que lista la información de bitacora
     *
     * @return lista de tipo bitacora con toda la información
     */
    @WebMethod(operationName = "listarBitacora")
    public List<Bitacora> listarBitacora() {

        List<Bitacora> Resultado = null;
        try {
            Resultado = ejbBitacora.listarBitacora();
        } catch (Exception e) {
            log.error("Error en Servicio listarBitacora");
            log.fatal("Error fatal en Servicio  listarBitacora");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método que vacia toda la información de bitacora
     *
     * @return entero 1 si se vacio correctamente y 0 si no se vacio
     */
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
            log.error("Error en Servicio vaciarBitacora");
            log.fatal("Error fatal en Servicio  vaciarBitacora");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método que confirma la valija en el Proveedor que se selecciona
     *
     * @param idValija
     * @param proveedor
     * @param codProveedor
     * @return entero 1 si se confirma correctamente y 0 si no se confirma
     */
    @WebMethod(operationName = "confirmarValija")
    public int confirmarValija(@WebParam(name = "idValija") String idValija, @WebParam(name = "proveedor") String proveedor, @WebParam(name = "codProveedor") String codProveedor) {
        codProveedor = codProveedor.trim();
        int Resultado = 0;
        List<Paquete> lista;
        BigDecimal idPaquete;
        BigDecimal idVal;
        BigDecimal idPro;
        String localizacion;
        Valija idValPaq;
        try {
            idVal = new BigDecimal(idValija);
            idValPaq = new Valija();
            idValPaq.setIdval(idVal);
            Valija consulta = ejbValija.find(idVal);
            idPro = new BigDecimal(proveedor);
            if (consulta != null) {
                ejbValija.editarProveedorValija(idVal, codProveedor, idPro);
                lista = ejbPaquete.listarPaquetesXValija(idValPaq);
                localizacion = ejbProveedor.consultarProveedorXId(idPro).getNombrepro();
                for (int i = 0; i < lista.size(); i++) {
                    idPaquete = lista.get(i).getIdpaq();
                    ejbPaquete.editarLocalizacionPaquete(idPaquete, localizacion);
                }
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio confirmarValija");
            log.fatal("Error fatal en Servicio  confirmarValija");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Metodo que reporta el paquete excedente, pertenezca o no a una valija
     *
     * @param registroPaquete
     * @param registroUsuario
     * @param registroSede
     * @param datosPaquete
     * @return entero 1 si se reporta correctamente, 0 si no se reporta
     */
    @WebMethod(operationName = "reportarPaqueteExcedente")
    public int reportarPaqueteExcedente(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "registroUsuario") String registroUsuario, @WebParam(name = "registroSede") String registroSede, @WebParam(name = "datosPaquete") String datosPaquete) {

        int Resultado = 0;
        BigDecimal idVal;
        BigDecimal idPaq;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;
        Paquete registroPaq;
        Mensaje nuevoMensaje;
        Valija idValija;
        try {
            datosPaquete = datosPaquete.trim();
            registroPaq = new Paquete();
            idPaq = new BigDecimal(registroPaquete);
            registroPaq = ejbPaquete.consultarPaqueteXIdOCodigoBarras(registroPaquete);
            Usuario usu = ejbUsuario.consultarUsuario(registroUsuario);
            Sede origen = ejbSede.consultarSedeXId(new BigDecimal(registroSede));
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
            if (registroPaq.getStatuspaq().equals("1")) {
                Resultado = 2;
            } else {
                //Seguimiento
                nuevoSeg = new Seguimiento();
                nuevoSeg.setIdpaq(registroPaq);
                nuevoSeg.setIduse(use);
                nuevoSeg.setFechaseg(new Date());
                nuevoSeg.setStatusseg("2");
                nuevoSeg.setTiposeg("1");
                nuevoSeg.setNivelseg("Valija");
                ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                //Mensaje
                nuevoMensaje = new Mensaje();
                nuevoMensaje.setNombremen("Paquete Excedente");
                nuevoMensaje.setDescripcionmen(datosPaquete);
                nuevoMensaje.setIdpaq(registroPaq);
                ejbMensaje.insertarMensaje(nuevoMensaje);
                //Cambio de Status de Paquete a Reenviado (3)
                ejbPaquete.editarStatusPaquete(idPaq, "3");
                if (registroPaq.getIdval() != null) {
                    idVal = registroPaq.getIdval().getIdval();
                    idValija = new Valija();
                    idValija.setIdval(idVal);
                    //Incidente
                    nuevoIncidente = new Incidente();
                    nuevoIncidente.setNombreinc("Paquete Excedente");
                    nuevoIncidente.setDescripcioninc("Reporte de paquete excedente");
                    nuevoIncidente.setIdval(idValija);
                    ejbIncidente.insertarIncidente(nuevoIncidente);
                    //Cambio de Status de Valija con Paquete Excedente (3)
                    ejbValija.editarStatusValija(idVal, "3");
                }
                Resultado = 1;
            }
        } catch (Exception e) {
             log.error("Error en Servicio reportarPaqueteExcedente");
            log.fatal("Error fatal en Servicio  reportarPaqueteExcedente");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Metodo que reporta el paquete por extravió, pertenezca o no a una valija
     *
     * @param registroPaquete
     * @param registroUsuario
     * @param registroSede
     * @param datosPaquete
     * @return entero 1 si se reporta correctamente, 0 si no se reporta
     */
    @WebMethod(operationName = "reportarPaqueteExtravio")
    public int reportarPaqueteExtravio(@WebParam(name = "registroPaquete") String registroPaquete, @WebParam(name = "registroUsuario") String registroUsuario, @WebParam(name = "registroSede") String registroSede, @WebParam(name = "datosPaquete") String datosPaquete) {

        int Resultado = 0;
        BigDecimal idPaq;
        Paquete registroPaq;
        Mensaje nuevoMensaje;
        try {
            datosPaquete = datosPaquete.trim();
            registroPaq = ejbPaquete.consultarPaqueteXIdOCodigoBarras(registroPaquete);
            if (registroPaq.getStatuspaq().equals("4")) {
                Resultado = 2;
            } else {
                Usuario usu = ejbUsuario.consultarUsuario(registroUsuario);
                Sede origen = ejbSede.consultarSedeXId(new BigDecimal(registroSede));
                Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
                String idP = ejbSeguimiento.ultimoSegXPaq(registroPaquete);
                //Edito Seguimiento
                ejbSeguimiento.editarSeguimiento(new BigDecimal(idP), "4");
                //Mensaje
                nuevoMensaje = new Mensaje();
                nuevoMensaje.setNombremen("Paquete Extraviado");
                nuevoMensaje.setDescripcionmen(datosPaquete);
                nuevoMensaje.setIdpaq(registroPaq);
                ejbMensaje.insertarMensaje(nuevoMensaje);
                //Cambio de Status de Paquete a extraviado (4)
                ejbPaquete.ActualizacionPaqueteExtraviado(registroPaquete);
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio reportarPaqueteExtravio");
            log.fatal("Error fatal en Servicio  reportarPaqueteExtravio");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método que reporta valija con destino erroneo
     *
     * @param registroValija
     * @param registroUsuario
     * @param registroSede
     * @param datosValija
     * @return entero 1 si se reporta correctamente, 0 si no se reporta
     */
    @WebMethod(operationName = "reportarValija")
    public int reportarValija(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "registroUsuario") String registroUsuario, @WebParam(name = "registroSede") String registroSede, @WebParam(name = "datosValija") String datosValija) {

        int Resultado = 0;
        List<Paquete> lista;
        BigDecimal idPaq;
        Seguimiento nuevoSeg;
        Incidente nuevoIncidente;
        Paquete registroPaquete;
        Valija idValija;
        try {
            datosValija = datosValija.trim();
            Usuario usu = ejbUsuario.consultarUsuario(registroUsuario);
            Sede origen = ejbSede.consultarSedeXId(new BigDecimal(registroSede));
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
            Valija val = ejbValija.consultarValijaXIdOCodigoBarra(registroValija);
            if (val.getStatusval().equals("1")) {
                Resultado = 2;
            } else {
                //Incidente
                nuevoIncidente = new Incidente();
                nuevoIncidente.setNombreinc("Valija Incorrecta");
                nuevoIncidente.setDescripcioninc(datosValija);
                nuevoIncidente.setIdval(val);
                ejbIncidente.insertarIncidente(nuevoIncidente);
                lista = ejbPaquete.listarPaquetesXValija(val);
                for (int i = 0; i < lista.size(); i++) {
                    idPaq = lista.get(i).getIdpaq();
                    registroPaquete = new Paquete();
                    registroPaquete.setIdpaq(idPaq);
                    //Seguimiento
                    nuevoSeg = new Seguimiento();
                    nuevoSeg.setIdpaq(registroPaquete);
                    nuevoSeg.setIduse(use);
                    nuevoSeg.setFechaseg(new Date());
                    nuevoSeg.setStatusseg("2");
                    nuevoSeg.setTiposeg("1");
                    nuevoSeg.setNivelseg("Valija");
                    ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                    //Cambio de Status de Paquete a Reenviado (3)
                    ejbPaquete.editarStatusPaquete(idPaq, "3");
                }
                //Cambio de Status de Valija a Reenviada (4)
                ejbValija.editarStatusValija(val.getIdval(), "4");
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio reportarValija");
            log.fatal("Error fatal en Servicio  reportarValija");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método que reporta valija con destino erroneo
     *
     * @param registroValija
     * @param registroUsuario
     * @param registroSede
     * @param datosValija
     * @return entero 1 si se reporta correctamente, 0 si no se reporta
     */
    @WebMethod(operationName = "reportarValijaExtravio")
    public int reportarValijaExtravio(@WebParam(name = "registroValija") String registroValija, @WebParam(name = "registroUsuario") String registroUsuario, @WebParam(name = "registroSede") String registroSede, @WebParam(name = "datosValija") String datosValija) {

        int Resultado = 0;
        List<Paquete> lista;
        BigDecimal idPaq;
        Incidente nuevoIncidente;

        try {
            datosValija = datosValija.trim();
            Usuario usu = ejbUsuario.consultarUsuario(registroUsuario);
            Sede origen = ejbSede.consultarSedeXId(new BigDecimal(registroSede));
            Usuariosede use = ejbUsuariosede.ConsultarXUsuarioYSede(usu, origen);
            Valija val = ejbValija.consultarValijaXIdOCodigoBarra(registroValija);
            if (val.getStatusval().equals("5")) {
                Resultado = 2;
            } else {
                //Incidente
                nuevoIncidente = new Incidente();
                nuevoIncidente.setNombreinc("Valija extraviada");
                nuevoIncidente.setDescripcioninc(datosValija);
                nuevoIncidente.setIdval(val);
                ejbIncidente.insertarIncidente(nuevoIncidente);
                lista = ejbPaquete.listarPaquetesXValija(val);
                for (int i = 0; i < lista.size(); i++) {
                    idPaq = lista.get(i).getIdpaq();
                    //Cambio de Status de Paquete a extraviado (4)
                    ejbPaquete.ActualizacionPaqueteExtraviado(idPaq.toString());
                }
                //Cambio de Status de Valija a extraviada (5)
                ejbValija.editarStatusValija(val.getIdval(), "5");
                Resultado = 1;
            }
        } catch (Exception e) {
            log.error("Error en Servicio reportarValijaExtravio");
            log.fatal("Error fatal en Servicio  reportarValijaExtravio");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * Método que lista las valijas dependiendo del rango de fecha y de la sede
     *
     * @param fechaInicio
     * @param idsede
     * @param fechaFinal
     * @param consulta
     * @return lista tipo valija con toda la información
     */
    @WebMethod(operationName = "consultarEstadisticasValijas")
    public List<Valija> consultarEstadisticasValijas(@WebParam(name = "fechaInicio") String fechaInicio, @WebParam(name = "fechaFinal") String fechaFinal, @WebParam(name = "consulta") String consulta, @WebParam(name = "idsede") String idsede) {

        List<Valija> Resultado = new ArrayList<Valija>();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        System.out.println(fecha);
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaini = formatoDeFecha.parse(fechaInicio);
            Date fechafin = formatoDeFecha.parse(fechaFinal);
            Resultado = ejbValija.estadisticasValija(fechaini, fechafin, consulta, idsede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarEstadisticasValijas");
            log.fatal("Error fatal en Servicio  consultarEstadisticasValijas");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método que lista los paquetes dependiendo del rango de fecha y de la sede
     *
     * @param fechaInicio
     * @param idsede
     * @param fechaFinal
     * @param consulta
     * @return lista tipo paquete con toda la información
     */
    @WebMethod(operationName = "consultarEstadisticasPaquetes")
    public List<Paquete> consultarEstadisticasPaquetes(@WebParam(name = "fechaInicio") String fechaInicio, @WebParam(name = "fechaFinal") String fechaFinal, @WebParam(name = "consulta") String consulta, @WebParam(name = "idsede") String idsede) {

        List<Paquete> Resultado = new ArrayList<Paquete>();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        System.out.println(fecha);
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaini = formatoDeFecha.parse(fechaInicio);
            Date fechafin = formatoDeFecha.parse(fechaFinal);
            Resultado = ejbPaquete.estadisticasPaquete(fechaini, fechafin, consulta, idsede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarEstadisticasPaquetes");
            log.fatal("Error fatal en Servicio  consultarEstadisticasPaquetes");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método que consulta los datos de la sede por el id
     *
     * @param idSede
     * @return objeto tipo sede con toda la información
     */
    @WebMethod(operationName = "consultarSedeXId")
    public Sede consultarSedeXId(@WebParam(name = "idSede") String idSede) {

        Sede Resultado = null;
        try {
            BigDecimal idSed = new BigDecimal(idSede);
            Resultado = ejbSede.consultarSedeXId(idSed);
        } catch (Exception e) {
            log.error("Error en Servicio consultarSedeXId");
            log.fatal("Error fatal en Servicio  consultarSedeXId");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método para obtener el último paquete guardado
     *
     * @param idUsuario
     * @return objeto tipo paquete con toda la información
     */
    @WebMethod(operationName = "ultimoPaqueteXOrigen")
    public Paquete ultimoPaqueteXOrigen(@WebParam(name = "idUsuario") String idUsuario) {

        String idPaquete;
        BigDecimal idPaq;
        Usuario idUsua;
        Paquete Resultado = null;
        try {
            idUsua = ejbUsuario.consultarUsuario(idUsuario);
            idPaquete = ejbPaquete.ultimoPaqueteXOrigen(idUsua);
            if (idPaquete != null) {
                idPaq = new BigDecimal(idPaquete);
                Resultado = ejbPaquete.consultarPaquete(idPaq);
            }
        } catch (Exception e) {
            log.error("Error en Servicio ultimoPaqueteXOrigen");
            log.fatal("Error fatal en Servicio  ultimoPaqueteXOrigen");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método que consulta datos del paquete por el id
     *
     * @param idPaquete
     * @return objeto tipo paquete con toda la información
     */
    @WebMethod(operationName = "consultarPaqueteXId")
    public Paquete consultarPaqueteXId(@WebParam(name = "idPaquete") String idPaquete) {

        Paquete Resultado = null;
        BigDecimal idPaq = new BigDecimal(idPaquete);
        try {
            Resultado = ejbPaquete.consultarPaquete(idPaq);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaqueteXId");
            log.fatal("Error fatal en Servicio  consultarPaqueteXId");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método para obtener la última valija guardada
     *
     * @param idUsuario
     * @return objeto tipo valija con toda la información
     */
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
             log.error("Error en Servicio ultimaValijaXUsuario");
            log.fatal("Error fatal en Servicio  ultimaValijaXUsuario");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Método que consulta datos del adjunto por el id
     *
     * @param idPaquete
     * @return objeto tipo adjunto con toda la información
     */
    @WebMethod(operationName = "consultarAdjuntoXPaquete")
    public Adjunto consultarAdjuntoXPaquete(@WebParam(name = "idPaquete") String idPaquete) {

        Adjunto Resultado = null;
        Paquete idPaq;
        try {
            idPaq = new Paquete();
            idPaq.setIdpaq(new BigDecimal(idPaquete));
            Resultado = ejbAdjunto.consultarAdjuntoXPaquete(idPaq);
        } catch (Exception e) {
            log.error("Error en Servicio consultarAdjuntoXPaquete");
            log.fatal("Error fatal en Servicio  consultarAdjuntoXPaquete");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idPaquete
     * @return
     */
    @WebMethod(operationName = "consultarSeguimientoXPaquete")
    public List<Seguimiento> consultarSeguimientoXPaquete(@WebParam(name = "idPaquete") String idPaquete) {

        List<Seguimiento> Resultado = new ArrayList<Seguimiento>();
        List<Seguimiento> Result;
        Paquete idPaq;
        try {
            idPaq = new Paquete();
            idPaq.setIdpaq(new BigDecimal(idPaquete));
            Result = ejbSeguimiento.consultarSeguimientoXPaquete(idPaq);
            Iterator<Seguimiento> lista = Result.iterator();
            while (lista.hasNext()) {
                Seguimiento aux = lista.next();
                aux.setAlerta(null);
                Resultado.add(aux);
            }
        } catch (Exception e) {
            log.error("Error en Servicio consultarSeguimientoXPaquete");
            log.fatal("Error fatal en Servicio  consultarSeguimientoXPaquete");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param nombre
     * @param idsed
     * @return
     */
    @WebMethod(operationName = "consultarProveedorexistente")
    public int consultarProveedorexistente(@WebParam(name = "nombre") String nombre, @WebParam(name = "idsed") String idsed) {

        int Resultado = 0;
        try {
            Resultado = ejbProveedor.consultarProveedorexistente(nombre, idsed);
        } catch (Exception e) {
            log.error("Error en Servicio consultarProveedorexistente");
            log.fatal("Error fatal en Servicio  consultarProveedorexistente");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    @WebMethod(operationName = "consultarSedeCodigo")
    public int consultarSedeCodigo(@WebParam(name = "codigo") String codigo) {

        int Resultado = 0;
        try {
            Resultado = ejbSede.ConsultarSedeCodigo(codigo);
        } catch (Exception e) {
            log.error("Error en Servicio consultarSedeCodigo");
            log.fatal("Error fatal en Servicio  consultarSedeCodigo");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarProveedorXSede")
    public List<Proveedor> consultarProveedorXSede(@WebParam(name = "sede") Sede sede) {

        List<Proveedor> Resultado;
        try {
            Resultado = ejbProveedorSede.consultarProveedorXSede(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarProveedorXSede");
            log.fatal("Error fatal en Servicio  consultarProveedorXSede");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    /**
     *
     * @param nombre
     * @return
     */
    @WebMethod(operationName = "consultarProveedorXNombre")
    public List<Proveedor> consultarProveedorXNombre(@WebParam(name = "nombre") String nombre) {

        List<Proveedor> Resultado;
        try {
            Resultado = (List<Proveedor>) ejbProveedor.consultarProveedorNombre(nombre);
        } catch (Exception e) {
            log.error("Error en Servicio consultarProveedorXNombre");
            log.fatal("Error fatal en Servicio  consultarProveedorXNombre");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     *
     * @param idpro
     * @return
     */
    @WebMethod(operationName = "consultarProveedorXId")
    public Proveedor consultarProveedorXId(@WebParam(name = "id") String idpro) {

        Proveedor Resultado;
        try {
            Resultado = ejbProveedor.consultarProveedorXId(new BigDecimal(idpro));
        } catch (Exception e) {
            log.error("Error en Servicio consultarProveedorXId");
            log.fatal("Error fatal en Servicio  consultarProveedorXId");
            Resultado = null;
        }
        return Resultado;
    }

    /**
     * Registra el seguimiento del paquete retorna 0 si ya fue confirmado
     * retorna 1 si confirmo retorna 2 si aún no le corresponde confirmar
     *
     * @param registroPaquete
     * @param registroUsuario
     * @param registroSede
     * @param Caso
     * @return
     */
    @WebMethod(operationName = "registroSeguimiento")
    public int registroSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede, @WebParam(name = "Caso") String Caso) {
        int Resultado = 0, primeraVez = 0;
        boolean aunNo = false;
        String nivelSeg = "", Tipo;
        Seguimiento nuevoSeg = new Seguimiento();
        Usuariosede usuarioSede = null;
        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
            usuarioSede = consultarUsuarioSede(registroUsuario, registroSede);
            registroPaquete = ejbPaquete.ConsultarPaqueteXId(registroPaquete.getIdpaq());
            if (registroPaquete.getStatuspaq().compareTo("1") != 0 && registroPaquete.getStatuspaq().compareTo("4") != 0) {
                try {
                    Paquete Registro = ejbPaquete.ConsultarPaqueteXIdPaqueteYSedeDeValija(registroSede, registroPaquete.getIdpaq());
                    Tipo = "1";
                } catch (Exception e) {
                    Tipo = "0";
                }
                if (RegistrosSeguimiento.isEmpty()) {
                    if (Caso.compareTo("Confirmar") == 0) {
                        primeraVez = 1;
                    } else {
                        nuevoSeg = new Seguimiento();
                        nuevoSeg.setFechaseg(new Date());
                        nuevoSeg.setIdpaq(registroPaquete);
                        nuevoSeg.setIduse(usuarioSede);
                        nuevoSeg.setTiposeg(Tipo);
                        nuevoSeg.setNivelseg("Usuario");
                        nuevoSeg.setStatusseg("0");
                        ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Usuario Normal");
                        primeraVez = 2;
                        return 1;
                    }
                    if (primeraVez == 1) {
                        return 2;
                    } else if (primeraVez == 2) {
                        return 1;
                    }
                }

                if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                    nivelSeg = "Area de Trabajo";
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                    nivelSeg = "Sede";
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                    nivelSeg = "Emisario";
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0) {
                    nivelSeg = "Valija";
                } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0) {//caso del multirol
                    if (Tipo.compareTo("0") == 0) {
                        if (registroPaquete.getLocalizacionpaq().compareTo("Sede") == 0) {//REVISAR LOCALIZACION
                            nivelSeg = "Valija";
                        } else {
                            nivelSeg = "Sede";
                        }
                        if (Caso.compareTo("Confirmar") == 0) {
                            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                                if (RegistrosSeguimiento.get(i).getIduse().getIdusu().getIdusu().toString().compareTo(usuarioSede.getIdusu().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                                    return Resultado;
                                }
                            }
                        }
                    } else {//destino 
                        if (registroPaquete.getLocalizacionpaq().compareTo("Valija") == 0) {//REVISAR LOCALIZACION
                            nivelSeg = "Sede";
                        } else {
                            nivelSeg = "Valija";
                        }
                        if (Caso.compareTo("Confirmar") == 0) {
                            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                                if (RegistrosSeguimiento.get(i).getIduse().getIdusu().getIdusu().toString().compareTo(usuarioSede.getIdusu().getIdusu().toString()) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                                    return Resultado;
                                }
                            }
                        }
                    }
                }
                if (RegistrosSeguimiento.size() == 1) {
                    if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0 || Tipo.compareTo("0") == 0) {
                        nuevoSeg = new Seguimiento();
                        nuevoSeg.setFechaseg(new Date());
                        nuevoSeg.setIdpaq(registroPaquete);
                        nuevoSeg.setIduse(usuarioSede);
                        nuevoSeg.setTiposeg(Tipo);
                        nuevoSeg.setNivelseg(nivelSeg);
                        nuevoSeg.setStatusseg("0");
                        ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                        ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), nivelSeg);
                        if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                            ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Area de trabajo");
                        } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0) {
                            ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Sede");
                        } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                            ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Emisario");
                        }
                        RegistrosSeguimiento.get(0).setStatusseg("1");
                        ejbSeguimiento.edit(RegistrosSeguimiento.get(0));
                        return 1;
                    } else {
                        return 2;
                    }
                }

                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getTiposeg().compareTo(Tipo) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo(nivelSeg) == 0 && RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") != 0) {
                        return Resultado;
                    }
                }
                //Caso  Receptor nivel 1 Origen o Receptor nivel 3 Origen
                if ((usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("0") == 0) {
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                            return Resultado;
                        }
                    }
                    Resultado = 1;

                } //Caso  Receptor nivel 2 Origen
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("0") == 0) {
                    Resultado = 1;
                } //Caso  MultiRol Origen
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0 && Tipo.compareTo("0") == 0) {
                    Resultado = 1;
                } //Caso  Empaquetador 
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("0") == 0) {
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                            Resultado = 1;
                            aunNo = true;
                            break;
                        }
                    }
                    if (!aunNo) {
                        return 2;
                    }
                }//Caso  Desenvalijador 
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("1") == 0) {
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0 && registroPaquete.getIdval().getCodproveedorval() != null) {
                            Resultado = 1;
                            aunNo = true;
                            break;
                        }
                    }
                    if (!aunNo) {
                        return 2;
                    }
                }//Caso  Receptor nivel 1 Destino o Caso  Receptor nivel 3 Destino
                else if ((usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0 || usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("1") == 0) {
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Sede") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                            Resultado = 1;
                            aunNo = true;
                            break;
                        }
                    }
                    if (!aunNo) {
                        return 2;
                    }
                } //Caso  Receptor nivel 2 Destino
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("1") == 0) {
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                            Resultado = 1;
                            aunNo = true;
                            break;
                        }
                    }
                    if (!aunNo) {
                        return 2;
                    }
                }//Caso  MultiRol Destino
                else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("5") == 0 && Tipo.compareTo("1") == 0) {
                    if (nivelSeg.compareTo("Sede") == 0) { //si es sede 
                        for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                            if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                                Resultado = 1;
                                aunNo = true;
                                break;
                            }
                        }
                        if (!aunNo) {
                            return 2;
                        }
                    } else { // si es desenvajilador
                        for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                            if (RegistrosSeguimiento.get(i).getNivelseg().compareTo("Valija") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0 && registroPaquete.getIdval().getCodproveedorval() != null) {
                                Resultado = 1;
                                aunNo = true;
                                break;
                            }
                        }
                        if (!aunNo) {
                            return 2;
                        }
                    }
                    Resultado = 1;
                }
                if (Resultado == 1) {
                    nuevoSeg = new Seguimiento();
                    nuevoSeg.setFechaseg(new Date());
                    nuevoSeg.setIdpaq(registroPaquete);
                    nuevoSeg.setIduse(usuarioSede);
                    nuevoSeg.setTiposeg(Tipo);
                    nuevoSeg.setNivelseg(nivelSeg);
                    nuevoSeg.setStatusseg("0");
                    for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                        RegistrosSeguimiento.get(i).setStatusseg("1");
                        ejbSeguimiento.edit(RegistrosSeguimiento.get(i));
                    }
                    ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                    ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), nivelSeg);
                    if (usuarioSede.getIdrol().getIdrol().toString().compareTo("1") == 0) {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Área de trabajo");
                    } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Sede");
                    } else if (usuarioSede.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                        ejbBitacora.insertarBitacora(registroSede, registroUsuario, "CONFIRMACIÓN", "Registro de paquete Emisario");
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
            } else {
                return 2;
            }

        } catch (Exception e) {
            log.error("Error en Servicio registroSeguimiento");
            log.fatal("Error fatal en Servicio  registroSeguimiento");
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
            log.error("Error en Servicio consultarUsuarioSede");
            log.fatal("Error fatal en Servicio  consultarUsuarioSede");
            return null;
        }
        return Resultado;
    }

    /**
     * consulta el buzon por usuario
     *
     * @param registroUsuario
     * @return
     */
    @WebMethod(operationName = "consultarBuzonUsuario")
    public List<Buzon> consultarBuzonUsuario(@WebParam(name = "registroUsuario") String registroUsuario) {

        List<Buzon> Resultado;
        try {
            Resultado = ejbBuzon.ConsultarBuzonXUsuario(new BigDecimal(registroUsuario));
        } catch (Exception e) {
            log.error("Error en Servicio consultarBuzonUsuario");
            log.fatal("Error fatal en Servicio consultarBuzonUsuario");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idbuz
     * @return
     */
    @WebMethod(operationName = "consultarBuzon")
    public Buzon consultarBuzon(@WebParam(name = "idbuz") String idbuz) {

        Buzon Resultado;
        try {
            Resultado = ejbBuzon.consultarBuzonXId(idbuz);
        } catch (Exception e) {
            log.error("Error en Servicio consultarBuzon");
            log.fatal("Error fatal consultarBuzon");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param area
     * @param miBuzon
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarBuzonParaEnviar")
    public List<Buzon> consultarBuzonParaEnviar(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "area") String area, @WebParam(name = "miBuzon") Buzon miBuzon, @WebParam(name = "sede") String sede) {

        List<Buzon> Resultado;
        try {
            Resultado = ejbBuzon.buscarBuzonParaEnviar(nombre, apellido, area, miBuzon, sede);
        } catch (Exception e) {
             log.error("Error en Servicio consultarBuzonParaEnviar");
            log.fatal("Error fatal consultarBuzonParaEnviar");
            return null;
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
            registroUsuario.setApellidousu(registroUsuario.getApellidousu().trim());
            registroUsuario.setCorreousu(registroUsuario.getCorreousu().trim());
            registroUsuario.setDireccionusu(registroUsuario.getDireccionusu().trim());
            registroUsuario.setNombreusu(registroUsuario.getNombreusu().trim());
            registroUsuario.setTelefono2usu(registroUsuario.getTelefono2usu().trim());
            registroUsuario.setTelefonousu(registroUsuario.getTelefonousu().trim());
            ejbUsuario.editarUsuario(registroUsuario);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio editarUsuario");
            log.fatal("Error fatal editarUsuario");
            return 0;
        }
        return Resultado;
    }

    /**
     * Editar usuario
     *
     * @param registroBuzon
     * @return
     */
    @WebMethod(operationName = "editarBuzon")
    public int editarBuzon(@WebParam(name = "registroBuzon") Buzon registroBuzon) {

        int Resultado = 0;
        try {
            registroBuzon.setNombrebuz(registroBuzon.getNombrebuz().trim());
            registroBuzon.setDireccionbuz(registroBuzon.getDireccionbuz().trim());
            registroBuzon.setTelefonobuz(registroBuzon.getTelefonobuz().trim());
            ejbBuzon.editarBuzon(registroBuzon);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio editarBuzon");
            log.fatal("Error fatal editarBuzon");
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

        List<Paquete> ResultadoVencidas = null;
        try {
            ResultadoVencidas = ejbAlerta.consultarPaquetesXUsuarioOrigen(registroUsuario, registroSede);
        } catch (Exception e) {
            log.error("Error en Servicio paquetesVencidosXOrigen");
            log.fatal("Error fatal en Servicio  paquetesVencidosXOrigen");
            return null;
        }
        return ResultadoVencidas;
    }

    /**
     * lista paquetes que se pasaron de fecha limite o si la fecha alerta es
     * igual a la actualy el paquete no ha llegado de los paquetes que debetria
     * recibir
     *
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "paquetesVencidosXDestino")
    public List<Paquete> paquetesVencidosXDestino(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbAlerta.consultarPaquetesXUsuarioDestino(registroUsuario, registroSede);
        } catch (Exception e) {
            log.error("Error en Servicio paquetesVencidosXDestino");
            log.fatal("Error fatal en Servicio  paquetesVencidosXDestino");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param usuarioSede
     * @return
     */
    @WebMethod(operationName = "paquetesVencidosXSeguimiento")
    public List<Paquete> paquetesVencidosXSeguimiento(@WebParam(name = "usuarioSede") Usuariosede usuarioSede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbAlerta.consultarPaquetesXSeguimiento(usuarioSede);
        } catch (Exception e) {
            log.error("Error en Servicio paquetesVencidosXSeguimiento");
            log.fatal("Error fatal en Servicio  paquetesVencidosXSeguimiento");
            return null;
        }
        return Resultado;
    }

    /**
     * consulta los contactos que el usuario tiene registrado en buzón
     *
     * @param registroUsuario
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "consultarBuzonXUsuario")
    public List<Buzon> consultarBuzonXUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede) {

        List<Buzon> Interno = null, Externo = null;
        try {
            Interno = ejbBuzon.ConsultarBuzonInternoXUsuario(registroUsuario, registroSede);
            Externo = ejbBuzon.ConsultarBuzonExternoXUsuario(registroUsuario);
            if (Interno.isEmpty() && !Externo.isEmpty()) {
                return Externo;
            } else if (!Interno.isEmpty() && Externo.isEmpty()) {
                return Interno;
            } else if (!Interno.isEmpty() && !Externo.isEmpty()) {
                for (int i = 0; i < Externo.size(); i++) {
                    Interno.add(Externo.get(i));
                }
            }
        } catch (Exception e) {
            
            log.error("Error en Servicio consultarBuzonXUsuario");
            log.fatal("Error fatal en Servicio  consultarBuzonXUsuario");
            return null;
        }
        return Interno;
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
            log.error("Error en Servicio listarDocumentos");
            log.fatal("Error fatal en Servicio  listarDocumentos");
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
            log.error("Error en Servicio listarPrioridad");
            log.fatal("Error fatal en Servicio  listarPrioridad");
            return null;
        }
        return Resultado;
    }

    /**
     * consultarPaquetesXUsuario confirmados al día
     *
     * @param idUsuarioSede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesConfirmadosXUsuarioSede")
    public List<Paquete> consultarPaquetesConfirmadosXUsuarioSede(@WebParam(name = "idUsuarioSede") Usuariosede idUsuarioSede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbSeguimiento.listaPaquetesProcesadosXUsuarioSede(idUsuarioSede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaquetesConfirmadosXUsuarioSede");
            log.fatal("Error fatal en Servicio  consultarPaquetesConfirmadosXUsuarioSede");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idUsuarioSede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesConfirmadosXRol")
    public List<Paquete> consultarPaquetesConfirmadosXRol(@WebParam(name = "idUsuarioSede") Usuariosede idUsuarioSede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbSeguimiento.consultarPaquetesConfirmadosXRol(idUsuarioSede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaquetesConfirmadosXRol");
            log.fatal("Error fatal en Servicio  consultarPaquetesConfirmadosXRol");
            return null;
        }
        return Resultado;
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
            log.error("Error en Servicio consultarSedeDeUsuario");
            log.fatal("Error fatal en Servicio  consultarSedeDeUsuario");
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
            log.error("Error en Servicio consultarUsuarioXUser");
            log.fatal("Error fatal en Servicio  consultarUsuarioXUser");
            return null;
        }
        if (Resul.getIdusu() == null) {
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
            log.error("Error en Servicio insertarAdjunto");
            log.fatal("Error fatal en Servicio  insertarAdjunto");
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
            log.error("Error en Servicio listarSedes");
            log.fatal("Error fatal en Servicio  listarSedes");
            return null;
        }
        return Resultado;
    }

    /**
     * lista todas las sedes
     *
     * @return
     */
    @WebMethod(operationName = "consultarOrganizaciones")
    public List<Organizacion> consultarOrganizaciones() {

        List<Organizacion> Resultado = null;
        try {
            Resultado = ejbOrganizacion.findAll();
        } catch (Exception e) {
            log.error("Error en Servicio consultarOrganizaciones");
            log.fatal("Error fatal en Servicio  consultarOrganizaciones");
            return null;
        }
        return Resultado;
    }

    /**
     * caso en el cual se crea usuario
     *
     * @param registroUsuSede
     * @param userUsu
     * @return
     */
    @WebMethod(operationName = "insertarUsuarioSedeXDefecto")
    public int insertarUsuarioSedeXDefecto(@WebParam(name = "registroUsuSede") Usuariosede registroUsuSede, @WebParam(name = "userUsu") String userUsu) {

        int Resultado = 0;
        try {
            Usuario RegUsu = new Usuario(ejbUsuario.consultarMAXId(userUsu));
            registroUsuSede.setIdusu(RegUsu);
            ejbUsuariosede.insertarUsuarioSede(registroUsuSede);
            Resultado = 1;
            Resultado = insertarBuzon(RegUsu.getIdusu().toString(), registroUsuSede.getIdsed().getIdsed().toString());
        } catch (Exception e) {
            log.error("Error en Servicio insertarUsuarioSedeXDefecto");
            log.fatal("Error fatal en Servicio  insertarUsuarioSedeXDefecto");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     * caso en buzon adicional
     *
     * @param idusu
     * @param idatr
     * @param idsed
     * @return
     */
    @WebMethod(operationName = "insertarUsuarioSedeXAdicional")
    public int insertarUsuarioSedeXAdicional(@WebParam(name = "idusu") String idusu, @WebParam(name = "idatr") String idatr, @WebParam(name = "idsed") String idsed) {

        int Resultado = 0;
        try {
            Usuariosede registroUsuSede = new Usuariosede();
            Rol rol = ejbRol.find(new BigDecimal("6"));
            Sede sed = ejbSede.ConsultarSedeXNombre(idsed);
            registroUsuSede.setIdatr(ejbAreaTrabajo.consultarAreasXId(idatr));
            registroUsuSede.setIdsed(sed);
            registroUsuSede.setIdusu(ejbUsuario.find(new BigDecimal(idusu)));
            registroUsuSede.setIdrol(rol);
            Usuario usuario = ejbUsuario.find(new BigDecimal(idusu));
            try {
                Usuariosede registro = ejbUsuariosede.ConsultarXUsuarioYSede(usuario, sed);
                return 2;
            } catch (Exception e) {
                log.error("Error en Servicio ConsultarXUsuarioYSede en insertarUsuarioSedeXAdicional");
            log.fatal("Error fatal en Servicio  ConsultarXUsuarioYSede en insertarUsuarioSedeXAdicional");
            }
            ejbUsuariosede.insertarUsuarioSede(registroUsuSede);
            Resultado = 1;
            Resultado = insertarBuzon(idusu, sed.getIdsed().toString());
        } catch (Exception e) {
              log.error("Error en Servicio insertarUsuarioSedeXAdicional");
            log.fatal("Error fatal en Servicio  insertarUsuarioSedeXAdicional");
            Resultado = 0;
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
                log.error("Error en Servicio consultaNombreSedeXId");
            log.fatal("Error fatal en Servicio  consultaNombreSedeXId");
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
            Resultado = ejbBuzon.ConsultarBuzonInternoXNombreUsuario(userUsu, idUsuario);
            if (Resultado == null) {
                Resultado = ejbBuzon.ConsultarBuzonExternoXNombreUsuario(userUsu, idUsuario);
            }
        } catch (Exception e) {
            log.error("Error en Servicio consultarBuzonXNombreUsuario");
            log.fatal("Error fatal en Servicio  consultarBuzonXNombreUsuario");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idusu
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarBuzonXUsuarioSede")
    public Buzon consultarBuzonXUsuarioSede(@WebParam(name = "idusu") String idusu, @WebParam(name = "idsede") String sede) {

        Buzon Resultado = null;
        try {
            Resultado = ejbBuzon.ConsultarBuzonXNombreSede(idusu, sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarBuzonXUsuarioSede");
            log.fatal("Error fatal en Servicio  consultarBuzonXUsuarioSede");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param dueno
     * @param contacto
     * @param idSede
     * @return
     */
    @WebMethod(operationName = "verificarExistenciaBuzon")
    public int verificarExistenciaBuzon(@WebParam(name = "dueno") Usuario dueno, @WebParam(name = "contacto") Usuario contacto, @WebParam(name = "idSede") Sede idSede) {

        int Resultado = 0;
        try {
            Buzon Result = ejbBuzon.verficarBuzon(dueno, contacto, idSede);
            Resultado = 1;
        } catch (Exception e) {
            Buzon Nuevo = new Buzon();
            Nuevo.setIdusu(dueno);
            Nuevo.setTipobuz("0");
            ejbBuzon.insertarBuzon(Nuevo);
            return Resultado;
        }
        return Resultado;
    }

    /**
     *
     * @param idSede
     * @return
     */
    @WebMethod(operationName = "valijasXFechaVencidaXUsuarioOrigen")
    public List<Valija> valijasXFechaVencidaXUsuarioOrigen(@WebParam(name = "idSede") String idSede) {

        List<Valija> Resultado = null;
        try {
            Resultado = ejbValija.listarValijasXFechaVencimientoOrigen(new BigDecimal(idSede));
        } catch (Exception e) {
            log.error("Error en Servicio valijasXFechaVencidaXUsuarioOrigen");
            log.fatal("Error fatal en Servicio  valijasXFechaVencidaXUsuarioOrigen");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param registroSede
     * @return
     */
    @WebMethod(operationName = "valijasXFechaVencidaXUsuarioDestino")
    public List<Valija> valijasXFechaVencidaXUsuarioDestino(@WebParam(name = "registroSede") Sede registroSede) {

        List<Valija> Resultado = null;
        try {
            Resultado = ejbValija.listarValijasXFechaVencimientoDestino(registroSede);
        } catch (Exception e) {
             log.error("Error en Servicio valijasXFechaVencidaXUsuarioDestino");
            log.fatal("Error fatal en Servicio  valijasXFechaVencidaXUsuarioDestino");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "insertarBandejaOrigen")
    public int insertarBandejaOrigen(@WebParam(name = "idpaq") String idpaq) {

        int Resultado;
        try {
            Paquete paquete = ejbPaquete.find(new BigDecimal(idpaq));
            Bandeja nuevo = new Bandeja();
            Infobandeja registroInfoB;
            registroInfoB = new Infobandeja(new BigDecimal("1"));
            nuevo.setIdiba(registroInfoB);
            nuevo.setIdpaq(paquete);
            nuevo.setLeidoban("0");
            nuevo.setIdusu(paquete.getOrigenpaq().getIdusu());
            ejbBandeja.insertarBandeja(nuevo);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio insertarBandejaOrigen");
            log.fatal("Error fatal en Servicio  insertarBandejaOrigen");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "insertarBandejaDestino")
    public int insertarBandejaDestino(@WebParam(name = "idpaq") String idpaq) {

        int Resultado;
        try {
            Paquete paquete = ejbPaquete.find(new BigDecimal(idpaq));
            Bandeja nuevo = new Bandeja();
            Infobandeja registroInfoB;
            registroInfoB = new Infobandeja(new BigDecimal("3"));
            nuevo.setIdiba(registroInfoB);
            nuevo.setIdpaq(paquete);
            nuevo.setLeidoban("0");
            nuevo.setIdusu(paquete.getDestinopaq().getIdusu());
            ejbBandeja.insertarBandeja(nuevo);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio insertarBandejaDestino");
            log.fatal("Error fatal en Servicio  insertarBandejaDestino");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @param status
     * @return
     */
    @WebMethod(operationName = "editarEstatusPaquete")
    public int editarEstatusPaquete(@WebParam(name = "idpaq") String idpaq, @WebParam(name = "status") String status) {

        int Resultado;
        try {
            ejbPaquete.editarStatusPaquete(new BigDecimal(idpaq), status);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio editarEstatusPaquete");
            log.fatal("Error fatal en Servicio  editarEstatusPaquete");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesExternosXEnviar")
    public List<Paquete> consultarPaquetesExternosXEnviar(@WebParam(name = "sede") Sede sede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.ConsultarPaquetesExternos(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaquetesExternosXEnviar");
            log.fatal("Error fatal en Servicio  consultarPaquetesExternosXEnviar");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param registroPaquete
     * @param registroUsuario
     * @param registroSede
     * @param localizacion
     * @return
     */
    @WebMethod(operationName = "seguimientoExterno")
    public int seguimientoExterno(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario, @WebParam(name = "registroSede") Sede registroSede, @WebParam(name = "localizacion") String localizacion) {
        int Resultado = 0;
        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                RegistrosSeguimiento.get(i).setStatusseg("1");
                ejbSeguimiento.edit(RegistrosSeguimiento.get(i));
            }
            Usuariosede registroUsuSede = ejbUsuariosede.ConsultarXUsuarioYSede(registroUsuario, registroSede);
            Seguimiento nuevoSeg = new Seguimiento();
            nuevoSeg.setIdpaq(registroPaquete);
            nuevoSeg.setIduse(registroUsuSede);
            nuevoSeg.setFechaseg(new Date());
            nuevoSeg.setStatusseg("0");
            nuevoSeg.setTiposeg("0");
            nuevoSeg.setNivelseg("Externo");
            ejbSeguimiento.insertarSeguimiento(nuevoSeg);
            ejbBitacora.insertarBitacora(registroSede, registroUsuario, "ENVIO EXTERNO", "Envio de corrspondencia externa");
            ejbPaquete.editarLocalizacionPaquete(registroPaquete.getIdpaq(), localizacion);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio seguimientoExterno");
            log.fatal("Error fatal en Servicio  seguimientoExterno");
            return 0;
        }
        return Resultado;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarPaquetesXConfirmarExternos")
    public List<Paquete> consultarPaquetesXConfirmarExternos(@WebParam(name = "sede") Sede sede) {

        List<Paquete> Resultado = null;
        try {
            Resultado = ejbSeguimiento.consultarPaquetesXConfirmarExternos(sede);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaquetesXConfirmarExternos");
            log.fatal("Error fatal en Servicio  consultarPaquetesXConfirmarExternos");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    @WebMethod(operationName = "consultarPaqueteXIdOCodigoBarras")
    public Paquete consultarPaqueteXIdOCodigoBarras(@WebParam(name = "codigo") String codigo) {
        Paquete Resultado = null;
        try {
            Resultado = ejbPaquete.consultarPaqueteXIdOCodigoBarras(codigo);
        } catch (Exception e) {
            log.error("Error en Servicio consultarPaqueteXIdOCodigoBarras");
            log.fatal("Error fatal en Servicio  consultarPaqueteXIdOCodigoBarras");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param codigo
     * @param sede
     * @return
     */
    @WebMethod(operationName = "consultarValijaXIdOCodigoBarras")
    public Valija consultarValijaXIdOCodigoBarras(@WebParam(name = "codigo") String codigo, @WebParam(name = "sede") String sede) {
        Valija Resultado = null;
        try {
            Resultado = ejbValija.consultarValijaXIdOCodigoBarras(codigo, sede);
            if (Resultado.getCodproveedorval() == null) {
                Resultado = null;
            }
        } catch (Exception e) {
            log.error("Error en Servicio consultarValijaXIdOCodigoBarras");
            log.fatal("Error fatal en Servicio  consultarValijaXIdOCodigoBarras");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    @WebMethod(operationName = "consultarValijaXIdOCodigoBarra")
    public Valija consultarValijaXIdOCodigoBarra(@WebParam(name = "codigo") String codigo) {
        Valija Resultado = null;
        try {
            Resultado = ejbValija.consultarValijaXIdOCodigoBarra(codigo);
        } catch (Exception e) {
            log.error("Error en Servicio consultarValijaXIdOCodigoBarra");
            log.fatal("Error fatal en Servicio  consultarValijaXIdOCodigoBarra");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    @WebMethod(operationName = "auntenticarLDAP")
    public String auntenticarLDAP(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {

        String Resultado;
        try {
            Resultado = ejbUsuario.auntenticarLDAPauntenticarLDAP(user, password);

        } catch (Exception e) {
            
            log.error("Error en Servicio auntenticarLDAP no pertenece a la organizacion");
            log.fatal("Error fatal en Servicio  auntenticarLDAP");
            Resultado = "Fail";
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "confirmarCorrespondenciaExterna")
    public int confirmarCorrespondenciaExterna(@WebParam(name = "idpaq") String idpaq) {

        int Resultado;
        try {
            BigDecimal idu = new BigDecimal(idpaq);
            Paquete paq = ejbPaquete.find(idu);
            ejbPaquete.ActualizacionLocalizacionyDelPaqueteExterno(paq);
            ejbBandeja.actualizacionBandejaEnvioExterno(paq);
            ejbSeguimiento.actualizacionEstadoEnvioExterno(paq);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio confirmarCorrespondenciaExterna");
            log.fatal("Error fatal en Servicio  confirmarCorrespondenciaExterna");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "miIdBuzon")
    public int miIdBuzon(@WebParam(name = "idUsuario") Usuario idUsuario) {
        int Resultado = 0;
        try {
            Resultado = ejbBuzon.myIdBuzon(idUsuario);
        } catch (Exception e) {
            log.error("Error en Servicio miIdBuzon");
            log.fatal("Error fatal en Servicio  miIdBuzon");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "editarRespuestaPaquete")
    public int editarRespuestaPaquete(@WebParam(name = "idpaq") String idpaq) {
        int Resultado;
        try {
            ejbPaquete.editarRespaqDePaquete(new BigDecimal(idpaq));
            Resultado = 1;
        } catch (Exception e) {
             log.error("Error en Servicio editarRespuestaPaquete");
            log.fatal("Error fatal en Servicio  editarRespuestaPaquete");
            Resultado = 0;
        }
        return Resultado;
    }

    /**
     *
     * @param idSede
     * @return
     */
    @WebMethod(operationName = "listarSedesParaEnvio")
    public List<Sede> listarSedesParaEnvio(@WebParam(name = "idSede") String idSede) {

        List<Sede> Resultado = null;
        try {
            Resultado = ejbSede.listarSedesParaEnvio(new BigDecimal(idSede));
        } catch (Exception e) {
             log.error("Error en Servicio listarSedesParaEnvio");
            log.fatal("Error fatal en Servicio  listarSedesParaEnvio");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idpaq
     * @return
     */
    @WebMethod(operationName = "listarMensajesXPaquete")
    public List<Mensaje> listarMensajesXPaquete(@WebParam(name = "idpaq") Paquete idpaq) {
        List<Mensaje> Resultado = null;
        try {
            Resultado = ejbMensaje.consultarMensajeXIdPaquete(idpaq);
        } catch (Exception e) {
            log.error("Error en Servicio listarMensajesXPaquete");
            log.fatal("Error fatal en Servicio  listarMensajesXPaquete");
            return null;
        }
        return Resultado;
    }

    /**
     *
     * @param idval
     * @return
     */
    @WebMethod(operationName = "listarIncidentesXValija")
    public List<Incidente> listarIncidentesXValija(@WebParam(name = "idval") String idval) {
        List<Incidente> Resultado = null;
        Valija idValija;
        try {
            idValija = new Valija();
            idValija.setIdval(new BigDecimal(idval));
            Resultado = ejbIncidente.consultarIncidenteXValija(idValija);
        } catch (Exception e) {
            log.error("Error en Servicio listarIncidentesXValija");
            log.fatal("Error fatal en Servicio  listarIncidentesXValija");
            return null;
        }
        return Resultado;
    }

    /**
     * Método encargado de insertar registros de la entidad Usuario
     *
     * @param nombre
     * @param direccion
     * @param idorg
     * @param telefono2
     * @param telefono
     * @param codigo
     * @return
     */
    @WebMethod(operationName = "insertarNuevaSede")
    public int insertarNuevaSede(@WebParam(name = "nombresed") String nombre, @WebParam(name = "direccionsed") String direccion, @WebParam(name = "telefonosed") String telefono, @WebParam(name = "telefono2sed") String telefono2, @WebParam(name = "idorg") String idorg, @WebParam(name = "codigosed") String codigo) {


        Organizacion org = ejbOrganizacion.find(new BigDecimal(idorg));
        Sede registroSede = new Sede();
        registroSede.setNombresed(nombre);
        registroSede.setDireccionsed(direccion);
        registroSede.setTelefonosed(telefono);
        registroSede.setTelefono2sed(telefono2);
        registroSede.setCodigosed(codigo);
        registroSede.setBorradosed("0");
        registroSede.setIdorg(org);

        int Resultado;
        try {
            ejbSede.create(registroSede);
            Resultado = 1;
        } catch (Exception e) {
            log.error("Error en Servicio insertarNuevaSede");
            log.fatal("Error fatal en Servicio  insertarNuevaSede");
            Resultado = 0;
        }
        return Resultado;
    }
}