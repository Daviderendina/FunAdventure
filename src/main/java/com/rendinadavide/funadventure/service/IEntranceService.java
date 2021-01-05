package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Client;
import com.rendinadavide.funadventure.repository.Entrance;
import com.rendinadavide.funadventure.repository.Equipment;

import java.util.Set;

public interface IEntranceService extends IService<Entrance> {

    Entrance create(Set<Client> clientList, Set<Equipment> equipmentList);

}
