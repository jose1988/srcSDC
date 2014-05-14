/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Rol;
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

    public Usuariosede sedeRolXId(String idu, Sede sede) {
        BigDecimal idusu = new BigDecimal(idu);
        Usuariosede sedeId = (Usuariosede) em.createNamedQuery("Usuariosede.findByIdusu").setParameter("idusu", idusu).setParameter("idsed", sede.getIdsed()).getSingleResult();
        return sedeId;
    }

    public List<Usuariosede> listaUsuarios(Sede idSede) {
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

    public Usuariosede ConsultarXId(BigDecimal idUsuSede) {
        Usuariosede sedeId = (Usuariosede) em.createNamedQuery("Usuariosede.findByIduse").setParameter("iduse", idUsuSede).getSingleResult();
        return sedeId;
    }

    public List<Usuario> ConsultarUsuariosXSede(Sede sede) {
        List<Usuario> usuario;
        usuario = (List<Usuario>) em.createNamedQuery("Usuariosede.findUsuarioBySede").setParameter("idsed", sede).getResultList();
        return usuario;
    }

    public List<Sede> ConsultarSedeDeUsuario(Usuario Idusuario) {
        List<Sede> result;
        result = em.createNamedQuery("Usuariosede.findSedeByUsuario").setParameter("idusu", Idusuario).getResultList();
        return result;
    }

    public void insertarUsuarioSede(Usuariosede registroUsuSede) {
        Query q = em.createNativeQuery("INSERT INTO USUARIO_SEDE (IDUSE, IDUSU, IDSED, IDROL, IDATR) VALUES (SEQ_USUARIOSEDE.nextVal, ?, ?, ?,?)");
        q.setParameter(1, registroUsuSede.getIdusu().getIdusu());
        q.setParameter(2, registroUsuSede.getIdsed().getIdsed());
        q.setParameter(3, registroUsuSede.getIdrol().getIdrol());
        q.setParameter(4, registroUsuSede.getIdatr().getIdatr());
        q.executeUpdate();
    }

    public void editarRol(BigDecimal idusu, BigDecimal rol, BigDecimal idsede) {
        Query q = em.createNativeQuery("UPDATE Usuario_sede SET idrol=? WHERE idusu=? AND idsed=?");
        q.setParameter(1, rol);
        q.setParameter(2, idusu);
        q.setParameter(3, idsede);
        q.executeUpdate();
    }

    public void asignarSede(Usuario idusu, Rol rol, Sede sede) {
        Usuariosede Usede = new Usuariosede();
        Usede.setIdrol(rol);
        Usede.setIdsed(sede);
        Usede.setIdusu(idusu);
        this.create(Usede);

    }
}