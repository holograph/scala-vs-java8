package com.tomergabel.examples.java;

import org.slf4j.Logger;

public interface Logging {
    Logger getLogger();

    default void debug(String message) {
        if (getLogger().isDebugEnabled())
            getLogger().debug(message);
    }

    default void debug(String message, Exception cause) {
        if (getLogger().isDebugEnabled())
            getLogger().debug(message, cause);
    }

    default void info(String message) {
        if (getLogger().isInfoEnabled())
            getLogger().info(message);
    }

    default void info(String message, Exception cause) {
        if (getLogger().isInfoEnabled())
            getLogger().info(message, cause);
    }

    default void warn(String message) {
        if (getLogger().isWarnEnabled())
            getLogger().warn(message);
    }

    default void warn(String message, Exception cause) {
        if (getLogger().isWarnEnabled())
            getLogger().warn(message, cause);
    }

    default void error(String message) {
        if (getLogger().isErrorEnabled())
            getLogger().error(message);
    }

    default void error(String message, Exception cause) {
        if (getLogger().isErrorEnabled())
            getLogger().error(message, cause);
    }
}
