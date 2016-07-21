package com.liriansu.atm.impl.command;

import com.liriansu.atm.Context;
import com.liriansu.atm.IProcess;
import com.liriansu.atm.entity.cmdline.CommandLine;
import jline.console.ConsoleReader;
import jline.console.completer.*;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Shell implements IProcess {
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
        List<String> args = context.getCmdline().getArgList();
        return parse(args);
    }

    public static void usage() {
        System.out.println("Usage: atm shell [none/simple/files/dictionary [trigger mask]]");
        System.out.println("  none - no completors");
        System.out.println("  simple - a simple completor that completes " + "\"foo\", \"bar\", and \"baz\"");
        System.out.println("  files - a completor that completes " + "file names");
        System.out.println("  classes - a completor that completes " + "java class names");
        System.out.println("  trigger - a special word which causes it to assume " + "the next line is a password");
        System.out.println("  mask - is the character to print in place of " + "the actual password character");
        System.out.println("  color - colored prompt and feedback");
        System.out.println("\n  E.g - java Example simple su '*'\n" + "will use the simple completor with 'su' triggering\n" + "the use of '*' as a password mask.");
    }

    private boolean parse(List<String> args) {
        try {
            Character     mask    = null;
            String        trigger = null;
            boolean       color   = false;
            ConsoleReader reader  = new ConsoleReader();
            reader.setPrompt("atm> ");
            List<Completer> completers = new ArrayList<>();
            if (args.size() > 0) {
                if (args.get(0).equals("none")) {
                    System.out.println("None");
                } else if (args.get(0).equals("files")) {
                    completers.add(new FileNameCompleter());
                } else if (args.get(0).equals("simple")) {
                    completers.add(new StringsCompleter("foo", "bar", "baz"));
                } else if (args.get(0).equals("color")) {
                    color = true;
                    reader.setPrompt("\u001B[42mfoo\u001B[0m@bar\u001B[32m@baz\u001B[0m> ");
                    completers.add(new AnsiStringsCompleter("\u001B[1mfoo\u001B[0m", "bar", "\u001B[32mbaz\u001B[0m"));
                    CandidateListCompletionHandler handler = new CandidateListCompletionHandler();
                    handler.setStripAnsi(true);
                    reader.setCompletionHandler(handler);
                } else {
                    usage();
                    return false;
                }
            }

            System.out.println(args.size());
            System.out.println(StringUtils.join(args, ","));
            if (args.size() == 3) {
                mask = args.get(2).charAt(0);
                trigger = args.get(1);
            }

            for (Completer c : completers) {
                reader.addCompleter(c);
            }

            String      line;
            PrintWriter out = new PrintWriter(reader.getOutput());

            while ((line = reader.readLine()) != null) {
                if (color) {
                    out.println("\u001B[33m======>\u001B[0m\"" + line + "\"");

                } else {
                    out.println("======>\"" + line + "\"");
                }
                out.flush();

                // If we input the special word then we will mask
                // the next line.
                if ((trigger != null) && (line.equalsIgnoreCase(trigger))) {
                    reader.readLine("password> ", mask);
                } else if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                    break;
                } else if (line.equalsIgnoreCase("cls")) {
                    reader.clearScreen();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
