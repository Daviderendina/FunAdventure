package com.rendinadavide.assignment3.service;

import com.rendinadavide.assignment3.repository.Client;
import com.rendinadavide.assignment3.repository.Entrance;
import com.rendinadavide.assignment3.repository.Equipment;

import java.util.Set;

public interface IEntranceService extends IService<Entrance> {

    Entrance create(Set<Client> clientList, Set<Equipment> equipmentList);

}
