package com.liriansu.atm.acfun;

public class AC {
    private long id;

    public AC(long id) {
        this.id = id;
    }

    public AC(String id) {
        this.id = Long.parseLong(id);
    }

    public String getUrl() {
//        return String.format("%s://%s/a/ac%s", ATM.PROTOCOL, ATM.DOMAIN, id);
        return String.format("http://www.acfun.tv/a/ac%s", id);
    }
}
