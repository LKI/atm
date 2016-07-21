package com.liriansu.atm.entity;

import com.liriansu.atm.util.ATM;

public class AC {
    private long id;

    public AC(long id) {
        this.id = id;
    }

    public AC(String id) {
        this.id = Long.parseLong(id);
    }

    public String getUrl() {
        return String.format("%s://%s/a/ac%s", ATM.PROTOCOL, ATM.DOMAIN, id);
    }
}
