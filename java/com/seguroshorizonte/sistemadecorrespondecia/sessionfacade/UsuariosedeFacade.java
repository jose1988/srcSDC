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
public class UsuariosedeFacade extends AbstractFacade<Usuariosede> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosedeFacade() {
        super(Usuariosede.class);
    }
 
   
     public Usuariosede sedeRolXId(String idu, Sede sede){
        
       BigDecimal idusu=new BigDecimal(idu);
       
       Usuariosede sedeId=(Usuariosede) em.createNamedQuery("Usuariosede.findByIdusu").setParameter("idusu", idusu).setParameter("idsed", sede.getIdsed()).getSingleResult();
       
       return sedeId;
    }
     public List<Usuariosede> listaUsuarios(Sede idSede){
        
        List<Usuariosede> Resultado = null;
        
        Query consulta = em.createNamedQuery("Usuariosede.findByIdsed").setParameter("idsed", idSede);
        Resultado = consulta.getResultList();
        return Resultado;
    }
     
         public Usuariosede sedeXId(BigDecimal sede) {

        Usuariosede sedeId = (Usuariosede) em.createNamedQuery("Usuariosede.findByIdsed").setParameter("Idsed", sede).getSingleResult();

        return sedeId;
    }

    public Usuariosede ConsultarXUsuarioYSede(Usuario usuario, Sede sede) {

        Usuariosede sedeId = (Usuariosede) em.createNamedQuery("Usuariosede.findByUsuarioYSede").setParameter("idusu", usuario).setParameter("idsed", sede).getSingleResult();

        return sedeId;
    }

    public List<Usuario> ConsultarXRolYSede(Sede sede) {
        List<Usuario> usuario;
        usuario = (List<Usuario>) em.createNamedQuery("Usuariosede.findByRolYSedeOrigen").setParameter("idsed", sede).getResultList();

        return usuario;
    }

    public Sede ConsultarSedeDeUsuario(Usuario Idusuario) {
        Sede usuario;
        usuario = (Sede) em.createNamedQuery("Usuariosede.findSedeByUsuario").setParameter("idusu", Idusuario).getSingleResult();

        return usuario;
    }

    public void insertarUsuarioSede(Usuariosede registroUsuSede) {
        Query q = em.createNativeQuery("INSERT INTO USUARIOSEDE (IDUSE, IDUSU, IDSED, IDROL) VALUES (SEQ_USUARIOSEDE.nextVal, ?, ?, ?)");
        q.setParameter(1, registroUsuSede.getIdusu().getIdusu());
        q.setParameter(2, registroUsuSede.getIdsed().getIdsed());
        q.setParameter(3, registroUsuSede.getIdrol().getIdrol());

        q.executeUpdate();
    }
    

}
