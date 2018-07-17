/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sipembayaran.Model.DetailPenjualan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sipembayaran.Controller.exceptions.NonexistentEntityException;
import sipembayaran.Controller.exceptions.PreexistingEntityException;
import sipembayaran.Model.Penjualan;

/**
 *
 * @author agungnotfound
 */
public class PenjualanJpaController implements Serializable {

    public PenjualanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Penjualan penjualan) throws PreexistingEntityException, Exception {
        if (penjualan.getDetailPenjualanList() == null) {
            penjualan.setDetailPenjualanList(new ArrayList<DetailPenjualan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetailPenjualan> attachedDetailPenjualanList = new ArrayList<DetailPenjualan>();
            for (DetailPenjualan detailPenjualanListDetailPenjualanToAttach : penjualan.getDetailPenjualanList()) {
                detailPenjualanListDetailPenjualanToAttach = em.getReference(detailPenjualanListDetailPenjualanToAttach.getClass(), detailPenjualanListDetailPenjualanToAttach.getIddetail());
                attachedDetailPenjualanList.add(detailPenjualanListDetailPenjualanToAttach);
            }
            penjualan.setDetailPenjualanList(attachedDetailPenjualanList);
            em.persist(penjualan);
            for (DetailPenjualan detailPenjualanListDetailPenjualan : penjualan.getDetailPenjualanList()) {
                Penjualan oldIdPenjualanOfDetailPenjualanListDetailPenjualan = detailPenjualanListDetailPenjualan.getIdPenjualan();
                detailPenjualanListDetailPenjualan.setIdPenjualan(penjualan);
                detailPenjualanListDetailPenjualan = em.merge(detailPenjualanListDetailPenjualan);
                if (oldIdPenjualanOfDetailPenjualanListDetailPenjualan != null) {
                    oldIdPenjualanOfDetailPenjualanListDetailPenjualan.getDetailPenjualanList().remove(detailPenjualanListDetailPenjualan);
                    oldIdPenjualanOfDetailPenjualanListDetailPenjualan = em.merge(oldIdPenjualanOfDetailPenjualanListDetailPenjualan);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPenjualan(penjualan.getIdPenjualan()) != null) {
                throw new PreexistingEntityException("Penjualan " + penjualan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Penjualan penjualan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Penjualan persistentPenjualan = em.find(Penjualan.class, penjualan.getIdPenjualan());
            List<DetailPenjualan> detailPenjualanListOld = persistentPenjualan.getDetailPenjualanList();
            List<DetailPenjualan> detailPenjualanListNew = penjualan.getDetailPenjualanList();
            List<DetailPenjualan> attachedDetailPenjualanListNew = new ArrayList<DetailPenjualan>();
            for (DetailPenjualan detailPenjualanListNewDetailPenjualanToAttach : detailPenjualanListNew) {
                detailPenjualanListNewDetailPenjualanToAttach = em.getReference(detailPenjualanListNewDetailPenjualanToAttach.getClass(), detailPenjualanListNewDetailPenjualanToAttach.getIddetail());
                attachedDetailPenjualanListNew.add(detailPenjualanListNewDetailPenjualanToAttach);
            }
            detailPenjualanListNew = attachedDetailPenjualanListNew;
            penjualan.setDetailPenjualanList(detailPenjualanListNew);
            penjualan = em.merge(penjualan);
            for (DetailPenjualan detailPenjualanListOldDetailPenjualan : detailPenjualanListOld) {
                if (!detailPenjualanListNew.contains(detailPenjualanListOldDetailPenjualan)) {
                    detailPenjualanListOldDetailPenjualan.setIdPenjualan(null);
                    detailPenjualanListOldDetailPenjualan = em.merge(detailPenjualanListOldDetailPenjualan);
                }
            }
            for (DetailPenjualan detailPenjualanListNewDetailPenjualan : detailPenjualanListNew) {
                if (!detailPenjualanListOld.contains(detailPenjualanListNewDetailPenjualan)) {
                    Penjualan oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan = detailPenjualanListNewDetailPenjualan.getIdPenjualan();
                    detailPenjualanListNewDetailPenjualan.setIdPenjualan(penjualan);
                    detailPenjualanListNewDetailPenjualan = em.merge(detailPenjualanListNewDetailPenjualan);
                    if (oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan != null && !oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan.equals(penjualan)) {
                        oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan.getDetailPenjualanList().remove(detailPenjualanListNewDetailPenjualan);
                        oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan = em.merge(oldIdPenjualanOfDetailPenjualanListNewDetailPenjualan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = penjualan.getIdPenjualan();
                if (findPenjualan(id) == null) {
                    throw new NonexistentEntityException("The penjualan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Penjualan penjualan;
            try {
                penjualan = em.getReference(Penjualan.class, id);
                penjualan.getIdPenjualan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The penjualan with id " + id + " no longer exists.", enfe);
            }
            List<DetailPenjualan> detailPenjualanList = penjualan.getDetailPenjualanList();
            for (DetailPenjualan detailPenjualanListDetailPenjualan : detailPenjualanList) {
                detailPenjualanListDetailPenjualan.setIdPenjualan(null);
                detailPenjualanListDetailPenjualan = em.merge(detailPenjualanListDetailPenjualan);
            }
            em.remove(penjualan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Penjualan> findPenjualanEntities() {
        return findPenjualanEntities(true, -1, -1);
    }

    public List<Penjualan> findPenjualanEntities(int maxResults, int firstResult) {
        return findPenjualanEntities(false, maxResults, firstResult);
    }

    private List<Penjualan> findPenjualanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Penjualan.class));
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

    public Penjualan findPenjualan(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Penjualan.class, id);
        } finally {
            em.close();
        }
    }

    public int getPenjualanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Penjualan> rt = cq.from(Penjualan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
