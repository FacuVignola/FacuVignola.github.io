/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.logica.cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.logica.dispositivo;
import com.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tacho
 */
public class clienteJpaController implements Serializable {

    public clienteJpaController() {
        emf=Persistence.createEntityManagerFactory("proyectoPU");
    }

    public clienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(cliente cliente) {
        if (cliente.getListaDispositivos() == null) {
            cliente.setListaDispositivos(new ArrayList<dispositivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<dispositivo> attachedListaDispositivos = new ArrayList<dispositivo>();
            for (dispositivo listaDispositivosdispositivoToAttach : cliente.getListaDispositivos()) {
                listaDispositivosdispositivoToAttach = em.getReference(listaDispositivosdispositivoToAttach.getClass(), listaDispositivosdispositivoToAttach.getId());
                attachedListaDispositivos.add(listaDispositivosdispositivoToAttach);
            }
            cliente.setListaDispositivos(attachedListaDispositivos);
            em.persist(cliente);
            for (dispositivo listaDispositivosdispositivo : cliente.getListaDispositivos()) {
                cliente oldDuenoOfListaDispositivosdispositivo = listaDispositivosdispositivo.getDueno();
                listaDispositivosdispositivo.setDueno(cliente);
                listaDispositivosdispositivo = em.merge(listaDispositivosdispositivo);
                if (oldDuenoOfListaDispositivosdispositivo != null) {
                    oldDuenoOfListaDispositivosdispositivo.getListaDispositivos().remove(listaDispositivosdispositivo);
                    oldDuenoOfListaDispositivosdispositivo = em.merge(oldDuenoOfListaDispositivosdispositivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente persistentcliente = em.find(cliente.class, cliente.getId());
            List<dispositivo> listaDispositivosOld = persistentcliente.getListaDispositivos();
            List<dispositivo> listaDispositivosNew = cliente.getListaDispositivos();
            List<dispositivo> attachedListaDispositivosNew = new ArrayList<dispositivo>();
            for (dispositivo listaDispositivosNewdispositivoToAttach : listaDispositivosNew) {
                listaDispositivosNewdispositivoToAttach = em.getReference(listaDispositivosNewdispositivoToAttach.getClass(), listaDispositivosNewdispositivoToAttach.getId());
                attachedListaDispositivosNew.add(listaDispositivosNewdispositivoToAttach);
            }
            listaDispositivosNew = attachedListaDispositivosNew;
            cliente.setListaDispositivos(listaDispositivosNew);
            cliente = em.merge(cliente);
            for (dispositivo listaDispositivosOlddispositivo : listaDispositivosOld) {
                if (!listaDispositivosNew.contains(listaDispositivosOlddispositivo)) {
                    listaDispositivosOlddispositivo.setDueno(null);
                    listaDispositivosOlddispositivo = em.merge(listaDispositivosOlddispositivo);
                }
            }
            for (dispositivo listaDispositivosNewdispositivo : listaDispositivosNew) {
                if (!listaDispositivosOld.contains(listaDispositivosNewdispositivo)) {
                    cliente oldDuenoOfListaDispositivosNewdispositivo = listaDispositivosNewdispositivo.getDueno();
                    listaDispositivosNewdispositivo.setDueno(cliente);
                    listaDispositivosNewdispositivo = em.merge(listaDispositivosNewdispositivo);
                    if (oldDuenoOfListaDispositivosNewdispositivo != null && !oldDuenoOfListaDispositivosNewdispositivo.equals(cliente)) {
                        oldDuenoOfListaDispositivosNewdispositivo.getListaDispositivos().remove(listaDispositivosNewdispositivo);
                        oldDuenoOfListaDispositivosNewdispositivo = em.merge(oldDuenoOfListaDispositivosNewdispositivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId();
                if (findcliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            cliente cliente;
            try {
                cliente = em.getReference(cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<dispositivo> listaDispositivos = cliente.getListaDispositivos();
            for (dispositivo listaDispositivosdispositivo : listaDispositivos) {
                listaDispositivosdispositivo.setDueno(null);
                listaDispositivosdispositivo = em.merge(listaDispositivosdispositivo);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<cliente> findclienteEntities() {
        return findclienteEntities(true, -1, -1);
    }

    public List<cliente> findclienteEntities(int maxResults, int firstResult) {
        return findclienteEntities(false, maxResults, firstResult);
    }

    private List<cliente> findclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cliente.class));
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

    public cliente findcliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<cliente> rt = cq.from(cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
