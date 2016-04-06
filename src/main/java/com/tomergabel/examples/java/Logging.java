package com.tomergabel.examples.java;

import org.slf4j.Logger;

/**
 * Created by tomerga on 06/04/2016.
 */
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
}
