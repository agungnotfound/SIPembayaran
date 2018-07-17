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
import sipembayaran.Model.Menu;

/**
 *
 * @author agungnotfound
 */
public class MenuJpaController implements Serializable {

    public MenuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Menu menu) throws PreexistingEntityException, Exception {
        if (menu.getDetailPenjualanList() == null) {
            menu.setDetailPenjualanList(new ArrayList<DetailPenjualan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetailPenjualan> attachedDetailPenjualanList = new ArrayList<DetailPenjualan>();
            for (DetailPenjualan detailPenjualanListDetailPenjualanToAttach : menu.getDetailPenjualanList()) {
                detailPenjualanListDetailPenjualanToAttach = em.getReference(detailPenjualanListDetailPenjualanToAttach.getClass(), detailPenjualanListDetailPenjualanToAttach.getIddetail());
                attachedDetailPenjualanList.add(detailPenjualanListDetailPenjualanToAttach);
            }
            menu.setDetailPenjualanList(attachedDetailPenjualanList);
            em.persist(menu);
            for (DetailPenjualan detailPenjualanListDetailPenjualan : menu.getDetailPenjualanList()) {
                Menu oldIdMenuOfDetailPenjualanListDetailPenjualan = detailPenjualanListDetailPenjualan.getIdMenu();
                detailPenjualanListDetailPenjualan.setIdMenu(menu);
                detailPenjualanListDetailPenjualan = em.merge(detailPenjualanListDetailPenjualan);
                if (oldIdMenuOfDetailPenjualanListDetailPenjualan != null) {
                    oldIdMenuOfDetailPenjualanListDetailPenjualan.getDetailPenjualanList().remove(detailPenjualanListDetailPenjualan);
                    oldIdMenuOfDetailPenjualanListDetailPenjualan = em.merge(oldIdMenuOfDetailPenjualanListDetailPenjualan);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMenu(menu.getIdMenu()) != null) {
                throw new PreexistingEntityException("Menu " + menu + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Menu menu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menu persistentMenu = em.find(Menu.class, menu.getIdMenu());
            List<DetailPenjualan> detailPenjualanListOld = persistentMenu.getDetailPenjualanList();
            List<DetailPenjualan> detailPenjualanListNew = menu.getDetailPenjualanList();
            List<DetailPenjualan> attachedDetailPenjualanListNew = new ArrayList<DetailPenjualan>();
            for (DetailPenjualan detailPenjualanListNewDetailPenjualanToAttach : detailPenjualanListNew) {
                detailPenjualanListNewDetailPenjualanToAttach = em.getReference(detailPenjualanListNewDetailPenjualanToAttach.getClass(), detailPenjualanListNewDetailPenjualanToAttach.getIddetail());
                attachedDetailPenjualanListNew.add(detailPenjualanListNewDetailPenjualanToAttach);
            }
            detailPenjualanListNew = attachedDetailPenjualanListNew;
            menu.setDetailPenjualanList(detailPenjualanListNew);
            menu = em.merge(menu);
            for (DetailPenjualan detailPenjualanListOldDetailPenjualan : detailPenjualanListOld) {
                if (!detailPenjualanListNew.contains(detailPenjualanListOldDetailPenjualan)) {
                    detailPenjualanListOldDetailPenjualan.setIdMenu(null);
                    detailPenjualanListOldDetailPenjualan = em.merge(detailPenjualanListOldDetailPenjualan);
                }
            }
            for (DetailPenjualan detailPenjualanListNewDetailPenjualan : detailPenjualanListNew) {
                if (!detailPenjualanListOld.contains(detailPenjualanListNewDetailPenjualan)) {
                    Menu oldIdMenuOfDetailPenjualanListNewDetailPenjualan = detailPenjualanListNewDetailPenjualan.getIdMenu();
                    detailPenjualanListNewDetailPenjualan.setIdMenu(menu);
                    detailPenjualanListNewDetailPenjualan = em.merge(detailPenjualanListNewDetailPenjualan);
                    if (oldIdMenuOfDetailPenjualanListNewDetailPenjualan != null && !oldIdMenuOfDetailPenjualanListNewDetailPenjualan.equals(menu)) {
                        oldIdMenuOfDetailPenjualanListNewDetailPenjualan.getDetailPenjualanList().remove(detailPenjualanListNewDetailPenjualan);
                        oldIdMenuOfDetailPenjualanListNewDetailPenjualan = em.merge(oldIdMenuOfDetailPenjualanListNewDetailPenjualan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = menu.getIdMenu();
                if (findMenu(id) == null) {
                    throw new NonexistentEntityException("The menu with id " + id + " no longer exists.");
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
            Menu menu;
            try {
                menu = em.getReference(Menu.class, id);
                menu.getIdMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menu with id " + id + " no longer exists.", enfe);
            }
            List<DetailPenjualan> detailPenjualanList = menu.getDetailPenjualanList();
            for (DetailPenjualan detailPenjualanListDetailPenjualan : detailPenjualanList) {
                detailPenjualanListDetailPenjualan.setIdMenu(null);
                detailPenjualanListDetailPenjualan = em.merge(detailPenjualanListDetailPenjualan);
            }
            em.remove(menu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Menu> findMenuEntities() {
        return findMenuEntities(true, -1, -1);
    }

    public List<Menu> findMenuEntities(int maxResults, int firstResult) {
        return findMenuEntities(false, maxResults, firstResult);
    }

    private List<Menu> findMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Menu.class));
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

    public Menu findMenu(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menu.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Menu> rt = cq.from(Menu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
