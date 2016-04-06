package com.tomergabel.examples.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSample {
    private static Logger log =
            LoggerFactory.getLogger(LoggingSample.class);

    public String getNormalizedName(Person person) {
        log.info("getNormalizedName called");
        log.debug("Normalizing " + person.toString());
        String normalizedName =
                person.getName().toUpperCase().trim();
        log.debug("Normalized name is: " + normalizedName);
        return normalizedName;
    }

    private class Person {
        private String name;
        public Person(String name) { this.name = name; }
        String getName() { return name; }
        public String toString() { return this.name; }
    }
}
