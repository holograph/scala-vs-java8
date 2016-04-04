package com.tomergabel.examples.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PatternMatching1 {

    static final int DEFAULT_LINE_COUNT = 10;

    public static void main(String[] args) throws IOException {
        // CLI --

        InputStream in = System.in;
        int lines = DEFAULT_LINE_COUNT;

        if (args.length > 0)
            in = new FileInputStream(args[0]);
        if (args.length > 1)
            lines = Integer.valueOf(args[1]);
        if (args.length > 2) {
            System.out.println("Usage: sbt 'runMain " + PatternMatching1.class.getName() + " [input] [lines]'");
            System.exit(-1);
        }

        // Test --

        Scanner scanner = new Scanner(in);
        try {
            int line = 0;
            while (scanner.hasNextLine() && line < lines) {
                System.out.println(scanner.nextLine());
                line++;
            }
        } finally {
            in.close();
        }
    }
}
