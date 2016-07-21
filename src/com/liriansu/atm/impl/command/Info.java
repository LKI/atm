package com.liriansu.atm.impl.command;

import com.liriansu.atm.Context;
import com.liriansu.atm.IProcess;
import com.liriansu.atm.entity.cmdline.CommandLine;

public class Info implements IProcess {
    /**
     * Setup the commandline. The {@link IProcess} should setup options and argument name in this method.
     *
     * @param cmdline the {@link CommandLine} to setup
     */
    @Override
    public void setupCmdline(CommandLine cmdline) {
    }

    /**
     * Execute and return the result. This method should display detail reports too.
     *
     * @param context the {@link Context} to run the command
     *
     * @return true if success, otherwise false
     */
    @Override
    public boolean execute(Context context) {
        // TODO show more info!
        System.out.println("Acfun TerMinal $VERSION");
        return true;
    }
}
