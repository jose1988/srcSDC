/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Sede;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuariosede;
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
        BigDecimal id=new BigDecimal(idUsuario);
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
                + "SET APELLIDOUSU=?,CORREOUSU=?,DIRECCIONUSU=?,DIRECCION2USU=?,NOMBREUSU=?,TELEFONOUSU=?,TELEFONO2USU=? WHERE IDUSU=?");
        q.setParameter(1, Registro.getApellidousu());
        q.setParameter(2, Registro.getCorreousu());
        q.setParameter(3, Registro.getDireccion2usu());
        q.setParameter(4, Registro.getDireccionusu());
        q.setParameter(5, Registro.getNombreusu());
        q.setParameter(6, Registro.getTelefono2usu());
        q.setParameter(7, Registro.getTelefonousu());
        q.setParameter(8, Registro.getIdusu());
        q.executeUpdate();
    }
 
  public BigDecimal consultarMAXId() {
        BigDecimal Id = (BigDecimal) em.createNamedQuery("Usuario.findMaxId").getSingleResult();
        return Id;
    }
  
  

}
