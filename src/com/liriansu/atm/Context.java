package com.liriansu.atm;

import com.liriansu.atm.acfun.AcFun;
import com.liriansu.atm.annotation.CommandRegistry;
import com.liriansu.atm.entity.Command;
import com.liriansu.atm.entity.cmdline.CommandLine;
import com.liriansu.atm.util.Err;
import com.liriansu.atm.util.MSG;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class Context {
    private AcFun          acfun;
    private Command        command;
    private CommandBuilder commands;
    private CommandLine    cmdline;
    private String         subcommand;
    private String[]       args;

    public Context(String[] args) {
        commands = new CommandBuilder();
        cmdline = new CommandLine();
        initializeSubcommand(args);
    }

    public boolean setup() {
        if (!setupCommands()) {
            return false;
        } else if (null == subcommand) {
            // TODO maybe start ATM shell
            commands.showCommands(System.err);
            return false;
        }
        command = commands.cmd(subcommand);
        if (null == command) {
            Err.error(String.format(MSG.NO_SUCH_PROCESS, subcommand));
            commands.showCommands(System.err);
            return false;
        }
        try {
            // TODO validate if we have chromedriver
            File driver = new File(System.getProperty("atm.dir"), "driver/chrome/chromedriver.exe");
            acfun = new AcFun(driver.getAbsolutePath());
        } catch (IOException e) {
            return Err.error(e, MSG.FAIL_TO_START_BS);
        }
        return true;
    }

    public void cleanup() {
        if (null != acfun) acfun.stop();
    }

    public boolean validate() {
        // TODO add validation here
        cmdline.addOption("h", "help", false, "show help message");
        cmdline.parse(args);
        if (cmdline.hasOption("h")) {
            cmdline.showHelp(System.err, command.getName());
            return false;
        }
        return true;
    }

    public AcFun getAcfun() {
        return acfun;
    }

    public Command getCommand() {
        return command;
    }

    public CommandBuilder getCommands() {
        return commands;
    }

    public CommandLine getCmdline() {
        return cmdline;
    }

    public String getSubcommand() {
        return subcommand;
    }

    private void initializeSubcommand(String[] args) {
        List<String> arguments = new ArrayList<>();
        for (String arg : args) {
            if (null == subcommand && !arg.startsWith("-")) {
                subcommand = arg;
            } else {
                arguments.add(arg);
            }
        }
        this.args = arguments.toArray(new String[0]);
    }

    private boolean setupCommands() {
        URL[]        urls = ClasspathUrlFinder.findClassPaths();
        AnnotationDB db   = new AnnotationDB();
        try {
            db.scanArchives(urls);
            Set<String>            clsSet = db.getAnnotationIndex().get(CommandRegistry.class.getName());
            List<ICommandRegistry> regs   = new ArrayList<>();
            for (String cls : clsSet) {
                Constructor<?>   c   = Class.forName(cls).getConstructor();
                ICommandRegistry reg = (ICommandRegistry) c.newInstance();
                regs.add(reg);
            }
            Collections.sort(regs, new Comparator<ICommandRegistry>() {
                @Override
                public int compare(ICommandRegistry r1, ICommandRegistry r2) {
                    return Integer.compare(r1.getClass().getAnnotation(CommandRegistry.class).value(), r2.getClass().getAnnotation(CommandRegistry.class).value());
                }
            });
            for (ICommandRegistry reg : regs) {
                reg.register(commands);
            }
        } catch (IOException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            Err.error(e, MSG.FAIL_TO_SETUP_CMDS);
        }
        return true;
    }
}
