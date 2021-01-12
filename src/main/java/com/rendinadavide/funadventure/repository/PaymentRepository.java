package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.payment.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PaymentRepository implements Repository<Payment> {

    private EntityManager em;

    public PaymentRepository(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    @Override
    public boolean save(Payment payment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(payment);
            tx.commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Payment findById(String Id) {
        return em.find(Payment.class, Id);
    }

    @Override
    public List<Payment> findAll() {
        return em.createQuery("FROM Payment", Payment.class).getResultList();
    }

    @Override
    public boolean update(Payment payment) {
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Payment payment) {
        em.getTransaction().begin();
        try {
            em.remove(payment);
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
    }
}
