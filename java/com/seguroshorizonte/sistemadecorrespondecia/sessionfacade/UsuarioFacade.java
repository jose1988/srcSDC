/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import java.io.UnsupportedEncodingException;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario consultarUsuarioXUser(String user) {
        Usuario Registro = new Usuario();
        try {
            Registro = (Usuario) (em.createNamedQuery("Usuario.findByUserusu").setParameter("userusu", user).getSingleResult());
        } catch (Exception e) {
            return Registro;
        }
        return Registro;
    }

    public List<Usuario> listarUsuarios(String status) {
        List<Usuario> lista;
        lista = em.createNamedQuery("Usuario.findByStatususu").setParameter("statususu", status).getResultList();
        return lista;
    }

    public List<Usuario> listar() {
        List<Usuario> c = null;
        c = (List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();
        return c;
    }

    public void insertar(Usuario registro) {
        this.create(registro);
    }

    public void editar(Usuario registro) {
        this.edit(registro);
    }

    public void deshabilitar(String ID) {
        Query q = em.createNativeQuery("UPDATE Usuario SET statususu='0' WHERE id=?");
        q.setParameter(1, ID);
        q.executeUpdate();
    }

    public void habilitar(String ID) {
        Query q = em.createNativeQuery("UPDATE Usuario SET statususu='1' WHERE id=?");
        q.setParameter(1, ID);
        q.executeUpdate();
    }

    public Usuario consultarUsuario(String idUsuario) {
        Usuario Registro;
        BigDecimal id = new BigDecimal(idUsuario);
        Registro = this.find(id);
        return Registro;
    }

    public List<Usuario> consultarUsuariosXSede(String sede) {
        List<Usuario> c = null;
        c = (List<Usuario>) em.createNamedQuery("Usuario.findByUsuxSede").setParameter("sede", sede).getResultList();
        return c;
    }

    public void editarUsuario(Usuario Registro) {
        Query q = em.createNativeQuery("UPDATE USUARIO "
                + "SET APELLIDOUSU=?,CORREOUSU=?,DIRECCIONUSU=?,NOMBREUSU=?,TELEFONOUSU=?,TELEFONO2USU=? WHERE IDUSU=?");
        q.setParameter(1, Registro.getApellidousu());
        q.setParameter(2, Registro.getCorreousu());
        q.setParameter(3, Registro.getDireccionusu());
        q.setParameter(4, Registro.getNombreusu());
        q.setParameter(5, Registro.getTelefono2usu());
        q.setParameter(6, Registro.getTelefonousu());
        q.setParameter(7, Registro.getIdusu());
        q.executeUpdate();
    }

    public BigDecimal consultarMAXId(String userUsu) {
        BigDecimal Id = (BigDecimal) em.createNamedQuery("Usuario.findMaxIdXuserUsu").setParameter("userusu", userUsu).getSingleResult();
        return Id;
    }

    public String auntenticarLDAPauntenticarLDAP(String user, String password) {
        int ldapPort;
        int ldapVersion;
        String base = "@seguroshorizonte.com";
        String ldapHost = "172.19.4.6";
        String dn = "";
        String[] ATTRS = {"mail", "sAMAccountName"};
        LDAPConnection conn;
        String[] values;
        String[] vect = new String[2];
        boolean find = false;

        try {
            ldapVersion = LDAPConnection.LDAP_V3;
            //Puerto por Defecto 389
            ldapPort = LDAPConnection.DEFAULT_PORT;
            //ldapPort = LDAPConnection.DEFAULT_SSL_PORT; //Puerto SSL 636
            conn = new LDAPConnection();
            dn = user + base;
            conn.connect(ldapHost, ldapPort);
            conn.bind(ldapVersion, dn, password.getBytes("UTF8"));

            if (conn.isBound()) {
                return "ACEPT";

            } else {
                return "FAIL";
            }

        } //catch(LDAPException ex){
        catch (UnsupportedEncodingException ex) {
            return "FAIL";
        } catch (LDAPException ex) {
            if (ex.getLDAPErrorMessage().split(",")[2].trim().split(" ")[1].trim().compareTo("773") == 0) {
                return "PASS";
            }
            return "FAIL";
        } finally {
            //conn.disconnect();
        }
    }
}