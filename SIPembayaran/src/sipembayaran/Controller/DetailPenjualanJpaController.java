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
import sipembayaran.Model.DetailPenjualan;
import sipembayaran.Model.Penjualan;
import sipembayaran.Model.Menu;

/**
 *
 * @author YAS
 */
public class DetailPenjualanJpaController implements Serializable {

    public DetailPenjualanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetailPenjualan detailPenjualan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Penjualan idPenjualan = detailPenjualan.getIdPenjualan();
            if (idPenjualan != null) {
                idPenjualan = em.getReference(idPenjualan.getClass(), idPenjualan.getIdPenjualan());
                detailPenjualan.setIdPenjualan(idPenjualan);
            }
            Menu idMenu = detailPenjualan.getIdMenu();
            if (idMenu != null) {
                idMenu = em.getReference(idMenu.getClass(), idMenu.getIdMenu());
                detailPenjualan.setIdMenu(idMenu);
            }
            em.persist(detailPenjualan);
            if (idPenjualan != null) {
                idPenjualan.getDetailPenjualanList().add(detailPenjualan);
                idPenjualan = em.merge(idPenjualan);
            }
            if (idMenu != null) {
                idMenu.getDetailPenjualanList().add(detailPenjualan);
                idMenu = em.merge(idMenu);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetailPenjualan detailPenjualan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetailPenjualan persistentDetailPenjualan = em.find(DetailPenjualan.class, detailPenjualan.getIddetail());
            Penjualan idPenjualanOld = persistentDetailPenjualan.getIdPenjualan();
            Penjualan idPenjualanNew = detailPenjualan.getIdPenjualan();
            Menu idMenuOld = persistentDetailPenjualan.getIdMenu();
            Menu idMenuNew = detailPenjualan.getIdMenu();
            if (idPenjualanNew != null) {
                idPenjualanNew = em.getReference(idPenjualanNew.getClass(), idPenjualanNew.getIdPenjualan());
                detailPenjualan.setIdPenjualan(idPenjualanNew);
            }
            if (idMenuNew != null) {
                idMenuNew = em.getReference(idMenuNew.getClass(), idMenuNew.getIdMenu());
                detailPenjualan.setIdMenu(idMenuNew);
            }
            detailPenjualan = em.merge(detailPenjualan);
            if (idPenjualanOld != null && !idPenjualanOld.equals(idPenjualanNew)) {
                idPenjualanOld.getDetailPenjualanList().remove(detailPenjualan);
                idPenjualanOld = em.merge(idPenjualanOld);
            }
            if (idPenjualanNew != null && !idPenjualanNew.equals(idPenjualanOld)) {
                idPenjualanNew.getDetailPenjualanList().add(detailPenjualan);
                idPenjualanNew = em.merge(idPenjualanNew);
            }
            if (idMenuOld != null && !idMenuOld.equals(idMenuNew)) {
                idMenuOld.getDetailPenjualanList().remove(detailPenjualan);
                idMenuOld = em.merge(idMenuOld);
            }
            if (idMenuNew != null && !idMenuNew.equals(idMenuOld)) {
                idMenuNew.getDetailPenjualanList().add(detailPenjualan);
                idMenuNew = em.merge(idMenuNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detailPenjualan.getIddetail();
                if (findDetailPenjualan(id) == null) {
                    throw new NonexistentEntityException("The detailPenjualan with id " + id + " no longer exists.");
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
            DetailPenjualan detailPenjualan;
            try {
                detailPenjualan = em.getReference(DetailPenjualan.class, id);
                detailPenjualan.getIddetail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detailPenjualan with id " + id + " no longer exists.", enfe);
            }
            Penjualan idPenjualan = detailPenjualan.getIdPenjualan();
            if (idPenjualan != null) {
                idPenjualan.getDetailPenjualanList().remove(detailPenjualan);
                idPenjualan = em.merge(idPenjualan);
            }
            Menu idMenu = detailPenjualan.getIdMenu();
            if (idMenu != null) {
                idMenu.getDetailPenjualanList().remove(detailPenjualan);
                idMenu = em.merge(idMenu);
            }
            em.remove(detailPenjualan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetailPenjualan> findDetailPenjualanEntities() {
        return findDetailPenjualanEntities(true, -1, -1);
    }

    public List<DetailPenjualan> findDetailPenjualanEntities(int maxResults, int firstResult) {
        return findDetailPenjualanEntities(false, maxResults, firstResult);
    }

    private List<DetailPenjualan> findDetailPenjualanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetailPenjualan.class));
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

    public DetailPenjualan findDetailPenjualan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetailPenjualan.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailPenjualanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetailPenjualan> rt = cq.from(DetailPenjualan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
