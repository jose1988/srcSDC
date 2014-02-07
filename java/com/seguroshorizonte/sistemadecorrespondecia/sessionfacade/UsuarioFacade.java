/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
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
        Registro = this.find(idUsuario);
        return Registro;
    }

    public void editarUsuario(Usuario Registro) {
        Query q = em.createNativeQuery("UPDATE usuario "
                + "SET borrado=?,cedula=?,clave=?,descripcion=?,direccion_personal=?,direccion_oficina=?,estado=?,fax=?,id_clasificacion_usuario=?,id_organizacion=?,id_skin=?,mail=?,primer_apellido=?,primer_nombre=?,rif=?,segundo_apellido=?,segundo_nombre=?,telefonos_oficina=?,telefonos_personal=? "
                + "WHERE id=?");
//        q.setParameter(1, Registro.getBorrado());
//        q.setParameter(2, Registro.getCedula());
//        q.setParameter(3, Registro.getClave());
//        q.setParameter(4, Registro.getDescripcion());
//        q.setParameter(5, Registro.getDireccionOficina());
//        q.setParameter(6, Registro.getDireccionPersonal());
//        q.setParameter(7, Registro.getEstado());
//        q.setParameter(8, Registro.getFax());
//        q.setParameter(9, Registro.getIdClasificacionUsuario().getId());
//        q.setParameter(10, Registro.getIdOrganizacion().getId());
//        q.setParameter(11, Registro.getIdSkin().getId());
//        q.setParameter(12, Registro.getMail());
//        q.setParameter(13, Registro.getPrimerApellido());
//        q.setParameter(14, Registro.getPrimerNombre());
//        q.setParameter(15, Registro.getRif());
//        q.setParameter(16, Registro.getSegundoApellido());
//        q.setParameter(17, Registro.getSegundoNombre());
//        q.setParameter(18, Registro.getTelefonosOficina());
//        q.setParameter(19, Registro.getTelefonosPersonal());
//        q.setParameter(20, Registro.getId());
        q.executeUpdate();
    }
}
