package com.liriansu.atm;

import com.liriansu.atm.annotation.CommandRegistry;

/**
 * {@link ICommandRegistry} register commands to ATM. When a class implement this interface, it should
 * have the {@link CommandRegistry} annotation. <br/>
 * If multiple {@link ICommandRegistry} are found under classpath, they will run with min to max order
 * of the int value of {@link CommandRegistry}.
 */
public interface ICommandRegistry {
    /**
     * Register all commands using {@link CommandBuilder}
     *
     * @param commands the {@link CommandBuilder}
     */
    void register(CommandBuilder commands);
}
