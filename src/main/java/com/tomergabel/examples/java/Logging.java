package com.tomergabel.examples.java;

import org.slf4j.Logger;

import java.util.function.Supplier;

public interface Logging {
    Logger getLogger();

    default void debug(Supplier<String> message) {
        if (getLogger().isDebugEnabled())
            getLogger().debug(message.get());
    }

    default void debug(Supplier<String> message, Exception cause) {
        if (getLogger().isDebugEnabled())
            getLogger().debug(message.get(), cause);
    }

    default void info(Supplier<String> message) {
        if (getLogger().isInfoEnabled())
            getLogger().info(message.get());
    }

    default void info(Supplier<String> message, Exception cause) {
        if (getLogger().isInfoEnabled())
            getLogger().info(message.get(), cause);
    }

    default void warn(Supplier<String> message) {
        if (getLogger().isWarnEnabled())
            getLogger().warn(message.get());
    }

    default void warn(Supplier<String> message, Exception cause) {
        if (getLogger().isWarnEnabled())
            getLogger().warn(message.get(), cause);
    }

    default void error(Supplier<String> message) {
        if (getLogger().isErrorEnabled())
            getLogger().error(message.get());
    }

    default void error(Supplier<String> message, Exception cause) {
        if (getLogger().isErrorEnabled())
            getLogger().error(message.get(), cause);
    }
}
