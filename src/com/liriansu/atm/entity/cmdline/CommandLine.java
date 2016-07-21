package com.liriansu.atm.entity.cmdline;

import com.liriansu.atm.util.StringUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLine {
    private List<Option>        options;
    private List<String>        argList;
    private Map<String, String> longNameMap;
    private Map<String, String> shortNameMap;
    private String              argname;

    public CommandLine() {
        this.options = new ArrayList<>();
    }

    public CommandLine parse(String[] args) {
        argList = new ArrayList<>();
        shortNameMap = new HashMap<>();
        longNameMap = new HashMap<>();
        int i = 0;
        while (i < args.length) {
            if (args[i].startsWith("-")) {
                String optionName = args[i].startsWith("--") ? args[i].substring(2) : args[i].substring(1);
                for (Option option : options) {
                    if (optionName.equals(option.getShortName()) || optionName.equals(option.getLongName())) {
                        if (option.hasArg()) {
                            i += 1;
                            String value = i < args.length ? args[i] : "";
                            shortNameMap.put(option.getShortName(), value);
                            longNameMap.put(option.getLongName(), value);
                        } else {
                            shortNameMap.put(option.getShortName(), null);
                            longNameMap.put(option.getLongName(), null);
                        }
                    }
                }
            } else {
                argList.add(args[i]);
            }
            i += 1;
        }
        return this;
    }

    public void showHelp(PrintStream out, String cmd) {
        out.println(getUsage(cmd));
        int maxLength = 0;
        for (Option option : options) {
            int length = option.getShortName().length();
            length += option.getLongName() == null ? 0 : option.getLongName().length();
            length += option.hasArg() ? 7 : 0;
            if (length > maxLength) {
                maxLength = length;
            }
        }
        for (Option option : options) {
            StringBuilder usage = new StringBuilder(" -%s");
            if (option.getLongName() != null) {
                usage.append(",--%s");
            }
            if (option.hasArg()) {
                usage.append(" <argname>");
            }
            int spaces = maxLength + 3 - option.getShortName().length();
            spaces -= option.getLongName() == null ? 0 : option.getLongName().length();
            spaces -= option.hasArg() ? 7 : 0;
            usage.append(new String(new char[spaces]).replace("\0", " "));
            out.print(String.format(usage.toString(), option.getShortName(), option.getLongName()));
            out.println(option.getDescription() == null ? "" : option.getDescription());
        }
    }

    public boolean hasOption(String name) {
        return shortNameMap.containsKey(name) || longNameMap.containsKey(name);
    }

    public String getOptionValue(String name, String defaultValue) {
        String value = getOptionValue(name);
        return null == value ? defaultValue : value;
    }

    public String getOptionValue(String name) {
        if (shortNameMap.containsKey(name)) {
            return shortNameMap.get(name);
        } else {
            return longNameMap.get(name);
        }
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<String> getArgList() {
        return argList;
    }

    public CommandLine addOption(String shortName, String longName, boolean hasArg, String desc) {
        return addOption(shortName, longName, hasArg, desc, false);
    }

    public CommandLine addOption(String shortName, String longName, boolean hasArg, String desc, boolean required) {
        return addOption(new Option(shortName, longName, hasArg, desc, required));
    }

    public CommandLine addOption(Option option) {
        options.add(option);
        return this;
    }

    public void setArgname(String argsName) {
        argname = argsName;
    }


    private String getUsage(String cmd) {
        String usage  = "usage: " + cmd;
        int    indent = usage.length();
        String line   = "";
        for (Option opt : options) {
            if (line.length() > 72) {
                usage += line + String.format("%n");
                line = StringUtils.repeat(" ", indent);
            }
            line += getOptionString(opt, true);
        }
        if (null != argname) {
            line += String.format(" %s...", argname);
        }
        for (Option opt : options) {
            if (line.length() > 72) {
                usage += line + String.format("%n");
                line = StringUtils.repeat(" ", indent);
            }
            line += getOptionString(opt, false);
        }
        usage += line + String.format("%n");
        return usage;
    }

    private String getOptionString(Option option, boolean require) {
        if (require != option.isRequired()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" ");
        if (!require) {
            sb.append("[");
        }
        sb.append("-").append(option.getShortName());
        if (option.hasArg()) {
            sb.append(" <arg>");
        }
        if (!require) {
            sb.append("]");
        }
        return sb.toString();
    }
}
