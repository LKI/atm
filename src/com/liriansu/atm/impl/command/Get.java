package com.liriansu.atm.impl.command;

import com.liriansu.atm.Context;
import com.liriansu.atm.IProcess;
import com.liriansu.atm.acfun.AC;
import com.liriansu.atm.entity.cmdline.CommandLine;

public class Get implements IProcess {
    /**
     * Setup the commandline. The {@link IProcess} should setup options and argument name in this method.
     *
     * @param cmdline the {@link CommandLine} to setup
     */
    @Override
    public void setupCmdline(CommandLine cmdline) {
        System.out.println("Setting up cmdline");
        cmdline.setArgname("ac");
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
        System.out.println("Context#execute");
        // TODO show more info!
        for (String id : context.getCmdline().getArgList()) {
            System.out.println(String.format("Fetching [%s]", id));
            AC ac = context.getAcfun().get(Long.parseLong(id));
            System.out.println(ac.getHtml());
            System.out.println(ac.getArticle());
        }
        return true;
    }
}
