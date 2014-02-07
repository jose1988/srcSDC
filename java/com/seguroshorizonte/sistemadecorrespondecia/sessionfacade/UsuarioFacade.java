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
        try{
          Registro = (Usuario) (em.createNamedQuery("Usuario.findByUserusu").setParameter("userusu", user).getSingleResult());
        }catch(Exception e){
            
           
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
     
     
}
