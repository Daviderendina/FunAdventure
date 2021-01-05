package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Client;

import java.util.Date;

public interface IClientService extends IService<Client> {

    Client create(String name, String surname, Date bDate);

}
