package com.tomergabel.examples.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSample implements Logging {

    public String getNormalizedName(Person person) {
        info(() -> "getNormalizedName called");
        debug(() -> "Normalizing " + person.toString());
        String normalizedName =
                person.getName().toUpperCase().trim();
        debug(() -> "Normalized name is: " + normalizedName);
        return normalizedName;
    }

    private static Logger log =
            LoggerFactory.getLogger(LoggingSample.class);

    public Logger getLogger() { return log; }

    private class Person {
        private String name;
        public Person(String name) { this.name = name; }
        String getName() { return name; }
        public String toString() { return this.name; }
    }
}
