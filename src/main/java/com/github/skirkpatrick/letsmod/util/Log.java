package com.github.skirkpatrick.letsmod.util;

import com.github.skirkpatrick.letsmod.reference.Reference;
import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Log {

    public static void log(Level logLevel, Object object)
    {
        FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(object));
    }

    /**
     * Turn on all logging. Will this ever be useful?
     *
     * @param object
     */
    public static void all(Object object)
    {
        log(Level.ALL, object);
    }

    /**
     * Log DEBUG information. This should be used to log messages that might be useful to developers working
     * on the mod, but won't be useful to someone just using the mod.
     *
     * @param object
     */
    public static void debug(Object object)
    {
        log(Level.DEBUG, object);
    }

    /**
     * Log an ERROR that may be recoverable.
     *
     * @param object
     */
    public static void error(Object object)
    {
        log(Level.ERROR, object);
    }

    /**
     * Log a FATAL error that is likely not recoverable.
     *
     * @param object
     */
    public static void fatal(Object object)
    {
        log(Level.FATAL, object);
    }

    /**
     * Log general INFO.
     *
     * @param object
     */
    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    /**
     * Turn off all logging. Will this ever be useful?
     *
     * @param object
     */
    public static void off(Object object)
    {
        log(Level.OFF, object);
    }

    /**
     * Log program TRACE information. This is like {@link #debug(Object)}, but more fine grained.
     *
     * @param object
     */
    public static void trace(Object object)
    {
        log(Level.TRACE, object);
    }

    /**
     * Log a WARNing which could lead to an error.
     *
     * @param object
     */
    public static void warn(Object object)
    {
        log(Level.WARN, object);
    }
}
