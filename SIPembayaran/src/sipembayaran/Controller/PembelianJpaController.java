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
import sipembayaran.Model.DetailPembelian;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sipembayaran.Controller.exceptions.NonexistentEntityException;
import sipembayaran.Controller.exceptions.PreexistingEntityException;
import sipembayaran.Model.Pembelian;

/**
 *
 * @author YAS
 */
public class PembelianJpaController implements Serializable {

    public PembelianJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pembelian pembelian) throws PreexistingEntityException, Exception {
        if (pembelian.getDetailPembelianList() == null) {
            pembelian.setDetailPembelianList(new ArrayList<DetailPembelian>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetailPembelian> attachedDetailPembelianList = new ArrayList<DetailPembelian>();
            for (DetailPembelian detailPembelianListDetailPembelianToAttach : pembelian.getDetailPembelianList()) {
                detailPembelianListDetailPembelianToAttach = em.getReference(detailPembelianListDetailPembelianToAttach.getClass(), detailPembelianListDetailPembelianToAttach.getIddetail());
                attachedDetailPembelianList.add(detailPembelianListDetailPembelianToAttach);
            }
            pembelian.setDetailPembelianList(attachedDetailPembelianList);
            em.persist(pembelian);
            for (DetailPembelian detailPembelianListDetailPembelian : pembelian.getDetailPembelianList()) {
                Pembelian oldIdPembelianOfDetailPembelianListDetailPembelian = detailPembelianListDetailPembelian.getIdPembelian();
                detailPembelianListDetailPembelian.setIdPembelian(pembelian);
                detailPembelianListDetailPembelian = em.merge(detailPembelianListDetailPembelian);
                if (oldIdPembelianOfDetailPembelianListDetailPembelian != null) {
                    oldIdPembelianOfDetailPembelianListDetailPembelian.getDetailPembelianList().remove(detailPembelianListDetailPembelian);
                    oldIdPembelianOfDetailPembelianListDetailPembelian = em.merge(oldIdPembelianOfDetailPembelianListDetailPembelian);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPembelian(pembelian.getIdPembelian()) != null) {
                throw new PreexistingEntityException("Pembelian " + pembelian + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pembelian pembelian) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pembelian persistentPembelian = em.find(Pembelian.class, pembelian.getIdPembelian());
            List<DetailPembelian> detailPembelianListOld = persistentPembelian.getDetailPembelianList();
            List<DetailPembelian> detailPembelianListNew = pembelian.getDetailPembelianList();
            List<DetailPembelian> attachedDetailPembelianListNew = new ArrayList<DetailPembelian>();
            for (DetailPembelian detailPembelianListNewDetailPembelianToAttach : detailPembelianListNew) {
                detailPembelianListNewDetailPembelianToAttach = em.getReference(detailPembelianListNewDetailPembelianToAttach.getClass(), detailPembelianListNewDetailPembelianToAttach.getIddetail());
                attachedDetailPembelianListNew.add(detailPembelianListNewDetailPembelianToAttach);
            }
            detailPembelianListNew = attachedDetailPembelianListNew;
            pembelian.setDetailPembelianList(detailPembelianListNew);
            pembelian = em.merge(pembelian);
            for (DetailPembelian detailPembelianListOldDetailPembelian : detailPembelianListOld) {
                if (!detailPembelianListNew.contains(detailPembelianListOldDetailPembelian)) {
                    detailPembelianListOldDetailPembelian.setIdPembelian(null);
                    detailPembelianListOldDetailPembelian = em.merge(detailPembelianListOldDetailPembelian);
                }
            }
            for (DetailPembelian detailPembelianListNewDetailPembelian : detailPembelianListNew) {
                if (!detailPembelianListOld.contains(detailPembelianListNewDetailPembelian)) {
                    Pembelian oldIdPembelianOfDetailPembelianListNewDetailPembelian = detailPembelianListNewDetailPembelian.getIdPembelian();
                    detailPembelianListNewDetailPembelian.setIdPembelian(pembelian);
                    detailPembelianListNewDetailPembelian = em.merge(detailPembelianListNewDetailPembelian);
                    if (oldIdPembelianOfDetailPembelianListNewDetailPembelian != null && !oldIdPembelianOfDetailPembelianListNewDetailPembelian.equals(pembelian)) {
                        oldIdPembelianOfDetailPembelianListNewDetailPembelian.getDetailPembelianList().remove(detailPembelianListNewDetailPembelian);
                        oldIdPembelianOfDetailPembelianListNewDetailPembelian = em.merge(oldIdPembelianOfDetailPembelianListNewDetailPembelian);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pembelian.getIdPembelian();
                if (findPembelian(id) == null) {
                    throw new NonexistentEntityException("The pembelian with id " + id + " no longer exists.");
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
            Pembelian pembelian;
            try {
                pembelian = em.getReference(Pembelian.class, id);
                pembelian.getIdPembelian();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pembelian with id " + id + " no longer exists.", enfe);
            }
            List<DetailPembelian> detailPembelianList = pembelian.getDetailPembelianList();
            for (DetailPembelian detailPembelianListDetailPembelian : detailPembelianList) {
                detailPembelianListDetailPembelian.setIdPembelian(null);
                detailPembelianListDetailPembelian = em.merge(detailPembelianListDetailPembelian);
            }
            em.remove(pembelian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pembelian> findPembelianEntities() {
        return findPembelianEntities(true, -1, -1);
    }

    public List<Pembelian> findPembelianEntities(int maxResults, int firstResult) {
        return findPembelianEntities(false, maxResults, firstResult);
    }

    private List<Pembelian> findPembelianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pembelian.class));
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

    public Pembelian findPembelian(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pembelian.class, id);
        } finally {
            em.close();
        }
    }

    public int getPembelianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pembelian> rt = cq.from(Pembelian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
