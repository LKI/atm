package com.liriansu.atm.impl;

import com.liriansu.atm.CommandBuilder;
import com.liriansu.atm.ICommandRegistry;
import com.liriansu.atm.annotation.CommandRegistry;
import com.liriansu.atm.entity.Command;
import com.liriansu.atm.impl.command.Get;
import com.liriansu.atm.impl.command.Info;
import com.liriansu.atm.impl.command.Shell;

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
        cmd.setDesc("show ATM information");
        cmd.setImpl(Info.class);

        cmd = commands.cmd("get");
        cmd.setDesc("get AcFun article");
        cmd.setImpl(Get.class);

        cmd = commands.cmd("shell");
        cmd.setDesc("go into a Brave New World");
        cmd.setImpl(Shell.class);

    }
}
