package com.liriansu.atm.entity;

import com.liriansu.atm.IProcess;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private String                    name;
    private Class<? extends IProcess> impl;
    private String                    desc;
    private List<String>              alias;

    public Command(String name) {
        this.name = name;
        this.desc = "";
        alias = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Class<? extends IProcess> getImpl() {
        return impl;
    }

    public Command setImpl(Class<? extends IProcess> impl) {
        this.impl = impl;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getAlias() {
        return alias;
    }

    public Command addAlias(String alias) {
        this.alias.add(alias);
        return this;
    }
}
