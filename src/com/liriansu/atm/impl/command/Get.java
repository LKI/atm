package com.liriansu.atm.impl.command;

import com.liriansu.atm.Context;
import com.liriansu.atm.IProcess;
import com.liriansu.atm.entity.AC;
import com.liriansu.atm.entity.cmdline.CommandLine;
import com.liriansu.atm.util.Err;
import com.liriansu.atm.util.HTML;
import com.liriansu.atm.util.MSG;
import com.liriansu.atm.util.StringUtils;

import java.io.IOException;

public class Get implements IProcess {
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
        AC ac = new AC(StringUtils.getACid("ac2903169"));
        try {
            System.out.println(ac.getUrl());
            String html = HTML.fetch(ac.getUrl());
            System.out.println(html);
        } catch (IOException e) {
            return Err.error(e, MSG.FAIL_TO_FETCH_HTML);
        }
        return true;
    }
}
