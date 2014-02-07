/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.ws;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Seguimiento;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.PaqueteFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.SeguimientoFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.UsuarioFacade;
import com.seguroshorizonte.sistemadecorrespondecia.sessionfacade.ValijaFacade;
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
    SeguimientoFacade seguimientoFacade;
    @EJB
    PaqueteFacade paqueteFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ValijaFacade valijaFacade;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registroSeguimiento")
    public int registroSeguimiento(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
        Seguimiento nuevoSeg = new Seguimiento();
        try {
            List<Seguimiento> RegistrosSeguimiento = seguimientoFacade.consultarSeguimientoXPaquete(registroPaquete);
            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().compareTo(registroUsuario.getIdrol().getIdrol()) == 0) {
                    return Resultado;
                }
            }
            //Caso  Receptor nivel 1 Origen o Receptor nivel 3 Origen
            if (registroUsuario.getIdrol().getIdrol().toString().compareTo("1") == 1 || registroUsuario.getIdrol().getIdrol().toString().compareTo("3") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("2") == 0) {
                        return Resultado;
                    }
                }
                Resultado = 1;

            } //Caso  Receptor nivel 2 Origen
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("2") == 0) {
                Resultado = 1;
            } //Caso  Empaquetador 
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("4") == 0) {
                boolean nivel2 = false;
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("2") == 0) {
                        nivel2 = true;
                        break;
                    }
                }
                if (nivel2) {
                    Resultado = 1;
                }
            }//Caso  Desenvalijador 
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("5") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0) {
                        registroPaquete = paqueteFacade.ConsultarPaqueteXId(registroPaquete);
                        if (registroPaquete.getIdval().getZoomval() != null) {
                            Resultado = 1;
                        }
                        break;
                    }
                }
            }//Caso  Receptor nivel 1 Destino o Caso  Receptor nivel 3 Destino
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("6") == 0 || registroUsuario.getIdrol().getIdrol().toString().compareTo("7") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().toString().compareTo("7") == 0) {
                        Resultado = 1;
                        break;
                    }
                }

            } //Caso  Receptor nivel 2 Destino
            else if (registroUsuario.getIdrol().getIdrol().toString().compareTo("7") == 0) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("5") == 0) {
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
                nuevoSeg.setStatusseg("En seguimiento");
                seguimientoFacade.insertarSeguimiento(nuevoSeg);
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
            List<Seguimiento> RegistrosSeguimiento = seguimientoFacade.consultarSeguimientoXPaquete(registroPaquete);
            boolean Nivel2 = false;
            for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("7") == 0) {
                    Nivel2 = true;
                    break;
                }
            }
            if (Nivel2) {
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    RegistrosSeguimiento.get(i).setStatusseg("SI");
                    seguimientoFacade.edit(RegistrosSeguimiento.get(i));
                }
            }

        } catch (Exception e) {
        }

        return 0;
    }

    @WebMethod(operationName = "registroReenvioXDesenvajilador")
    public int registroReenvioXDesenvajilador(@WebParam(name = "registroPaquete") Paquete registroPaquete, @WebParam(name = "registroUsuario") Usuario registroUsuario) {
        int Resultado = 0;
        Seguimiento nuevoSeg = new Seguimiento();
        try {
            if (registroUsuario.getIdrol().getIdrol().toString().compareTo("5") == 0) {
                List<Seguimiento> RegistrosSeguimiento = seguimientoFacade.consultarSeguimientoXPaquete(registroPaquete);
                for (int i = 0; i < RegistrosSeguimiento.size(); i++) {
                    if (RegistrosSeguimiento.get(i).getIdusu().getIdrol().getIdrol().toString().compareTo("4") == 0) {
                        registroPaquete = paqueteFacade.ConsultarPaqueteXId(registroPaquete);
                        if (registroPaquete.getIdval().getZoomval() != null) {
                            nuevoSeg = new Seguimiento();
                            nuevoSeg.setFechaseg(new Date());
                            nuevoSeg.setIdpaq(registroPaquete);
                            nuevoSeg.setIdusu(registroUsuario);
                            nuevoSeg.setStatusseg("Reenvio");
                            seguimientoFacade.insertarSeguimiento(nuevoSeg);
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
            usuarioFacade.editarUsuario(registroUsuario);
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
            registroPaquete = paqueteFacade.ConsultarPaqueteXId(registroPaquete);
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

    @WebMethod(operationName = "paquetesXFechaAlerta")
    public List<Paquete> paquetesXFechaAlerta() {
        List<Paquete> Resultado = null;
        try {
            Resultado = paqueteFacade.consultarPaqueteXFechaAlerta();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }

    @WebMethod(operationName = "paquetesXFechaVencimiento")
    public List<Paquete> paquetesXFechaVencimiento() {
        List<Paquete> Resultado = null;
        try {
            Resultado = paqueteFacade.consultarPaqueteXFechaVencimiento();
        } catch (Exception e) {
            return null;
        }
        return Resultado;
    }
}
