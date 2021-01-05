package com.rendinadavide.assignment3.service;

import com.rendinadavide.assignment3.repository.Equipment;

import java.util.Date;

public interface IEquipmentService extends IService<Equipment> {

    Equipment create(Date purchaseDate, String sn);

}
