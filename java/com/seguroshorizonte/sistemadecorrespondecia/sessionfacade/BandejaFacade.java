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
   
    
    public void actualizacionBandeja(Usuario idusuO,Usuario idusuD, Paquete idPaq) {

       
           BigDecimal ips=new BigDecimal("2");
        Infobandeja find = ejbInfo.find(ips);
        Query q = em.createNativeQuery("UPDATE bandeja SET idiba=? WHERE idpaq=? AND idusu=?");
        q.setParameter(1, find.getIdiba());
        q.setParameter(2, idPaq.getIdpaq());
        q.setParameter(3, idusuO.getIdusu());
        q.executeUpdate();
        BigDecimal ip=new BigDecimal("4");
        Infobandeja fin = ejbInfo.find(ip);
        Query qd = em.createNativeQuery("UPDATE bandeja SET idiba=? WHERE idpaq=? AND idusu=?");
        qd.setParameter(1, fin.getIdiba());
        qd.setParameter(2, idPaq.getIdpaq());
        qd.setParameter(3, idusuD.getIdusu());
        qd.executeUpdate();

    }
    
   
    
}
