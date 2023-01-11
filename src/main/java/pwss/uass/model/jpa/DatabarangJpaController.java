/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pwss.uass.model.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pwss.uass.model.entity.Databarang;
import pwss.uass.model.jpa.exceptions.NonexistentEntityException;
import pwss.uass.model.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author DELL
 */
public class DatabarangJpaController implements Serializable {

    public DatabarangJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pwss_uass_jar_0.0.1-SNAPSHOTPU");

    public DatabarangJpaController() {
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Databarang databarang) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(databarang);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatabarang(databarang.getId()) != null) {
                throw new PreexistingEntityException("Databarang " + databarang + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Databarang databarang) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            databarang = em.merge(databarang);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = databarang.getId();
                if (findDatabarang(id) == null) {
                    throw new NonexistentEntityException("The databarang with id " + id + " no longer exists.");
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
            Databarang databarang;
            try {
                databarang = em.getReference(Databarang.class, id);
                databarang.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The databarang with id " + id + " no longer exists.", enfe);
            }
            em.remove(databarang);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Databarang> findDatabarangEntities() {
        return findDatabarangEntities(true, -1, -1);
    }

    public List<Databarang> findDatabarangEntities(int maxResults, int firstResult) {
        return findDatabarangEntities(false, maxResults, firstResult);
    }

    private List<Databarang> findDatabarangEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Databarang.class));
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

    public Databarang findDatabarang(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Databarang.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatabarangCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Databarang> rt = cq.from(Databarang.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
