package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.payment.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PaymentRepository implements Repository<Payment> {

    private EntityManager em;

    public PaymentRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public Payment findById(String Id) {
        return em.find(Payment.class, Id);
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public void delete(Payment payment) {

    }
}
