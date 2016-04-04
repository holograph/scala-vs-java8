package com.tomergabel.examples.patterns

import java.io.FileInputStream
import java.util.Scanner

/**
  * Created by tomerga on 04/04/2016.
  */
object PatternMatching1 extends App {

  val defaultLineCount = 10

  // CLI --

  val (in, lines) =
    args match {
      case Array() =>
        (System.in, defaultLineCount)

      case Array(inPath) =>
        (new FileInputStream(inPath), defaultLineCount)

      case Array(inPath, lineStr) =>
        (new FileInputStream(inPath), lineStr.toInt)

      case _ =>
        println(s"Usage: sbt 'runMain ${this.getClass.getName} [input] [lines]'")
        sys.exit(-1)
    }

  // Test --

  val scanner = new Scanner(in)
  try {
    var line = 0
    while (scanner.hasNextLine && line < lines) {
      println(scanner.nextLine)
      line = line + 1
    }
  } finally {
    in.close()
  }
}
