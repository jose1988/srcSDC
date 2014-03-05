/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Bandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Infobandeja;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete;
import com.seguroshorizonte.sistemadecorrespondecia.entidades.Usuario;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pangea
 */
@Stateless
public class BandejaFacade extends AbstractFacade<Bandeja> {
    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @EJB
    private InfobandejaFacade ejbInfo;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BandejaFacade() {
        super(Bandeja.class);
    }
    
    public void insertarBandeja(Paquete idpaq, Usuario idusuO, Usuario idusuD){
        
       Bandeja ban=new Bandeja();
       Infobandeja info = ejbInfo.find("1");
       ban.setIdpaq(idpaq);
       ban.setIdusu(idusuO);
       ban.setIdiba(info);
       ban.setLeidoban("0");
       
       this.create(ban);
       
       info = ejbInfo.find("3");
       ban.setIdusu(idusuD);
       ban.setIdiba(info);
       
       this.create(ban);
           
        
    }
   
    
    public void actualizacionBandeja(BigDecimal idusuO,BigDecimal idusuD, String idPaq) {

        
        Query q = em.createNativeQuery("UPDATE bandeja SET idban=? WHERE bandeja.idpaq.idpaq=? AND bandeja.idusu.idusu=?");
        q.setParameter(1, "2");
        q.setParameter(2, idPaq);
        q.setParameter(3, idusuO);
        q.executeUpdate();
        
        Query qd = em.createNativeQuery("UPDATE bandeja SET idban=? WHERE bandeja.idpaq.idpaq=? AND bandeja.idusu.idusu=?");
        q.setParameter(1, "4");
        q.setParameter(2, idPaq);
        q.setParameter(3, idusuD);
        q.executeUpdate();

    }
    
   
    
}
