package com.liriansu.atm;

import com.liriansu.atm.entity.cmdline.CommandLine;

/**
 * {@link IProcess} defines an ATM command
 */
public interface IProcess {
    /**
     * Setup the commandline. The {@link IProcess} should setup options and argument name in this method.
     *
     * @param cmdline the {@link CommandLine} to setup
     */
    void setupCmdline(CommandLine cmdline);

    /**
     * Execute and return the result. This method should display detail reports too.
     *
     * @param context the {@link Context} to run the command
     *
     * @return true if success, otherwise false
     */
    boolean execute(Context context);
}
