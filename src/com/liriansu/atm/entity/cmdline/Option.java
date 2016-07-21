package com.liriansu.atm.entity.cmdline;

/**
 * The command option
 */
public class Option {
    private final String  shortName;
    private final String  longName;
    private final String  description;
    private final boolean required;
    private final boolean hasArg;

    /**
     * Construct an option
     *
     * @param shortName   the short name like "h", "nc"
     * @param longName    the long name like "help", "no-compile"
     * @param hasArg      if this option has argument
     * @param description a brief description of the option
     */
    public Option(String shortName, String longName, boolean hasArg, String description) {
        this(shortName, longName, hasArg, description, false);
    }

    /**
     * Construct an option
     *
     * @param shortName   the short name like "h", "nc"
     * @param longName    the long name like "help", "no-compile"
     * @param hasArg      if this option has argument
     * @param description a brief description of the option
     * @param required    if this option is required
     */
    public Option(String shortName, String longName, boolean hasArg, String description, boolean required) {
        this.shortName = shortName;
        this.longName = longName;
        this.hasArg = hasArg;
        this.description = description;
        this.required = required;
    }

    /**
     * Get short name
     *
     * @return the short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Get long name
     *
     * @return the long name
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Get description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get if option needs argument
     *
     * @return if needs argument
     */
    public boolean hasArg() {
        return hasArg;
    }

    /**
     * Get if option is required
     *
     * @return if is required
     */
    public boolean isRequired() {
        return required;
    }
}
