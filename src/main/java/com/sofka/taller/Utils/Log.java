package com.sofka.taller.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void logDegub(String debug) {
        logger.debug(debug);
    }
}
