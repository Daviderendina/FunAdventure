package com.rendinadavide.assignment3.service;

import com.rendinadavide.assignment3.repository.Client;

import java.util.Date;

public interface IClientService extends IService<Client> {

    Client create(String name, String surname, Date bDate);

}
