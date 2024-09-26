package com.dealership.car.persistence;

import com.dealership.car.logic.Car;
import com.dealership.car.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CarJpaController implements Serializable {

    public CarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CarJpaController() {
        emf = Persistence.createEntityManagerFactory("CarPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Car car) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Car car) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            car = em.merge(car);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = car.getId();
                if (findCar(id) == null) {
                    throw new NonexistentEntityException("The car with id " + id + " no longer exists.");
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
            Car car;
            try {
                car = em.getReference(Car.class, id);
                car.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The car with id " + id + " no longer exists.", enfe);
            }
            em.remove(car);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Car> findCarEntities() {
        return findCarEntities(true, -1, -1);
    }

    public List<Car> findCarEntities(int maxResults, int firstResult) {
        return findCarEntities(false, maxResults, firstResult);
    }

    private List<Car> findCarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Car.class));
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

    public Car findCar(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Car.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Car> rt = cq.from(Car.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
