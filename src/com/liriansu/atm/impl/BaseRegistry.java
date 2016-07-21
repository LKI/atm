package com.liriansu.atm.impl;

import com.liriansu.atm.CommandBuilder;
import com.liriansu.atm.ICommandRegistry;
import com.liriansu.atm.annotation.CommandRegistry;
import com.liriansu.atm.entity.Command;
import com.liriansu.atm.impl.command.Get;
import com.liriansu.atm.impl.command.Info;

@CommandRegistry(0)
public class BaseRegistry implements ICommandRegistry {
    /**
     * Register all commands using {@link CommandBuilder}
     *
     * @param commands the {@link CommandBuilder}
     */
    @Override
    public void register(CommandBuilder commands) {
        Command cmd;

        cmd = commands.cmd("info");
        cmd.setDesc("Show ATM information");
        cmd.setImpl(Info.class);

        cmd = commands.cmd("get");
        cmd.setDesc("Get AcFun article");
        cmd.setImpl(Get.class);

    }
}
