package com.tomergabel.examples.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching3 {

    public static void main(String[] args) {
        Pattern url = Pattern.compile("(\\w+)://([a-zA-Z0-9.-]+?)(/[a-zA-Z0-9.-/]*)?");
        Matcher matcher = url.matcher(args[0]);
        if (matcher.matches()) {
            String scheme = matcher.group(1);
            String host = matcher.group(2);
            String path = matcher.group(3);

            switch (scheme) {
                case "http":
                    System.out.println("HTTP URL detected\nHost: " + host + "\nPath: " + path);
                    break;

                case "https":
                    System.out.println("SSL-enabled HTTP URL detected\nHost: " + host + "\nPath: " + path);
                    break;

                case "ftp":
                    System.out.println("FTP URL detected\nHost: " + host + "\nPath: " + path);
                    break;

                default:
                    System.out.println("Unrecognized scheme " + scheme + " for URL " + args[0]);
                    break;
            }
        } else
            System.out.println("Not a recognized URL: " + args[0]);
    }
}
