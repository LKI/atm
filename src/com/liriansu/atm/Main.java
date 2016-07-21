package com.liriansu.atm;

import com.liriansu.atm.util.Err;
import com.liriansu.atm.util.MSG;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        System.exit(parse(args));
    }

    public static int parse(String[] args) {
        Context context = new Context(args);
        try {
            if (context.setup()) {
                try {
                    Constructor<?> con     = context.getCommand().getImpl().getConstructor();
                    IProcess       process = (IProcess) con.newInstance();
                    process.setupCmdline(context.getCmdline());
                    if (context.validate() && process.execute(context)) {
                        return 0;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    Err.error(e, MSG.FAIL_TO_START_PS, MSG.STH_WRONG);
                }
            }
        } finally {
            context.cleanup();
        }
        return 1;
    }
}
