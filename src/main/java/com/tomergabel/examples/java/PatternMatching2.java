package com.tomergabel.examples.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tomerga on 05/04/2016.
 */
public class PatternMatching2 {

    public static void main(String[] args) {
        Pattern url = Pattern.compile("(\\w+)://([a-zA-Z0-9.-]+?)(/[a-zA-Z0-9.-/]*)?");
        Matcher matcher = url.matcher(args[0]);
        if (matcher.matches()) {
            String scheme = matcher.group(1);
            String host = matcher.group(2);
            String path = matcher.group(3);
            System.out.println("Scheme: " + scheme + "\nHost: " + host + "\nPath: " + path);
        } else
            System.out.println("Not a recognized URL: " + args[0]);
    }
}
