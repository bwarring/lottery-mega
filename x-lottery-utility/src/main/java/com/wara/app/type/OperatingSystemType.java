package com.wara.app.type;

/**
 * Copyright 2018 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Bob Warring
 * Oct 12, 2018 1:13:48 PM
 */
public enum OperatingSystemType {
	/**
     * WINDOWS {type = win}
     */
    WINDOWS("win"),

    /**
     * MAC {type = mac}
     */
    MAC("mac"),

    /**
     * UNIX_NIX {type = nix}
     */
    UNIX_NIX("nix"),

    /**
     * UNIX_NUX {type = nux}
     */
    UNIX_NUX("nux"),

    /**
     * UNIX_AIX {type = aix}
     */
    UNIX_AIX("aix"),

    /**
     * SOLARIS {type = sunos}
     */
    SOLARIS("sunos");

    private String type;

    private OperatingSystemType(String type)
    {
        this.type = type;
    }

    public static boolean isWindows(String osName)
    {
        return (osName.indexOf(WINDOWS.getType()) >= 0);
    }

    public static boolean isMac(String osName)
    {
        return (osName.indexOf(MAC.getType()) >= 0);
    }

    public static boolean isUnix(String osName)
    {
        return ((osName.indexOf(UNIX_NIX.getType()) >= 0 || osName.indexOf(UNIX_NUX.getType()) >= 0 || osName.indexOf(UNIX_AIX.getType()) > 0));
    }

    public static boolean isSolaris(String osName)
    {
        return (osName.indexOf(SOLARIS.getType()) >= 0);
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

}
