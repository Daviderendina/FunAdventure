package com.rendinadavide.funadventure.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryFactory {

    public enum RepositoryEntityType{ACCESS, CLIENT, EQUIPMENT, PAYMENT}

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");

    public static Repository getRepository(RepositoryEntityType repositoryEntityType){
        switch (repositoryEntityType){
            case EQUIPMENT:
                return new EquipmentRepository(emf);
            case PAYMENT:
                return new PaymentRepository(emf);
            case CLIENT:
                return new ClientRepository(emf);
            case ACCESS:
                return new AccessRepository(emf);
            default:
                return null;
        }
    }
}
