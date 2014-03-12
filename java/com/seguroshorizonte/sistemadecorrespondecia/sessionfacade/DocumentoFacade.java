/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.sessionfacade;

import com.seguroshorizonte.sistemadecorrespondecia.entidades.Documento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pangea
 */
@Stateless
public class DocumentoFacade extends AbstractFacade<Documento> {

    @PersistenceContext(unitName = "SistemaDeCorrespondeciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoFacade() {
        super(Documento.class);
    }

    public List<Documento> listarDocumentos() {
        List<Documento> lista;
        lista = (List<Documento>) em.createNamedQuery("Documento.findAll").getResultList();
        return lista;
    }

    public void insertar(Documento registro) {
        this.create(registro);
    }

    public void editar(Documento registro) {
        this.edit(registro);
    }

    public void eliminar(String ID) {
        Documento doc = this.find(ID);
        this.remove(doc);
    }

    public Documento consultarDocumento(String idDocumento) {
        Documento Registro;
        Registro = this.find(idDocumento);
        return Registro;
    }

    public Documento consultarDocumentoXNombre(String doc) {
        Documento Registro = new Documento();
        try {
            Registro = (Documento) (em.createNamedQuery("Documento.findByNombredoc").setParameter("nombredoc", doc).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return Registro;
    }
}