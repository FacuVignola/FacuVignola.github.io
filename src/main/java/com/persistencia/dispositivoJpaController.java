/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.logica.cliente;
import com.logica.dispositivo;
import com.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usr
 */
public class dispositivoJpaController implements Serializable {
    
    public dispositivoJpaController(){
        this.emf=Persistence.createEntityManagerFactory("proyectoPU");
    }

    public dispositivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(dispositivo dispositivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente dueño = dispositivo.getDueño();
            if (dueño != null) {
                dueño = em.getReference(dueño.getClass(), dueño.getId());
                dispositivo.setDueño(dueño);
            }
            em.persist(dispositivo);
            if (dueño != null) {
                dueño.getListaDispositivos().add(dispositivo);
                dueño = em.merge(dueño);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(dispositivo dispositivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dispositivo persistentdispositivo = em.find(dispositivo.class, dispositivo.getId());
            cliente dueñoOld = persistentdispositivo.getDueño();
            cliente dueñoNew = dispositivo.getDueño();
            if (dueñoNew != null) {
                dueñoNew = em.getReference(dueñoNew.getClass(), dueñoNew.getId());
                dispositivo.setDueño(dueñoNew);
            }
            dispositivo = em.merge(dispositivo);
            if (dueñoOld != null && !dueñoOld.equals(dueñoNew)) {
                dueñoOld.getListaDispositivos().remove(dispositivo);
                dueñoOld = em.merge(dueñoOld);
            }
            if (dueñoNew != null && !dueñoNew.equals(dueñoOld)) {
                dueñoNew.getListaDispositivos().add(dispositivo);
                dueñoNew = em.merge(dueñoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dispositivo.getId();
                if (finddispositivo(id) == null) {
                    throw new NonexistentEntityException("The dispositivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dispositivo dispositivo;
            try {
                dispositivo = em.getReference(dispositivo.class, id);
                dispositivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dispositivo with id " + id + " no longer exists.", enfe);
            }
            cliente dueño = dispositivo.getDueño();
            if (dueño != null) {
                dueño.getListaDispositivos().remove(dispositivo);
                dueño = em.merge(dueño);
            }
            em.remove(dispositivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<dispositivo> finddispositivoEntities() {
        return finddispositivoEntities(true, -1, -1);
    }

    public List<dispositivo> finddispositivoEntities(int maxResults, int firstResult) {
        return finddispositivoEntities(false, maxResults, firstResult);
    }

    private List<dispositivo> finddispositivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(dispositivo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public dispositivo finddispositivo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(dispositivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getdispositivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<dispositivo> rt = cq.from(dispositivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
