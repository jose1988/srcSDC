/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Buzon;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Prioridad;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Valija;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.BuzonFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.DocumentoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PrioridadFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SeguimientoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuarioFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pc
 */
@WebService(serviceName = "mariela")
public class mariela {

    @EJB
    SeguimientoFacade ejbSeguimiento;
    @EJB
    PaqueteFacade ejbPaquete;
    @EJB
    UsuarioFacade ejbUsuario;
    @EJB
    BuzonFacade ejbBuzon;
    @EJB
    DocumentoFacade ejbDocumento;
    @EJB
    PrioridadFacade ejbPrioridad;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registroSeguimiento")
    public int registroSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario, String Tipo) {
        int Resultado = 0;
        boolean reenvio = false;
        Seguimiento nuevoSeg = new Seguimiento();
        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);

            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                //pregunto ya un envajilador habia tocado el paquete y el q recibo como parametro es envajilador quiere decir que es un reenvio de paquete
                if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol() == registroUsuario.getIdrol().getIdrol() && RegistrosSeguimiento.get(i).getTiposeg().compareTo(Tipo) == 0 && RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("0") == 0) {
                    reenvio = true;
                } else {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol() == registroUsuario.getIdrol().getIdrol() && RegistrosSeguimiento.get(i).getTiposeg().compareTo(Tipo) == 0 && RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") != 0) {
                        return Resultado;
                    }
                }
            }
            //Caso  Receptor nivel 1 Origen o Receptor nivel 3 Origen
            if ((registroUsuario.getIdrol().getIdrol().toString().compareTo("1") == 1 || registroUsuario.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("0") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("2") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        return Resultado;
                    }
                }
                Resultado = 1;

            } //Caso  Receptor nivel 2 Origen
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("0") == 0) {
                Resultado = 1;
            } //Caso  Empaquetador 
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("0") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("2") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        Resultado = 1;
                        break;
                    }
                }
            }//Caso  Desenvalijador 
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("4") == 0 && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("0") == 0) {
                        registroPaquete = ejbPaquete.ConsultarPaqueteXId(registroPaquete);
                        if (registroPaquete.getIdval().getZoomval() != null) {
                            Resultado = 1;
                        }
                        break;
                    }
                }
            }//Caso  Receptor nivel 1 Destino o Caso  Receptor nivel 3 Destino
            else if ((registroUsuario.getIdrol().getIdrol().toString().compareTo("1") == 0 || registroUsuario.getIdrol().getIdrol().toString().compareTo("3") == 0) && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().toString().compareTo("2") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                        Resultado = 1;
                        break;
                    }
                }

            } //Caso  Receptor nivel 2 Destino
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("2") == 0 && Tipo.compareTo("1") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                        Resultado = 1;
                        break;
                    }
                }
            }

            if (Resultado == 1) {
                nuevoSeg = new Seguimiento();
                nuevoSeg.setFechaseg(new Date());
                nuevoSeg.setIdpaq(registroPaquete);
                nuevoSeg.setIdusu(registroUsuario);
                if (reenvio) {
                    nuevoSeg.setStatusseg("2");
                } else {
                    nuevoSeg.setStatusseg("0");
                }
                ejbSeguimiento.insertarSeguimiento(nuevoSeg);
            }
        } catch (Exception e) {
            return Resultado;

        }
        //Caso  Receptor nivel 1 Origen  1 
        //Caso  Receptor nivel 2 Origen 2
        //Caso  Receptor nivel 3 Origen3 
        //Caso  Empaquetador 4
        //Caso  Desglosa la Valija 5
        //Caso  Receptor nivel 1 Destino 6
        //Caso  Receptor nivel 2 Destino 7
        //Caso  Receptor nivel 3 Destino 8
        return Resultado;
    }

    @WebMethod(operationName = "finalizarSeguimiento")
    public int finalizarSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
//Caso  Usuario Destino
        try {
            List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
            boolean Nivel2 = false;
            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("2") == 0 && RegistrosSeguimiento.get(i).getTiposeg().compareTo("1") == 0) {
                    Nivel2 = true;
                    break;
                }
            }
            if (Nivel2) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    RegistrosSeguimiento.get(i).setStatusseg("1");
                    ejbSeguimiento.edit(RegistrosSeguimiento.get(i));
                }
            }

        } catch (Exception e) {
        }

        return 0;
    }
//esta ya no

    @WebMethod(operationName = "registroReenvioXDesenvajilador")
    public int registroReenvioXDesenvajilador(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
        Seguimiento nuevoSeg = new Seguimiento();
        try {
            if (registroUsuario.getIdrol().getIdrol().toString().compareTo("5") == 0) {
                List<Seguimiento> RegistrosSeguimiento = ejbSeguimiento.consultarSeguimientoXPaquete(registroPaquete);
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0) {
                        registroPaquete = ejbPaquete.ConsultarPaqueteXId(registroPaquete);
                        if (registroPaquete.getIdval().getZoomval() != null) {
                            nuevoSeg = new Seguimiento();
                            nuevoSeg.setFechaseg(new Date());
                            nuevoSeg.setIdpaq(registroPaquete);
                            nuevoSeg.setIdusu(registroUsuario);
                            nuevoSeg.setStatusseg("Reenvio");
                            ejbSeguimiento.insertarSeguimiento(nuevoSeg);
                            Resultado = 1;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Resultado = 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "editarUsuario")
    public int editarUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
        try {
            // ejbUsuario.editarUsuario(registroUsuario);
            Resultado = 1;
        } catch (Exception e) {
            return 0;
        }
        return Resultado;
    }

    @WebMethod(operationName = "alertaXFechaVencida")
    public String alertaXFechaVencida(@WebParam(name = "registroPaquete") Paquete registroPaquete) {
        String Alerta = "";
        try {
            registroPaquete = ejbPaquete.ConsultarPaqueteXId(registroPaquete);
            if (registroPaquete.getFechaapaq() == new Date()) {
                Alerta = "ALERTA";
            }
            if (registroPaquete.getFechaenviopaq().before(new Date())) {
                Alerta = "VENCIDO";
            }
        } catch (Exception e) {
            return null;
        }
        return Alerta;
    }

    @WebMethod(operationName = "paquetesXFechaAlertaXUsuarioDestino")
    public List<Paquete> paquetesXFechaAlertaXUsuarioDestino(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.consultarPaqueteXAlertaXUsuarioDestino(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "paquetesXFechaAlertaXUsuarioOrigen")
    public List<Paquete> paquetesXFechaAlertaXUsuarioOrigen(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.consultarPaqueteXAlertaXUsuarioOrigen(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "paquetesXFechaVencimientoXUsuarioOrigen")
    public List<Paquete> paquetesXFechaVencimientoXUsuarioOrigen(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.consultarPaqueteXFechaVencimientoXOrigen(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "paquetesXFechaVencimientoXUsuarioDestino")
    public List<Paquete> paquetesXFechaVencimientoXUsuarioDestino(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.consultarPaqueteXFechaVencimientoXDestino(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "consultarBuzonXUsuario")
    public List<Buzon> consultarBuzonXUsuario(@WebParam(name = "registroUsuario") Usuario registroUsuario) {
        List<Buzon> Resultado = null;
        try {
            Resultado = ejbBuzon.ConsultarBuzonXUsuario(registroUsuario);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

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

    @WebMethod(operationName = "ConsultarPaquetesXValija")
    public List<Paquete> ConsultarPaquetesXValija(@WebParam(name = "registroValija") Valija registroValija) {
        List<Paquete> Resultado = null;
        try {
            Resultado = ejbPaquete.ConsultarPaquetesXValija(registroValija);
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }
}
