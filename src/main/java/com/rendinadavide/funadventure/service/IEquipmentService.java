package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Equipment;

import java.util.Date;

public interface IEquipmentService extends IService<Equipment> {

    Equipment create(Date purchaseDate, String sn);

}
