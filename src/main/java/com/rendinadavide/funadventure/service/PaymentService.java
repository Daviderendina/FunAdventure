package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.payment.Payment;
import com.rendinadavide.funadventure.repository.AccessRepository;
import com.rendinadavide.funadventure.repository.PaymentRepository;
import com.rendinadavide.funadventure.repository.RepositoryFactory;

import java.util.List;

public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(){
        paymentRepository = (PaymentRepository) RepositoryFactory.getRepository(RepositoryFactory.RepositoryEntityType.PAYMENT);
    }

    public Payment findById(String equipmentId){
        return paymentRepository.findById(equipmentId);
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
}
