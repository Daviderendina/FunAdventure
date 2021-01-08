package com.rendinadavide.funadventure.utils;

import java.util.UUID;

public class IdGenerator {

    private static IdGenerator istance;

    private IdGenerator(){}

    public synchronized static IdGenerator getIstance() {
        if(istance == null)
            istance = new IdGenerator();
        return istance;
    }

    public String getUID(){
        return UUID.randomUUID().toString();
    }
}
