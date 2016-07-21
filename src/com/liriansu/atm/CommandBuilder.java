package com.liriansu.atm;

import com.liriansu.atm.entity.Command;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandBuilder {
    private Map<String, Command> cmds;

    CommandBuilder() {
        cmds = new HashMap<>();
    }

    public Command cmd(String name) {
        if (!cmds.containsKey(name)) {
            cmds.put(name, new Command(name));
        }
        return cmds.get(name);
    }

    public Collection<Command> getCommands() {
        return cmds.values();
    }

    public void showCommands(PrintStream out) {
        // TODO better format
        for (Command cmd : cmds.values()) {
            out.println(String.format("%-12s%s", cmd.getName(), cmd.getDesc()));
        }
    }
}
