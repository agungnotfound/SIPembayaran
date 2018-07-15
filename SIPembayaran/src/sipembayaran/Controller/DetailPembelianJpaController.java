/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sipembayaran.Controller.exceptions.NonexistentEntityException;
import sipembayaran.Model.DetailPembelian;
import sipembayaran.Model.Pembelian;

/**
 *
 * @author YAS
 */
public class DetailPembelianJpaController implements Serializable {

    public DetailPembelianJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetailPembelian detailPembelian) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pembelian idPembelian = detailPembelian.getIdPembelian();
            if (idPembelian != null) {
                idPembelian = em.getReference(idPembelian.getClass(), idPembelian.getIdPembelian());
                detailPembelian.setIdPembelian(idPembelian);
            }
            em.persist(detailPembelian);
            if (idPembelian != null) {
                idPembelian.getDetailPembelianList().add(detailPembelian);
                idPembelian = em.merge(idPembelian);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetailPembelian detailPembelian) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetailPembelian persistentDetailPembelian = em.find(DetailPembelian.class, detailPembelian.getIddetail());
            Pembelian idPembelianOld = persistentDetailPembelian.getIdPembelian();
            Pembelian idPembelianNew = detailPembelian.getIdPembelian();
            if (idPembelianNew != null) {
                idPembelianNew = em.getReference(idPembelianNew.getClass(), idPembelianNew.getIdPembelian());
                detailPembelian.setIdPembelian(idPembelianNew);
            }
            detailPembelian = em.merge(detailPembelian);
            if (idPembelianOld != null && !idPembelianOld.equals(idPembelianNew)) {
                idPembelianOld.getDetailPembelianList().remove(detailPembelian);
                idPembelianOld = em.merge(idPembelianOld);
            }
            if (idPembelianNew != null && !idPembelianNew.equals(idPembelianOld)) {
                idPembelianNew.getDetailPembelianList().add(detailPembelian);
                idPembelianNew = em.merge(idPembelianNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detailPembelian.getIddetail();
                if (findDetailPembelian(id) == null) {
                    throw new NonexistentEntityException("The detailPembelian with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetailPembelian detailPembelian;
            try {
                detailPembelian = em.getReference(DetailPembelian.class, id);
                detailPembelian.getIddetail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detailPembelian with id " + id + " no longer exists.", enfe);
            }
            Pembelian idPembelian = detailPembelian.getIdPembelian();
            if (idPembelian != null) {
                idPembelian.getDetailPembelianList().remove(detailPembelian);
                idPembelian = em.merge(idPembelian);
            }
            em.remove(detailPembelian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetailPembelian> findDetailPembelianEntities() {
        return findDetailPembelianEntities(true, -1, -1);
    }

    public List<DetailPembelian> findDetailPembelianEntities(int maxResults, int firstResult) {
        return findDetailPembelianEntities(false, maxResults, firstResult);
    }

    private List<DetailPembelian> findDetailPembelianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetailPembelian.class));
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

    public DetailPembelian findDetailPembelian(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetailPembelian.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailPembelianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetailPembelian> rt = cq.from(DetailPembelian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
